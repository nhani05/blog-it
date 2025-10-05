package com.javaweb.project.exception;


import javassist.tools.web.BadHttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception{

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        ErrorDetail errorDetail = new ErrorDetail(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                "An unexpected error occurred"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadHttpRequest.class)
    public ResponseEntity<Object> handleBadHttpRequest(BadHttpRequest badHttpRequest) {
        ErrorDetail errorDetail = new ErrorDetail(
                HttpStatus.BAD_REQUEST.value(),
                badHttpRequest.getMessage(),
                "Bad request"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}
