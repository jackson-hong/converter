package com.jackson.converter.domain;

import com.jackson.converter.controller.ConvertResponse;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Converter {

    private final String initialText;
    private String result;

    @Builder
    public Converter(String initialText, String result) {
        this.initialText = initialText;
        this.result = result;
    }

    public Converter(String initialText) {
        this.initialText = initialText;
    }

    public String convert(){
        TextQueues textQueues = new TextQueues();
        textQueues.divide(initialText);
        result = textQueues.merge();
        return result;
    }

    public ConvertResponse toResponse(int unit){
        int length = result.length();
        int lengthRemainder = length % unit;
        String remainder = result.substring(length - lengthRemainder, length);
        String quotient = result.substring(0, length - lengthRemainder);
        return new ConvertResponse(quotient, remainder);
    }
}
