package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

/**
 * API response returning general analytics.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralAnalyticsResponse extends BaseResponse<GeneralAnalyticsData> {
    public GeneralAnalyticsResponse() {
        super();
    }

    public GeneralAnalyticsResponse(Integer code, java.util.List<String> errors, GeneralAnalyticsData data) {
        super(code, errors, data);
    }
}
