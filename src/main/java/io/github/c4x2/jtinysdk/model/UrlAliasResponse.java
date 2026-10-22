package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * API response returning an alias with its long URL.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlAliasResponse extends BaseResponse<UrlAliasData> {
    public UrlAliasResponse() {
        super();
    }

    public UrlAliasResponse(Integer code, List<String> errors, UrlAliasData data) {
        super(code, errors, data);
    }
}

