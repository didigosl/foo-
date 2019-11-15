package com.technology.greenenjoyshoppingstreet.main.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2017/12/26 0026.
 */

public class IndexRecommendBean extends BaseBean {


    /**
     * data : [{"categoryId":"6","categoryName":"服装","recommendPic":"http://xianggou.kz/uploads/images/08/77/087742f94475ce594886d8117226c7a348321.jpg","list":[{"spuId":"4","spuName":"初语2017秋装新款连帽宽松卫衣女韩版薄款学生套头粉色上衣帽衫潮","cover":"http://xianggou.kz/uploads/images/4d/40/4d407747353e5dde7a326cc33ba49ccf481243_m.jpg","price":"200"}]}]
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
         * categoryId : 6
         * categoryName : 服装
         * recommendPic : http://xianggou.kz/uploads/images/08/77/087742f94475ce594886d8117226c7a348321.jpg
         * list : [{"spuId":"4","spuName":"初语2017秋装新款连帽宽松卫衣女韩版薄款学生套头粉色上衣帽衫潮","cover":"http://xianggou.kz/uploads/images/4d/40/4d407747353e5dde7a326cc33ba49ccf481243_m.jpg","price":"200"}]
         */

        private String categoryId;
        private String categoryName;
        private String recommendPic;
        private List<ListBean> list;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getRecommendPic() {
            return recommendPic;
        }

        public void setRecommendPic(String recommendPic) {
            this.recommendPic = recommendPic;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * spuId : 4
             * spuName : 初语2017秋装新款连帽宽松卫衣女韩版薄款学生套头粉色上衣帽衫潮
             * cover : http://xianggou.kz/uploads/images/4d/40/4d407747353e5dde7a326cc33ba49ccf481243_m.jpg
             * price : 200
             */

            private String spuId;
            private String spuName;
            private String cover;
            private String price;

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
        }
    }
}
