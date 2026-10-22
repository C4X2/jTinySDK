package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Data container for general browser, OS, and device analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralAnalyticsData {

    @JsonProperty("dataset")
    private List<GeneralAnalyticsItem> dataset;

    @JsonProperty("total")
    private Integer total;

    public GeneralAnalyticsData() {
    }

    public List<GeneralAnalyticsItem> getDataset() {
        return dataset;
    }

    public void setDataset(List<GeneralAnalyticsItem> dataset) {
        this.dataset = dataset;
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
        GeneralAnalyticsData that = (GeneralAnalyticsData) o;
        return Objects.equals(dataset, that.dataset) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataset, total);
    }

    @Override
    public String toString() {
        return "GeneralAnalyticsData{" +
                "dataset=" + dataset +
                ", total=" + total +
                '}';
    }
}
