package com.jackson.converter.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConvertResponse {

    private String quotient;
    private String remainder;

    public static ConvertResponse from(String convert) {
        return null;
    }
}
