package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * API response returning alias data with target URL, click hits, and billable user/team info.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlAliasHitsBillableResponse extends BaseResponse<UrlAliasHitsBillableData> {
    public UrlAliasHitsBillableResponse() {
        super();
    }

    public UrlAliasHitsBillableResponse(Integer code, List<String> errors, UrlAliasHitsBillableData data) {
        super(code, errors, data);
    }
}

