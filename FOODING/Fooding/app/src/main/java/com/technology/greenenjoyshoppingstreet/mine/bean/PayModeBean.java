package com.technology.greenenjoyshoppingstreet.mine.bean;

/**
 * Created by Administrator on 2018/1/2.
 */

public class PayModeBean {
    public static final String PAYPAL = "redsys";
    public static final String IDEN_CARD = "信用卡支付";
    public static final String OFFLINE_PAY = "线下支付";
    private String payMode;
    private boolean isCheck;

    public PayModeBean(String payMode, boolean isCheck) {
        this.payMode = payMode;
        this.isCheck = isCheck;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
