package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * API response returning alias data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasResponse extends BaseResponse<AliasData> {
    public AliasResponse() {
        super();
    }

    public AliasResponse(Integer code, List<String> errors, AliasData data) {
        super(code, errors, data);
    }
}

