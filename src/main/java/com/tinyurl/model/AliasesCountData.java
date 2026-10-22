package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Data container for TinyURL aliases count.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasesCountData {

    @JsonProperty("count")
    private Integer count;

    public AliasesCountData() {
    }

    public AliasesCountData(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AliasesCountData that = (AliasesCountData) o;
        return Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    @Override
    public String toString() {
        return "AliasesCountData{" +
                "count=" + count +
                '}';
    }
}
