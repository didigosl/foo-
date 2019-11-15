package com.technology.greenenjoyshoppingstreet.mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class OrderListBean extends BaseBean {


    /**
     * data : {"totalPages":1,"pageLimit":20,"page":1,"list":[{"orderId":"1","sn":"Z2017111218541","totalAmount":"7.00","totalRebate":"0.15","totalDiscount":"1.00","totalCoupon":"1.00","expressFee":"0.00","totalFee":"10.00","createTime":"2017-12-01 15:03:40","flag":"1","flagText":"未付款","refoundFlag":1,"refoundFlagText":"申请退款","goods":[{"spuId":"5","skuId":"1","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","specInfo":"颜色：红色，尺码：L","amount":"2.00","num":"1"}]}]}
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
         * list : [{"orderId":"1","sn":"Z2017111218541","totalAmount":"7.00","totalRebate":"0.15","totalDiscount":"1.00","totalCoupon":"1.00","expressFee":"0.00","totalFee":"10.00","createTime":"2017-12-01 15:03:40","flag":"1","flagText":"未付款","refoundFlag":1,"refoundFlagText":"申请退款","goods":[{"spuId":"5","skuId":"1","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","specInfo":"颜色：红色，尺码：L","amount":"2.00","num":"1"}]}]
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
             * orderId : 1
             * sn : Z2017111218541
             * totalAmount : 7.00
             * totalRebate : 0.15
             * totalDiscount : 1.00
             * totalCoupon : 1.00
             * expressFee : 0.00
             * totalFee : 10.00
             * createTime : 2017-12-01 15:03:40
             * flag : 1
             * flagText : 未付款
             * refoundFlag : 1
             * refoundFlagText : 申请退款
             * goods : [{"spuId":"5","skuId":"1","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","specInfo":"颜色：红色，尺码：L","amount":"2.00","num":"1"}]
             */

            private String orderId;
            private String sn;
            private String totalAmount;
            private String totalRebate;
            private String totalDiscount;
            private String totalCoupon;
            private String expressFee;
            private String totalFee;
            private String createTime;
            private String flag;
            private String flagText;
            private String refoundFlag;
            private String refoundFlagText;
            private List<GoodsBean> goods;

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

            public String getTotalRebate() {
                return totalRebate;
            }

            public void setTotalRebate(String totalRebate) {
                this.totalRebate = totalRebate;
            }

            public String getTotalDiscount() {
                return totalDiscount;
            }

            public void setTotalDiscount(String totalDiscount) {
                this.totalDiscount = totalDiscount;
            }

            public String getTotalCoupon() {
                return totalCoupon;
            }

            public void setTotalCoupon(String totalCoupon) {
                this.totalCoupon = totalCoupon;
            }

            public String getExpressFee() {
                return expressFee;
            }

            public void setExpressFee(String expressFee) {
                this.expressFee = expressFee;
            }

            public String getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(String totalFee) {
                this.totalFee = totalFee;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }

            public String getFlagText() {
                return flagText;
            }

            public void setFlagText(String flagText) {
                this.flagText = flagText;
            }

            public String getRefoundFlag() {
                return refoundFlag;
            }

            public void setRefoundFlag(String refoundFlag) {
                this.refoundFlag = refoundFlag;
            }

            public String getRefoundFlagText() {
                return refoundFlagText;
            }

            public void setRefoundFlagText(String refoundFlagText) {
                this.refoundFlagText = refoundFlagText;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean implements Parcelable {


                /**
                 * spuId : 5
                 * skuId : 1
                 * cover : http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg
                 * spuName : 中高腰黑色显瘦修身小脚女牛仔裤
                 * specInfo : 颜色：红色，尺码：L
                 * amount : 2.00
                 * num : 1
                 */

                private String spuId;
                private String skuId;
                private String cover;
                private String spuName;
                private String specInfo;
                private String amount;
                private String num;

                public String getSpuId() {
                    return spuId;
                }

                public void setSpuId(String spuId) {
                    this.spuId = spuId;
                }

                public String getSkuId() {
                    return skuId;
                }

                public void setSkuId(String skuId) {
                    this.skuId = skuId;
                }

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
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

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
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
                    dest.writeString(this.spuId);
                    dest.writeString(this.skuId);
                    dest.writeString(this.cover);
                    dest.writeString(this.spuName);
                    dest.writeString(this.specInfo);
                    dest.writeString(this.amount);
                    dest.writeString(this.num);
                }

                public GoodsBean() {
                }

                protected GoodsBean(Parcel in) {
                    this.spuId = in.readString();
                    this.skuId = in.readString();
                    this.cover = in.readString();
                    this.spuName = in.readString();
                    this.specInfo = in.readString();
                    this.amount = in.readString();
                    this.num = in.readString();
                }

                public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
                    @Override
                    public GoodsBean createFromParcel(Parcel source) {
                        return new GoodsBean(source);
                    }

                    @Override
                    public GoodsBean[] newArray(int size) {
                        return new GoodsBean[size];
                    }
                };
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
                dest.writeString(this.totalRebate);
                dest.writeString(this.totalDiscount);
                dest.writeString(this.totalCoupon);
                dest.writeString(this.expressFee);
                dest.writeString(this.totalFee);
                dest.writeString(this.createTime);
                dest.writeString(this.flag);
                dest.writeString(this.flagText);
                dest.writeString(this.refoundFlag);
                dest.writeString(this.refoundFlagText);
                dest.writeList(this.goods);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.orderId = in.readString();
                this.sn = in.readString();
                this.totalAmount = in.readString();
                this.totalRebate = in.readString();
                this.totalDiscount = in.readString();
                this.totalCoupon = in.readString();
                this.expressFee = in.readString();
                this.totalFee = in.readString();
                this.createTime = in.readString();
                this.flag = in.readString();
                this.flagText = in.readString();
                this.refoundFlag = in.readString();
                this.refoundFlagText = in.readString();
                this.goods = new ArrayList<GoodsBean>();
                in.readList(this.goods, GoodsBean.class.getClassLoader());
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
