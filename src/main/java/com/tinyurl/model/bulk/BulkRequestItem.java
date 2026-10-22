package com.tinyurl.model.bulk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An item in a bulk request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkRequestItem {

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("metadata")
    private List<Object> metadata;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("url")
    private String url;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("description")
    private String description;

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

    public BulkRequestItem() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static BulkRequestItem create(String url) {
        return builder().operation(BulkOperation.CREATE).url(url).build();
    }

    public static BulkRequestItem create(String url, String domain, String alias) {
        return builder().operation(BulkOperation.CREATE).url(url).domain(domain).alias(alias).build();
    }

    public static BulkRequestItem change(String domain, String alias, String newUrl) {
        return builder().operation(BulkOperation.CHANGE).domain(domain).alias(alias).url(newUrl).build();
    }

    public static BulkRequestItem delete(String domain, String alias) {
        return builder().operation(BulkOperation.DELETE).domain(domain).alias(alias).build();
    }

    public static BulkRequestItem archive(String domain, String alias) {
        return builder().operation(BulkOperation.ARCHIVE).domain(domain).alias(alias).build();
    }

    public static BulkRequestItem unarchive(String domain, String alias) {
        return builder().operation(BulkOperation.UNARCHIVE).domain(domain).alias(alias).build();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Object> metadata) {
        this.metadata = metadata;
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
        private String operation;
        private List<Object> metadata;
        private String domain;
        private String alias;
        private String url;
        private List<String> tags;
        private String expiresAt;
        private String description;
        private String newDomain;
        private String newAlias;
        private Boolean newStats;
        private List<String> newTags;
        private String newExpiresAt;
        private String newDescription;

        public Builder operation(BulkOperation operation) {
            this.operation = operation != null ? operation.getValue() : null;
            return this;
        }

        public Builder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder metadata(List<Object> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder metadata(Object... metadata) {
            this.metadata = new ArrayList<>(Arrays.asList(metadata));
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

        public Builder url(String url) {
            this.url = url;
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

        public BulkRequestItem build() {
            if (operation == null || operation.isBlank()) {
                throw new IllegalArgumentException("operation must not be null or blank");
            }
            BulkRequestItem item = new BulkRequestItem();
            item.setOperation(operation);
            item.setMetadata(metadata);
            item.setDomain(domain);
            item.setAlias(alias);
            item.setUrl(url);
            item.setTags(tags);
            item.setExpiresAt(expiresAt);
            item.setDescription(description);
            item.setNewDomain(newDomain);
            item.setNewAlias(newAlias);
            item.setNewStats(newStats);
            item.setNewTags(newTags);
            item.setNewExpiresAt(newExpiresAt);
            item.setNewDescription(newDescription);
            return item;
        }
    }
}
