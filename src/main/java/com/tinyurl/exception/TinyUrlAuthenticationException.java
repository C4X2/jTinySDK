package com.tinyurl.exception;

import java.util.List;

/**
 * Thrown when TinyURL API returns 401 Unauthorized or error code 1.
 */
public class TinyUrlAuthenticationException extends TinyUrlApiException {

    public TinyUrlAuthenticationException(int statusCode, Integer apiCode, List<String> errors, String rawResponseBody) {
        super(statusCode, apiCode, errors, rawResponseBody);
    }
}
