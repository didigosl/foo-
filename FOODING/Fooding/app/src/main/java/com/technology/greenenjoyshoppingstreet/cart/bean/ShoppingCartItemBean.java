package com.technology.greenenjoyshoppingstreet.cart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.Tools;


/**
 * Created by Bern on 2017/11/28 0028.
 */

public class ShoppingCartItemBean implements Parcelable {
    private String id;
    private String name;
    private String unitPrice;
    private String productUrl;
    private String count;
    private String service;
    private transient boolean isChecked;

    public void increment() {
        if (Tools.isDouble(count)) {
            count = String.valueOf(Integer.valueOf(count).intValue() + 1);
        }

    }

    public void increment(String incrementCount) {
        if (Tools.isDouble(count) && Tools.isDouble(incrementCount)) {
            count = String.valueOf(Integer.valueOf(count).intValue() + Integer.valueOf
                    (incrementCount).intValue());
        }

    }

    public void decrement() {
        if (Tools.isDouble(count)) {
            count = String.valueOf(Integer.valueOf(count).intValue() - 1);
        }

    }

    public boolean isHasGoods() {
        if (Tools.isDouble(count)) {
            int number = Integer.valueOf(count).intValue();
            return number >= 1;

        } else {
            return false;
        }


    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public ShoppingCartItemBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.unitPrice);
        dest.writeString(this.productUrl);
        dest.writeString(this.count);
        dest.writeString(this.service);
    }

    protected ShoppingCartItemBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.unitPrice = in.readString();
        this.productUrl = in.readString();
        this.count = in.readString();
        this.service = in.readString();
    }

    public static final Creator<ShoppingCartItemBean> CREATOR = new Creator<ShoppingCartItemBean>() {
        @Override
        public ShoppingCartItemBean createFromParcel(Parcel source) {
            return new ShoppingCartItemBean(source);
        }

        @Override
        public ShoppingCartItemBean[] newArray(int size) {
            return new ShoppingCartItemBean[size];
        }
    };
}
