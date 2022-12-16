package com.jackson.converter.controller.base;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

}
