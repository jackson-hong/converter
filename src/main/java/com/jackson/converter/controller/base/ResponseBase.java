package com.jackson.converter.controller.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jackson.converter.common.code.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
class ResponseBase {
    private ResultCode resultCode;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime systemDt;
    private String resultMessage;

    public ResponseBase(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.systemDt = LocalDateTime.now();
    }

    public String getResultMessage() {
        return resultCode.getResultMessage();
    }
    public String getResultCode() {return resultCode.getResultCode();}
    public String getSystemDt() {
        return systemDt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

}
