package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Data container for timeline analytics response.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimelineAnalyticsData {

    @JsonProperty("dataset")
    private List<TimelineItem> dataset;

    @JsonProperty("interval")
    private String interval;

    @JsonProperty("timezone")
    private String timezone;

    public TimelineAnalyticsData() {
    }

    public List<TimelineItem> getDataset() {
        return dataset;
    }

    public void setDataset(List<TimelineItem> dataset) {
        this.dataset = dataset;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimelineAnalyticsData that = (TimelineAnalyticsData) o;
        return Objects.equals(dataset, that.dataset) &&
                Objects.equals(interval, that.interval) &&
                Objects.equals(timezone, that.timezone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataset, interval, timezone);
    }

    @Override
    public String toString() {
        return "TimelineAnalyticsData{" +
                "dataset=" + dataset +
                ", interval='" + interval + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}

