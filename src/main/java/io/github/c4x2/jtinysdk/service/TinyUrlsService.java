package io.github.c4x2.jtinysdk.service;

import io.github.c4x2.jtinysdk.client.HttpTransport;
import io.github.c4x2.jtinysdk.model.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Service providing operations on TinyURL links (create, update, change long URL, delete, list, archive, etc.).
 */
public class TinyUrlsService {

    private final HttpTransport transport;

    public TinyUrlsService(HttpTransport transport) {
        this.transport = Objects.requireNonNull(transport, "transport must not be null");
    }

    /**
     * Creates a new TinyURL from a given long URL and returns the generated short URL string.
     *
     * @param longUrl the target URL to shorten
     * @return the shortened TinyURL (e.g. "https://tinyurl.com/xyz123")
     */
    public String shorten(String longUrl) {
        UrlAliasResponse response = create(CreateTinyUrlRequest.builder().url(longUrl).build());
        return extractTinyUrl(response);
    }

    /**
     * Creates a new TinyURL with a custom alias and returns the generated short URL string.
     *
     * @param longUrl the target URL to shorten
     * @param alias   custom alias string
     * @return the shortened TinyURL
     */
    public String shorten(String longUrl, String alias) {
        UrlAliasResponse response = create(CreateTinyUrlRequest.builder().url(longUrl).alias(alias).build());
        return extractTinyUrl(response);
    }

    /**
     * Creates a new TinyURL with a custom domain and alias and returns the generated short URL string.
     *
     * @param longUrl the target URL to shorten
     * @param domain  custom domain (e.g. "tinyurl.com" or branded domain)
     * @param alias   custom alias string
     * @return the shortened TinyURL
     */
    public String shorten(String longUrl, String domain, String alias) {
        UrlAliasResponse response = create(CreateTinyUrlRequest.builder().url(longUrl).domain(domain).alias(alias).build());
        return extractTinyUrl(response);
    }

    /**
     * Create a new TinyURL.
     *
     * @param request configuration for creating the TinyURL
     * @return the API response containing the created alias details
     */
    public UrlAliasResponse create(CreateTinyUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.send("POST", "/create", null, request, UrlAliasResponse.class);
    }

    /**
     * Asynchronously create a new TinyURL.
     *
     * @param request configuration for creating the TinyURL
     * @return a CompletableFuture holding the created alias response
     */
    public CompletableFuture<UrlAliasResponse> createAsync(CreateTinyUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.sendAsync("POST", "/create", null, request, UrlAliasResponse.class);
    }

    /**
     * Update an existing TinyURL's alias, tags, description, or stats options.
     *
     * @param request update details
     * @return the updated alias response
     */
    public AliasResponse update(UpdateTinyUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.send("PATCH", "/update", null, request, AliasResponse.class);
    }

    /**
     * Asynchronously update an existing TinyURL.
     *
     * @param request update details
     * @return a CompletableFuture holding the updated alias response
     */
    public CompletableFuture<AliasResponse> updateAsync(UpdateTinyUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.sendAsync("PATCH", "/update", null, request, AliasResponse.class);
    }

    /**
     * Change the target long URL of an existing TinyURL.
     *
     * @param request update long URL details
     * @return the API response with the updated long URL
     */
    public UrlResponse changeUrl(UpdateUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.send("PATCH", "/change", null, request, UrlResponse.class);
    }

    /**
     * Convenience method to change the target long URL of an existing TinyURL.
     *
     * @param domain the TinyURL domain
     * @param alias  the TinyURL alias
     * @param newUrl the new destination URL
     * @return the API response with the updated long URL
     */
    public UrlResponse changeUrl(String domain, String alias, String newUrl) {
        return changeUrl(new UpdateUrlRequest(domain, alias, newUrl));
    }

    /**
     * Asynchronously change the target long URL of an existing TinyURL.
     *
     * @param request update long URL details
     * @return a CompletableFuture holding the updated URL response
     */
    public CompletableFuture<UrlResponse> changeUrlAsync(UpdateUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.sendAsync("PATCH", "/change", null, request, UrlResponse.class);
    }

