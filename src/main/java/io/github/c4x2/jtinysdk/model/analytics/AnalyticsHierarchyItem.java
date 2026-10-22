package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Hierarchical analytics item containing a name, count value, and optional child breakdown.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyticsHierarchyItem {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("children")
    private List<AnalyticsHierarchyItem> children;

    public AnalyticsHierarchyItem() {
    }

    public AnalyticsHierarchyItem(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public AnalyticsHierarchyItem(String name, Integer value, List<AnalyticsHierarchyItem> children) {
        this.name = name;
        this.value = value;
        this.children = children;
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

    public List<AnalyticsHierarchyItem> getChildren() {
        return children;
    }

    public void setChildren(List<AnalyticsHierarchyItem> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyticsHierarchyItem that = (AnalyticsHierarchyItem) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, children);
    }

    @Override
    public String toString() {
        return "AnalyticsHierarchyItem{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", children=" + children +
                '}';
    }
}

