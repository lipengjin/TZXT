package com.tzxt.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RestException.class)
    public void ErrorHandler(HttpServletResponse res, RestException e) throws Exception {
        res.sendError(e.getStatus().value(), e.getMessage());
    }

}