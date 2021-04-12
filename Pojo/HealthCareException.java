package com.slokam.healthcare.Pojo;

public class HealthCareException extends Exception{
    private Integer errorCode;

    public Integer getErrorCode() {
        return errorCode;
    }
    private HealthCareException(){
    }
    public HealthCareException(String message) {
        super(message);
    }
    public HealthCareException(String message,Integer  errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public HealthCareException(String message,Throwable t) {
        super(message,t);
    }
    public HealthCareException(String message,Throwable t,Integer errorCode) {
        super(message,t);
        this.errorCode=errorCode;
    }
}
