package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * TinyURL alias information.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasData {

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("deleted")
    private Boolean deleted;

    @JsonProperty("archived")
    private Boolean archived;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("analytics")
    private AnalyticsSettings analytics;

    @JsonProperty("tiny_url")
    private String tinyUrl;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    public AliasData() {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public AnalyticsSettings getAnalytics() {
        return analytics;
    }

    public void setAnalytics(AnalyticsSettings analytics) {
        this.analytics = analytics;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AliasData aliasData = (AliasData) o;
        return Objects.equals(domain, aliasData.domain) &&
                Objects.equals(alias, aliasData.alias) &&
                Objects.equals(deleted, aliasData.deleted) &&
                Objects.equals(archived, aliasData.archived) &&
                Objects.equals(tags, aliasData.tags) &&
                Objects.equals(analytics, aliasData.analytics) &&
                Objects.equals(tinyUrl, aliasData.tinyUrl) &&
                Objects.equals(createdAt, aliasData.createdAt) &&
                Objects.equals(expiresAt, aliasData.expiresAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, alias, deleted, archived, tags, analytics, tinyUrl, createdAt, expiresAt);
    }

    @Override
    public String toString() {
        return "AliasData{" +
                "domain='" + domain + '\'' +
                ", alias='" + alias + '\'' +
                ", deleted=" + deleted +
                ", archived=" + archived +
                ", tags=" + tags +
                ", analytics=" + analytics +
                ", tinyUrl='" + tinyUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                '}';
    }
}
