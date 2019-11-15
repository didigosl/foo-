package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2018/8/16.
 */

public class OrderPayBean extends BaseBean {


    /**
     * data : {"amount":"1.35","orderId":"199","sn":"18081678901659225554","stripe_key":null,"customerId":null,"redsys":{"form_action":"https://sis-t.redsys.es:25443/sis/realizarPago","Ds_MerchantParameters":"eyJEU19NRVJDSEFOVF9NRVJDSEFOVFVSTCI6Imh0dHA6XC9cL2Zvb2Qub3oyNGcuY29tXC9hcGl  cL3BheVwvbm90aWZpY2F0aW9uIiwiRFNfTUVSQ0hBTlRfTUVSQ0hBTlRDT0RFIjoiMzQ0NzMyMTY5IiwiRFNfTUVSQ0hBTlRfVEVSTUlOQUwiO  iIwMDEiLCJEU19NRVJDSEFOVF9DVVJSRU5DWSI6OTc4LCJEU19NRVJDSEFOVF9BTU9VTlQiOjEzNSwiRFNfTUVSQ0hBTlRfT1JERVIiOiIxOTl  CMjc4OTkiLCJEU19NRVJDSEFOVF9UUkFOU0FDVElPTlRZUEUiOiIwIiwiRFNfTUVSQ0hBTlRfUEFZTUVUSE9EUyI6IkMiLCJEU19NRVJDSEFOV  F9VUkxPSyI6Imh0dHA6XC9cL2Zvb2Qub3oyNGcuY29tXC93XC9wYXlcL3N1Y2Nlc3M/b3JkZXJfaWQ9MTk5IiwiRFNfTUVSQ0hBTlRfVVJMS0  8iOiJodHRwOlwvXC9mb29kLm96MjRnLmNvbVwvd1wvcGF5XC9mYWlsP29yZGVyX2lkPTE5OSIsIkRTX01FUkNIQU5UX01FUkNIQU5UTkFNRSI6  Ilx1NmIyN1x1NmQzMjI0XHU4ZDJkXHU1NTQ2XHU1N2NlXHU2NTJmXHU0ZWQ4IiwiRFNfTUVSQ0hBTlRfVElUVUxBUiI6Ilx1OGJhMlx1NTM1NV  x1NjUyZlx1NGVkOCIsIkRTX01FUkNIQU5UX1BST0RVQ1RERVNDUklQVElPTiI6Ilx1OGJhMlx1NTM1NVx1N2YxNlx1NTNmN1x1ZmYxYTE4MDgx  Njc4OTAxNjU5MjI1NTU0In0=","Ds_Signature":"GWaVo6+sYY0nifRGfZhhjxzgbGtjyLbwjfdet0D06I4=","Ds_SignatureVersion":"HMAC_SHA256_V1"}}
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
         * amount : 1.35
         * orderId : 199
         * sn : 18081678901659225554
         * stripe_key : null
         * customerId : null
         * redsys : {"form_action":"https://sis-t.redsys.es:25443/sis/realizarPago","Ds_MerchantParameters":"eyJEU19NRVJDSEFOVF9NRVJDSEFOVFVSTCI6Imh0dHA6XC9cL2Zvb2Qub3oyNGcuY29tXC9hcGl  cL3BheVwvbm90aWZpY2F0aW9uIiwiRFNfTUVSQ0hBTlRfTUVSQ0hBTlRDT0RFIjoiMzQ0NzMyMTY5IiwiRFNfTUVSQ0hBTlRfVEVSTUlOQUwiO  iIwMDEiLCJEU19NRVJDSEFOVF9DVVJSRU5DWSI6OTc4LCJEU19NRVJDSEFOVF9BTU9VTlQiOjEzNSwiRFNfTUVSQ0hBTlRfT1JERVIiOiIxOTl  CMjc4OTkiLCJEU19NRVJDSEFOVF9UUkFOU0FDVElPTlRZUEUiOiIwIiwiRFNfTUVSQ0hBTlRfUEFZTUVUSE9EUyI6IkMiLCJEU19NRVJDSEFOV  F9VUkxPSyI6Imh0dHA6XC9cL2Zvb2Qub3oyNGcuY29tXC93XC9wYXlcL3N1Y2Nlc3M/b3JkZXJfaWQ9MTk5IiwiRFNfTUVSQ0hBTlRfVVJMS0  8iOiJodHRwOlwvXC9mb29kLm96MjRnLmNvbVwvd1wvcGF5XC9mYWlsP29yZGVyX2lkPTE5OSIsIkRTX01FUkNIQU5UX01FUkNIQU5UTkFNRSI6  Ilx1NmIyN1x1NmQzMjI0XHU4ZDJkXHU1NTQ2XHU1N2NlXHU2NTJmXHU0ZWQ4IiwiRFNfTUVSQ0hBTlRfVElUVUxBUiI6Ilx1OGJhMlx1NTM1NV  x1NjUyZlx1NGVkOCIsIkRTX01FUkNIQU5UX1BST0RVQ1RERVNDUklQVElPTiI6Ilx1OGJhMlx1NTM1NVx1N2YxNlx1NTNmN1x1ZmYxYTE4MDgx  Njc4OTAxNjU5MjI1NTU0In0=","Ds_Signature":"GWaVo6+sYY0nifRGfZhhjxzgbGtjyLbwjfdet0D06I4=","Ds_SignatureVersion":"HMAC_SHA256_V1"}
         */

