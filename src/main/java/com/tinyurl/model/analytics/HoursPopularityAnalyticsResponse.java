package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

/**
 * API response returning hourly popularity analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HoursPopularityAnalyticsResponse extends BaseResponse<HoursPopularityAnalyticsData> {
    public HoursPopularityAnalyticsResponse() {
        super();
    }

    public HoursPopularityAnalyticsResponse(Integer code, java.util.List<String> errors, HoursPopularityAnalyticsData data) {
        super(code, errors, data);
    }
}
