package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

/**
 * API response returning top languages analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopLanguagesAnalyticsResponse extends BaseResponse<TopLanguagesAnalyticsData> {
    public TopLanguagesAnalyticsResponse() {
        super();
    }

    public TopLanguagesAnalyticsResponse(Integer code, java.util.List<String> errors, TopLanguagesAnalyticsData data) {
        super(code, errors, data);
    }
}
