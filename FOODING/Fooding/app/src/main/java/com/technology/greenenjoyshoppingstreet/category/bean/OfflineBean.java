package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2018/2/11.
 */

public class OfflineBean extends BaseBean {


    /**
     * data : {"amount":"0.00","orderId":"11","sn":"17122608237064331020"}
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
         * amount : 0.00
         * orderId : 11
         * sn : 17122608237064331020
         */

        private String amount;
        private String orderId;
        private String sn;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

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
    }
}
