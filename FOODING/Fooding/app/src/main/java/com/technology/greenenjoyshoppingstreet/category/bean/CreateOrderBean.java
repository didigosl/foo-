package com.technology.greenenjoyshoppingstreet.category.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Bern on 2018/1/3 0003.
 */

public class CreateOrderBean extends BaseBean {


    /**
     * data : {"orderId":"12","sn":"17122775267064337640","totalAmount":540}
     * code : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * orderId : 12
         * sn : 17122775267064337640
         * totalAmount : 540
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.orderId);
            dest.writeString(this.sn);
            dest.writeString(this.totalAmount);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.orderId = in.readString();
            this.sn = in.readString();
            this.totalAmount = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
