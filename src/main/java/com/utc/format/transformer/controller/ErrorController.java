package com.utc.format.transformer.controller;

import com.utc.format.transformer.exception.InvalidFormatException;
import com.utc.format.transformer.exception.ParameterRequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = {InvalidFormatException.class, ParameterRequiredException.class})
    public ResponseEntity<Object> invalidFormatExceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> generalExceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>("An error occurred processing your request ", HttpStatus.BAD_REQUEST);
    }
}
