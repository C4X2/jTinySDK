# TinyURL Java SDK

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://openjdk.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![API Version](https://img.shields.io/badge/TinyURL%20API-v2.4.2-green.svg)](https://tinyurl.com/app/help)

A modern, enterprise-ready, fluent Java SDK for the [TinyURL API](https://tinyurl.com). Built on Java standard `java.net.http.HttpClient` with zero heavy HTTP dependencies, Jackson serialization, fluent builders, typed responses, and synchronous/asynchronous support.

---

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
  - [Gradle](#gradle)
  - [Maven](#maven)
- [Authentication](#authentication)
- [Quick Start](#quick-start)
- [Client Configuration](#client-configuration)
- [API Services & Usage](#api-services--usage)
  - [1. TinyURL Core Operations](#1-tinyurl-core-operations)
    - [Shorten (Quick)](#shorten-quick)
    - [Create with Full Options](#create-with-full-options)
    - [Update Existing TinyURL](#update-existing-tinyurl)
    - [Change Long URL](#change-long-url)
    - [Get TinyURL Details & Click Hits](#get-tinyurl-details--click-hits)
    - [Delete a TinyURL](#delete-a-tinyurl)
    - [List TinyURLs](#list-tinyurls)
    - [Count TinyURLs](#count-tinyurls)
    - [Archive / Unarchive](#archive--unarchive)
    - [Toggle Analytics & Sharing](#toggle-analytics--sharing)
  - [2. Bulk Batch Operations](#2-bulk-batch-operations)
    - [Submit Bulk Batch](#submit-bulk-batch)
    - [Check Bulk Status](#check-bulk-status)
    - [Fetch Bulk Report](#fetch-bulk-report)
  - [3. Analytics Operations](#3-analytics-operations)
    - [Raw Analytics Logs](#raw-analytics-logs)
    - [Timeline Analytics](#timeline-analytics)
    - [General Analytics (Browser, OS, Device)](#general-analytics-browser-os-device)
    - [Weekday & Hourly Popularity](#weekday--hourly-popularity)
    - [Top Sources & Languages](#top-sources--languages)
    - [Geographic Location Analytics](#geographic-location-analytics)
- [Asynchronous Support](#asynchronous-support)
- [Error Handling](#error-handling)
- [Building & Testing](#building--testing)
- [License](#license)

---

## Features

- **Standard Java 17+**: Leverages standard `java.net.http.HttpClient` (no Jersey, OkHttp, or Apache HttpClient dependencies needed in runtime).
- **Zero-Friction URL Shortening**: 1-liner `client.shorten("https://...")` method.
- **Full OpenAPI 2.4.2 Coverage**:
  - Core link operations (Create, Update, Change destination, Get, Delete, List, Count, Archive).
  - Bulk processing pipeline (Submit batch, Poll status, Download report).
  - Complete analytics suite (Raw logs, Timeline, Device/OS/Browser general breakdown, Weekdays/Hours popularity, Top referrers, Languages, Regions).
- **Authentication**: Supports both Bearer Token HTTP headers (default) and API Token Query Parameter (`?api_token=...`).
- **Sync & Async**: Every endpoint offers synchronous execution and `CompletableFuture<T>` asynchronous execution.
- **Typed Error Hierarchy**: Clear exceptions mapping HTTP status and TinyURL error codes (`TinyUrlAuthenticationException`, `TinyUrlValidationException`, `TinyUrlNotFoundException`, `TinyUrlPermissionDeniedException`, `TinyUrlServerException`).

---

## Requirements

- **Java**: JDK 17 or higher (tested with Java 17 and Java 21)

---

## Installation

### Gradle

```groovy
dependencies {
    implementation 'com.tinyurl:tinyurl-java-sdk:2.4.2'
}
```

### Maven

```xml
<dependency>
    <groupId>com.tinyurl</groupId>
    <artifactId>tinyurl-java-sdk</artifactId>
    <version>2.4.2</version>
</dependency>
```

---

## Authentication

Get your API token by logging into your TinyURL account and navigating to [API Settings](https://tinyurl.com/app/settings/api).

You can supply the token directly or set the `TINYURL_API_TOKEN` environment variable.

```java
// Method 1: Pass API token directly
TinyUrlClient client = TinyUrlClient.create("YOUR_API_TOKEN");

// Method 2: Load automatically from environment variable TINYURL_API_TOKEN
TinyUrlClient client = TinyUrlClient.fromEnv();
```

---

## Quick Start

Shorten a long URL in a single line of code:

```java
import com.tinyurl.TinyUrlClient;

public class App {
    public static void main(String[] args) {
        TinyUrlClient client = TinyUrlClient.create("YOUR_API_TOKEN");

        // Simple shorten
        String shortUrl = client.shorten("https://www.example.com/very/long/url");
        System.out.println("Short URL: " + shortUrl);

        // Shorten with a custom alias
        String customUrl = client.shorten("https://www.example.com", "my-promo-2024");
        System.out.println("Custom Short URL: " + customUrl);
    }
}
```

---

## Client Configuration

Use `TinyUrlClient.builder()` for full control over timeouts, authentication mode, custom base URL (e.g. for mock testing), or custom `HttpClient`:

```java
import com.tinyurl.TinyUrlClient;
import com.tinyurl.client.AuthType;
import java.time.Duration;

TinyUrlClient client = TinyUrlClient.builder()
    .apiToken("YOUR_API_TOKEN")
    .authType(AuthType.BEARER)                  // Default: BEARER; or AuthType.QUERY_PARAM
    .baseUrl("https://api.tinyurl.com")          // Custom base URL
    .timeout(Duration.ofSeconds(15))             // Request timeout
    .build();
```

---

## API Services & Usage

The client is organized into three specialized service domains:
- `client.urls()` — Link creation, updates, destination changes, listing, and archival.
- `client.bulk()` — Batch link processing, status tracking, and report downloads.
- `client.analytics()` — Traffic analysis, time-series, referrers, geolocation, and device metrics.

---

### 1. TinyURL Core Operations

#### Shorten (Quick)
```java
String shortUrl = client.shorten("https://example.com/target");
String brandedUrl = client.shorten("https://example.com/target", "tinyurl.com", "my-alias");
```

#### Create with Full Options
```java
import com.tinyurl.model.CreateTinyUrlRequest;
import com.tinyurl.model.UrlAliasResponse;

CreateTinyUrlRequest request = CreateTinyUrlRequest.builder()
    .url("https://www.example.com/fall-campaign-2024")
    .domain("tinyurl.com")                      // Or branded domain
    .alias("fall-campaign-24")                  // Custom alias (optional)
    .tags("campaign", "fall", "social")         // Tags for grouping
    .description("Landing page for fall promo") // Alias description
    .expiresAt("2024-12-31 23:59:59")           // Expiration in ISO8601 format
    .build();

UrlAliasResponse response = client.urls().create(request);
System.out.println("Created: " + response.getData().getTinyUrl());
```

#### Update Existing TinyURL
```java
import com.tinyurl.model.UpdateTinyUrlRequest;
import com.tinyurl.model.AliasResponse;

UpdateTinyUrlRequest updateRequest = UpdateTinyUrlRequest.builder()
    .domain("tinyurl.com")
    .alias("fall-campaign-24")
    .newAlias("autumn-sale-24")
    .newTags("autumn", "marketing")
    .newStats(true)
    .build();

AliasResponse response = client.urls().update(updateRequest);
```

#### Change Long URL
Update the destination target of an existing TinyURL without changing its alias:

```java
client.urls().changeUrl("tinyurl.com", "autumn-sale-24", "https://www.example.com/new-landing-page");
```

#### Get TinyURL Details & Click Hits
Retrieve alias details along with total click count (`hits`), creation date, and billable user/team account info:

```java
import com.tinyurl.model.UrlAliasHitsBillableResponse;

UrlAliasHitsBillableResponse details = client.urls().get("tinyurl.com", "autumn-sale-24");
System.out.println("Destination: " + details.getData().getUrl());
System.out.println("Total Clicks: " + details.getData().getHits());
System.out.println("Created By: " + details.getData().getUser().getName());
```

#### Delete a TinyURL
```java
client.urls().delete("tinyurl.com", "autumn-sale-24");
```

#### List TinyURLs
Query available or archived links with optional date filtering and tag/alias search:

```java
import com.tinyurl.model.GetUrlsListRequest;
import com.tinyurl.model.UrlsType;
import com.tinyurl.model.AliasResponse;

GetUrlsListRequest listReq = GetUrlsListRequest.builder()
    .type(UrlsType.AVAILABLE)
    .from("2024-01-01 00:00:00")
    .to("2024-10-01 00:00:00")
    .searchByTag("campaign")                    // Or searchByAlias("myalias")
    .build();

AliasResponse response = client.urls().list(listReq);
```

#### Count TinyURLs
```java
import com.tinyurl.model.UrlsType;
import com.tinyurl.model.AliasesCountResponse;

AliasesCountResponse countRes = client.urls().count(UrlsType.AVAILABLE);
System.out.println("Total Available TinyURLs: " + countRes.getData().getCount());
```

#### Archive / Unarchive
```java
client.urls().archive("tinyurl.com", "autumn-sale-24");
```

#### Toggle Analytics & Sharing
```java
// Turn analytics collection on/off
client.urls().updateAnalyticsStatus("tinyurl.com", "autumn-sale-24", true);

// Enable/disable public analytics sharing
client.urls().updateAnalyticsSharing("tinyurl.com", "autumn-sale-24", false);
```

---

### 2. Bulk Batch Operations

Process hundreds or thousands of URL operations in asynchronous batches.

#### Submit Bulk Batch
```java
import com.tinyurl.model.bulk.*;
import java.util.List;

BulkStatusResponse batch = client.bulk().create("tinyurl.com", List.of(
    BulkRequestItem.create("https://example.com/product/1"),
    BulkRequestItem.create("https://example.com/product/2", "tinyurl.com", "custom-prod-2"),
    BulkRequestItem.change("tinyurl.com", "old-alias", "https://example.com/updated"),
    BulkRequestItem.archive("tinyurl.com", "obsolete-alias"),
    BulkRequestItem.delete("tinyurl.com", "temp-alias")
));

long batchId = batch.getData().getId();
System.out.println("Submitted Bulk Batch ID: " + batchId);
```

#### Check Bulk Status
```java
BulkStatusResponse status = client.bulk().getStatus(batchId);
System.out.println("Batch Status: " + status.getData().getStatus()); // 0=New, 1=Processing, 2=Processed
System.out.println("Progress: " + status.getData().getSuccess() + "/" + status.getData().getTotal());
```

#### Fetch Bulk Report
```java
BulkReportResponse report = client.bulk().getReport(batchId);
for (BulkReportData item : report.getData()) {
    System.out.printf("[%s] %s -> %s (Status: %s)%n",
        item.getOperation(), item.getUrl(), item.getTinyurl(), item.getStatus());
}
```

---

### 3. Analytics Operations

All analytics endpoints accept an `AnalyticsFilter` specifying the start date (`from`), optional end date (`to`), and optional `alias` or `tag` filters.

#### Raw Analytics Logs
```java
import com.tinyurl.model.analytics.AnalyticsFilter;
import com.tinyurl.model.analytics.RawLogsAnalyticsResponse;
import com.tinyurl.model.analytics.RawLogsAnalyticsData;

AnalyticsFilter filter = AnalyticsFilter.builder()
    .from("2024-01-01 00:00:00")
    .alias("autumn-sale-24")
    .build();

RawLogsAnalyticsResponse logs = client.analytics().getRawLogs(filter);
for (RawLogsAnalyticsData log : logs.getData()) {
    System.out.println(log.getDatetime() + " | IP: " + log.getIp() + " | " + log.getCountry() + 
                       " | Browser: " + log.getBrowser().getName());
}
```

#### Timeline Analytics
```java
import com.tinyurl.model.analytics.TimelineAnalyticsResponse;
import com.tinyurl.model.analytics.TimelineInterval;
import com.tinyurl.model.analytics.TimelineItem;

TimelineAnalyticsResponse timeline = client.analytics().getTimeline(filter, TimelineInterval.DAY);
for (TimelineItem point : timeline.getData().getDataset()) {
    System.out.printf("%s -> Total: %d, Unique: %d, Human: %d%n",
        point.getDatetime(), point.getTotal(), point.getUnique(), point.getHuman());
}
```

#### General Analytics (Browser, OS, Device)
```java
import com.tinyurl.model.analytics.GeneralAnalyticsResponse;

GeneralAnalyticsResponse general = client.analytics().getGeneral(filter);
System.out.println("Total Clicks: " + general.getData().getTotal());
```

#### Weekday & Hourly Popularity
```java
import com.tinyurl.model.analytics.WeekdaysPopularityAnalyticsResponse;
import com.tinyurl.model.analytics.HoursPopularityAnalyticsResponse;

// Weekdays (7 elements: Sun-Sat)
WeekdaysPopularityAnalyticsResponse weekdays = client.analytics().getWeekdaysPopularity(filter);
System.out.println("Weekday distribution: " + weekdays.getData().getDataset());

// Hours (24 elements: 0-23)
HoursPopularityAnalyticsResponse hours = client.analytics().getHoursPopularity(filter);
System.out.println("Hourly distribution: " + hours.getData().getDataset());
```

#### Top Sources & Languages
```java
// Top Referrers
var sources = client.analytics().getTopSources(filter);
sources.getData().getDataset().forEach(s -> System.out.println(s.getSource() + ": " + s.getHits()));

// Top Languages
var languages = client.analytics().getTopLanguages(filter);
languages.getData().getDataset().forEach(l -> System.out.println(l.getLanguage() + ": " + l.getHits()));
```

#### Geographic Location Analytics
```java
// Global Breakdown ("world")
var worldLocations = client.analytics().getLocation(filter);

// Specific country breakdown (e.g. "US")
var usLocations = client.analytics().getLocation("US", filter);
```

---

## Asynchronous Support

All operations provide non-blocking asynchronous counterparts returning `CompletableFuture<T>`:

```java
client.urls().createAsync(CreateTinyUrlRequest.builder().url("https://example.com").build())
    .thenAccept(res -> System.out.println("Async created: " + res.getData().getTinyUrl()))
    .exceptionally(ex -> {
        System.err.println("Async failed: " + ex.getMessage());
        return null;
    });
```

---

## Error Handling

Errors returned by the TinyURL API (both HTTP error codes and API JSON response codes) are mapped to a structured unchecked exception hierarchy under `com.tinyurl.exception`:

```
TinyUrlException (Unchecked base exception)
  └── TinyUrlApiException
        ├── TinyUrlAuthenticationException    (HTTP 401 / Code 1)
        ├── TinyUrlPermissionDeniedException  (HTTP 403, 405 / Code 3, 4)
        ├── TinyUrlNotFoundException          (HTTP 404 / Code 6)
        ├── TinyUrlValidationException        (HTTP 422 / Code 5)
        └── TinyUrlServerException            (HTTP 5xx / Code 7)
```

Example:

```java
import com.tinyurl.exception.*;

try {
    client.urls().create(CreateTinyUrlRequest.builder().url("https://example.com").alias("taken").build());
} catch (TinyUrlAuthenticationException e) {
    System.err.println("Invalid API Token! HTTP " + e.getStatusCode());
} catch (TinyUrlValidationException e) {
    System.err.println("Validation failed: " + e.getErrors());
} catch (TinyUrlNotFoundException e) {
    System.err.println("Alias or domain not found");
} catch (TinyUrlApiException e) {
    System.err.println("API error (" + e.getStatusCode() + "): " + e.getErrors());
} catch (TinyUrlException e) {
    System.err.println("Communication error: " + e.getMessage());
}
```

---

## Building & Testing

The repository uses Gradle wrapper. To build and execute all unit and integration tests:

```bash
# Build and run tests
./gradlew test

# Full build with JARs, sources, and Javadoc
./gradlew build
```

Built JAR artifacts will be located in `build/libs/`:
- `tinyurl-java-sdk-2.4.2.jar` (Library binary)
- `tinyurl-java-sdk-2.4.2-sources.jar` (Source code)
- `tinyurl-java-sdk-2.4.2-javadoc.jar` (Javadoc documentation)

---

## License

This SDK is distributed under the [Apache License, Version 2.0](LICENSE).
