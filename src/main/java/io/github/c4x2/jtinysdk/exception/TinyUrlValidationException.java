package io.github.c4x2.jtinysdk.exception;

import java.util.List;

/**
 * Thrown when TinyURL API returns 422 Unprocessable Entity or validation failure code 5.
 */
public class TinyUrlValidationException extends TinyUrlApiException {

    public TinyUrlValidationException(int statusCode, Integer apiCode, List<String> errors, String rawResponseBody) {
        super(statusCode, apiCode, errors, rawResponseBody);
    }
}

