package com.technology.greenenjoyshoppingstreet.main.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2017/12/25 0025.
 */

public class FlashSaleBean extends BaseBean {


    /**
     * data : [{"saleId":"3","startTime":"2017-11-01 00:00:00","endTime":"2018-02-01 00:00:00","goods":[{"spuId":"4","spuName":"初语2017秋装新款连帽宽松卫衣女韩版薄款学生套头粉色上衣帽衫潮","cover":"http://xianggou.kz/uploads/images/4d/40/4d407747353e5dde7a326cc33ba49ccf481243_m.jpg","pirce":"200","originPrice":"300"}]}]
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
         * saleId : 3
         * startTime : 2017-11-01 00:00:00
         * endTime : 2018-02-01 00:00:00
         * goods : [{"spuId":"4","spuName":"初语2017秋装新款连帽宽松卫衣女韩版薄款学生套头粉色上衣帽衫潮","cover":"http://xianggou.kz/uploads/images/4d/40/4d407747353e5dde7a326cc33ba49ccf481243_m.jpg","pirce":"200","originPrice":"300"}]
         */

        private String saleId;
        private String startTime;
        private String endTime;
        private List<GoodsBean> goods;

        public String getSaleId() {
            return saleId;
        }

        public void setSaleId(String saleId) {
            this.saleId = saleId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * spuId : 4
             * spuName : 初语2017秋装新款连帽宽松卫衣女韩版薄款学生套头粉色上衣帽衫潮
             * cover : http://xianggou.kz/uploads/images/4d/40/4d407747353e5dde7a326cc33ba49ccf481243_m.jpg
             * pirce : 200
             * originPrice : 300
             */

            private String spuId;
            private String spuName;
            private String cover;
            private String price;
            private String originPrice;

            public String getSpuId() {
                return spuId;
            }

            public void setSpuId(String spuId) {
                this.spuId = spuId;
            }

            public String getSpuName() {
                return spuName;
            }

            public void setSpuName(String spuName) {
                this.spuName = spuName;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOriginPrice() {
                return originPrice;
            }

            public void setOriginPrice(String originPrice) {
                this.originPrice = originPrice;
            }
        }
    }
}
