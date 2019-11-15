package com.technology.greenenjoyshoppingstreet.mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/1.
 */

public class MyCouponBean extends BaseBean {


    /**
     * data : {"totalPages":1,"pageLimit":20,"page":1,"list":[{"couponUserId":"2","couponName":"代金券很牛逼","amount":"1.00","startTime":"2017-12-24","endTime":"2017-12-30","minLimit":"1.00","withRebate":"0","withDiscount":"0"}]}
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
         * totalPages : 1
         * pageLimit : 20
         * page : 1
         * list : [{"couponUserId":"2","couponName":"代金券很牛逼","amount":"1.00","startTime":"2017-12-24","endTime":"2017-12-30","minLimit":"1.00","withRebate":"0","withDiscount":"0"}]
         */

        private String totalPages;
        private String pageLimit;
        private String page;
        private List<ListBean> list;

        public String getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(String totalPages) {
            this.totalPages = totalPages;
        }

        public String getPageLimit() {
            return pageLimit;
        }

        public void setPageLimit(String pageLimit) {
            this.pageLimit = pageLimit;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {


            /**
             * couponUserId : 2
             * couponName : 代金券很牛逼
             * amount : 1.00
             * startTime : 2017-12-24
             * endTime : 2017-12-30
             * minLimit : 1.00
             * withRebate : 0
             * withDiscount : 0
             */

            private String couponUserId;
            private String couponName;
            private String amount;
            private String startTime;
            private String endTime;
            private String minLimit;
            private String withRebate;
            private String withDiscount;

            public String getCouponUserId() {
                return couponUserId;
            }

            public void setCouponUserId(String couponUserId) {
                this.couponUserId = couponUserId;
            }

            public String getCouponName() {
                return couponName;
            }

            public void setCouponName(String couponName) {
                this.couponName = couponName;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getMinLimit() {
                return minLimit;
            }

            public void setMinLimit(String minLimit) {
                this.minLimit = minLimit;
            }

            public String getWithRebate() {
                return withRebate;
            }

            public void setWithRebate(String withRebate) {
                this.withRebate = withRebate;
            }

            public String getWithDiscount() {
                return withDiscount;
            }

            public void setWithDiscount(String withDiscount) {
                this.withDiscount = withDiscount;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.couponUserId);
                dest.writeString(this.couponName);
                dest.writeString(this.amount);
                dest.writeString(this.startTime);
                dest.writeString(this.endTime);
                dest.writeString(this.minLimit);
                dest.writeString(this.withRebate);
                dest.writeString(this.withDiscount);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.couponUserId = in.readString();
                this.couponName = in.readString();
                this.amount = in.readString();
                this.startTime = in.readString();
                this.endTime = in.readString();
                this.minLimit = in.readString();
                this.withRebate = in.readString();
                this.withDiscount = in.readString();
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
