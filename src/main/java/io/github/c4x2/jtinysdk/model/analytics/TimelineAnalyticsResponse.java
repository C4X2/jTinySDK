package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

/**
 * API response returning timeline analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimelineAnalyticsResponse extends BaseResponse<TimelineAnalyticsData> {
    public TimelineAnalyticsResponse() {
        super();
    }

    public TimelineAnalyticsResponse(Integer code, java.util.List<String> errors, TimelineAnalyticsData data) {
        super(code, errors, data);
    }
}

