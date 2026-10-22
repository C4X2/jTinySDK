package io.github.c4x2.jtinysdk.model.bulk;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.c4x2.jtinysdk.model.BaseResponse;

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

