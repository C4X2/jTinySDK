package com.tinyurl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinyurl.auth.EnvironmentAPIKeyLoader;
import com.tinyurl.auth.PropertiesAPIKeyLoader;
import com.tinyurl.client.AuthType;
import com.tinyurl.client.HttpTransport;
import com.tinyurl.client.TinyUrlConfig;
import com.tinyurl.model.CreateTinyUrlRequest;
import com.tinyurl.model.UrlAliasResponse;
import com.tinyurl.service.AnalyticsService;
import com.tinyurl.service.BulkService;
import com.tinyurl.service.TinyUrlsService;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Objects;

/**
 * Main client for interacting with the TinyURL API (v2.4.2).
 * <p>
 * Example usage:
 * <pre>{@code
 * TinyUrlClient client = TinyUrlClient.builder()
 *     .apiToken("your-api-token")
 *     .build();
 *
 * // Quick 1-liner shorten
 * String shortUrl = client.shorten("https://example.com/very/long/url");
 *
 * // Full URL operations
 * UrlAliasResponse res = client.urls().create(CreateTinyUrlRequest.builder()
 *     .url("https://example.com")
 *     .alias("custom-alias")
 *     .build());
 *
 * // Bulk processing
 * BulkStatusResponse bulk = client.bulk().create("tinyurl.com", List.of(
 *     BulkRequestItem.create("https://example.com/1"),
 *     BulkRequestItem.create("https://example.com/2")
 * ));
 *
 * // Analytics
 * TimelineAnalyticsResponse timeline = client.analytics().getTimeline(
 *     AnalyticsFilter.builder().from("2024-01-01T00:00:00Z").build(),
 *     TimelineInterval.DAY
 * );
 * }</pre>
 */
public class TinyUrlClient {

    private final TinyUrlConfig config;
    private final HttpTransport transport;

    private final TinyUrlsService urlsService;
    private final BulkService bulkService;
    private final AnalyticsService analyticsService;

    private TinyUrlClient(TinyUrlConfig config) {
        this.config = Objects.requireNonNull(config, "config must not be null");
        this.transport = new HttpTransport(config);
        this.urlsService = new TinyUrlsService(this.transport);
        this.bulkService = new BulkService(this.transport);
        this.analyticsService = new AnalyticsService(this.transport);
    }

    /**
     * Create a new client builder.
     *
     * @return a new client Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Create a TinyURL client with the provided API token and default settings (Bearer auth, standard timeout).
     *
     * @param apiToken the TinyURL API token
     * @return a configured TinyUrlClient instance
     */
    public static TinyUrlClient create(String apiToken) {
        return builder().apiToken(apiToken).build();
    }

    public static TinyUrlClient create() {
        String token = new EnvironmentAPIKeyLoader()
                .loadAPIKey()
                .orElse(new PropertiesAPIKeyLoader()
                        .loadAPIKey().orElse(""));
        return create(token);
    }

    /**
     * Create a TinyURL client using the {@code TINYURL_API_TOKEN} environment variable.
     *
     * @return a configured TinyUrlClient instance
     * @throws IllegalStateException if the TINYURL_API_TOKEN environment variable is not set
     */
    public static TinyUrlClient fromEnv() {
        String token = System.getenv("TINYURL_API_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Environment variable TINYURL_API_TOKEN is not set or blank");
        }
        return create(token);
    }

    /**
     * Access the URLs service for creating, updating, archiving, deleting, and querying TinyURL links.
     *
     * @return the TinyUrlsService instance
     */
    public TinyUrlsService urls() {
        return urlsService;
    }

    /**
     * Access the Bulk service for submitting and managing batch processing operations.
     *
     * @return the BulkService instance
     */
    public BulkService bulk() {
        return bulkService;
    }

    /**
     * Access the Analytics service for querying metrics, timeline, referrer logs, and popularity.
     *
     * @return the AnalyticsService instance
     */
    public AnalyticsService analytics() {
        return analyticsService;
    }

    /**
     * Convenience method to shorten a URL and return the short URL string directly.
     *
     * @param longUrl target URL to shorten
     * @return the shortened TinyURL (e.g. "https://tinyurl.com/xyz123")
     */
    public String shorten(String longUrl) {
        return urlsService.shorten(longUrl);
    }

    /**
     * Convenience method to shorten a URL with a custom alias.
     *
     * @param longUrl target URL to shorten
     * @param alias   custom alias string
     * @return the shortened TinyURL
     */
    public String shorten(String longUrl, String alias) {
        return urlsService.shorten(longUrl, alias);
    }

    /**
     * Convenience method to shorten a URL with a custom domain and alias.
     *
     * @param longUrl target URL to shorten
     * @param domain  domain (e.g. "tinyurl.com" or branded domain)
     * @param alias   custom alias string
     * @return the shortened TinyURL
     */
    public String shorten(String longUrl, String domain, String alias) {
        return urlsService.shorten(longUrl, domain, alias);
    }

    /**
     * Convenience method to create a new TinyURL.
     *
     * @param request creation request options
     * @return API response with created alias details
     */
    public UrlAliasResponse create(CreateTinyUrlRequest request) {
        return urlsService.create(request);
    }

    /**
     * Returns the configuration used by this client.
     *
     * @return the active TinyUrlConfig
     */
    public TinyUrlConfig getConfig() {
        return config;
    }

    public static class Builder {
        private final TinyUrlConfig.Builder configBuilder = TinyUrlConfig.builder();

        public Builder apiToken(String apiToken) {
            configBuilder.apiToken(apiToken);
            return this;
        }

        public Builder authType(AuthType authType) {
            configBuilder.authType(authType);
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            configBuilder.baseUrl(baseUrl);
            return this;
        }

        public Builder timeout(Duration timeout) {
            configBuilder.timeout(timeout);
            return this;
        }

        public Builder httpClient(HttpClient httpClient) {
            configBuilder.httpClient(httpClient);
            return this;
        }

        public Builder objectMapper(ObjectMapper objectMapper) {
            configBuilder.objectMapper(objectMapper);
            return this;
        }

        public TinyUrlClient build() {
            return new TinyUrlClient(configBuilder.build());
        }
    }
}
