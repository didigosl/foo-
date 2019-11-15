package com.technology.greenenjoyshoppingstreet.newui.model;

import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.ktnewui.model.GoodSpu;

import java.util.List;

public class GoodsModel {


    /**
     * totalPages : 2
     * pageLimit : 20
     * page : 1
     * list : [{"spuId":"8559","spuName":"韩国夹心巧克力棒棒脆  50g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301086-cover.jpg","price":"2.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8563","spuName":"韩国虾片 75g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301090-cover.jpg","price":"1.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8576","spuName":"韩国米棒  90g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301103-cover.jpg","price":"2.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8577","spuName":"韩国干脆面炸鸡味 90g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301104-cover.jpg","price":"1.25","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8578","spuName":"DIY抹茶布丁","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301105-cover.jpg","price":"4.25","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8560","spuName":"韩国乐天原味红巧克力棒 47g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301087-cover.jpg","price":"2.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8564","spuName":"韩国虾条  75g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301091-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8565","spuName":"韩国辣虾条  75g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301092-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8566","spuName":"韩国甜红薯条 55g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301093-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8567","spuName":"韩国马铃薯条 55g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301094-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8568","spuName":"韩国蜂蜜小球  115g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301095-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8569","spuName":"韩国小章鱼薯片 60g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301096-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8570","spuName":"韩国香蕉味零食  45g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301097-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8571","spuName":"韩国辣火鸡味杏仁 30g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301098-cover.jpg","price":"1.75","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8572","spuName":"韩国鱿鱼味零食 55g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301099-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8573","spuName":"韩国蟹味零食 50g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301100-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8574","spuName":"韩国蜂蜜条 75g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301101-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8575","spuName":"韩国辣味洋葱圈 40g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301102-cover.jpg","price":"1.35","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8579","spuName":"韩国高笑美饼干  80g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301106-cover.jpg","price":"2.25","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8580","spuName":"芥末味豌豆  180g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301107-cover.jpg","price":"5.55","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null}]
     */

    private int totalPages;
    private int pageLimit;
    private int page;
    private List<ListBean> list;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * spuId : 8559
         * spuName : 韩国夹心巧克力棒棒脆  50g
         * cover : http://food.oz24g.com/uploads/shop1/import/314/M301086-cover.jpg
         * price : 2.65
         * originPrice : 0.00
         * labels : [{"labelId":"1","labelName":"新品"}]
         * rebates : null
         */

        private String spuId;
        private String spuName;
        private String cover;
        private String price;
        private String originPrice;
        private Object rebates;
        private List<LabelsBean> labels;

        private String unit;
        private String stock;
        private String minInCart;
        private String minToBuy;
        private String perLimit;
        private String hasDefaultSku;
        private List<GoodSpu.Data.Good.Sku> skus;
        private List<ProductDetailBean.DataBean.SpecsBeanX> specs;


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

        public Object getRebates() {
            return rebates;
        }

        public void setRebates(Object rebates) {
            this.rebates = rebates;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getMinInCart() {
            return minInCart;
        }

        public void setMinInCart(String minInCart) {
            this.minInCart = minInCart;
        }

        public String getMinToBuy() {
            return minToBuy;
        }

        public void setMinToBuy(String minToBuy) {
            this.minToBuy = minToBuy;
        }

        public String getPerLimit() {
            return perLimit;
        }

        public void setPerLimit(String perLimit) {
            this.perLimit = perLimit;
        }

        public String getHasDefaultSku() {
            return hasDefaultSku;
        }

        public void setHasDefaultSku(String hasDefaultSku) {
            this.hasDefaultSku = hasDefaultSku;
        }

        public List<GoodSpu.Data.Good.Sku> getSkus() {
            return skus;
        }

        public void setSkus(List<GoodSpu.Data.Good.Sku> skus) {
            this.skus = skus;
        }

        public List<ProductDetailBean.DataBean.SpecsBeanX> getSpecs() {
            return specs;
        }

        public void setSpecs(List<ProductDetailBean.DataBean.SpecsBeanX> specs) {
            this.specs = specs;
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
    }
}
