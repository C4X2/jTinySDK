package com.tinyurl.exception;

import java.util.List;

/**
 * Thrown when TinyURL API returns 404 Not Found or error code 6.
 */
public class TinyUrlNotFoundException extends TinyUrlApiException {

    public TinyUrlNotFoundException(int statusCode, Integer apiCode, List<String> errors, String rawResponseBody) {
        super(statusCode, apiCode, errors, rawResponseBody);
    }
}
