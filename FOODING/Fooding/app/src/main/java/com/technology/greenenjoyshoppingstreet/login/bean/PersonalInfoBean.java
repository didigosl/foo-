package com.technology.greenenjoyshoppingstreet.login.bean;


import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2017/8/24 0024.
 */

public class PersonalInfoBean extends BaseBean {


    /**
     * success : 0
     * data : [{"realName":"王红","userAccount":"1001002","unitAccount":"00002","accountStatus":0,"accountStatusInfo":"正常","IDType":"身份证","IDNumber":"3342221191111111111","mobile":"18955555555","homePhone":"05556555555","workPhone":"05556777777","homeAddress":"合肥市","postalcode":"230000"},{"realName":"王红","userAccount":"1001045","unitAccount":"00055","accountStatus":1,"accountStatusInfo":"封存","IDType":"身份证","IDNumber":"3342221191111111111","mobile":"18955555555","homePhone":"05556555555","workPhone":"05556777777","homeAddress":"合肥市","postalcode":"230000"},{"realName":"王红","userAccount":"1001101","unitAccount":"00150","accountStatus":1,"accountStatusInfo":"转移销户","IDType":"身份证","IDNumber":"3342221191111111111","mobile":"18955555555","homePhone":"05556555555","workPhone":"05556777777","homeAddress":"合肥市","postalcode":"230000"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * realName : 王红
         * userAccount : 1001002
         * unitAccount : 00002
         * accountStatus : 0
         * accountStatusInfo : 正常
         * IDType : 身份证
         * IDNumber : 3342221191111111111
         * mobile : 18955555555
         * homePhone : 05556555555
         * workPhone : 05556777777
         * homeAddress : 合肥市
         * postalcode : 230000
         */

        private String realName;
        private String userAccount;
        private String unitAccount;
        private String accountStatus;
        private String accountStatusInfo;
        private String IDType;
        private String IDNumber;
        private String mobile;
        private String homePhone;
        private String workPhone;
        private String homeAddress;
        private String postalcode;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUnitAccount() {
            return unitAccount;
        }

        public void setUnitAccount(String unitAccount) {
            this.unitAccount = unitAccount;
        }

        public String getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
        }

        public String getAccountStatusInfo() {
            return accountStatusInfo;
        }

        public void setAccountStatusInfo(String accountStatusInfo) {
            this.accountStatusInfo = accountStatusInfo;
        }

        public String getIDType() {
            return IDType;
        }

        public void setIDType(String IDType) {
            this.IDType = IDType;
        }

        public String getIDNumber() {
            return IDNumber;
        }

        public void setIDNumber(String IDNumber) {
            this.IDNumber = IDNumber;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHomePhone() {
            return homePhone;
        }

        public void setHomePhone(String homePhone) {
            this.homePhone = homePhone;
        }

        public String getWorkPhone() {
            return workPhone;
        }

        public void setWorkPhone(String workPhone) {
            this.workPhone = workPhone;
        }

        public String getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }
    }
}
//    参数名称	参数说明	参数类型	是否必须
//    realName	真实姓名	String	是
//    userAccount	个人公积金帐号	String	是
//    unitAccount	单位公积金帐号	String	是
//    accountStatus	公积金账户状态	Int	是
//    accountStatusInfo	账户状态说明	String	否
//    IDType	证件类型	String	否
//    IDNumber	证件号码	String	否
//    mobile	手机号码	String	否
//    homePhone	家庭电话	String	否
//    workPhone	单位电话	String	否
//    homeAddress	家庭地址	String	否
//    postalcode	邮政编码	String	否
//说明：
//        (1)一个人在系统中可能存在多个账户，此处返回一个JSON数组。
//        (2)accountStatus：账户状态大致可分为3类，值为0，正常缴存；值为1，封存；其它的值，各种原因销户。状态中文说明由accountStatusInfo字段返回。
//
//        3.3.6.返回示例
//
