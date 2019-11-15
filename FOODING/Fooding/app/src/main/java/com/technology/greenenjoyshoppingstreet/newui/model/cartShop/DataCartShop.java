package com.technology.greenenjoyshoppingstreet.newui.model.cartShop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataCartShop {

    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("totalRebate")
    @Expose
    private String totalRebate;
    @SerializedName("total")
    @Expose
    private long total;
    @SerializedName("list")
    @Expose
    private List<ListCartShop> list = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataCartShop() {
    }

    /**
     *
     * @param total
     * @param totalAmount
     * @param list
     * @param totalRebate
     */
    public DataCartShop(String totalAmount, String totalRebate, long total, List<ListCartShop> list) {
        super();
        this.totalAmount = totalAmount;
        this.totalRebate = totalRebate;
        this.total = total;
        this.list = list;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalRebate() {
        return totalRebate;
    }

    public void setTotalRebate(String totalRebate) {
        this.totalRebate = totalRebate;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<ListCartShop> getList() {
        return list;
    }

    public void setList(List<ListCartShop> list) {
        this.list = list;
    }

}
