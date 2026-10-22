package io.github.c4x2.jtinysdk.model.analytics;

import java.util.Objects;

/**
 * Filter parameters for querying TinyURL analytics endpoints.
 */
public class AnalyticsFilter {

    private String from;
    private String to;
    private String alias;
    private String tag;

    public AnalyticsFilter() {
    }

    public AnalyticsFilter(String from) {
        this.from = from;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static class Builder {
        private String from;
        private String to;
        private String alias;
        private String tag;

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder to(String to) {
            this.to = to;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public AnalyticsFilter build() {
            if (from == null || from.isBlank()) {
                throw new IllegalArgumentException("from must not be null or blank");
            }
            AnalyticsFilter filter = new AnalyticsFilter();
            filter.setFrom(from);
            filter.setTo(to);
            filter.setAlias(alias);
            filter.setTag(tag);
            return filter;
        }
    }
}

