package com.technology.greenenjoyshoppingstreet.mine.bean;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class OrderStatusBean {
    private OrderStatus orderStatus;
    private String count;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public OrderStatusBean(OrderStatus orderStatus, String count, int position) {
        this.orderStatus = orderStatus;
        this.count = count;
        this.position = position;
    }
}
