package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * An item in the timeline analytics dataset.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimelineItem {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("unique")
    private Integer unique;

    @JsonProperty("human")
    private Integer human;

    public TimelineItem() {
    }

    public TimelineItem(String datetime, Integer total, Integer unique, Integer human) {
        this.datetime = datetime;
        this.total = total;
        this.unique = unique;
        this.human = human;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUnique() {
        return unique;
    }

    public void setUnique(Integer unique) {
        this.unique = unique;
    }

    public Integer getHuman() {
        return human;
    }

    public void setHuman(Integer human) {
        this.human = human;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimelineItem that = (TimelineItem) o;
        return Objects.equals(datetime, that.datetime) &&
                Objects.equals(total, that.total) &&
                Objects.equals(unique, that.unique) &&
                Objects.equals(human, that.human);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datetime, total, unique, human);
    }

    @Override
    public String toString() {
        return "TimelineItem{" +
                "datetime='" + datetime + '\'' +
                ", total=" + total +
                ", unique=" + unique +
                ", human=" + human +
                '}';
    }
}
