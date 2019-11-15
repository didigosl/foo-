package com.duma.ld.baselibarary.model;

import java.io.Serializable;

/**
 * Created by liudong on 2017/5/26.
 */

public class HttpResModel<T> implements Serializable {
    private T data;
    private String status;
    private String code;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
