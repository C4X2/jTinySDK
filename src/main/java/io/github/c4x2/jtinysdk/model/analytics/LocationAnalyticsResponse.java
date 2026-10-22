package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

/**
 * API response returning geographic location analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationAnalyticsResponse extends BaseResponse<LocationAnalyticsData> {
    public LocationAnalyticsResponse() {
        super();
    }

    public LocationAnalyticsResponse(Integer code, java.util.List<String> errors, LocationAnalyticsData data) {
        super(code, errors, data);
    }
}

