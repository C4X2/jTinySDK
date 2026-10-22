package io.github.c4x2.jtinysdk.example;

import io.github.c4x2.jtinysdk.TinyUrlClient;
import io.github.c4x2.jtinysdk.model.*;
import io.github.c4x2.jtinysdk.model.analytics.AnalyticsFilter;
import io.github.c4x2.jtinysdk.model.analytics.TimelineAnalyticsResponse;
import io.github.c4x2.jtinysdk.model.analytics.TimelineInterval;
import io.github.c4x2.jtinysdk.model.bulk.BulkReportResponse;
import io.github.c4x2.jtinysdk.model.bulk.BulkRequestItem;
import io.github.c4x2.jtinysdk.model.bulk.BulkStatusResponse;

import java.util.List;

/**
 * Quick start examples demonstrating how to use the TinyURL Java SDK.
 */
public class QuickStartExample {

    public static void main(String[] args) {
        // 1. Initialize client with your API token
        //String apiToken = System.getenv().getOrDefault("TINYURL_API_TOKEN", "your-api-token-here");
        TinyUrlClient client = TinyUrlClient.create();

        // 2. Simplest 1-line URL shortening
        System.out.println("--- 1. Quick Shorten ---");
        String shortUrl = client.shorten("https://example.com/some/deep/resource");
        System.out.println("Short URL: " + shortUrl);

        // 3. Creating a TinyURL with custom alias, domain, tags, and expiration
        System.out.println("\n--- 2. Create with Options ---");
        CreateTinyUrlRequest createReq = CreateTinyUrlRequest.builder()
                .url("https://example.com/fall-promotion-2024")
                .domain("tinyurl.com")
                .alias("fall-promo-24")
                .tags("fall", "marketing", "social")
                .description("Fall campaign landing page")
                .expiresAt("2025-01-01 00:00:00")
                .build();
        UrlAliasResponse createRes = client.urls().create(createReq);
        System.out.println("Created: " + createRes.getData().getTinyUrl());

        // 4. Update alias, tags, and stats
        System.out.println("\n--- 3. Update TinyURL ---");
        UpdateTinyUrlRequest updateReq = UpdateTinyUrlRequest.builder()
                .domain("tinyurl.com")
                .alias("fall-promo-24")
                .newTags("fall", "marketing", "updated")
                .newStats(true)
                .build();
        // client.urls().update(updateReq);

        // 5. Change target long URL
        System.out.println("\n--- 4. Change Long URL ---");
        // client.urls().changeUrl("tinyurl.com", "fall-promo-24", "https://example.com/new-fall-destination");

        // 6. Get alias details and click statistics
        System.out.println("\n--- 5. Get Alias Details & Hits ---");
        // UrlAliasHitsBillableResponse info = client.urls().get("tinyurl.com", "fall-promo-24");
        // System.out.println("Hits: " + info.getData().getHits());

        // 7. Bulk operations
        System.out.println("\n--- 6. Bulk Operations ---");
        // BulkStatusResponse bulkStatus = client.bulk().create("tinyurl.com", List.of(
        //         BulkRequestItem.create("https://example.com/page1"),
        //         BulkRequestItem.create("https://example.com/page2"),
        //         BulkRequestItem.archive("tinyurl.com", "old-promo")
        // ));
        // long batchId = bulkStatus.getData().getId();
        // BulkReportResponse report = client.bulk().getReport(batchId);

        // 8. Analytics
        System.out.println("\n--- 7. Query Analytics ---");
        AnalyticsFilter filter = AnalyticsFilter.builder()
                .from("2024-01-01")
                .alias("fall-promo-24")
                .build();
        // TimelineAnalyticsResponse timeline = client.analytics().getTimeline(filter, TimelineInterval.DAY);
    }
}

