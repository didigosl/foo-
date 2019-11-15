package com.technology.greenenjoyshoppingstreet.mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2018/1/1.
 */

public class MyAccountBean extends BaseBean {


    /**
     * data : {"money":"100.00"}
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
         * money : 100.00
         */

        private String money;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }


        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.money);
        }

        protected DataBean(Parcel in) {
            this.money = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