    /**
     * Receive detailed TinyURL information including target URL, click hits, and billable user/team info.
     *
     * @param domain TinyURL domain (e.g. "tinyurl.com")
     * @param alias  TinyURL alias
     * @return detailed alias information
     */
    public UrlAliasHitsBillableResponse get(String domain, String alias) {
        Objects.requireNonNull(domain, "domain must not be null");
        Objects.requireNonNull(alias, "alias must not be null");
        String path = "/alias/" + domain + "/" + alias;
        return transport.send("GET", path, null, null, UrlAliasHitsBillableResponse.class);
    }

    /**
     * Asynchronously receive detailed TinyURL information.
     *
     * @param domain TinyURL domain
     * @param alias  TinyURL alias
     * @return a CompletableFuture holding detailed alias information
     */
    public CompletableFuture<UrlAliasHitsBillableResponse> getAsync(String domain, String alias) {
        Objects.requireNonNull(domain, "domain must not be null");
        Objects.requireNonNull(alias, "alias must not be null");
        String path = "/alias/" + domain + "/" + alias;
        return transport.sendAsync("GET", path, null, null, UrlAliasHitsBillableResponse.class);
    }

    /**
     * Delete an existing TinyURL.
     *
     * @param domain TinyURL domain
     * @param alias  TinyURL alias
     * @return response indicating deletion
     */
    public UrlAliasResponse delete(String domain, String alias) {
        Objects.requireNonNull(domain, "domain must not be null");
        Objects.requireNonNull(alias, "alias must not be null");
        String path = "/alias/" + domain + "/" + alias;
        return transport.send("DELETE", path, null, null, UrlAliasResponse.class);
    }

    /**
     * Asynchronously delete an existing TinyURL.
     *
     * @param domain TinyURL domain
     * @param alias  TinyURL alias
     * @return a CompletableFuture holding the deletion response
     */
    public CompletableFuture<UrlAliasResponse> deleteAsync(String domain, String alias) {
        Objects.requireNonNull(domain, "domain must not be null");
        Objects.requireNonNull(alias, "alias must not be null");
        String path = "/alias/" + domain + "/" + alias;
        return transport.sendAsync("DELETE", path, null, null, UrlAliasResponse.class);
    }

    /**
     * Get a list of TinyURLs filtered by type (available/archived), date range, or tag/alias search.
     *
     * @param request filter options
     * @return alias response
     */
    public AliasResponse list(GetUrlsListRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        String path = "/urls/" + request.getType().getValue();
        Map<String, String> queryParams = new LinkedHashMap<>();
        if (request.getFrom() != null) queryParams.put("from", request.getFrom());
        if (request.getTo() != null) queryParams.put("to", request.getTo());
        if (request.getSearch() != null) queryParams.put("search", request.getSearch());

        return transport.send("GET", path, queryParams, null, AliasResponse.class);
    }

    /**
     * Convenience method to get a list of TinyURLs by type.
     *
     * @param type AVAILABLE or ARCHIVED
     * @return alias response
     */
    public AliasResponse list(UrlsType type) {
        return list(new GetUrlsListRequest(type));
    }

    /**
     * Asynchronously get a list of TinyURLs.
     *
     * @param request filter options
     * @return a CompletableFuture holding the alias response
     */
    public CompletableFuture<AliasResponse> listAsync(GetUrlsListRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        String path = "/urls/" + request.getType().getValue();
        Map<String, String> queryParams = new LinkedHashMap<>();
        if (request.getFrom() != null) queryParams.put("from", request.getFrom());
        if (request.getTo() != null) queryParams.put("to", request.getTo());
        if (request.getSearch() != null) queryParams.put("search", request.getSearch());

        return transport.sendAsync("GET", path, queryParams, null, AliasResponse.class);
    }

    /**
     * Get count of TinyURLs filtered by type (available/archived), date range, or search criteria.
     *
     * @param request filter options
     * @return response containing the aliases count
     */
    public AliasesCountResponse count(GetUrlsCountRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        String path = "/urls/" + request.getType().getValue() + "/count";
        Map<String, String> queryParams = new LinkedHashMap<>();
        if (request.getFrom() != null) queryParams.put("from", request.getFrom());
        if (request.getTo() != null) queryParams.put("to", request.getTo());
        if (request.getSearch() != null) queryParams.put("search", request.getSearch());

        return transport.send("GET", path, queryParams, null, AliasesCountResponse.class);
    }

