package com.tinyurl.model.bulk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tinyurl.model.BaseResponse;

import java.util.List;

/**
 * API response returning bulk request report items.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BulkReportResponse extends BaseResponse<List<BulkReportData>> {
    public BulkReportResponse() {
        super();
    }

    public BulkReportResponse(Integer code, List<String> errors, List<BulkReportData> data) {
        super(code, errors, data);
    }
}
