package com.awolumate.ledgr.exception;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseModel> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(ex.getStatus())
            .body(new ErrorResponseModel(ex.getErrorCode(), ex.getMessage()));
    }
    @ExceptionHandler(ViolatedException.class)
    public ResponseEntity<ErrorResponseModel> handleBusinessRule(ViolatedException ex) {
        return ResponseEntity.status(ex.getStatus())
            .body(new ErrorResponseModel(ex.getErrorCode(), ex.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseModel> handleValidation(MethodArgumentNotValidException ex) {
        String details = ex.getBindingResult().getFieldErrors().stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest()
            .body(new ErrorResponseModel("VALIDATION_FAILED", details));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handleUnexpected(Exception ex) {
      
        return ResponseEntity.internalServerError()
            .body(new ErrorResponseModel("INTERNAL_ERROR", ex.getMessage() +" An unexpected error occurred, "));
    }
}
