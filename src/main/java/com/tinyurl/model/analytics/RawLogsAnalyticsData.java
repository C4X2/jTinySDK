package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 * Raw analytics log entry.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RawLogsAnalyticsData {

    @JsonProperty("datetime")
    private String datetime;

    @JsonProperty("tiny_url")
    private String tinyUrl;

    @JsonProperty("url")
    private String url;

    @JsonProperty("referer")
    private String referer;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("country")
    private String country;

    @JsonProperty("is_bot")
    private Boolean isBot;

    @JsonProperty("browser")
    private SoftwareVersion browser;

    @JsonProperty("os")
    private SoftwareVersion os;

    @JsonProperty("device")
    private String device;

    @JsonProperty("language")
    private String language;

    @JsonProperty("tags")
    private List<String> tags;

    public RawLogsAnalyticsData() {
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsBot() {
        return isBot;
    }

    public void setIsBot(Boolean isBot) {
        this.isBot = isBot;
    }

    public SoftwareVersion getBrowser() {
        return browser;
    }

    public void setBrowser(SoftwareVersion browser) {
        this.browser = browser;
    }

    public SoftwareVersion getOs() {
        return os;
    }

    public void setOs(SoftwareVersion os) {
        this.os = os;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawLogsAnalyticsData that = (RawLogsAnalyticsData) o;
        return Objects.equals(datetime, that.datetime) &&
                Objects.equals(tinyUrl, that.tinyUrl) &&
                Objects.equals(url, that.url) &&
                Objects.equals(referer, that.referer) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(country, that.country) &&
                Objects.equals(isBot, that.isBot) &&
                Objects.equals(browser, that.browser) &&
                Objects.equals(os, that.os) &&
                Objects.equals(device, that.device) &&
                Objects.equals(language, that.language) &&
                Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datetime, tinyUrl, url, referer, ip, country, isBot, browser, os, device, language, tags);
    }

    @Override
    public String toString() {
        return "RawLogsAnalyticsData{" +
                "datetime='" + datetime + '\'' +
                ", tinyUrl='" + tinyUrl + '\'' +
                ", url='" + url + '\'' +
                ", referer='" + referer + '\'' +
                ", ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", isBot=" + isBot +
                ", browser=" + browser +
                ", os=" + os +
                ", device='" + device + '\'' +
                ", language='" + language + '\'' +
                ", tags=" + tags +
                '}';
    }
}
