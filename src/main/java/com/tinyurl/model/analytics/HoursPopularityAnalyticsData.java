package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Data container for hour popularity analytics (24 hours).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HoursPopularityAnalyticsData {

    @JsonProperty("dataset")
    private List<Integer> dataset;

    public HoursPopularityAnalyticsData() {
    }

    public HoursPopularityAnalyticsData(List<Integer> dataset) {
        this.dataset = dataset;
    }

    public List<Integer> getDataset() {
        return dataset;
    }

    public void setDataset(List<Integer> dataset) {
        this.dataset = dataset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoursPopularityAnalyticsData that = (HoursPopularityAnalyticsData) o;
        return Objects.equals(dataset, that.dataset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataset);
    }

    @Override
    public String toString() {
        return "HoursPopularityAnalyticsData{" +
                "dataset=" + dataset +
                '}';
    }
}
