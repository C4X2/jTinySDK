package io.github.c4x2.jtinysdk.auth;

import java.util.Optional;

public class EnvironmentAPIKeyLoader implements APIKeyLoader {

    private static final String TINY_URL_API_TOKEN = "TINYURL_API_TOKEN";

    @Override
    public Optional<String> loadAPIKey() {
        return Optional.ofNullable(System.getenv().get(TINY_URL_API_TOKEN));
    }
}

