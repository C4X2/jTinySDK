package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request payload for enabling or disabling TinyURL public analytics sharing.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateAnalyticsSharingRequest {

    @JsonProperty("domain")
    private String domain = "tinyurl.com";

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("public")
    private Boolean isPublic;

    public UpdateAnalyticsSharingRequest() {
    }

    public UpdateAnalyticsSharingRequest(String domain, String alias, Boolean isPublic) {
        this.domain = domain;
        this.alias = alias;
        this.isPublic = isPublic;
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

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public static class Builder {
        private String domain = "tinyurl.com";
        private String alias;
        private Boolean isPublic;

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder isPublic(Boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public UpdateAnalyticsSharingRequest build() {
            if (domain == null || domain.isBlank()) {
                throw new IllegalArgumentException("domain must not be null or blank");
            }
            if (alias == null || alias.isBlank()) {
                throw new IllegalArgumentException("alias must not be null or blank");
            }
            if (isPublic == null) {
                throw new IllegalArgumentException("isPublic must not be null");
            }
            return new UpdateAnalyticsSharingRequest(domain, alias, isPublic);
        }
    }
}
