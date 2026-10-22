package com.tinyurl;

import com.tinyurl.model.bulk.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BulkServiceTest {

    private MockWebServer server;
    private TinyUrlClient client;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();

        client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("bulk-token")
                .build();
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void testCreateBulkRequest() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "id": 98765,
                        "status": 0,
                        "created_at": "2024-01-01 10:00:00",
                        "updated_at": "2024-01-01 10:00:00",
                        "total": 3,
                        "success": 0,
                        "failed": 0
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        BulkStatusResponse response = client.bulk().create("tinyurl.com", List.of(
                BulkRequestItem.create("https://example.com/1"),
                BulkRequestItem.create("https://example.com/2"),
                BulkRequestItem.archive("tinyurl.com", "old-link")
        ));

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().getId()).isEqualTo(98765L);
        assertThat(response.getData().isNew()).isTrue();
        assertThat(response.getData().getTotal()).isEqualTo(3);

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("POST");
        assertThat(recorded.getPath()).isEqualTo("/bulk");
        assertThat(recorded.getBody().readUtf8()).contains("\"operation\":\"archive\"");
    }

    @Test
    void testGetBulkStatus() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "id": 98765,
                        "status": 2,
                        "created_at": "2024-01-01 10:00:00",
                        "updated_at": "2024-01-01 10:05:00",
                        "total": 10,
                        "success": 9,
                        "failed": 1
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        BulkStatusResponse response = client.bulk().getStatus(98765L);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData().isProcessed()).isTrue();
        assertThat(response.getData().getSuccess()).isEqualTo(9);
        assertThat(response.getData().getFailed()).isEqualTo(1);

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("GET");
        assertThat(recorded.getPath()).isEqualTo("/bulk/98765/status");
    }

    @Test
    void testGetBulkReport() throws InterruptedException {
        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": [
                        {
                            "operation": "create",
                            "domain": "tinyurl.com",
                            "alias": "batch1",
                            "url": "https://google.com",
                            "tinyurl": "https://tinyurl.com/batch1",
                            "status": "success",
                            "comment": ""
                        },
                        {
                            "operation": "create",
                            "domain": "tinyurl.com",
                            "alias": "batch2",
                            "url": "invalid-url",
                            "status": "failed",
                            "comment": "Invalid target URL"
                        }
                    ]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        BulkReportResponse response = client.bulk().getReport(98765L);

        assertThat(response.isSuccess()).isTrue();
        List<BulkReportData> items = response.getData();
        assertThat(items).hasSize(2);
        assertThat(items.get(0).isSuccessful()).isTrue();
        assertThat(items.get(0).getTinyurl()).isEqualTo("https://tinyurl.com/batch1");
        assertThat(items.get(1).isSuccessful()).isFalse();
        assertThat(items.get(1).getComment()).isEqualTo("Invalid target URL");

        RecordedRequest recorded = server.takeRequest();
        assertThat(recorded.getMethod()).isEqualTo("GET");
        assertThat(recorded.getPath()).isEqualTo("/bulk/98765/report");
    }
}
