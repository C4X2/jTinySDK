package io.github.c4x2.jtinysdk.auth;

import java.util.Optional;

public interface APIKeyLoader {
    Optional<String> loadAPIKey();
}

