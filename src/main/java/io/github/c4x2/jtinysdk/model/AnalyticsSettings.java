package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Analytics settings for a TinyURL alias.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyticsSettings {

    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("public")
    private Boolean isPublic;

    public AnalyticsSettings() {
    }

    public AnalyticsSettings(Boolean enabled, Boolean isPublic) {
        this.enabled = enabled;
        this.isPublic = isPublic;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalyticsSettings that = (AnalyticsSettings) o;
        return Objects.equals(enabled, that.enabled) && Objects.equals(isPublic, that.isPublic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, isPublic);
    }

    @Override
    public String toString() {
        return "AnalyticsSettings{" +
                "enabled=" + enabled +
                ", isPublic=" + isPublic +
                '}';
    }
}

