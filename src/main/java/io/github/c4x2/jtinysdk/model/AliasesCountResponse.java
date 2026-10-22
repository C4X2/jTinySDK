package io.github.c4x2.jtinysdk.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * API response returning aliases count.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasesCountResponse extends BaseResponse<AliasesCountData> {
    public AliasesCountResponse() {
        super();
    }

    public AliasesCountResponse(Integer code, List<String> errors, AliasesCountData data) {
        super(code, errors, data);
    }
}

