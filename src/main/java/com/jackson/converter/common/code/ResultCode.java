package com.jackson.converter.common.code;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ResultCode {

    RESULT_0000("0000", "성공"),
    RESULT_9999("9999", "실패"),

    // CLIENT
    RESULT_4000("4000", "잘못 된 요청입니다.", HttpStatus.BAD_REQUEST),

    // SERVER
    RESULT_5000("5000", "internal server error", HttpStatus.INTERNAL_SERVER_ERROR);;


    private final String resultCode;

    @Getter
    private final String resultMessage;

    @Getter
    private final HttpStatus httpStatus;

    ResultCode(String resultCode, String resultMessage) {
        this(resultCode, resultMessage, HttpStatus.OK);
    }

    ResultCode(String resultCode, String resultMessage, HttpStatus httpStatus) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.httpStatus = httpStatus;
    }

    public static boolean isSuccess(String resultCode) {
        return RESULT_0000.resultCode.equals(resultCode);
    }

    @JsonValue
    public String getResultCode() {
        return resultCode;
    }
}
