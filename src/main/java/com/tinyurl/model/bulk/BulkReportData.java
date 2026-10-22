package com.tinyurl.model.bulk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Report entry for a single item processed within a bulk request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkReportData {

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("domain")
    private String domain;

    @JsonProperty("alias")
    private String alias;

    @JsonProperty("url")
    private String url;

    @JsonProperty("tinyurl")
    private String tinyurl;

    @JsonProperty("metadata")
    private List<Object> metadata;

    @JsonProperty("status")
    private String status;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("comment")
    private String comment;

    public BulkReportData() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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

    public String getTinyurl() {
        return tinyurl;
    }

    public void setTinyurl(String tinyurl) {
        this.tinyurl = tinyurl;
    }

    public List<Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Object> metadata) {
        this.metadata = metadata;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSuccessful() {
        return "success".equalsIgnoreCase(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BulkReportData that = (BulkReportData) o;
        return Objects.equals(operation, that.operation) &&
                Objects.equals(domain, that.domain) &&
                Objects.equals(alias, that.alias) &&
                Objects.equals(url, that.url) &&
                Objects.equals(tinyurl, that.tinyurl) &&
                Objects.equals(metadata, that.metadata) &&
                Objects.equals(status, that.status) &&
                Objects.equals(expiresAt, that.expiresAt) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operation, domain, alias, url, tinyurl, metadata, status, expiresAt, comment);
    }

    @Override
    public String toString() {
        return "BulkReportData{" +
                "operation='" + operation + '\'' +
                ", domain='" + domain + '\'' +
                ", alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                ", tinyurl='" + tinyurl + '\'' +
                ", metadata=" + metadata +
                ", status='" + status + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
