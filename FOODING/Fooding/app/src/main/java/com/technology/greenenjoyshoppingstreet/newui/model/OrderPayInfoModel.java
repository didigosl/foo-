package com.technology.greenenjoyshoppingstreet.newui.model;

import java.io.Serializable;

public class OrderPayInfoModel implements Serializable {

    /**
     * orderId : 303
     * sn : 18122025779160566459
     * totalAmount : 1526.32
     */

    private String orderId;
    private String sn;
    private String totalAmount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
