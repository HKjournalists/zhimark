package com.spring.mvc.model.gson;

/**
 * Created by liluoqi on 15/7/9.
 */
public class SmsReturnGson {
    private String result;
    private String errorMessage;

    public SmsReturnGson() {

    }

    public SmsReturnGson(String result, String errorMessage) {
        this.result = result;
        this.errorMessage = errorMessage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

