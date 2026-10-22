package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Data container for top referral sources analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopSourcesAnalyticsData {

    @JsonProperty("dataset")
    private List<SourceHitsItem> dataset;

    @JsonProperty("direct")
    private Integer direct;

    @JsonProperty("total")
    private Integer total;

    public TopSourcesAnalyticsData() {
    }

    public List<SourceHitsItem> getDataset() {
        return dataset;
    }

    public void setDataset(List<SourceHitsItem> dataset) {
        this.dataset = dataset;
    }

    public Integer getDirect() {
        return direct;
    }

    public void setDirect(Integer direct) {
        this.direct = direct;
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
        TopSourcesAnalyticsData that = (TopSourcesAnalyticsData) o;
        return Objects.equals(dataset, that.dataset) &&
                Objects.equals(direct, that.direct) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataset, direct, total);
    }

    @Override
    public String toString() {
        return "TopSourcesAnalyticsData{" +
                "dataset=" + dataset +
                ", direct=" + direct +
                ", total=" + total +
                '}';
    }
}

