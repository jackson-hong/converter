package com.jackson.converter.controller;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
public class ConvertResponse {

    private String quotient;
    private String remainder;

}
