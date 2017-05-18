package com.tzxt.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by wangshang on 17/5/19.
 */
public class AjaxException extends RuntimeException {
    private HttpStatus status;

    public AjaxException(HttpStatus status, String message) {
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
