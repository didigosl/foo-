package com.technology.greenenjoyshoppingstreet.newui.model;

import java.io.Serializable;
import java.util.List;

public class GoodsDetailMode implements Serializable {

    /**
     * spuId : 5
     * shopId : 1
     * spuName : 中高腰黑色显瘦修身小脚女牛仔裤
     * sn : Z2015789456
     * cover : http://xgj.didigo.es/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg
     * video :
     * pics : ["http://xgj.didigo.eshttp://xianggou.kz/uploads//9e/59/9e595003db0a4e1c4579331d95990e42143958_m.jpg"]
     * labels : [{"labelId":"1","labelName":"新品"}]
     * coupon : [{"levelId":"1","levelName":"年卡VIP","rebate":"0.00"}]
     * originPrice : 5.00
     * price : 3.00
     * stock : 888
     * minInCart : 1
     * minToBuy : 1
     * content : <p>商品介绍详细图文内容</p>
     * status : 1
     * statusText : 上架
     * hasDefaultSku : 0
     * skus : [{"skuId":"1","specInfo":"颜色:红色,尺码:XL","stock":"100","price":"1.80","specMode":"000011"}]
     * specs : [{"specId":"1","specName":"颜色","specs":[{"mode":"1","value":"红色"}]}]
     * comments : {"total":1,"totalPages":1,"pageLimit":20,"page":1,"list":{"orderCommentId":"1","content":"样式好看，质量不错","star":"5","userId":"2","userName":"Jolin Wong","orderCreateTime":"2017-12-01 15:03:40"}}
     */

    private String spuId;
    private String shopId;
    private String spuName;
    private String sn;
    private String cover;
    private String video;
    private String originPrice;
    private String price;
    private String stock;
    private String minInCart;
    private String minToBuy;
    private String content;
    private String status;
    private String statusText;
    private String hasDefaultSku;
    private GoodsCommentModel comments;
    private List<String> pics;
    private List<LabelsBean> labels;
    private List<CouponBean> coupon;
    private List<SkusBean> skus;
    private List<SpecsBeanX> specs;

    public GoodsCommentModel getComments() {
        return comments;
    }

    public void setComments(GoodsCommentModel comments) {
        this.comments = comments;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getHasDefaultSku() {
        return hasDefaultSku;
    }

    public void setHasDefaultSku(String hasDefaultSku) {
        this.hasDefaultSku = hasDefaultSku;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<LabelsBean> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelsBean> labels) {
        this.labels = labels;
    }

    public List<CouponBean> getCoupon() {
        return coupon;
    }

    public void setCoupon(List<CouponBean> coupon) {
        this.coupon = coupon;
    }

    public List<SkusBean> getSkus() {
        return skus;
    }

    public void setSkus(List<SkusBean> skus) {
        this.skus = skus;
    }

    public List<SpecsBeanX> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecsBeanX> specs) {
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

    public static class CouponBean {
        /**
         * levelId : 1
         * levelName : 年卡VIP
         * rebate : 0.00
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

    public static class SkusBean {
        /**
         * skuId : 1
         * specInfo : 颜色:红色,尺码:XL
         * stock : 100
         * price : 1.80
         * specMode : 000011
         */

        private String skuId;
        private String specInfo;
        private String stock;
        private String price;
        private String specMode;

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSpecInfo() {
            return specInfo;
        }

        public void setSpecInfo(String specInfo) {
            this.specInfo = specInfo;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSpecMode() {
            return specMode;
        }

        public void setSpecMode(String specMode) {
            this.specMode = specMode;
        }
    }

    public static class SpecsBeanX {
        /**
         * specId : 1
         * specName : 颜色
         * specs : [{"mode":"1","value":"红色"}]
         */

        private String specId;
        private String specName;
        private List<SpecsBean> specs;

        public String getSpecId() {
            return specId;
        }

        public void setSpecId(String specId) {
            this.specId = specId;
        }

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
        }

        public List<SpecsBean> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBean> specs) {
            this.specs = specs;
        }

        public static class SpecsBean {
            /**
             * mode : 1
             * value : 红色
             */

            private String mode;
            private String value;

            public String getMode() {
                return mode;
            }

            public void setMode(String mode) {
                this.mode = mode;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
