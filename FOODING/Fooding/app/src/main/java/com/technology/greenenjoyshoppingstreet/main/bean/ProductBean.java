package com.technology.greenenjoyshoppingstreet.main.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Bern on 2017/12/26 0026.
 */

public class ProductBean extends BaseBean {


    /**
     * data : {"totalPages":1,"pageLimit":20,"page":1,"list":[{"spuId":"2","spuName":"康宝莱奶昔套餐 代餐奶昔 曲奇香草巧克力草莓薄荷味蛋白混合饮料","cover":"http://food.oz24g.com/uploads/images/fd/99/fd992d13624d11ac4462fb65492fac53259360_m.jpg","price":"179.02","originPrice":"179.01","labels":[{"labelId":"1","labelName":"新品"},{"labelId":"2","labelName":"热门"},{"labelId":"3","labelName":"折扣"},{"labelId":"4","labelName":"包邮"}],"rebates":[{"levelId":"2","levelName":"年卡VIP","rebate":0.112},{"levelId":"3","levelName":"永久VIP","rebate":0.223}]},{"spuId":"2","spuName":"康宝莱奶昔套餐 代餐奶昔 曲奇香草巧克力草莓薄荷味蛋白混合饮料","cover":"http://food.oz24g.com/uploads/images/fd/99/fd992d13624d11ac4462fb65492fac53259360_m.jpg","price":"179.02","originPrice":"179.01","labels":[{"labelId":"1","labelName":"新品"},{"labelId":"2","labelName":"热门"},{"labelId":"3","labelName":"折扣"},{"labelId":"4","labelName":"包邮"}],"rebates":[{"levelId":"2","levelName":"年卡VIP","rebate":0.112},{"levelId":"3","levelName":"永久VIP","rebate":0.223}]},{"spuId":"1","spuName":"biokap","cover":"http://food.oz24g.com/uploads/images/fa/03/fa0349366fbedf37993fd7d9126c5847250464_m.jpg","price":"5.05","originPrice":"6.00","labels":[],"rebates":{"2":{"levelId":"2","levelName":"年卡VIP","rebate":0.345},"3":{"levelId":"3","levelName":"永久VIP","rebate":0.456}}}]}
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
         * totalPages : 1
         * pageLimit : 20
         * page : 1
         * list : [{"spuId":"2","spuName":"康宝莱奶昔套餐 代餐奶昔 曲奇香草巧克力草莓薄荷味蛋白混合饮料","cover":"http://food.oz24g.com/uploads/images/fd/99/fd992d13624d11ac4462fb65492fac53259360_m.jpg","price":"179.02","originPrice":"179.01","labels":[{"labelId":"1","labelName":"新品"},{"labelId":"2","labelName":"热门"},{"labelId":"3","labelName":"折扣"},{"labelId":"4","labelName":"包邮"}],"rebates":[{"levelId":"2","levelName":"年卡VIP","rebate":0.112},{"levelId":"3","levelName":"永久VIP","rebate":0.223}]},{"spuId":"2","spuName":"康宝莱奶昔套餐 代餐奶昔 曲奇香草巧克力草莓薄荷味蛋白混合饮料","cover":"http://food.oz24g.com/uploads/images/fd/99/fd992d13624d11ac4462fb65492fac53259360_m.jpg","price":"179.02","originPrice":"179.01","labels":[{"labelId":"1","labelName":"新品"},{"labelId":"2","labelName":"热门"},{"labelId":"3","labelName":"折扣"},{"labelId":"4","labelName":"包邮"}],"rebates":[{"levelId":"2","levelName":"年卡VIP","rebate":0.112},{"levelId":"3","levelName":"永久VIP","rebate":0.223}]},{"spuId":"1","spuName":"biokap","cover":"http://food.oz24g.com/uploads/images/fa/03/fa0349366fbedf37993fd7d9126c5847250464_m.jpg","price":"5.05","originPrice":"6.00","labels":[],"rebates":{"2":{"levelId":"2","levelName":"年卡VIP","rebate":0.345},"3":{"levelId":"3","levelName":"永久VIP","rebate":0.456}}}]
         */

        private String totalPages;
        private String pageLimit;
        private String page;
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public String getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(String totalPages) {
            this.totalPages = totalPages;
        }

        public String getPageLimit() {
            return pageLimit;
        }

        public void setPageLimit(String pageLimit) {
            this.pageLimit = pageLimit;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public static class ListBean {
            /**
             * spuId : 2
             * spuName : 康宝莱奶昔套餐 代餐奶昔 曲奇香草巧克力草莓薄荷味蛋白混合饮料
             * cover : http://food.oz24g.com/uploads/images/fd/99/fd992d13624d11ac4462fb65492fac53259360_m.jpg
             * price : 179.02
             * originPrice : 179.01
             * labels : [{"labelId":"1","labelName":"新品"},{"labelId":"2","labelName":"热门"},{"labelId":"3","labelName":"折扣"},{"labelId":"4","labelName":"包邮"}]
             * rebates : [{"levelId":"2","levelName":"年卡VIP","rebate":0.112},{"levelId":"3","levelName":"永久VIP","rebate":0.223}]
             */

            private String spuId;
            private String spuName;
            private String cover;
            private String price;
            private String originPrice;
            private List<LabelsBean> labels;
            private List<RebatesBean> rebates;

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

            public List<LabelsBean> getLabels() {
                return labels;
            }

            public void setLabels(List<LabelsBean> labels) {
                this.labels = labels;
            }

            public List<RebatesBean> getRebates() {
                return rebates;
            }

            public void setRebates(List<RebatesBean> rebates) {
                this.rebates = rebates;
            }

            public static class LabelsBean {
                /**
                 * labelId : 1
                 * labelName : 新品
                 */

                private String labelId;
                private String labelName;

                public String getLabelId() {
                    return labelId;
                }

                public void setLabelId(String labelId) {
                    this.labelId = labelId;
                }

                public String getLabelName() {
                    return labelName;
                }

                public void setLabelName(String labelName) {
                    this.labelName = labelName;
                }
            }

            public static class RebatesBean {
                /**
                 * levelId : 2
                 * levelName : 年卡VIP
                 * rebate : 0.112
                 */

                private String levelId;
                private String levelName;
                private String rebate;

                public String getLevelId() {
                    return levelId;
                }

                public void setLevelId(String levelId) {
                    this.levelId = levelId;
                }

                public String getLevelName() {
                    return levelName;
                }

                public void setLevelName(String levelName) {
                    this.levelName = levelName;
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
}
