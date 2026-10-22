package io.github.c4x2.jtinysdk.model.analytics;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

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

