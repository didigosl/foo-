package com.technology.greenenjoyshoppingstreet.cart.bean;

/**
 * Created by Bern on 2018/1/3 0003.
 */

public class CartUpdateBean {
    private String cart_id;
    private String sku_id;
    private String num;

    public CartUpdateBean(String cart_id, String sku_id, String num) {
        this.cart_id = cart_id;
        this.sku_id = sku_id;
        this.num = num;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
