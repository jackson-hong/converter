package com.jackson.converter.controller.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jackson.converter.common.code.ResultCode;
import lombok.Getter;

import static com.jackson.converter.common.code.ResultCode.*;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> extends ResponseBase {

    public Response() {
        super(RESULT_0000);
    }

    public Response(ResultCode code) {
        this(code, null);
    }

    public Response(ResultCode code, T resultData) {
        super(code);
        this.resultData = resultData;
    }

    private T resultData;

    public static <T> Response<T> success(T data) {
        return new Response<T>(RESULT_0000, data);
    }

    public static Response<String> success() {
        return new Response<>(RESULT_0000);
    }

    public static Response<String> error() {
        return new Response<>(RESULT_9999);
    }

    public static Response<String> customError(ResultCode resultCode) {
        return new Response<>(resultCode);
    }

    public static Response<String> success(String message) {
        return new Response<>(RESULT_0000, message);
    }

    public static Response<ErrorResponse> error(ErrorResponse payload) {
        return new Response<>(RESULT_9999, payload);
    }

    public static Response<ErrorResponse> badRequestError(ErrorResponse payload) {
        return new Response<>(RESULT_4000, payload);
    }
}
