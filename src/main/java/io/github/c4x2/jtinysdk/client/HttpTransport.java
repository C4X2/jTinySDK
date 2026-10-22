package io.github.c4x2.jtinysdk.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.c4x2.jtinysdk.exception.*;
import io.github.c4x2.jtinysdk.model.BaseResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * Internal transport layer executing HTTP requests and mapping responses/errors.
 */
public class HttpTransport {

    private static final String USER_AGENT = "TinyURL-Java-SDK/2.4.2";

    private final TinyUrlConfig config;
    private final ObjectMapper objectMapper;

    public HttpTransport(TinyUrlConfig config) {
        this.config = Objects.requireNonNull(config, "config must not be null");
        this.objectMapper = config.getObjectMapper();
    }

    public TinyUrlConfig getConfig() {
        return config;
    }

    public <T> T send(String method, String path, Map<String, String> queryParams, Object body, Class<T> responseType) {
        HttpRequest request = buildRequest(method, path, queryParams, body);
        try {
            HttpResponse<String> response = config.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            return handleResponse(response, responseType);
        } catch (IOException e) {
            throw new TinyUrlException("Failed to communicate with TinyURL API: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TinyUrlException("Request to TinyURL API was interrupted: " + e.getMessage(), e);
        }
    }

    public <T> CompletableFuture<T> sendAsync(String method, String path, Map<String, String> queryParams, Object body, Class<T> responseType) {
        HttpRequest request = buildRequest(method, path, queryParams, body);
        return config.getHttpClient()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(response -> handleResponse(response, responseType))
                .exceptionally(throwable -> {
                    Throwable cause = throwable instanceof CompletionException ? throwable.getCause() : throwable;
                    if (cause instanceof TinyUrlException) {
                        throw (TinyUrlException) cause;
                    }
                    throw new TinyUrlException("Async request failed: " + cause.getMessage(), cause);
                });
    }

    private HttpRequest buildRequest(String method, String path, Map<String, String> queryParams, Object body) {
        Map<String, String> effectiveQueryParams = queryParams != null ? new LinkedHashMap<>(queryParams) : new LinkedHashMap<>();

        // Handle query parameter authentication if enabled
        if (config.getAuthType() == AuthType.QUERY_PARAM && config.getApiToken() != null && !config.getApiToken().isBlank()) {
            effectiveQueryParams.put("api_token", config.getApiToken());
        }

        String uriString = buildUriString(path, effectiveQueryParams);
        URI uri = URI.create(uriString);

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(config.getTimeout())
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json");

        // Handle Bearer header authentication if enabled
        if (config.getAuthType() == AuthType.BEARER && config.getApiToken() != null && !config.getApiToken().isBlank()) {
            builder.header("Authorization", "Bearer " + config.getApiToken());
        }

        HttpRequest.BodyPublisher bodyPublisher;
        if (body != null) {
            builder.header("Content-Type", "application/json");
            try {
                String jsonBody = objectMapper.writeValueAsString(body);
                bodyPublisher = HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8);
            } catch (JsonProcessingException e) {
                throw new TinyUrlException("Failed to serialize request body to JSON", e);
            }
        } else {
            bodyPublisher = HttpRequest.BodyPublishers.noBody();
        }

        builder.method(method.toUpperCase(Locale.ROOT), bodyPublisher);
        return builder.build();
    }

    private String buildUriString(String path, Map<String, String> queryParams) {
        StringBuilder sb = new StringBuilder();
        sb.append(config.getBaseUrl());
        if (!path.startsWith("/")) {
            sb.append("/");
        }
        sb.append(path);

        if (queryParams != null && !queryParams.isEmpty()) {
            StringJoiner joiner = new StringJoiner("&", "?", "");
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                if (entry.getValue() != null && !entry.getValue().isBlank()) {
                    joiner.add(urlEncode(entry.getKey()) + "=" + urlEncode(entry.getValue()));
                }
            }
            String query = joiner.toString();
            if (query.length() > 1) { // more than just "?"
                sb.append(query);
            }
        }
        return sb.toString();
    }

    private static String urlEncode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    private <T> T handleResponse(HttpResponse<String> response, Class<T> responseType) {
        int statusCode = response.statusCode();
        String responseBody = response.body();

        Integer apiCode = null;
        List<String> errors = new ArrayList<>();

        if (responseBody != null && !responseBody.isBlank()) {
            try {
                JsonNode rootNode = objectMapper.readTree(responseBody);
                if (rootNode.has("code") && !rootNode.get("code").isNull()) {
                    apiCode = rootNode.get("code").asInt();
                }
                if (rootNode.has("errors") && rootNode.get("errors").isArray()) {
                    for (JsonNode errNode : rootNode.get("errors")) {
                        errors.add(errNode.asText());
                    }
                }
            } catch (Exception ignored) {
                // Not standard JSON error structure, keep raw response
            }
        }

        // If HTTP status is error or API error code indicates failure
        if (statusCode >= 400 || (apiCode != null && apiCode != 0)) {
            throw createApiException(statusCode, apiCode, errors, responseBody);
        }

        try {
            return objectMapper.readValue(responseBody, responseType);
        } catch (JsonProcessingException e) {
            throw new TinyUrlException("Failed to deserialize API response: " + e.getMessage() + "\nRaw response: " + responseBody, e);
        }
    }

    private TinyUrlApiException createApiException(int statusCode, Integer apiCode, List<String> errors, String responseBody) {
        if (statusCode == 401 || (apiCode != null && apiCode == 1)) {
            return new TinyUrlAuthenticationException(statusCode, apiCode, errors, responseBody);
        }
        if (statusCode == 405 || statusCode == 403 || (apiCode != null && (apiCode == 3 || apiCode == 4))) {
            return new TinyUrlPermissionDeniedException(statusCode, apiCode, errors, responseBody);
        }
        if (statusCode == 404 || (apiCode != null && apiCode == 6)) {
            return new TinyUrlNotFoundException(statusCode, apiCode, errors, responseBody);
        }
        if (statusCode == 422 || (apiCode != null && apiCode == 5)) {
            return new TinyUrlValidationException(statusCode, apiCode, errors, responseBody);
        }
        if (statusCode >= 500 || (apiCode != null && apiCode == 7)) {
            return new TinyUrlServerException(statusCode, apiCode, errors, responseBody);
        }
        return new TinyUrlApiException(statusCode, apiCode, errors, responseBody);
    }
}

