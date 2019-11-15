package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2018/1/11.
 */

public class BankInfoBean extends BaseBean {


    /**
     * data : {"bank":"工商银行","bankAccount":"88845545454454","bankUser":"张三","bankIntro":"请汇款后及时与我们联系"}
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
         * bank : 工商银行
         * bankAccount : 88845545454454
         * bankUser : 张三
         * bankIntro : 请汇款后及时与我们联系
         */

        private String bank;
        private String bankAccount;
        private String bankUser;
        private String bankIntro;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public String getBankUser() {
            return bankUser;
        }

        public void setBankUser(String bankUser) {
            this.bankUser = bankUser;
        }

        public String getBankIntro() {
            return bankIntro;
        }

        public void setBankIntro(String bankIntro) {
            this.bankIntro = bankIntro;
        }
    }
}
