package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request payload for enabling or disabling TinyURL analytics collecting.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateAnalyticsStatusRequest {

    @JsonProperty("domain")
    private String domain = "tinyurl.com";

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("enabled")
    private Boolean enabled;

    public UpdateAnalyticsStatusRequest() {
    }

    public UpdateAnalyticsStatusRequest(String domain, String alias, Boolean enabled) {
        this.domain = domain;
        this.alias = alias;
        this.enabled = enabled;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public static class Builder {
        private String domain = "tinyurl.com";
        private String alias;
        private Boolean enabled;

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UpdateAnalyticsStatusRequest build() {
            if (domain == null || domain.isBlank()) {
                throw new IllegalArgumentException("domain must not be null or blank");
            }
            if (alias == null || alias.isBlank()) {
                throw new IllegalArgumentException("alias must not be null or blank");
            }
            if (enabled == null) {
                throw new IllegalArgumentException("enabled must not be null");
            }
            return new UpdateAnalyticsStatusRequest(domain, alias, enabled);
        }
    }
}

