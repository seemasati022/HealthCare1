package com.slokam.healthcare.Pojo;

public class ErrorInfo {
    private Integer errorId;
    private String messsage;
    private Throwable exception;

    public ErrorInfo() {
    }
    public ErrorInfo(Integer errorId, String messsage, Throwable exception) {
        this.errorId = errorId;
        this.messsage = messsage;
        this.exception = exception;
    }

    public Integer getErrorId() {
        return errorId;
    }

    public String getMesssage() {
        return messsage;
    }

    public Throwable getException() {
        return exception;
    }

    public void setErrorId(Integer errorId) {
        this.errorId = errorId;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
