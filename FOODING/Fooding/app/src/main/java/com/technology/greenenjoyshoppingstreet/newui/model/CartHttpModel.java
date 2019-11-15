package com.technology.greenenjoyshoppingstreet.newui.model;

public class CartHttpModel {

    /**
     * cart_id : 1
     * num : 2
     */

    private String cart_id;
    private String num;
    private String sku_id;

    public CartHttpModel(String cart_id, String num, String sku_id) {
        this.cart_id = cart_id;
        this.num = num;
        this.sku_id = sku_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
