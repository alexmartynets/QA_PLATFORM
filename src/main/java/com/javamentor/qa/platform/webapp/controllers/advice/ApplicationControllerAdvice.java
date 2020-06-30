package com.javamentor.qa.platform.webapp.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> constraintEx(RuntimeException runtimeException, WebRequest webRequest) {
        logger.info(runtimeException.fillInStackTrace().toString());
        String[] split = runtimeException.getMessage().split(":");
        return handleExceptionInternal(runtimeException, split[split.length - 1].trim(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> notFoundEx(RuntimeException runtimeException, WebRequest webRequest) {
        logger.info(runtimeException.fillInStackTrace().toString());
        String[] body = runtimeException.getMessage().split(":");
        return handleExceptionInternal(runtimeException, body[body.length - 1].trim(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String[] m = ex.getCause().getMessage().split(":");
        return ResponseEntity.badRequest().body(m[0]);
    }

}