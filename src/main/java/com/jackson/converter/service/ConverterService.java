package com.jackson.converter.service;

import com.jackson.converter.common.code.ResultCode;
import com.jackson.converter.common.exception.ConverterException;
import com.jackson.converter.controller.ConvertRequest;
import com.jackson.converter.controller.ConvertResponse;
import com.jackson.converter.domain.Converter;
import com.jackson.converter.domain.RestResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConverterService {

    private final RestTemplate restTemplate;

    public ConvertResponse convert(ConvertRequest convertRequest) {
        RestResult restResult = request(convertRequest);
        restResult.validate();
        Converter converter = new Converter(restResult.organize(convertRequest.getTextType()));
        String afterConvert = converter.convert();
        log.info("after converting : {}", afterConvert);
        return converter.toResponse(convertRequest.getUnit());
    }

    private RestResult request(ConvertRequest request) {
        URI uri = getUri(request);
        try{
            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, HttpEntity.EMPTY, String.class);
            log.info(result.getBody());
            return new RestResult(result);
        }catch (ResourceAccessException e) {
            log.error(e.getMessage());
            throw new ConverterException(ResultCode.RESULT_5000, "요청 서버에서 응답하지 않습니다.");
        }
    }

    private URI getUri(ConvertRequest convertRequest) {
        return UriComponentsBuilder
                .fromUriString(convertRequest.getUrl())
                .build()
                .toUri();
    }
}
