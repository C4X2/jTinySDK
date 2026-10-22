package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Data container for weekday popularity analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeekdaysPopularityAnalyticsData {

    @JsonProperty("dataset")
    private List<Integer> dataset;

    public WeekdaysPopularityAnalyticsData() {
    }

    public WeekdaysPopularityAnalyticsData(List<Integer> dataset) {
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
        WeekdaysPopularityAnalyticsData that = (WeekdaysPopularityAnalyticsData) o;
        return Objects.equals(dataset, that.dataset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataset);
    }

    @Override
    public String toString() {
        return "WeekdaysPopularityAnalyticsData{" +
                "dataset=" + dataset +
                '}';
    }
}

