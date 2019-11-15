package com.technology.greenenjoyshoppingstreet.newui.model.cartShop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cart {

    @SerializedName("cartId")
    @Expose
    private String cartId;
    @SerializedName("spuId")
    @Expose
    private String spuId;
    @SerializedName("spuName")
    @Expose
    private String spuName;
    @SerializedName("specInfo")
    @Expose
    private String specInfo;
    @SerializedName("cover")
    @Expose
    private Object cover;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("rebate")
    @Expose
    private String rebate;
    @SerializedName("num")
    @Expose
    private String num;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("minToBuy")
    @Expose
    private String minToBuy;

    /**
     * No args constructor for use in serialization
     *
     */
    public Cart() {
    }

    /**
     *
     * @param unit
     * @param num
     * @param cover
     * @param price
     * @param spuId
     * @param minToBuy
     * @param rebate
     * @param cartId
     * @param spuName
     * @param specInfo
     */
    public Cart(String cartId, String spuId, String spuName, String specInfo, Object cover, String price, String rebate, String num, String unit, String minToBuy) {
        super();
        this.cartId = cartId;
        this.spuId = spuId;
        this.spuName = spuName;
        this.specInfo = specInfo;
        this.cover = cover;
        this.price = price;
        this.rebate = rebate;
        this.num = num;
        this.unit = unit;
        this.minToBuy = minToBuy;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public Object getCover() {
        return cover;
    }

    public void setCover(Object cover) {
        this.cover = cover;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getMinToBuy() {
        return minToBuy;
    }

    public void setMinToBuy(String minToBuy) {
        this.minToBuy = minToBuy;
    }

}
