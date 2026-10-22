package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Data object representing a TinyURL alias along with its target long URL.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlAliasData extends AliasData {

    @JsonProperty("url")
    private String url;

    public UrlAliasData() {
        super();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UrlAliasData that = (UrlAliasData) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), url);
    }

    @Override
    public String toString() {
        return "UrlAliasData{" +
                "url='" + url + '\'' +
                ", domain='" + getDomain() + '\'' +
                ", alias='" + getAlias() + '\'' +
                ", tinyUrl='" + getTinyUrl() + '\'' +
                ", deleted=" + getDeleted() +
                ", archived=" + getArchived() +
                ", tags=" + getTags() +
                ", analytics=" + getAnalytics() +
                ", createdAt='" + getCreatedAt() + '\'' +
                ", expiresAt='" + getExpiresAt() + '\'' +
                '}';
    }
}