    /**
     * Convenience method to get count of TinyURLs by type.
     *
     * @param type AVAILABLE or ARCHIVED
     * @return response containing the aliases count
     */
    public AliasesCountResponse count(UrlsType type) {
        return count(new GetUrlsCountRequest(type));
    }

    /**
     * Asynchronously get count of TinyURLs.
     *
     * @param request filter options
     * @return a CompletableFuture holding the aliases count response
     */
    public CompletableFuture<AliasesCountResponse> countAsync(GetUrlsCountRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        String path = "/urls/" + request.getType().getValue() + "/count";
        Map<String, String> queryParams = new LinkedHashMap<>();
        if (request.getFrom() != null) queryParams.put("from", request.getFrom());
        if (request.getTo() != null) queryParams.put("to", request.getTo());
        if (request.getSearch() != null) queryParams.put("search", request.getSearch());

        return transport.sendAsync("GET", path, queryParams, null, AliasesCountResponse.class);
    }

    /**
     * Archive or unarchive a TinyURL.
     *
     * @param request payload specifying domain and alias
     * @return alias response
     */
    public AliasResponse archive(SourceTinyUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.send("PATCH", "/archive", null, request, AliasResponse.class);
    }

    /**
     * Convenience method to archive/unarchive a TinyURL.
     *
     * @param domain TinyURL domain
     * @param alias  TinyURL alias
     * @return alias response
     */
    public AliasResponse archive(String domain, String alias) {
        return archive(new SourceTinyUrlRequest(domain, alias));
    }

    /**
     * Asynchronously archive or unarchive a TinyURL.
     *
     * @param request payload specifying domain and alias
     * @return a CompletableFuture holding the alias response
     */
    public CompletableFuture<AliasResponse> archiveAsync(SourceTinyUrlRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.sendAsync("PATCH", "/archive", null, request, AliasResponse.class);
    }

    /**
     * Enable or disable click analytics collection for a TinyURL.
     *
     * @param request analytics status update details
     * @return alias response
     */
    public AliasResponse updateAnalyticsStatus(UpdateAnalyticsStatusRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.send("PATCH", "/analytics/status", null, request, AliasResponse.class);
    }

    /**
     * Convenience method to enable or disable click analytics collection.
     *
     * @param domain  TinyURL domain
     * @param alias   TinyURL alias
     * @param enabled whether analytics collection is enabled
     * @return alias response
     */
    public AliasResponse updateAnalyticsStatus(String domain, String alias, boolean enabled) {
        return updateAnalyticsStatus(new UpdateAnalyticsStatusRequest(domain, alias, enabled));
    }

    /**
     * Asynchronously enable or disable click analytics collection.
     *
     * @param request analytics status update details
     * @return a CompletableFuture holding the alias response
     */
    public CompletableFuture<AliasResponse> updateAnalyticsStatusAsync(UpdateAnalyticsStatusRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.sendAsync("PATCH", "/analytics/status", null, request, AliasResponse.class);
    }

    /**
     * Enable or disable public analytics sharing for a TinyURL.
     *
     * @param request analytics sharing update details
     * @return alias response
     */
    public AliasResponse updateAnalyticsSharing(UpdateAnalyticsSharingRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.send("PATCH", "/analytics/sharing", null, request, AliasResponse.class);
    }

    /**
     * Convenience method to enable or disable public analytics sharing.
     *
     * @param domain   TinyURL domain
     * @param alias    TinyURL alias
     * @param isPublic whether analytics are publicly viewable
     * @return alias response
     */
    public AliasResponse updateAnalyticsSharing(String domain, String alias, boolean isPublic) {
        return updateAnalyticsSharing(new UpdateAnalyticsSharingRequest(domain, alias, isPublic));
    }

    /**
     * Asynchronously enable or disable public analytics sharing.
     *
     * @param request analytics sharing update details
     * @return a CompletableFuture holding the alias response
     */
    public CompletableFuture<AliasResponse> updateAnalyticsSharingAsync(UpdateAnalyticsSharingRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return transport.sendAsync("PATCH", "/analytics/sharing", null, request, AliasResponse.class);
    }

    private static String extractTinyUrl(UrlAliasResponse response) {
        if (response != null && response.getData() != null) {
            return response.getData().getTinyUrl();
        }
        return null;
    }
}

