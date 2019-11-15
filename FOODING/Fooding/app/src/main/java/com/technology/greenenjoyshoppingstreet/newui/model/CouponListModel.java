package com.technology.greenenjoyshoppingstreet.newui.model;

public class CouponListModel {

    /**
     * couponUserId : 2
     * couponName : 代金券很牛逼
     * amount : 1.00
     * startTime : 2017-12-24
     * endTime : 2017-12-30
     * minLimit : 1.00
     * withRebate : 0
     * withDiscount : 0
     */

    private String couponUserId;
    private String couponName;
    private String amount;
    private String startTime;
    private String endTime;
    private String minLimit;
    private String withRebate;
    private String withDiscount;

    public String getCouponUserId() {
        return couponUserId;
    }

    public void setCouponUserId(String couponUserId) {
        this.couponUserId = couponUserId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(String minLimit) {
        this.minLimit = minLimit;
    }

    public String getWithRebate() {
        return withRebate;
    }

    public void setWithRebate(String withRebate) {
        this.withRebate = withRebate;
    }

    public String getWithDiscount() {
        return withDiscount;
    }

    public void setWithDiscount(String withDiscount) {
        this.withDiscount = withDiscount;
    }
}
