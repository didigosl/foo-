package com.technology.greenenjoyshoppingstreet.mine.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/14.
 */

public class AccountRecordBean extends BaseBean {


    public static final String REBATE  = "rebate";

    /**
     * data : {"totalPages":1,"pageLimit":20,"page":1,"list":[{"moneyLogId":"11","amount":"1.00","money":"25.00","type":"rebate","typeText":"返利","remark":"1级子属：张三","createTime":"2018-01-01 12:12:12"}]}
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
         * list : [{"moneyLogId":"11","amount":"1.00","money":"25.00","type":"rebate","typeText":"返利","remark":"1级子属：张三","createTime":"2018-01-01 12:12:12"}]
         */

        private int totalPages;
        private int pageLimit;
        private int page;
        private List<ListBean> list;

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPageLimit() {
            return pageLimit;
        }

        public void setPageLimit(int pageLimit) {
            this.pageLimit = pageLimit;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * moneyLogId : 11
             * amount : 1.00
             * money : 25.00
             * type : rebate
             * typeText : 返利
             * remark : 1级子属：张三
             * createTime : 2018-01-01 12:12:12
             */

            private String moneyLogId;
            private String amount;
            private String money;
            private String type;
            private String typeText;
            private String remark;
            private String createTime;

            public String getMoneyLogId() {
                return moneyLogId;
            }

            public void setMoneyLogId(String moneyLogId) {
                this.moneyLogId = moneyLogId;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTypeText() {
                return typeText;
            }

            public void setTypeText(String typeText) {
                this.typeText = typeText;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
