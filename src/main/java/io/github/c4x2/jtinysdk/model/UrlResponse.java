package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * API response returning long URL data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlResponse extends BaseResponse<UrlData> {
    public UrlResponse() {
        super();
    }

    public UrlResponse(Integer code, List<String> errors, UrlData data) {
        super(code, errors, data);
    }
}

