package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Standard named analytics item with a count or numeric value.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyticsValueItem {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private Integer value;

    public AnalyticsValueItem() {
    }

    public AnalyticsValueItem(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyticsValueItem that = (AnalyticsValueItem) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "AnalyticsValueItem{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

