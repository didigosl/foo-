package com.duma.ld.baselibarary.model;

import java.io.Serializable;

/**
 * Created by liudong on 2017/6/5.
 */

public class HttpSimpleResModel implements Serializable {


    private String status;
    private String code;

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
