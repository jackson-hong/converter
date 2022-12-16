package com.jackson.converter.service;

import com.jackson.converter.controller.ConvertRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConverterService {

    private final RestTemplate restTemplate;

    public String convert(ConvertRequest convertRequest) throws URISyntaxException {
        URI uri = UriComponentsBuilder
                .fromUriString(convertRequest.getUrl())
                .build()
                .toUri();
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        log.info("restTemplate result : {}" ,result.toString());
        return null;
    }
}
