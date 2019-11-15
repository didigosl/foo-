package com.technology.greenenjoyshoppingstreet.mine.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class OrderDetialBean extends BaseBean {


    /**
     * data : {"orderId":"10","sn":"17122608217064333699","totalRebate":"0.00","totalDiscount":"0.00","totalCoupon":"0.00","expressFee":"0.00","totalAmount":"5.40","receiveArea":null,"receiveMan":null,"receivePhone":null,"receiveAddress":null,"expressCorpName":null,"expressNo":null,"flag":"1","flagText":"未付款","refoundFlag":"0","refoundFlagText":"","closeFlag":"0","closeFlagText":"","createTime":null,"payTime":null,"deliveryTime":null,"finishTime":null,"paymethod_text":"paypal","transaction_id":null,"goods":[{"spuId":"5","skuId":"1","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","specInfo":"颜色:红色,尺码:XL","amount":"0.00","num":"2"}],"hasComment":0,"comment":{"star":null,"content":null,"createTime":null}}
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
         * orderId : 10
         * sn : 17122608217064333699
         * totalRebate : 0.00
         * totalDiscount : 0.00
         * totalCoupon : 0.00
         * expressFee : 0.00
         * totalAmount : 5.40
         * receiveArea : null
         * receiveMan : null
         * receivePhone : null
         * receiveAddress : null
         * expressCorpName : null
         * expressNo : null
         * flag : 1
         * flagText : 未付款
         * refoundFlag : 0
         * refoundFlagText :
         * closeFlag : 0
         * closeFlagText :
         * createTime : null
         * payTime : null
         * deliveryTime : null
         * finishTime : null
         * paymethod_text : paypal
         * transaction_id : null
         * goods : [{"spuId":"5","skuId":"1","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","specInfo":"颜色:红色,尺码:XL","amount":"0.00","num":"2"}]
         * hasComment : 0
         * comment : {"star":null,"content":null,"createTime":null}
         */

        private String orderId;
        private String sn;
        private String totalRebate;
        private String totalDiscount;
        private String totalCoupon;
        private String expressFee;
        private String totalAmount;
        private String receiveArea;
        private String receiveMan;
        private String receivePhone;
        private String receiveAddress;
        private String expressCorpName;
        private String expressNo;
        private String flag;
        private String flagText;
        private String refoundFlag;
        private String refoundFlagText;
        private String closeFlag;
        private String closeFlagText;
        private String createTime;
        private String payTime;
        private String deliveryTime;
        private String finishTime;
        private String hasComment;
        private CommentBean comment;
        private String paymethodText;
        private String transactionId;

        public String getPaymethodText() {
            return paymethodText;
        }

        public void setPaymethodText(String paymethodText) {
            this.paymethodText = paymethodText;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        private List<OrderListBean.DataBean.ListBean.GoodsBean> goods;

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

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Object getReceiveArea() {
            return receiveArea;
        }

        public void setReceiveArea(String receiveArea) {
            this.receiveArea = receiveArea;
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

        public String getReceiveAddress() {
            return receiveAddress;
        }

        public void setReceiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
        }

        public String getExpressCorpName() {
            return expressCorpName;
        }

        public void setExpressCorpName(String expressCorpName) {
            this.expressCorpName = expressCorpName;
        }

        public String getExpressNo() {
            return expressNo;
        }

        public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
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

        public String getCloseFlag() {
            return closeFlag;
        }

        public void setCloseFlag(String closeFlag) {
            this.closeFlag = closeFlag;
        }

        public String getCloseFlagText() {
            return closeFlagText;
        }

        public void setCloseFlagText(String closeFlagText) {
            this.closeFlagText = closeFlagText;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(String deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }



        public String getHasComment() {
            return hasComment;
        }

        public void setHasComment(String hasComment) {
            this.hasComment = hasComment;
        }

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public List<OrderListBean.DataBean.ListBean.GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<OrderListBean.DataBean.ListBean.GoodsBean> goods) {
            this.goods = goods;
        }

        public static class CommentBean {
            /**
             * star : null
             * content : null
             * createTime : null
             */

            private String star;
            private String content;
            private String createTime;

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }


    }
}
