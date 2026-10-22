package com.tinyurl.exception;

import java.util.List;

/**
 * Thrown when TinyURL API returns 403 / 405 Permission Denied or error code 3/4.
 */
public class TinyUrlPermissionDeniedException extends TinyUrlApiException {

    public TinyUrlPermissionDeniedException(int statusCode, Integer apiCode, List<String> errors, String rawResponseBody) {
        super(statusCode, apiCode, errors, rawResponseBody);
    }
}
