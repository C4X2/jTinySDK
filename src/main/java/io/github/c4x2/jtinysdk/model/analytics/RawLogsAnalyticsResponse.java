package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

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

