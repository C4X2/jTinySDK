package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Long URL container.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlData {

    @JsonProperty("url")
    private String url;

    public UrlData() {
    }

    public UrlData(String url) {
        this.url = url;
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
        UrlData urlData = (UrlData) o;
        return Objects.equals(url, urlData.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "UrlData{" +
                "url='" + url + '\'' +
                '}';
    }
}
