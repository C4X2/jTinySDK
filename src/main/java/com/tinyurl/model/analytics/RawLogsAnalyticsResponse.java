package com.tinyurl.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

import java.util.List;

/**
 * API response returning raw analytics logs.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RawLogsAnalyticsResponse extends BaseResponse<List<RawLogsAnalyticsData>> {
    public RawLogsAnalyticsResponse() {
        super();
    }

    public RawLogsAnalyticsResponse(Integer code, List<String> errors, List<RawLogsAnalyticsData> data) {
        super(code, errors, data);
    }
}
