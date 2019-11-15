package com.technology.greenenjoyshoppingstreet.cart.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/27.
 */

public class RebateBean extends BaseBean {


    /**
     * data : {"vips":[{"levelName":"年卡VIP","levelId":"2","rebate":"11.11"},{"levelName":"永久VIP","levelId":"3","rebate":"22.22"}]}
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
        private List<VipsBean> vips;

        public List<VipsBean> getVips() {
            return vips;
        }

        public void setVips(List<VipsBean> vips) {
            this.vips = vips;
        }

        public static class VipsBean {
            /**
             * levelName : 年卡VIP
             * levelId : 2
             * rebate : 11.11
             */

            private String levelName;
            private String levelId;
            private String rebate;

            public String getLevelName() {
                return levelName;
            }

            public void setLevelName(String levelName) {
                this.levelName = levelName;
            }

            public String getLevelId() {
                return levelId;
            }

            public void setLevelId(String levelId) {
                this.levelId = levelId;
            }

            public String getRebate() {
                return rebate;
            }

            public void setRebate(String rebate) {
                this.rebate = rebate;
            }
        }
    }
}
