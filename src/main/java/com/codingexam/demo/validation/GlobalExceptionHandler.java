package com.codingexam.demo.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.codingexam.demo.api")
public class GlobalExceptionHandler {

    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<String> handleRequiredFieldException(RequiredFieldException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(e.getMessage());
    }
}
