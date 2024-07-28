package com.fast.pay.write.model;

import lombok.Data;

@Data
public class AppStatus<T extends Object> {
    private String httpCode;
    private String message;
    private T data;
    private boolean isError;
    private Long timeStamp;

    public AppStatus(String httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

    public AppStatus(String httpCode, String message, T data) {
        this(httpCode, message);
        this.data = data;
        this.isError = false;
        this.timeStamp = System.currentTimeMillis();
    }

    public AppStatus(String httpCode, String message, boolean isError) {
        this(httpCode, message);
        this.data = null;
        this.isError = isError;
        this.timeStamp = System.currentTimeMillis();
    }

    public AppStatus(String httpCode, String message, T data, boolean isError, Long timeStamp) {
        this(httpCode, message, data);
        this.isError = isError;
        this.timeStamp = timeStamp;
    }


    public static AppStatus of500() {
        return new AppStatus("500", "Internal Sever Eror", true);
    }

    public static AppStatus of200(Object data) {
        return new AppStatus("200", "Success", data);
    }





}
