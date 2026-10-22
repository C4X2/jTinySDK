package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

/**
 * API response returning weekdays popularity analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeekdaysPopularityAnalyticsResponse extends BaseResponse<WeekdaysPopularityAnalyticsData> {
    public WeekdaysPopularityAnalyticsResponse() {
        super();
    }

    public WeekdaysPopularityAnalyticsResponse(Integer code, java.util.List<String> errors, WeekdaysPopularityAnalyticsData data) {
        super(code, errors, data);
    }
}

