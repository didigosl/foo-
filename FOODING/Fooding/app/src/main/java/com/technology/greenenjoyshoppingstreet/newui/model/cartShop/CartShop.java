
package com.technology.greenenjoyshoppingstreet.newui.model.cartShop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartShop {

    @SerializedName("data")
    @Expose
    private DataCartShop data;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("msg")
    @Expose
    private String msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public CartShop() {
    }

    /**
     *
     * @param status
     * @param data
     * @param code
     */
    public CartShop(DataCartShop data, String status, long code, String msg) {
        super();
        this.data = data;
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public DataCartShop getData() {
        return data;
    }

    public void setData(DataCartShop data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


