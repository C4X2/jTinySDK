package com.tinyurl.exception;

import java.util.List;

/**
 * Thrown when TinyURL API returns 5xx server error or error code 7.
 */
public class TinyUrlServerException extends TinyUrlApiException {

    public TinyUrlServerException(int statusCode, Integer apiCode, List<String> errors, String rawResponseBody) {
        super(statusCode, apiCode, errors, rawResponseBody);
    }
}
