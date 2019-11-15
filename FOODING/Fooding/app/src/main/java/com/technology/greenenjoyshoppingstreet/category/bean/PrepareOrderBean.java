package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.mine.bean.MyCouponBean;
import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2018/2/11 0011.
 */

public class PrepareOrderBean extends BaseBean {


    /**
     * data : {"addressId":"4","areaId":"3300","receiveMan":"李小四","receivePhone":"13800138000","receiveArea":"中国 云南 丽江","receiveAddress":"XX街2号","totalFee":"5.40","totalAmount":"5.40","expressFee":"0.00","totalDiscount":"0.00","totalRebate":"0.00","userId":"11","levelId":"1","coupons":[{"couponUserId":"5","couponName":"代金券很牛逼","amount":"1.00","startTime":"2017-12-24","endTime":"2017-12-30","minLimit":"0","withRebate":"0","withDiscount":"0"}]}
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
         * addressId : 4
         * areaId : 3300
         * receiveMan : 李小四
         * receivePhone : 13800138000
         * receiveArea : 中国 云南 丽江
         * receiveAddress : XX街2号
         * totalFee : 5.40
         * totalAmount : 5.40
         * expressFee : 0.00
         * totalDiscount : 0.00
         * totalRebate : 0.00
         * userId : 11
         * levelId : 1
         * coupons : [{"couponUserId":"5","couponName":"代金券很牛逼","amount":"1.00","startTime":"2017-12-24","endTime":"2017-12-30","minLimit":"0","withRebate":"0","withDiscount":"0"}]
         */

        private String addressId;
        private String areaId;
        private String receiveMan;
        private String receivePhone;
        private String receiveArea;
        private String receiveAddress;
        private String totalFee;
        private String totalAmount;
        private String expressFee;
        private String totalDiscount;
        private String totalRebate;
        private String userId;
        private String levelId;
        private List<MyCouponBean.DataBean.ListBean> coupons;

        public String getAddressId() {
            return addressId;
        }

        public void setAddressId(String addressId) {
            this.addressId = addressId;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getReceiveMan() {
            return receiveMan;
        }

        public void setReceiveMan(String receiveMan) {
            this.receiveMan = receiveMan;
        }

        public String getReceivePhone() {
            return receivePhone;
        }

        public void setReceivePhone(String receivePhone) {
            this.receivePhone = receivePhone;
        }

        public String getReceiveArea() {
            return receiveArea;
        }

        public void setReceiveArea(String receiveArea) {
            this.receiveArea = receiveArea;
        }

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
        }

        public String getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(String totalFee) {
            this.totalFee = totalFee;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getExpressFee() {
            return expressFee;
        }

        public void setExpressFee(String expressFee) {
            this.expressFee = expressFee;
        }

        public String getTotalDiscount() {
            return totalDiscount;
        }

        public void setTotalDiscount(String totalDiscount) {
            this.totalDiscount = totalDiscount;
        }

        public String getTotalRebate() {
            return totalRebate;
        }

        public void setTotalRebate(String totalRebate) {
            this.totalRebate = totalRebate;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLevelId() {
            return levelId;
        }

        public void setLevelId(String levelId) {
            this.levelId = levelId;
        }

        public List<MyCouponBean.DataBean.ListBean> getCoupons() {
            return coupons;
        }

        public void setCoupons(List<MyCouponBean.DataBean.ListBean> coupons) {
            this.coupons = coupons;
        }


    }
}
