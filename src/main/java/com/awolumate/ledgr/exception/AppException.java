package com.awolumate.ledgr.exception;

import org.springframework.http.HttpStatus;



import lombok.Getter;

@Getter 
public abstract class AppException extends RuntimeException {
    private final HttpStatus status;
    private final String errorCode;

    protected AppException(String message, HttpStatus status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

}



