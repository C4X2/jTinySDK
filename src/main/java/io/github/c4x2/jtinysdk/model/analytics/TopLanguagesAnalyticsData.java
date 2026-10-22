package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Data container for top languages analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopLanguagesAnalyticsData {

    @JsonProperty("dataset")
    private List<LanguageHitsItem> dataset;

    @JsonProperty("none")
    private Integer none;

    @JsonProperty("total")
    private Integer total;

    public TopLanguagesAnalyticsData() {
    }

    public List<LanguageHitsItem> getDataset() {
        return dataset;
    }

    public void setDataset(List<LanguageHitsItem> dataset) {
        this.dataset = dataset;
    }

    public Integer getNone() {
        return none;
    }

    public void setNone(Integer none) {
        this.none = none;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopLanguagesAnalyticsData that = (TopLanguagesAnalyticsData) o;
        return Objects.equals(dataset, that.dataset) &&
                Objects.equals(none, that.none) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataset, none, total);
    }

    @Override
    public String toString() {
        return "TopLanguagesAnalyticsData{" +
                "dataset=" + dataset +
                ", none=" + none +
                ", total=" + total +
                '}';
    }
}

