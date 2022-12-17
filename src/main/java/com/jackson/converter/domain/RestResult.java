package com.jackson.converter.domain;

import com.jackson.converter.common.code.ResultCode;
import com.jackson.converter.common.exception.ConverterException;
import com.jackson.converter.common.type.TextType;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

@Slf4j
public class RestResult {
    private final ResponseEntity<String> response;

    public RestResult(ResponseEntity<String> response) {
        this.response = response;
    }

    public void validate(){
        HttpStatus status = response.getStatusCode();
        if(status.is4xxClientError()) {
            throw new ConverterException(ResultCode.RESULT_4000);
        }
        if(status.is5xxServerError()) {
            throw new ConverterException(ResultCode.RESULT_5000, "no answer from the requested server");
        }
    }

    public String organize(TextType textType) {
        String body = response.getBody();
        if(ObjectUtils.isEmpty(body)) {
            throw new ConverterException(ResultCode.RESULT_5000, "No body response");
        }
        if(textType == TextType.NO_HTML) {
            body = Jsoup.parse(body).text();
        }
        return body;
    }
}