        private String amount;
        private String orderId;
        private String sn;
        private Object stripe_key;
        private Object customerId;
        private RedsysBean redsys;

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

        public Object getStripe_key() {
            return stripe_key;
        }

        public void setStripe_key(Object stripe_key) {
            this.stripe_key = stripe_key;
        }

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public RedsysBean getRedsys() {
            return redsys;
        }

        public void setRedsys(RedsysBean redsys) {
            this.redsys = redsys;
        }

        public static class RedsysBean {
            /**
             * form_action : https://sis-t.redsys.es:25443/sis/realizarPago
             * Ds_MerchantParameters : eyJEU19NRVJDSEFOVF9NRVJDSEFOVFVSTCI6Imh0dHA6XC9cL2Zvb2Qub3oyNGcuY29tXC9hcGl  cL3BheVwvbm90aWZpY2F0aW9uIiwiRFNfTUVSQ0hBTlRfTUVSQ0hBTlRDT0RFIjoiMzQ0NzMyMTY5IiwiRFNfTUVSQ0hBTlRfVEVSTUlOQUwiO  iIwMDEiLCJEU19NRVJDSEFOVF9DVVJSRU5DWSI6OTc4LCJEU19NRVJDSEFOVF9BTU9VTlQiOjEzNSwiRFNfTUVSQ0hBTlRfT1JERVIiOiIxOTl  CMjc4OTkiLCJEU19NRVJDSEFOVF9UUkFOU0FDVElPTlRZUEUiOiIwIiwiRFNfTUVSQ0hBTlRfUEFZTUVUSE9EUyI6IkMiLCJEU19NRVJDSEFOV  F9VUkxPSyI6Imh0dHA6XC9cL2Zvb2Qub3oyNGcuY29tXC93XC9wYXlcL3N1Y2Nlc3M/b3JkZXJfaWQ9MTk5IiwiRFNfTUVSQ0hBTlRfVVJMS0  8iOiJodHRwOlwvXC9mb29kLm96MjRnLmNvbVwvd1wvcGF5XC9mYWlsP29yZGVyX2lkPTE5OSIsIkRTX01FUkNIQU5UX01FUkNIQU5UTkFNRSI6  Ilx1NmIyN1x1NmQzMjI0XHU4ZDJkXHU1NTQ2XHU1N2NlXHU2NTJmXHU0ZWQ4IiwiRFNfTUVSQ0hBTlRfVElUVUxBUiI6Ilx1OGJhMlx1NTM1NV  x1NjUyZlx1NGVkOCIsIkRTX01FUkNIQU5UX1BST0RVQ1RERVNDUklQVElPTiI6Ilx1OGJhMlx1NTM1NVx1N2YxNlx1NTNmN1x1ZmYxYTE4MDgx  Njc4OTAxNjU5MjI1NTU0In0=
             * Ds_Signature : GWaVo6+sYY0nifRGfZhhjxzgbGtjyLbwjfdet0D06I4=
             * Ds_SignatureVersion : HMAC_SHA256_V1
             */

            private String form_action;
            private String Ds_MerchantParameters;
            private String Ds_Signature;
            private String Ds_SignatureVersion;

            public String getForm_action() {
                return form_action;
            }

            public void setForm_action(String form_action) {
                this.form_action = form_action;
            }

            public String getDs_MerchantParameters() {
                return Ds_MerchantParameters;
            }

            public void setDs_MerchantParameters(String Ds_MerchantParameters) {
                this.Ds_MerchantParameters = Ds_MerchantParameters;
            }

            public String getDs_Signature() {
                return Ds_Signature;
            }

            public void setDs_Signature(String Ds_Signature) {
                this.Ds_Signature = Ds_Signature;
            }

            public String getDs_SignatureVersion() {
                return Ds_SignatureVersion;
            }

            public void setDs_SignatureVersion(String Ds_SignatureVersion) {
                this.Ds_SignatureVersion = Ds_SignatureVersion;
            }
        }
    }
}
