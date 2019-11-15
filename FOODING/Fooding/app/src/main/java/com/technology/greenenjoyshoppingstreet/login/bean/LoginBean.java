package com.technology.greenenjoyshoppingstreet.login.bean;


import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2017/8/21.
 */

public class LoginBean extends BaseBean {

    /**
     * data : {"phone":"13800138000","token":"$2y$08$bjRCQlZKYjE4N3RMTEFHa.GVtdFfaQNhJCnOgWGDHBmJD5ymRiQBi","secretKey":"$2y$08$R1BlOXN3d1RKaTRHcGhNe.DtY3NwQX5wAX0x9gsJMxmJZif6vE/EK"}
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
         * phone : 13800138000
         * token : $2y$08$bjRCQlZKYjE4N3RMTEFHa.GVtdFfaQNhJCnOgWGDHBmJD5ymRiQBi
         * secretKey : $2y$08$R1BlOXN3d1RKaTRHcGhNe.DtY3NwQX5wAX0x9gsJMxmJZif6vE/EK
         */


        private String act;
        private String phone;
        private String token;
        private String secretKey;

        public String getAct() {
            return act;
        }

        public void setAct(String act) {
            this.act = act;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    }
}
