package com.tinyurl.model.bulk;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Supported operations in a bulk request.
 */
public enum BulkOperation {
    CREATE("create"),
    UPDATE("update"),
    CHANGE("change"),
    DELETE("delete"),
    ARCHIVE("archive"),
    UNARCHIVE("unarchive");

    private final String value;

    BulkOperation(String value) {
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
