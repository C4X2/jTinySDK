package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Generic response with raw/untyped data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse extends BaseResponse<Object> {
    public GenericResponse() {
        super();
    }

    public GenericResponse(Integer code, java.util.List<String> errors, Object data) {
        super(code, errors, data);
    }
}
