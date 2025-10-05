package com.javaweb.project.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDetail {
    private int code;
    private String message;
    private String details;
    public ErrorDetail(int code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

}
