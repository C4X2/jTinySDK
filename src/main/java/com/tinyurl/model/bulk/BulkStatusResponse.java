package com.tinyurl.model.bulk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

import java.util.List;

/**
 * API response returning bulk request status.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkStatusResponse extends BaseResponse<BulkStatusData> {
    public BulkStatusResponse() {
        super();
    }

    public BulkStatusResponse(Integer code, List<String> errors, BulkStatusData data) {
        super(code, errors, data);
    }
}
