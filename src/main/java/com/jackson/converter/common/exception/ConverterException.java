package com.jackson.converter.common.exception;

import com.jackson.converter.common.code.ResultCode;
import lombok.Getter;

@Getter
public class ConverterException extends RuntimeException {
    private final ResultCode resultCode;
    private final String message;

    public ConverterException(ResultCode resultCode) {
        this(resultCode, resultCode.getResultMessage());
    }

    public ConverterException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
        this.message = message;
    }
}
