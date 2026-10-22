package com.tinyurl;

import com.tinyurl.client.AuthType;
import com.tinyurl.exception.*;
import com.tinyurl.model.CreateTinyUrlRequest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuthAndErrorHandlingTest {

    private MockWebServer server;

    @BeforeEach
    void setUp() throws IOException {
        server = new MockWebServer();
        server.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void testQueryParamAuth() throws InterruptedException {
        TinyUrlClient queryAuthClient = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("query-secret-token")
                .authType(AuthType.QUERY_PARAM)
                .build();

        String mockResponseJson = """
                {
                    "code": 0,
                    "errors": [],
                    "data": {
                        "tiny_url": "https://tinyurl.com/xyz",
                        "url": "https://example.com"
                    }
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(mockResponseJson));

        queryAuthClient.shorten("https://example.com");

        RecordedRequest request = server.takeRequest();
        assertThat(request.getHeader("Authorization")).isNull();
        assertThat(request.getPath()).contains("api_token=query-secret-token");
    }

    @Test
    void test401UnauthorizedThrowsAuthenticationException() {
        TinyUrlClient client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("invalid-token")
                .build();

        String errorJson = """
                {
                    "code": 1,
                    "data": [],
                    "errors": ["Unauthorized"]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setHeader("Content-Type", "application/json")
                .setBody(errorJson));

        assertThatThrownBy(() -> client.shorten("https://example.com"))
                .isInstanceOf(TinyUrlAuthenticationException.class)
                .satisfies(e -> {
                    TinyUrlAuthenticationException authEx = (TinyUrlAuthenticationException) e;
                    assertThat(authEx.getStatusCode()).isEqualTo(401);
                    assertThat(authEx.getApiCode()).isEqualTo(1);
                    assertThat(authEx.getErrors()).containsExactly("Unauthorized");
                });
    }

    @Test
    void test405PermissionDeniedThrowsPermissionDeniedException() {
        TinyUrlClient client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("token")
                .build();

        String errorJson = """
                {
                    "code": 4,
                    "data": [],
                    "errors": ["Method not allowed"]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(405)
                .setHeader("Content-Type", "application/json")
                .setBody(errorJson));

        assertThatThrownBy(() -> client.shorten("https://example.com"))
                .isInstanceOf(TinyUrlPermissionDeniedException.class)
                .satisfies(e -> {
                    TinyUrlPermissionDeniedException ex = (TinyUrlPermissionDeniedException) e;
                    assertThat(ex.getStatusCode()).isEqualTo(405);
                    assertThat(ex.getApiCode()).isEqualTo(4);
                });
    }

    @Test
    void test404NotFoundThrowsNotFoundException() {
        TinyUrlClient client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("token")
                .build();

        String errorJson = """
                {
                    "code": 6,
                    "data": [],
                    "errors": ["Resource not found"]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setHeader("Content-Type", "application/json")
                .setBody(errorJson));

        assertThatThrownBy(() -> client.urls().get("tinyurl.com", "unknown-alias"))
                .isInstanceOf(TinyUrlNotFoundException.class)
                .satisfies(e -> {
                    TinyUrlNotFoundException ex = (TinyUrlNotFoundException) e;
                    assertThat(ex.getStatusCode()).isEqualTo(404);
                    assertThat(ex.getApiCode()).isEqualTo(6);
                });
    }

    @Test
    void test422UnprocessableEntityThrowsValidationException() {
        TinyUrlClient client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("token")
                .build();

        String errorJson = """
                {
                    "code": 5,
                    "data": [],
                    "errors": ["Invalid URL", "Alias field is too long"]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(422)
                .setHeader("Content-Type", "application/json")
                .setBody(errorJson));

        assertThatThrownBy(() -> client.shorten("invalid-url"))
                .isInstanceOf(TinyUrlValidationException.class)
                .satisfies(e -> {
                    TinyUrlValidationException ex = (TinyUrlValidationException) e;
                    assertThat(ex.getStatusCode()).isEqualTo(422);
                    assertThat(ex.getApiCode()).isEqualTo(5);
                    assertThat(ex.getErrors()).contains("Invalid URL", "Alias field is too long");
                });
    }

    @Test
    void test500ServerErrorThrowsServerException() {
        TinyUrlClient client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("token")
                .build();

        String errorJson = """
                {
                    "code": 7,
                    "data": [],
                    "errors": ["Something went wrong"]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(500)
                .setHeader("Content-Type", "application/json")
                .setBody(errorJson));

        assertThatThrownBy(() -> client.shorten("https://example.com"))
                .isInstanceOf(TinyUrlServerException.class)
                .satisfies(e -> {
                    TinyUrlServerException ex = (TinyUrlServerException) e;
                    assertThat(ex.getStatusCode()).isEqualTo(500);
                    assertThat(ex.getApiCode()).isEqualTo(7);
                });
    }

    @Test
    void testAsyncExecutionAndErrorHandling() {
        TinyUrlClient client = TinyUrlClient.builder()
                .baseUrl(server.url("/").toString())
                .apiToken("token")
                .build();

        String errorJson = """
                {
                    "code": 1,
                    "errors": ["Unauthorized"]
                }
                """;

        server.enqueue(new MockResponse()
                .setResponseCode(401)
                .setHeader("Content-Type", "application/json")
                .setBody(errorJson));

        CompletableFuture<?> future = client.urls().createAsync(CreateTinyUrlRequest.builder().url("https://example.com").build());

        assertThatThrownBy(future::get)
                .isInstanceOf(ExecutionException.class)
                .hasCauseInstanceOf(TinyUrlAuthenticationException.class);
    }
}
