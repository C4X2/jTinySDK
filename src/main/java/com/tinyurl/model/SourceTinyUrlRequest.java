package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request payload identifying a source TinyURL alias (e.g. for archive/unarchive operations).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceTinyUrlRequest {

    @JsonProperty("domain")
    private String domain = "tinyurl.com";

    @JsonProperty("alias")
    private String alias;

    public SourceTinyUrlRequest() {
    }

    public SourceTinyUrlRequest(String domain, String alias) {
        this.domain = domain;
        this.alias = alias;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static class Builder {
        private String domain = "tinyurl.com";
        private String alias;

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public SourceTinyUrlRequest build() {
            if (domain == null || domain.isBlank()) {
                throw new IllegalArgumentException("domain must not be null or blank");
            }
            if (alias == null || alias.isBlank()) {
                throw new IllegalArgumentException("alias must not be null or blank");
            }
            return new SourceTinyUrlRequest(domain, alias);
        }
    }
}
