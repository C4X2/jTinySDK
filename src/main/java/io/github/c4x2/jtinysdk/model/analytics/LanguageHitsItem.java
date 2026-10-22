package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Language code and its hits count.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LanguageHitsItem {

    @JsonProperty("language")
    private String language;

    @JsonProperty("hits")
    private Integer hits;

    public LanguageHitsItem() {
    }

    public LanguageHitsItem(String language, Integer hits) {
        this.language = language;
        this.hits = hits;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        LanguageHitsItem that = (LanguageHitsItem) o;
        return Objects.equals(language, that.language) && Objects.equals(hits, that.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, hits);
    }

    @Override
    public String toString() {
        return "LanguageHitsItem{" +
                "language='" + language + '\'' +
                ", hits=" + hits +
                '}';
    }
}

