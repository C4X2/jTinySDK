package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Source referrer and its hits count.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceHitsItem {

    @JsonProperty("source")
    private String source;

    @JsonProperty("hits")
    private Integer hits;

    public SourceHitsItem() {
    }

    public SourceHitsItem(String source, Integer hits) {
        this.source = source;
        this.hits = hits;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceHitsItem that = (SourceHitsItem) o;
        return Objects.equals(source, that.source) && Objects.equals(hits, that.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, hits);
    }

    @Override
    public String toString() {
        return "SourceHitsItem{" +
                "source='" + source + '\'' +
                ", hits=" + hits +
                '}';
    }
}
