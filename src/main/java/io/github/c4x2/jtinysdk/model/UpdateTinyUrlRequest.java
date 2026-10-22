package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Request payload for updating TinyURL alias settings, tags, stats, or description.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateTinyUrlRequest {

    @JsonProperty("domain")
    private String domain = "tinyurl.com";

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("new_domain")
    private String newDomain;

    @JsonProperty("new_alias")
    private String newAlias;

    @JsonProperty("new_stats")
    private Boolean newStats;

    @JsonProperty("new_tags")
    private List<String> newTags;

    @JsonProperty("new_expires_at")
    private String newExpiresAt;

    @JsonProperty("new_description")
    private String newDescription;

    public UpdateTinyUrlRequest() {
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

    public String getNewDomain() {
        return newDomain;
    }

    public void setNewDomain(String newDomain) {
        this.newDomain = newDomain;
    }

    public String getNewAlias() {
        return newAlias;
    }

    public void setNewAlias(String newAlias) {
        this.newAlias = newAlias;
    }

    public Boolean getNewStats() {
        return newStats;
    }

    public void setNewStats(Boolean newStats) {
        this.newStats = newStats;
    }

    public List<String> getNewTags() {
        return newTags;
    }

    public void setNewTags(List<String> newTags) {
        this.newTags = newTags;
    }

    public String getNewExpiresAt() {
        return newExpiresAt;
    }

    public void setNewExpiresAt(String newExpiresAt) {
        this.newExpiresAt = newExpiresAt;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public static class Builder {
        private String domain = "tinyurl.com";
        private String alias;
        private String newDomain;
        private String newAlias;
        private Boolean newStats;
        private List<String> newTags;
        private String newExpiresAt;
        private String newDescription;

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder newDomain(String newDomain) {
            this.newDomain = newDomain;
            return this;
        }

        public Builder newAlias(String newAlias) {
            this.newAlias = newAlias;
            return this;
        }

        public Builder newStats(Boolean newStats) {
            this.newStats = newStats;
            return this;
        }

        public Builder newTags(List<String> newTags) {
            this.newTags = newTags;
            return this;
        }

        public Builder newTags(String... newTags) {
            this.newTags = new ArrayList<>(Arrays.asList(newTags));
            return this;
        }

        public Builder newExpiresAt(String newExpiresAt) {
            this.newExpiresAt = newExpiresAt;
            return this;
        }

        public Builder newDescription(String newDescription) {
            this.newDescription = newDescription;
            return this;
        }

        public UpdateTinyUrlRequest build() {
            if (domain == null || domain.isBlank()) {
                throw new IllegalArgumentException("domain must not be null or blank");
            }
            if (alias == null || alias.isBlank()) {
                throw new IllegalArgumentException("alias must not be null or blank");
            }
            UpdateTinyUrlRequest req = new UpdateTinyUrlRequest();
            req.setDomain(domain);
            req.setAlias(alias);
            req.setNewDomain(newDomain);
            req.setNewAlias(newAlias);
            req.setNewStats(newStats);
            req.setNewTags(newTags);
            req.setNewExpiresAt(newExpiresAt);
            req.setNewDescription(newDescription);
            return req;
        }
    }
}

