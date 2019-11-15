package com.technology.greenenjoyshoppingstreet.main.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2017/12/15 0015.
 */

public class NextSaleBean extends BaseBean {


    /**
     * data : {"saleId":"4","startTime":"2017-12-03 00:00:00","endTime":"2017-12-20 00:00:00","goods":[{"spuId":"3","spuName":"2017秋冬新款粉色飞行员夹克短外套","cover":"/uploads/images/30/68/3068e962b1dca0d26270b2355f3300c8536864_m.jpg","pirce":"168","originPrice":"188"}]}
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
         * saleId : 4
         * startTime : 2017-12-03 00:00:00
         * endTime : 2017-12-20 00:00:00
         * goods : [{"spuId":"3","spuName":"2017秋冬新款粉色飞行员夹克短外套","cover":"/uploads/images/30/68/3068e962b1dca0d26270b2355f3300c8536864_m.jpg","pirce":"168","originPrice":"188"}]
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
             * spuId : 3
             * spuName : 2017秋冬新款粉色飞行员夹克短外套
             * cover : /uploads/images/30/68/3068e962b1dca0d26270b2355f3300c8536864_m.jpg
             * pirce : 168
             * originPrice : 188
             */

            private String spuId;
            private String spuName;
            private String cover;
            private String pirce;
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

            public String getPirce() {
                return pirce;
            }

            public void setPirce(String pirce) {
                this.pirce = pirce;
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
