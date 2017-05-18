package com.tzxt.exception;

import com.tzxt.util.CurrentUser;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RestException.class)
    public ModelAndView ErrorHandler(RestException e) throws Exception {

        ModelAndView result = new ModelAndView("/500");
        result.addObject("currUser", CurrentUser.get());
        result.addObject("errorMessage", e.getMessage());
        return result;
    }

    @ExceptionHandler(value = AjaxException.class)
    public void AjaxErrorHandler(HttpServletResponse res, RestException e) throws IOException {
        res.sendError(e.getStatus().value(), e.getMessage());
    }


}