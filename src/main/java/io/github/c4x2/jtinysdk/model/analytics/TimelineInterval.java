package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Supported timeline intervals for analytics timeline requests.
 */
public enum TimelineInterval {
    MINUTE("minute"),
    HOUR("hour"),
    DAY("day"),
    WEEK("week"),
    MONTH("month");

    private final String value;

    TimelineInterval(String value) {
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

