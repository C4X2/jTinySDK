package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

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

