package com.jackson.converter.controller;

import com.jackson.converter.controller.base.Response;
import com.jackson.converter.service.ConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
@RequiredArgsConstructor
public class ConverterController {

    private final ConverterService service;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public Response<ConvertResponse> convert(@Validated ConvertRequest convertRequest) {
        return Response.success(service.convert(convertRequest));
    }
}
