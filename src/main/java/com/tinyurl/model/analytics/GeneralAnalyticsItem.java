package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Breakdown of browser, os, and device in general analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralAnalyticsItem {

    @JsonProperty("browser")
    private List<AnalyticsHierarchyItem> browser;

    @JsonProperty("os")
    private List<AnalyticsHierarchyItem> os;

    @JsonProperty("device")
    private List<AnalyticsValueItem> device;

    public GeneralAnalyticsItem() {
    }

    public List<AnalyticsHierarchyItem> getBrowser() {
        return browser;
    }

    public void setBrowser(List<AnalyticsHierarchyItem> browser) {
        this.browser = browser;
    }

    public List<AnalyticsHierarchyItem> getOs() {
        return os;
    }

    public void setOs(List<AnalyticsHierarchyItem> os) {
        this.os = os;
    }

    public List<AnalyticsValueItem> getDevice() {
        return device;
    }

    public void setDevice(List<AnalyticsValueItem> device) {
        this.device = device;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralAnalyticsItem that = (GeneralAnalyticsItem) o;
        return Objects.equals(browser, that.browser) &&
                Objects.equals(os, that.os) &&
                Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(browser, os, device);
    }

    @Override
    public String toString() {
        return "GeneralAnalyticsItem{" +
                "browser=" + browser +
                ", os=" + os +
                ", device=" + device +
                '}';
    }
}
