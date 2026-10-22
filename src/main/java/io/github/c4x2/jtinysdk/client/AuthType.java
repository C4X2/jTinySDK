package io.github.c4x2.jtinysdk.client;

/**
 * Authentication mechanism to use with the TinyURL API.
 */
public enum AuthType {
    /**
     * HTTP Bearer token in the 'Authorization' header: "Bearer {token}". (Default and recommended)
     */
    BEARER,

    /**
     * API token passed as query parameter: "?api_token={token}".
     */
    QUERY_PARAM
}

