package com.technology.greenenjoyshoppingstreet.mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2018/1/2 0002.
 */

public class AddressListBean extends BaseBean {

    public static final String DEFAULT_ADDRESS = "1";
    public static final String NO_DEFAULT_ADDRESS = "0";

    /**
     * data : [{"addressId":"2","man":"张三","area":"上海 静安区","address":"建设大街123号256室","defaultFlag":"1"}]
     * code : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * addressId : 2
         * man : 张三
         * area : 上海 静安区
         * address : 建设大街123号256室
         * defaultFlag : 1
         */

        private String addressId;
        private String man;
        private String phone;
        private String address;
        private String defaultFlag;
        private String provinceId;
        private String province;
        private String cityId;
        private String city;


        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public boolean isDefaultAddress() {

            return DEFAULT_ADDRESS.equals(defaultFlag);
        }


        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public String getMan() {
            return man;
        }

        public void setMan(String man) {
            this.man = man;
        }


        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDefaultFlag() {
            return defaultFlag;
        }

        public void setDefaultFlag(String defaultFlag) {
            this.defaultFlag = defaultFlag;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.addressId);
            dest.writeString(this.man);
            dest.writeString(this.phone);
            dest.writeString(this.address);
            dest.writeString(this.defaultFlag);
            dest.writeString(this.provinceId);
            dest.writeString(this.province);
            dest.writeString(this.cityId);
            dest.writeString(this.city);
        }

        protected DataBean(Parcel in) {
            this.addressId = in.readString();
            this.man = in.readString();
            this.phone = in.readString();
            this.address = in.readString();
            this.defaultFlag = in.readString();
            this.provinceId = in.readString();
            this.province = in.readString();
            this.cityId = in.readString();
            this.city = in.readString();
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
