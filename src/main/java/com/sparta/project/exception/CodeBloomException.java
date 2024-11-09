package com.sparta.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CodeBloomException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public CodeBloomException(ErrorCode errorCode) {
        this.httpStatus = errorCode.getHttpStatus();
        this.message = errorCode.getMessage();
    }
}
