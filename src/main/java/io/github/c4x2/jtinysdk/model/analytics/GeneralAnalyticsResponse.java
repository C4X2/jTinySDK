package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

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

