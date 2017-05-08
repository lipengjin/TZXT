package com.tzxt.util;

import com.tzxt.exception.RestException;
import org.springframework.http.HttpStatus;

/**
 * Created by wangshang on 17/3/24.
 *
 *
 */
public class ResponseHelper {

    public static <T> T getOrThrow(Response<T> response, HttpStatus status) {

        if (response.getError() != null) {
            throw new RestException(status, response.getError());
        }
        return response.getData();
    }

    public static <T> T getOrThrow(Response<T> response) {

        return getOrThrow(response, HttpStatus.BAD_REQUEST);
    }
}
