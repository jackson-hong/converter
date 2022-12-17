package com.jackson.converter.controller;

import com.jackson.converter.common.type.TextType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConvertRequest {
    private String url;
    private TextType textType;
    private Integer unit;
}
