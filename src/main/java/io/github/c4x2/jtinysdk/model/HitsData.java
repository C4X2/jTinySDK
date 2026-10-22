package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Click hits count data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HitsData {

    @JsonProperty("hits")
    private Integer hits;

    public HitsData() {
    }

    public HitsData(Integer hits) {
        this.hits = hits;
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
        HitsData hitsData = (HitsData) o;
        return Objects.equals(hits, hitsData.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hits);
    }

    @Override
    public String toString() {
        return "HitsData{" +
                "hits=" + hits +
                '}';
    }
}

