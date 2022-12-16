package com.jackson.converter.controller;

import com.jackson.converter.common.type.TextType;
import com.jackson.converter.controller.base.Response;
import com.jackson.converter.service.ConverterService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/converter")
@RequiredArgsConstructor
public class ConverterController {

    private final ConverterService service;

    @GetMapping
    public Response<ConvertResponse> convert(ConvertRequest convertRequest) throws URISyntaxException {
        return Response.success(ConvertResponse.from(service.convert(convertRequest)));
    }
}
