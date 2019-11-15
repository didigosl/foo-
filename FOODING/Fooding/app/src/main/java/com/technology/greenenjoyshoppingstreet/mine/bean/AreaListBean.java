package com.technology.greenenjoyshoppingstreet.mine.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2018/1/2 0002.
 */

public class AreaListBean extends BaseBean {


    /**
     * data : [{"areaId":"1","name":"中国","sons":[{"areaId":"243","name":"河北","sons":[{"areaId":"3015","name":"石家庄","sons":null}]}]}]
     * code : 0
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
         * areaId : 1
         * name : 中国
         * sons : [{"areaId":"243","name":"河北","sons":[{"areaId":"3015","name":"石家庄","sons":null}]}]
         */

        private String areaId;
        private String name;
        private List<DataBean> sons;

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DataBean> getSons() {
            return sons;
        }

        public void setSons(List<DataBean> sons) {
            this.sons = sons;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
