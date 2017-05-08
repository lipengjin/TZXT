package com.tzxt.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by wangshang on 17/3/24.
 */
public class RestException extends RuntimeException {
    private HttpStatus status;

    public RestException(HttpStatus status, String message) {
        super(message);
        this.status = status;

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
