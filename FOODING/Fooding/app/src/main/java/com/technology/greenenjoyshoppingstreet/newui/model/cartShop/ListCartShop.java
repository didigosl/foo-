package com.technology.greenenjoyshoppingstreet.newui.model.cartShop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListCartShop {

    @SerializedName("shopId")
    @Expose
    private String shopId;
    @SerializedName("shopName")
    @Expose
    private String shopName;
    @SerializedName("minBuyMomeny")
    @Expose
    private String minBuyMomeny;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("carts")
    @Expose
    private List<Cart> carts = null;
    @SerializedName("totalAmount")
    @Expose
    private double totalAmount;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListCartShop() {
    }

    /**
     *
     * @param carts
     * @param logo
     * @param shopName
     * @param shopId
     * @param totalAmount
     * @param minBuyMomeny
     */
    public ListCartShop(String shopId, String shopName, String minBuyMomeny, String logo, List<Cart> carts, double totalAmount) {
        super();
        this.shopId = shopId;
        this.shopName = shopName;
        this.minBuyMomeny = minBuyMomeny;
        this.logo = logo;
        this.carts = carts;
        this.totalAmount = totalAmount;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMinBuyMomeny() {
        return minBuyMomeny;
    }

    public void setMinBuyMomeny(String minBuyMomeny) {
        this.minBuyMomeny = minBuyMomeny;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
