package com.javamentor.qa.platform.webapp.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(UnsupportedOperationException.class)
    protected ResponseEntity<Object> illegalArgumentEx(RuntimeException runtimeException, WebRequest webRequest) {
        logger.info(runtimeException.fillInStackTrace().toString());
        return handleExceptionInternal(runtimeException, runtimeException.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    protected ResponseEntity<Object> illegalArgumentExe(RuntimeException runtimeException, WebRequest webRequest) {
        logger.error(runtimeException.getMessage());
        String[] body = runtimeException.getMessage().split("\\.");
        return handleExceptionInternal(runtimeException, String.format("%s id can not be null on update.", body[body.length - 1]), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null;
        return ResponseEntity.badRequest().body(fieldError.getDefaultMessage());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String[] m = ex.getCause().getMessage().split(":");
        return ResponseEntity.badRequest().body(m[0]);
    }
}