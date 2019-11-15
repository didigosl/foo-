package com.technology.greenenjoyshoppingstreet.cart.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 */

public class CartItemBean extends BaseBean {


    /**
     * data : {"totalAmount":1260,"totalRebate":0,"total":1,"list":[{"cartId":"1","spuId":"5","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","price":"1.80","rebate":"0.00","num":"7"}]}
     * code : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalAmount : 1260
         * totalRebate : 0
         * total : 1
         * list : [{"cartId":"1","spuId":"5","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","price":"1.80","rebate":"0.00","num":"7"}]
         */

        private String totalAmount;
        private String totalRebate;
        private String total;
        private List<ListBean> list;

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

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * cartId : 1
             * spuId : 5
             * spuName : 中高腰黑色显瘦修身小脚女牛仔裤
             * cover : http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg
             * price : 1.80
             * rebate : 0.00
             * num : 7
             */

            private String cartId;
            private String spuId;
            private String spuName;
            private String cover;
            private String price;
            private String rebate;
            private String num;
            private boolean isChecked;

            public void remove() {
                num = "0";
            }


            public void decrement() {
                if (!TextUtils.isEmpty(num) && TextUtils.isDigitsOnly(num)) {
                    int count = Integer.valueOf(num);
                    if (count > 0) {
                        count--;
                    }
                    num = String.valueOf(count);

                }
            }

            public void increment() {

                if (!TextUtils.isEmpty(num) && TextUtils.isDigitsOnly(num)) {
                    int count = Integer.valueOf(num);
                    count++;
                    num = String.valueOf(count);

                }
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
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

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.cartId);
                dest.writeString(this.spuId);
                dest.writeString(this.spuName);
                dest.writeString(this.cover);
                dest.writeString(this.price);
                dest.writeString(this.rebate);
                dest.writeString(this.num);
                dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.cartId = in.readString();
                this.spuId = in.readString();
                this.spuName = in.readString();
                this.cover = in.readString();
                this.price = in.readString();
                this.rebate = in.readString();
                this.num = in.readString();
                this.isChecked = in.readByte() != 0;
            }

            public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }
    }
}
