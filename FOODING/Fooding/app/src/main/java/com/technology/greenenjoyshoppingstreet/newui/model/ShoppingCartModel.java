package com.technology.greenenjoyshoppingstreet.newui.model;

import java.util.List;

public class ShoppingCartModel {
    /**
     * totalAmount : 300.00
     * totalRebate : 0.03
     * total : 1
     * list : [{"cartId":"1","spuId":"5","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","specInfo":"颜色:红色,尺码:XL","cover":"http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","price":"100.00","rebate":"1.00","num":"3"}]
     * vips : [{"levelId":"2","levelName":"年卡VIP","rebate":"3.00"}]
     */

    private String totalAmount;
    private String totalRebate;
    private int total;
    private List<ListBean> list;
    private List<VipsBean> vips;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalRebate() {
        return totalRebate;
    }

    public void setTotalRebate(String totalRebate) {
        this.totalRebate = totalRebate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<VipsBean> getVips() {
        return vips;
    }

    public void setVips(List<VipsBean> vips) {
        this.vips = vips;
    }

    public static class ListBean {
        /**
         * cartId : 1
         * spuId : 5
         * spuName : 中高腰黑色显瘦修身小脚女牛仔裤
         * specInfo : 颜色:红色,尺码:XL
         * cover : http://xianggou.kz/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg
         * price : 100.00
         * rebate : 1.00
         * num : 3
         */

        private String cartId;
        private String spuId;
        private String spuName;
        private String specInfo;
        private String cover;
        private String price;
        private String rebate;
        private String num;
        private boolean select;
        private String skuId;
        private String unit;

        public String getSkuId() {
            return skuId;
        }

        public ListBean(String skuId, String spuId, String spuName, String cover, String price,String unit) {
            this.spuId = spuId;
            this.spuName = spuName;
            this.cover = cover;
            this.price = price;
            this.num = "1";
            this.skuId = skuId;
            this.cartId = "-1";
            this.unit = unit;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "cartId='" + cartId + '\'' +
                    ", spuId='" + spuId + '\'' +
                    ", spuName='" + spuName + '\'' +
                    ", specInfo='" + specInfo + '\'' +
                    ", cover='" + cover + '\'' +
                    ", price='" + price + '\'' +
                    ", rebate='" + rebate + '\'' +
                    ", num='" + num + '\'' +
                    ", select=" + select +
                    ", unit=" + unit +
                    '}';
        }

        public ListBean() {
            select = false;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

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

        public String getSpecInfo() {
            return specInfo;
        }

        public void setSpecInfo(String specInfo) {
            this.specInfo = specInfo;
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

        public String getRebate() {
            return rebate;
        }

        public void setRebate(String rebate) {
            this.rebate = rebate;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }

    public static class VipsBean {
        /**
         * levelId : 2
         * levelName : 年卡VIP
         * rebate : 3.00
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
