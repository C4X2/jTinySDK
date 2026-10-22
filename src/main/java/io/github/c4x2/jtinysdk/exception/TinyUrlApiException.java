package io.github.c4x2.jtinysdk.exception;

import java.util.Collections;
import java.util.List;

/**
 * Exception thrown when the TinyURL API returns an error response (HTTP status >= 400 or code != 0).
 */
public class TinyUrlApiException extends TinyUrlException {

    private final int statusCode;
    private final Integer apiCode;
    private final List<String> errors;
    private final String rawResponseBody;

    public TinyUrlApiException(int statusCode, Integer apiCode, List<String> errors, String rawResponseBody) {
        super(buildMessage(statusCode, apiCode, errors, rawResponseBody));
        this.statusCode = statusCode;
        this.apiCode = apiCode;
        this.errors = errors != null ? Collections.unmodifiableList(errors) : Collections.emptyList();
        this.rawResponseBody = rawResponseBody;
    }

    private static String buildMessage(int statusCode, Integer apiCode, List<String> errors, String rawResponseBody) {
        StringBuilder sb = new StringBuilder();
        sb.append("TinyURL API error [HTTP ").append(statusCode);
        if (apiCode != null) {
            sb.append(", Code ").append(apiCode);
        }
        sb.append("]: ");
        if (errors != null && !errors.isEmpty()) {
            sb.append(String.join("; ", errors));
        } else if (rawResponseBody != null && !rawResponseBody.isBlank()) {
            sb.append(rawResponseBody);
        } else {
            sb.append("Unknown error");
        }
        return sb.toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Integer getApiCode() {
        return apiCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getRawResponseBody() {
        return rawResponseBody;
    }
}

