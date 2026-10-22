package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request payload for changing the destination long URL of an existing TinyURL.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUrlRequest {

    @JsonProperty("domain")
    private String domain = "tinyurl.com";

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("url")
    private String url;

    public UpdateUrlRequest() {
    }

    public UpdateUrlRequest(String domain, String alias, String url) {
        this.domain = domain;
        this.alias = alias;
        this.url = url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class Builder {
        private String domain = "tinyurl.com";
        private String alias;
        private String url;

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public UpdateUrlRequest build() {
            if (domain == null || domain.isBlank()) {
                throw new IllegalArgumentException("domain must not be null or blank");
            }
            if (alias == null || alias.isBlank()) {
                throw new IllegalArgumentException("alias must not be null or blank");
            }
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("url must not be null or blank");
            }
            return new UpdateUrlRequest(domain, alias, url);
        }
    }
}

