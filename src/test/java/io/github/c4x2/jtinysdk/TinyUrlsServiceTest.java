package io.github.c4x2.jtinysdk;

import io.github.c4x2.jtinysdk.model.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TinyUrlsServiceTest {

    private MockWebServer server;
    private TinyUrlClient client;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();

        client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("test-token-123")
                .build();
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void testShortenConvenienceMethod() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "domain": "tinyurl.com",
                        "alias": "abc1234",
                        "deleted": false,
                        "archived": false,
                        "tags": [],
                        "tiny_url": "https://tinyurl.com/abc1234",
                        "url": "https://example.com/long-page",
                        "created_at": "2024-01-01T00:00:00+00:00",
                        "expires_at": null
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        String result = client.shorten("https://example.com/long-page");

        assertThat(result).isEqualTo("https://tinyurl.com/abc1234");

        RecordedRequest request = server.takeRequest();
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/create");
        assertThat(request.getHeader("Authorization")).isEqualTo("Bearer test-token-123");
        assertThat(request.getBody().readUtf8()).contains("\"url\":\"https://example.com/long-page\"");
    }

    @Test
    void testCreateTinyUrlWithFullOptions() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "domain": "tinyurl.com",
                        "alias": "custom-alias",
                        "deleted": false,
                        "archived": false,
                        "tags": ["promo", "summer"],
                        "analytics": {
                            "enabled": true,
                            "public": false
                        },
                        "tiny_url": "https://tinyurl.com/custom-alias",
                        "url": "https://example.com/summer-sale",
                        "created_at": "2024-06-01T12:00:00+00:00",
                        "expires_at": "2024-12-31 23:59:59"
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        CreateTinyUrlRequest req = CreateTinyUrlRequest.builder()
                .url("https://example.com/summer-sale")
                .domain("tinyurl.com")
                .alias("custom-alias")
                .tags("promo", "summer")
                .expiresAt("2024-12-31 23:59:59")
                .description("Summer campaign")
                .build();

        UrlAliasResponse response = client.urls().create(req);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getAlias()).isEqualTo("custom-alias");
        assertThat(response.getData().getTinyUrl()).isEqualTo("https://tinyurl.com/custom-alias");
        assertThat(response.getData().getTags()).containsExactly("promo", "summer");
        assertThat(response.getData().getAnalytics().getEnabled()).isTrue();
    }

    @Test
    void testUpdateTinyUrl() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "domain": "tinyurl.com",
                        "alias": "new-alias",
                        "deleted": false,
                        "archived": false,
                        "tags": ["updated"],
                        "tiny_url": "https://tinyurl.com/new-alias",
                        "created_at": "2024-01-01T00:00:00+00:00"
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        UpdateTinyUrlRequest updateReq = UpdateTinyUrlRequest.builder()
                .domain("tinyurl.com")
                .alias("old-alias")
                .newAlias("new-alias")
                .newTags("updated")
                .newStats(true)
                .build();

        AliasResponse response = client.urls().update(updateReq);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getAlias()).isEqualTo("new-alias");

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("PATCH");
        assertThat(recorded.getPath()).isEqualTo("/update");
        assertThat(recorded.getBody().readUtf8()).contains("\"new_alias\":\"new-alias\"");
    }

    @Test
    void testChangeUrl() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "url": "https://newdestination.com"
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        UrlResponse response = client.urls().changeUrl("tinyurl.com", "myalias", "https://newdestination.com");

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getUrl()).isEqualTo("https://newdestination.com");

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("PATCH");
        assertThat(recorded.getPath()).isEqualTo("/change");
        assertThat(recorded.getBody().readUtf8()).contains("\"url\":\"https://newdestination.com\"");
    }

    @Test
    void testGetTinyUrlDetails() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "domain": "tinyurl.com",
                        "alias": "target-alias",
                        "deleted": false,
                        "archived": false,
                        "tags": ["cool"],
                        "analytics": {
                            "enabled": true,
                            "public": true
                        },
                        "tiny_url": "https://tinyurl.com/target-alias",
                        "url": "https://google.com",
                        "hits": 1024,
                        "user": {
                            "name": "Jane Doe",
                            "email": "jane@example.com"
                        },
                        "team": {
                            "name": "Marketing Team",
                            "slug": "marketing"
                        },
                        "created_at": "2024-01-01T00:00:00+00:00",
                        "expires_at": null
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        UrlAliasHitsBillableResponse response = client.urls().get("tinyurl.com", "target-alias");

        assertThat(response.isSuccess()).isTrue();
        UrlAliasHitsBillableData data = response.getData();
        assertThat(data.getAlias()).isEqualTo("target-alias");
        assertThat(data.getHits()).isEqualTo(1024);
        assertThat(data.getUser().getName()).isEqualTo("Jane Doe");
        assertThat(data.getTeam().getSlug()).isEqualTo("marketing");

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("GET");
        assertThat(recorded.getPath()).isEqualTo("/alias/tinyurl.com/target-alias");
    }

    @Test
    void testDeleteTinyUrl() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "domain": "tinyurl.com",
                        "alias": "delete-me",
                        "deleted": true,
                        "archived": false,
                        "tiny_url": "https://tinyurl.com/delete-me",
                        "url": "https://example.com"
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        UrlAliasResponse response = client.urls().delete("tinyurl.com", "delete-me");

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getDeleted()).isTrue();

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("DELETE");
        assertThat(recorded.getPath()).isEqualTo("/alias/tinyurl.com/delete-me");
    }

    @Test
    void testListTinyUrlsWithFilters() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "domain": "tinyurl.com",
                        "alias": "link1",
                        "deleted": false,
                        "archived": false,
                        "tags": ["test"],
                        "tiny_url": "https://tinyurl.com/link1"
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        GetUrlsListRequest listReq = GetUrlsListRequest.builder()
                .type(UrlsType.AVAILABLE)
                .from("2024-01-01 00:00:00")
                .to("2024-06-01 00:00:00")
                .searchByTag("campaign")
                .build();

        AliasResponse response = client.urls().list(listReq);

        assertThat(response.isSuccess()).isTrue();

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("GET");
        assertThat(recorded.getPath()).contains("/urls/available");
        assertThat(recorded.getPath()).contains("search=tag%3Acampaign");
    }

    @Test
    void testCountTinyUrls() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "count": 42
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        AliasesCountResponse response = client.urls().count(UrlsType.ARCHIVED);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getCount()).isEqualTo(42);

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("GET");
        assertThat(recorded.getPath()).isEqualTo("/urls/archived/count");
    }

    @Test
    void testArchiveAndAnalyticsStatusAndSharing() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "domain": "tinyurl.com",
                        "alias": "myalias",
                        "archived": true
                    }
                }
                """;

        server.enqueue(new MockResponse().setBody(mockResponseJson).setHeader("Content-Type", "application/json"));
        server.enqueue(new MockResponse().setBody(mockResponseJson).setHeader("Content-Type", "application/json"));
        server.enqueue(new MockResponse().setBody(mockResponseJson).setHeader("Content-Type", "application/json"));

        client.urls().archive("tinyurl.com", "myalias");
        RecordedRequest r1 = server.takeRequest();
        assertThat(r1.getPath()).isEqualTo("/archive");
        assertThat(r1.getMethod()).isEqualTo("PATCH");

        client.urls().updateAnalyticsStatus("tinyurl.com", "myalias", true);
        RecordedRequest r2 = server.takeRequest();
        assertThat(r2.getPath()).isEqualTo("/analytics/status");
        assertThat(r2.getMethod()).isEqualTo("PATCH");

        client.urls().updateAnalyticsSharing("tinyurl.com", "myalias", false);
        RecordedRequest r3 = server.takeRequest();
        assertThat(r3.getPath()).isEqualTo("/analytics/sharing");
        assertThat(r3.getMethod()).isEqualTo("PATCH");
    }
}

