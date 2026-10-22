package com.tinyurl.auth;

import java.util.Optional;

public interface APIKeyLoader {
    Optional<String> loadAPIKey();
}
