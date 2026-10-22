package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Request payload for creating a new TinyURL.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTinyUrlRequest {

    @JsonProperty("url")
    private String url;

    @JsonProperty("domain")
    private String domain = "tinyurl.com";

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("description")
    private String description;

    public CreateTinyUrlRequest() {
    }

    public CreateTinyUrlRequest(String url) {
        this.url = url;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private String url;
        private String domain = "tinyurl.com";
        private String alias;
        private List<String> tags;
        private String expiresAt;
        private String description;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder tags(String... tags) {
            this.tags = new ArrayList<>(Arrays.asList(tags));
            return this;
        }

        public Builder expiresAt(String expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public CreateTinyUrlRequest build() {
            if (url == null || url.isBlank()) {
                throw new IllegalArgumentException("url must not be null or blank");
            }
            CreateTinyUrlRequest req = new CreateTinyUrlRequest();
            req.setUrl(url);
            req.setDomain(domain != null ? domain : "tinyurl.com");
            req.setAlias(alias);
            req.setTags(tags);
            req.setExpiresAt(expiresAt);
            req.setDescription(description);
            return req;
        }
    }
}
