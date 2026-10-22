package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Filter type for aliases listing and counting: "available" or "archived".
 */
public enum UrlsType {
    AVAILABLE("available"),
    ARCHIVED("archived");

    private final String value;

    UrlsType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
