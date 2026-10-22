package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

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
