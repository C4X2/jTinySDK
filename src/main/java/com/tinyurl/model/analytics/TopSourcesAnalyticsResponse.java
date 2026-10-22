package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

/**
 * API response returning top referral sources analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopSourcesAnalyticsResponse extends BaseResponse<TopSourcesAnalyticsData> {
    public TopSourcesAnalyticsResponse() {
        super();
    }

    public TopSourcesAnalyticsResponse(Integer code, java.util.List<String> errors, TopSourcesAnalyticsData data) {
        super(code, errors, data);
    }
}
