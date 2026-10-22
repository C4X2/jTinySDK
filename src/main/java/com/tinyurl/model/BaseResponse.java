package com.tinyurl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Common base response for TinyURL API responses.
 *
 * @param <T> the type of data returned in this response
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("data")
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Integer code, List<String> errors, T data) {
        this.code = code;
        this.errors = errors;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<String> getErrors() {
        return errors != null ? Collections.unmodifiableList(errors) : Collections.emptyList();
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code != null && code == 0 && (errors == null || errors.isEmpty());
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", errors=" + errors +
                ", data=" + data +
                '}';
    }
}
