package com.technology.greenenjoyshoppingstreet.newui.model;

import java.util.List;

public class MainGoodsModel {
    /**
     * totalPages : 10
     * pageLimit : 20
     * page : 1
     * list : [{"spuId":"8475","spuName":"韩国红参浓缩液","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301001-cover.jpg","price":"65.00","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8486","spuName":"日本白味增汤拉面 100g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301013-cover.jpg","price":"2.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8487","spuName":"日本红味增汤拉面 100g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301014-cover.jpg","price":"1.95","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8488","spuName":"日本麻油味汤拉面 100g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301015-cover.jpg","price":"1.95","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8512","spuName":"韩国桂皮饮料 238ml","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301039-cover.jpg","price":"1.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8527","spuName":"韩国草莓柚子醋 500ml","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301054-cover.jpg","price":"6.85","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8533","spuName":"日本玄米茶 70g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301060-cover.jpg","price":"3.80","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8543","spuName":"韩国人参茶","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301070-cover.jpg","price":"7.55","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8556","spuName":"韩国锅巴味糖 95g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301083-cover.jpg","price":"2.54","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8559","spuName":"韩国夹心巧克力棒棒脆 50g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301086-cover.jpg","price":"2.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8563","spuName":"韩国虾片 75g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301090-cover.jpg","price":"1.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8576","spuName":"韩国米棒 90g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301103-cover.jpg","price":"2.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8577","spuName":"韩国干脆面炸鸡味 90g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301104-cover.jpg","price":"1.25","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8578","spuName":"DIY抹茶布丁","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301105-cover.jpg","price":"4.25","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8584","spuName":"日本抹茶粉 30g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301111-cover.jpg","price":"15.50","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8623","spuName":"韩国起司拉面 111g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301151-cover.jpg","price":"1.65","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8624","spuName":"韩国牛骨汤面 102g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301152-cover.jpg","price":"1.20","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8642","spuName":"韩国部队汤面 130g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301170-cover.jpg","price":"1.95","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8643","spuName":"韩国炸酱面 140g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301171-cover.jpg","price":"1.40","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null},{"spuId":"8644","spuName":"日本原味炒面 109g","cover":"http://food.oz24g.com/uploads/shop1/import/314/M301172-cover.jpg","price":"1.25","originPrice":"0.00","labels":[{"labelId":"1","labelName":"新品"}],"rebates":null}]
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * spuId : 8475
         * spuName : 韩国红参浓缩液
         * cover : http://food.oz24g.com/uploads/shop1/import/314/M301001-cover.jpg
         * price : 65.00
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
