package io.github.c4x2.jtinysdk;

import io.github.c4x2.jtinysdk.model.analytics.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class AnalyticsServiceTest {

    private MockWebServer server;
    private TinyUrlClient client;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();

        client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("analytics-token")
                .build();
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void testGetRawLogs() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": [
                        {
                            "datetime": "2024-01-01 12:00:00",
                            "tiny_url": "https://tinyurl.com/link1",
                            "url": "https://google.com",
                            "referer": "https://facebook.com",
                            "ip": "1.2.3.4",
                            "country": "US",
                            "is_bot": false,
                            "browser": {
                                "name": "Chrome",
                                "version": "120.0"
                            },
                            "os": {
                                "name": "Windows",
                                "version": "11"
                            },
                            "device": "desktop",
                            "language": "en",
                            "tags": ["social"]
                        }
                    ]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        AnalyticsFilter filter = AnalyticsFilter.builder()
                .from("2024-01-01")
                .alias("link1")
                .build();

        RawLogsAnalyticsResponse response = client.analytics().getRawLogs(filter);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).hasSize(1);
        RawLogsAnalyticsData log = response.getData().get(0);
        assertThat(log.getIp()).isEqualTo("1.2.3.4");
        assertThat(log.getCountry()).isEqualTo("US");
        assertThat(log.getBrowser().getName()).isEqualTo("Chrome");
        assertThat(log.getOs().getName()).isEqualTo("Windows");
        assertThat(log.getIsBot()).isFalse();

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("GET");
        assertThat(recorded.getPath()).contains("/analytics/raw/json");
        assertThat(recorded.getPath()).contains("from=2024-01-01");
        assertThat(recorded.getPath()).contains("alias=link1");
    }

    @Test
    void testGetTimeline() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "dataset": [
                            {
                                "datetime": "2024-01-01 00:00:00",
                                "total": 100,
                                "unique": 80,
                                "human": 95
                            }
                        ],
                        "interval": "day",
                        "timezone": "-05:00"
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        AnalyticsFilter filter = AnalyticsFilter.builder().from("2024-01-01").build();
        TimelineAnalyticsResponse response = client.analytics().getTimeline(filter, TimelineInterval.DAY);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getInterval()).isEqualTo("day");
        assertThat(response.getData().getDataset().get(0).getTotal()).isEqualTo(100);

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("GET");
        assertThat(recorded.getPath()).contains("/analytics/timeline");
        assertThat(recorded.getPath()).contains("interval=day");
    }

    @Test
    void testGetGeneral() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "dataset": [
                            {
                                "browser": [
                                    {
                                        "name": "Chrome",
                                        "value": 50,
                                        "children": [{"name": "120", "value": 50}]
                                    }
                                ],
                                "os": [
                                    {
                                        "name": "Windows",
                                        "value": 40,
                                        "children": []
                                    }
                                ],
                                "device": [
                                    {"name": "Desktop", "value": 70}
                                ]
                            }
                        ],
                        "total": 100
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        AnalyticsFilter filter = AnalyticsFilter.builder().from("2024-01-01").build();
        GeneralAnalyticsResponse response = client.analytics().getGeneral(filter);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getTotal()).isEqualTo(100);
        assertThat(response.getData().getDataset().get(0).getBrowser().get(0).getName()).isEqualTo("Chrome");
    }

    @Test
    void testWeekdaysAndHoursPopularity() throws InterruptedException {
        String mockWeekdaysJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "dataset": [10, 20, 30, 40, 50, 60, 70]
                    }
                }
                """;
        String mockHoursJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "dataset": [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]
                    }
                }
                """;

        server.enqueue(new MockResponse().setBody(mockWeekdaysJson).setHeader("Content-Type", "application/json"));
        server.enqueue(new MockResponse().setBody(mockHoursJson).setHeader("Content-Type", "application/json"));

        AnalyticsFilter filter = AnalyticsFilter.builder().from("2024-01-01").build();

        WeekdaysPopularityAnalyticsResponse wRes = client.analytics().getWeekdaysPopularity(filter);
        assertThat(wRes.getData().getDataset()).containsExactly(10, 20, 30, 40, 50, 60, 70);

        HoursPopularityAnalyticsResponse hRes = client.analytics().getHoursPopularity(filter);
        assertThat(hRes.getData().getDataset()).hasSize(24);
    }

    @Test
    void testTopSourcesLanguagesAndLocation() throws InterruptedException {
        String mockSourcesJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "dataset": [{"source": "google.com", "hits": 100}],
                        "direct": 20,
                        "total": 120
                    }
                }
                """;
        String mockLanguagesJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "dataset": [{"language": "en", "hits": 90}],
                        "none": 10,
                        "total": 100
                    }
                }
                """;
        String mockLocationJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "dataset": [{"name": "US", "value": 85}],
                        "none": 5,
                        "total": 90
                    }
                }
                """;

        server.enqueue(new MockResponse().setBody(mockSourcesJson).setHeader("Content-Type", "application/json"));
        server.enqueue(new MockResponse().setBody(mockLanguagesJson).setHeader("Content-Type", "application/json"));
        server.enqueue(new MockResponse().setBody(mockLocationJson).setHeader("Content-Type", "application/json"));

        AnalyticsFilter filter = AnalyticsFilter.builder().from("2024-01-01").build();

        TopSourcesAnalyticsResponse sRes = client.analytics().getTopSources(filter);
        assertThat(sRes.getData().getDataset().get(0).getSource()).isEqualTo("google.com");

        TopLanguagesAnalyticsResponse lRes = client.analytics().getTopLanguages(filter);
        assertThat(lRes.getData().getDataset().get(0).getLanguage()).isEqualTo("en");

        LocationAnalyticsResponse locRes = client.analytics().getLocation("US", filter);
        assertThat(locRes.getData().getDataset().get(0).getName()).isEqualTo("US");

        RecordedRequest r3 = server.takeRequest(); // 1
        RecordedRequest r4 = server.takeRequest(); // 2
        RecordedRequest r5 = server.takeRequest(); // 3
        assertThat(r5.getPath()).contains("/analytics/location/US");
    }
}

