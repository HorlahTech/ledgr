package com.awolumate.ledgr.exception;

import org.springframework.http.HttpStatus;

public class ViolatedException extends AppException {
    public ViolatedException(String message) {
        super(message, HttpStatus.CONFLICT, "RULE_VIOLATED");
    }}