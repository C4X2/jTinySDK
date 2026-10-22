package io.github.c4x2.jtinysdk.service;

import io.github.c4x2.jtinysdk.client.HttpTransport;
import io.github.c4x2.jtinysdk.model.analytics.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Service providing analytics queries for TinyURL links (raw logs, timeline, general breakdown, popularity, top sources, top languages, locations).
 */
public class AnalyticsService {

    private final HttpTransport transport;

    public AnalyticsService(HttpTransport transport) {
        this.transport = Objects.requireNonNull(transport, "transport must not be null");
    }

    /**
     * Return raw analytics logs.
     *
     * @param filter filter criteria specifying at least the start datetime ("from")
     * @return raw logs response
     */
    public RawLogsAnalyticsResponse getRawLogs(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.send("GET", "/analytics/raw/json", toQueryMap(filter), null, RawLogsAnalyticsResponse.class);
    }

    /**
     * Asynchronously return raw analytics logs.
     *
     * @param filter filter criteria
     * @return CompletableFuture holding raw logs response
     */
    public CompletableFuture<RawLogsAnalyticsResponse> getRawLogsAsync(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.sendAsync("GET", "/analytics/raw/json", toQueryMap(filter), null, RawLogsAnalyticsResponse.class);
    }

    /**
     * Return timeline analytics data.
     *
     * @param filter   filter criteria
     * @param interval aggregation interval (minute, hour, day, week, month)
     * @return timeline response
     */
    public TimelineAnalyticsResponse getTimeline(AnalyticsFilter filter, TimelineInterval interval) {
        Objects.requireNonNull(filter, "filter must not be null");
        Map<String, String> query = toQueryMap(filter);
        if (interval != null) {
            query.put("interval", interval.getValue());
        }
        return transport.send("GET", "/analytics/timeline", query, null, TimelineAnalyticsResponse.class);
    }

    /**
     * Asynchronously return timeline analytics data.
     *
     * @param filter   filter criteria
     * @param interval aggregation interval
     * @return CompletableFuture holding timeline response
     */
    public CompletableFuture<TimelineAnalyticsResponse> getTimelineAsync(AnalyticsFilter filter, TimelineInterval interval) {
        Objects.requireNonNull(filter, "filter must not be null");
        Map<String, String> query = toQueryMap(filter);
        if (interval != null) {
            query.put("interval", interval.getValue());
        }
        return transport.sendAsync("GET", "/analytics/timeline", query, null, TimelineAnalyticsResponse.class);
    }

    /**
     * Return browser, OS, and device general analytics.
     *
     * @param filter filter criteria
     * @return general analytics response
     */
    public GeneralAnalyticsResponse getGeneral(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.send("GET", "/analytics/general", toQueryMap(filter), null, GeneralAnalyticsResponse.class);
    }

    /**
     * Asynchronously return browser, OS, and device general analytics.
     *
     * @param filter filter criteria
     * @return CompletableFuture holding general analytics response
     */
    public CompletableFuture<GeneralAnalyticsResponse> getGeneralAsync(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.sendAsync("GET", "/analytics/general", toQueryMap(filter), null, GeneralAnalyticsResponse.class);
    }

    /**
     * Return weekdays popularity analytics.
     *
     * @param filter filter criteria
     * @return weekdays popularity response
     */
    public WeekdaysPopularityAnalyticsResponse getWeekdaysPopularity(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.send("GET", "/analytics/popularity/weekday", toQueryMap(filter), null, WeekdaysPopularityAnalyticsResponse.class);
    }

    /**
     * Asynchronously return weekdays popularity analytics.
     *
     * @param filter filter criteria
     * @return CompletableFuture holding weekdays popularity response
     */
    public CompletableFuture<WeekdaysPopularityAnalyticsResponse> getWeekdaysPopularityAsync(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.sendAsync("GET", "/analytics/popularity/weekday", toQueryMap(filter), null, WeekdaysPopularityAnalyticsResponse.class);
    }

