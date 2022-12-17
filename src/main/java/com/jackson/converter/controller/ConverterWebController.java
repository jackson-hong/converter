package com.jackson.converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("converter")
public class ConverterWebController {

    @GetMapping("/form")
    public String getForm() {
        return "form";
    }
}
