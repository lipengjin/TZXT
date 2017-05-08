package com.tzxt.util;

/**
 * Created by wangshang on 17/3/23.
 *
 * service 层返回的数据包装
 */
public class Response<T> {
    private T data;

    private String error = null;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static Response fail(String error) {
        Response response = new Response<>();
        response.setError(error);
        return response;
    }

    public static <T> Response ok(T data) {
        Response response = new Response<T>();
        response.setData(data);
        return response;
    }
}
