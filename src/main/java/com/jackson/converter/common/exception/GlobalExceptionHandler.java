package com.jackson.converter.common.exception;

import com.jackson.converter.common.code.ResultCode;
import com.jackson.converter.controller.base.ErrorResponse;
import com.jackson.converter.controller.base.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<ErrorResponse>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException : {}", e.getMessage());
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = getMessage(allErrors.iterator());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(Response.badRequestError(new ErrorResponse(message)));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Response<ErrorResponse>> bindException(BindException e) {
        log.error("BindException : {}", e.getMessage());
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = getMessage(allErrors.iterator());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(Response.badRequestError(new ErrorResponse(message)));
    }

    private String getMessage(Iterator<ObjectError> errorIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (errorIterator.hasNext()) {
            ObjectError error = errorIterator.next();
            resultMessageBuilder
                    .append("['")
                    .append(((FieldError) error).getField())
                    .append("' is '")
                    .append(((FieldError) error).getRejectedValue())
                    .append("' :: ")
                    .append(error.getDefaultMessage())
                    .append("]");

            if (errorIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        log.error(resultMessageBuilder.toString());
        return resultMessageBuilder.toString();
    }

    @ExceptionHandler(ConverterException.class)
    public ResponseEntity<Response<ErrorResponse>> converterException(ConverterException e) {
        log.error("ConverterException : {}", e.getMessage());
        final ResultCode resultCode = e.getResultCode();
        return ResponseEntity
                .status(resultCode.getHttpStatus())
                .body(Response.badRequestError(new ErrorResponse(e.getMessage())));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response<ErrorResponse>> illegalArgException(IllegalArgumentException e) {
        log.error("IllegalArgumentException : {}", e.getMessage());
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(Response.badRequestError(new ErrorResponse(e.getMessage())));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Response<ErrorResponse>> missingParameter(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException : {}", e.getMessage());
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(Response.badRequestError(new ErrorResponse(e.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<ErrorResponse>> unhandledException(Exception e) {
        log.error("unhandled", e);
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(Response.error(new ErrorResponse("Unhandled Exception")));
    }
}
