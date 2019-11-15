package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2018/1/11.
 */

public class PaypalTokenBean extends BaseBean {


    /**
     * data : {"clientToken":"eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiIyZTg0ZGVkMDViOGE0N2MxYWI0MDQxOTM1YmNkOTczM2ZiZmE1MDUxODU0NTQyYWUzMjBjN2JhMTk3MzllOWE5fGNyZWF0ZWRfYXQ9MjAxOC0wMS0wNVQwMzo1MTozOS40MTE3NjI0MDUrMDAwMFx1MDAyNm1lcmNoYW50X2lkPXZjeHpxOGY3Zzd0emp3c3lcdTAwMjZwdWJsaWNfa2V5PW55eDZ2cmRtMmI3OTZkY3giLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvdmN4enE4ZjdnN3R6andzeS9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzL3ZjeHpxOGY3Zzd0emp3c3kvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vY2xpZW50LWFuYWx5dGljcy5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tL3ZjeHpxOGY3Zzd0emp3c3kifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiTXVkYW8gVGVjaCBDb21wYW55IiwiY2xpZW50SWQiOiJBWVBJcWtnTFMzdFpZdFVjbmhMSXJ5WE1jUVM1WS12Vk5zcklDN09uTUM5c25pMS00eDNvN3BjQjVrdkpuQnJIUU1tSGFkbzVGV0lhcnNLTSIsInByaXZhY3lVcmwiOiJodHRwOi8vZXhhbXBsZS5jb20vcHAiLCJ1c2VyQWdyZWVtZW50VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3RvcyIsImJhc2VVcmwiOiJodHRwczovL2Fzc2V0cy5icmFpbnRyZWVnYXRld2F5LmNvbSIsImFzc2V0c1VybCI6Imh0dHBzOi8vY2hlY2tvdXQucGF5cGFsLmNvbSIsImRpcmVjdEJhc2VVcmwiOm51bGwsImFsbG93SHR0cCI6dHJ1ZSwiZW52aXJvbm1lbnROb05ldHdvcmsiOmZhbHNlLCJlbnZpcm9ubWVudCI6Im9mZmxpbmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJtYXN0ZXJjbGllbnQzIiwiYmlsbGluZ0FncmVlbWVudHNFbmFibGVkIjp0cnVlLCJtZXJjaGFudEFjY291bnRJZCI6Im11ZGFvdGVjaGNvbXBhbnkiLCJjdXJyZW5jeUlzb0NvZGUiOiJFVVIifSwibWVyY2hhbnRJZCI6InZjeHpxOGY3Zzd0emp3c3kiLCJ2ZW5tbyI6Im9mZiJ9","amount":"0.00","orderId":"11","sn":"17122608237064331020"}
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
         * clientToken : eyJ2ZXJzaW9uIjoyLCJhdXRob3JpemF0aW9uRmluZ2VycHJpbnQiOiIyZTg0ZGVkMDViOGE0N2MxYWI0MDQxOTM1YmNkOTczM2ZiZmE1MDUxODU0NTQyYWUzMjBjN2JhMTk3MzllOWE5fGNyZWF0ZWRfYXQ9MjAxOC0wMS0wNVQwMzo1MTozOS40MTE3NjI0MDUrMDAwMFx1MDAyNm1lcmNoYW50X2lkPXZjeHpxOGY3Zzd0emp3c3lcdTAwMjZwdWJsaWNfa2V5PW55eDZ2cmRtMmI3OTZkY3giLCJjb25maWdVcmwiOiJodHRwczovL2FwaS5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tOjQ0My9tZXJjaGFudHMvdmN4enE4ZjdnN3R6andzeS9jbGllbnRfYXBpL3YxL2NvbmZpZ3VyYXRpb24iLCJjaGFsbGVuZ2VzIjpbXSwiZW52aXJvbm1lbnQiOiJzYW5kYm94IiwiY2xpZW50QXBpVXJsIjoiaHR0cHM6Ly9hcGkuc2FuZGJveC5icmFpbnRyZWVnYXRld2F5LmNvbTo0NDMvbWVyY2hhbnRzL3ZjeHpxOGY3Zzd0emp3c3kvY2xpZW50X2FwaSIsImFzc2V0c1VybCI6Imh0dHBzOi8vYXNzZXRzLmJyYWludHJlZWdhdGV3YXkuY29tIiwiYXV0aFVybCI6Imh0dHBzOi8vYXV0aC52ZW5tby5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tIiwiYW5hbHl0aWNzIjp7InVybCI6Imh0dHBzOi8vY2xpZW50LWFuYWx5dGljcy5zYW5kYm94LmJyYWludHJlZWdhdGV3YXkuY29tL3ZjeHpxOGY3Zzd0emp3c3kifSwidGhyZWVEU2VjdXJlRW5hYmxlZCI6dHJ1ZSwicGF5cGFsRW5hYmxlZCI6dHJ1ZSwicGF5cGFsIjp7ImRpc3BsYXlOYW1lIjoiTXVkYW8gVGVjaCBDb21wYW55IiwiY2xpZW50SWQiOiJBWVBJcWtnTFMzdFpZdFVjbmhMSXJ5WE1jUVM1WS12Vk5zcklDN09uTUM5c25pMS00eDNvN3BjQjVrdkpuQnJIUU1tSGFkbzVGV0lhcnNLTSIsInByaXZhY3lVcmwiOiJodHRwOi8vZXhhbXBsZS5jb20vcHAiLCJ1c2VyQWdyZWVtZW50VXJsIjoiaHR0cDovL2V4YW1wbGUuY29tL3RvcyIsImJhc2VVcmwiOiJodHRwczovL2Fzc2V0cy5icmFpbnRyZWVnYXRld2F5LmNvbSIsImFzc2V0c1VybCI6Imh0dHBzOi8vY2hlY2tvdXQucGF5cGFsLmNvbSIsImRpcmVjdEJhc2VVcmwiOm51bGwsImFsbG93SHR0cCI6dHJ1ZSwiZW52aXJvbm1lbnROb05ldHdvcmsiOmZhbHNlLCJlbnZpcm9ubWVudCI6Im9mZmxpbmUiLCJ1bnZldHRlZE1lcmNoYW50IjpmYWxzZSwiYnJhaW50cmVlQ2xpZW50SWQiOiJtYXN0ZXJjbGllbnQzIiwiYmlsbGluZ0FncmVlbWVudHNFbmFibGVkIjp0cnVlLCJtZXJjaGFudEFjY291bnRJZCI6Im11ZGFvdGVjaGNvbXBhbnkiLCJjdXJyZW5jeUlzb0NvZGUiOiJFVVIifSwibWVyY2hhbnRJZCI6InZjeHpxOGY3Zzd0emp3c3kiLCJ2ZW5tbyI6Im9mZiJ9
         * amount : 0.00
         * orderId : 11
         * sn : 17122608237064331020
         */

        private String clientToken;
        private String amount;
        private String orderId;
        private String sn;

        public String getClientToken() {
            return clientToken;
        }

        public void setClientToken(String clientToken) {
            this.clientToken = clientToken;
        }

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
