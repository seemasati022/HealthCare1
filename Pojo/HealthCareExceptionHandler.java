package com.slokam.healthcare.Pojo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HealthCareExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleException(Exception e){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorId(123);
        errorInfo.setException(e);
        errorInfo.setMesssage("Application went wronge");
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public ResponseEntity<ErrorInfo> handlerHealthCareException(HealthCareException e){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorId(e.getErrorCode());
        errorInfo.setException(e);
        errorInfo.setMesssage(e.getMessage());
        return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
