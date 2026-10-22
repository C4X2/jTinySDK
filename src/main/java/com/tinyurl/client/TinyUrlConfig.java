package com.tinyurl.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Objects;

/**
 * Configuration options for the TinyURL client.
 */
public class TinyUrlConfig {

    public static final String DEFAULT_BASE_URL = "https://api.tinyurl.com";
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);

    private final String apiToken;
    private final AuthType authType;
    private final String baseUrl;
    private final Duration timeout;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private TinyUrlConfig(Builder builder) {
        this.apiToken = builder.apiToken;
        this.authType = builder.authType != null ? builder.authType : AuthType.BEARER;
        this.baseUrl = sanitizeBaseUrl(builder.baseUrl != null ? builder.baseUrl : DEFAULT_BASE_URL);
        this.timeout = builder.timeout != null ? builder.timeout : DEFAULT_TIMEOUT;
        this.httpClient = builder.httpClient != null ? builder.httpClient : HttpClient.newBuilder()
                .connectTimeout(this.timeout)
                .build();
        this.objectMapper = builder.objectMapper != null ? builder.objectMapper : createDefaultObjectMapper();
    }

    public static Builder builder() {
        return new Builder();
    }

    private static String sanitizeBaseUrl(String url) {
        if (url == null || url.isBlank()) {
            return DEFAULT_BASE_URL;
        }
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }

    public static ObjectMapper createDefaultObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String getApiToken() {
        return apiToken;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static class Builder {
        private String apiToken;
        private AuthType authType = AuthType.BEARER;
        private String baseUrl = DEFAULT_BASE_URL;
        private Duration timeout = DEFAULT_TIMEOUT;
        private HttpClient httpClient;
        private ObjectMapper objectMapper;

        public Builder apiToken(String apiToken) {
            this.apiToken = apiToken;
            return this;
        }

        public Builder authType(AuthType authType) {
            this.authType = authType;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder objectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public TinyUrlConfig build() {
            return new TinyUrlConfig(this);
        }
    }
}