    /**
     * Return hour popularity analytics.
     *
     * @param filter filter criteria
     * @return hour popularity response
     */
    public HoursPopularityAnalyticsResponse getHoursPopularity(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.send("GET", "/analytics/popularity/hour", toQueryMap(filter), null, HoursPopularityAnalyticsResponse.class);
    }

    /**
     * Asynchronously return hour popularity analytics.
     *
     * @param filter filter criteria
     * @return CompletableFuture holding hour popularity response
     */
    public CompletableFuture<HoursPopularityAnalyticsResponse> getHoursPopularityAsync(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.sendAsync("GET", "/analytics/popularity/hour", toQueryMap(filter), null, HoursPopularityAnalyticsResponse.class);
    }

    /**
     * Return top referral sources analytics.
     *
     * @param filter filter criteria
     * @return top sources response
     */
    public TopSourcesAnalyticsResponse getTopSources(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.send("GET", "/analytics/sources/top", toQueryMap(filter), null, TopSourcesAnalyticsResponse.class);
    }

    /**
     * Asynchronously return top referral sources analytics.
     *
     * @param filter filter criteria
     * @return CompletableFuture holding top sources response
     */
    public CompletableFuture<TopSourcesAnalyticsResponse> getTopSourcesAsync(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.sendAsync("GET", "/analytics/sources/top", toQueryMap(filter), null, TopSourcesAnalyticsResponse.class);
    }

    /**
     * Return top languages analytics.
     *
     * @param filter filter criteria
     * @return top languages response
     */
    public TopLanguagesAnalyticsResponse getTopLanguages(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.send("GET", "/analytics/languages/top", toQueryMap(filter), null, TopLanguagesAnalyticsResponse.class);
    }

    /**
     * Asynchronously return top languages analytics.
     *
     * @param filter filter criteria
     * @return CompletableFuture holding top languages response
     */
    public CompletableFuture<TopLanguagesAnalyticsResponse> getTopLanguagesAsync(AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        return transport.sendAsync("GET", "/analytics/languages/top", toQueryMap(filter), null, TopLanguagesAnalyticsResponse.class);
    }

    /**
     * Return geographic location analytics for a region.
     *
     * @param region Country Alpha-2 ISO 3166-1 code or "world"
     * @param filter filter criteria
     * @return location analytics response
     */
    public LocationAnalyticsResponse getLocation(String region, AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        String targetRegion = (region != null && !region.isBlank()) ? region : "world";
        String path = "/analytics/location/" + targetRegion;
        return transport.send("GET", path, toQueryMap(filter), null, LocationAnalyticsResponse.class);
    }

    /**
     * Convenience method to return global location analytics ("world").
     *
     * @param filter filter criteria
     * @return location analytics response
     */
    public LocationAnalyticsResponse getLocation(AnalyticsFilter filter) {
        return getLocation("world", filter);
    }

    /**
     * Asynchronously return location analytics.
     *
     * @param region Country Alpha-2 code or "world"
     * @param filter filter criteria
     * @return CompletableFuture holding location analytics response
     */
    public CompletableFuture<LocationAnalyticsResponse> getLocationAsync(String region, AnalyticsFilter filter) {
        Objects.requireNonNull(filter, "filter must not be null");
        String targetRegion = (region != null && !region.isBlank()) ? region : "world";
        String path = "/analytics/location/" + targetRegion;
        return transport.sendAsync("GET", path, toQueryMap(filter), null, LocationAnalyticsResponse.class);
    }

    private static Map<String, String> toQueryMap(AnalyticsFilter filter) {
        Map<String, String> map = new LinkedHashMap<>();
        if (filter.getFrom() != null) map.put("from", filter.getFrom());
        if (filter.getTo() != null) map.put("to", filter.getTo());
        if (filter.getAlias() != null) map.put("alias", filter.getAlias());
        if (filter.getTag() != null) map.put("tag", filter.getTag());
        return map;
    }
}

