package com.technology.greenenjoyshoppingstreet.newui.model;

import java.io.Serializable;
import java.util.List;

public class MainClassModel implements Serializable {
    /**
     * categoryId : 123
     * categoryName : 日韩专区
     * categoryCover : null
     * sons : [{"categoryId":"124","categoryName":"韩国馆","categoryCover":"http://food.oz24g.com/uploads/shop1/image/f1/b1/f1b1e610766c5ea39c20995d14bff96476510.png","sons":[{"categoryId":"139","categoryName":"韩国零食","categoryCover":"http://food.oz24g.com/uploads/shop1/image/f6/f7/f6f78cb99ab67b48df7f8688552feab6133698.jpg","sons":null},{"categoryId":"128","categoryName":"韩国方便面","categoryCover":"http://food.oz24g.com/uploads/shop1/image/d9/91/d991839b72a7b70ec3f710c8ddad6e2e138587.jpg","sons":null},{"categoryId":"125","categoryName":"韩国饮品","categoryCover":"http://food.oz24g.com/uploads/shop1/image/6e/a6/6ea6ca1dd9040a5897747466ab61604d87244.jpg","sons":null},{"categoryId":"129","categoryName":"韩国酒水","categoryCover":"http://food.oz24g.com/uploads/shop1/image/9f/f3/9ff39ffe67a2efc544cd11da7ba6bf4d57081.jpg","sons":null},{"categoryId":"132","categoryName":"韩国茶饮","categoryCover":"http://food.oz24g.com/uploads/shop1/image/07/b8/07b864ecff423560b337122a6819bc3d124343.jpg","sons":null},{"categoryId":"133","categoryName":"韩国糖果","categoryCover":"http://food.oz24g.com/uploads/shop1/image/34/2d/342d5a4bf590e43ca4874070572c58cb89002.jpg","sons":null},{"categoryId":"134","categoryName":"韩国罐头","categoryCover":"http://food.oz24g.com/uploads/shop1/image/e6/3e/e63e64939e7aa92ae17c415eaf0c2bb1141485.jpg","sons":null},{"categoryId":"135","categoryName":"韩国紫菜","categoryCover":"http://food.oz24g.com/uploads/shop1/image/50/60/5060a084e9eae361339b0b7db6c2e963114029.jpg","sons":null},{"categoryId":"138","categoryName":"韩国米面","categoryCover":"http://food.oz24g.com/uploads/shop1/image/a6/d6/a6d671974762ee88887503808f6b90e088559.jpg","sons":null}]},{"categoryId":"126","categoryName":"日本馆","categoryCover":"http://food.oz24g.com/uploads/shop1/image/81/74/817463939c879da592b18ca27ca46eb565878.png","sons":[{"categoryId":"127","categoryName":"日本零食","categoryCover":"http://food.oz24g.com/uploads/shop1/image/dc/83/dc838ebcafe1a672fc451adce6a15e00138732.jpg","sons":null},{"categoryId":"137","categoryName":"日本方便面","categoryCover":"http://food.oz24g.com/uploads/shop1/image/54/c7/54c7b1ab005aa432da012b3b17d82ff5133035.jpg","sons":null},{"categoryId":"131","categoryName":"日本饮品","categoryCover":"http://food.oz24g.com/uploads/shop1/image/b8/2d/b82d62ec11904279152983c8046fbbac87225.jpg","sons":null},{"categoryId":"130","categoryName":"日本酒水","categoryCover":"http://food.oz24g.com/uploads/shop1/image/71/a7/71a7ff486c033d4ffb3127aaa469de1339215.jpg","sons":null},{"categoryId":"136","categoryName":"日本汤料","categoryCover":"http://food.oz24g.com/uploads/shop1/image/4c/bd/4cbd4e83fbe68b675f88368d30993c6d124230.jpg","sons":null}]}]
     */

    private String categoryId;
    private String categoryName;
    private String categoryCover;
    private List<SonsBeanX> sons;

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

    public String getCategoryCover() {
        return categoryCover;
    }

    public void setCategoryCover(String categoryCover) {
        this.categoryCover = categoryCover;
    }

    public List<SonsBeanX> getSons() {
        return sons;
    }

    public void setSons(List<SonsBeanX> sons) {
        this.sons = sons;
    }

    public static class SonsBeanX implements Serializable{
        /**
         * categoryId : 124
         * categoryName : 韩国馆
         * categoryCover : http://food.oz24g.com/uploads/shop1/image/f1/b1/f1b1e610766c5ea39c20995d14bff96476510.png
         * sons : [{"categoryId":"139","categoryName":"韩国零食","categoryCover":"http://food.oz24g.com/uploads/shop1/image/f6/f7/f6f78cb99ab67b48df7f8688552feab6133698.jpg","sons":null},{"categoryId":"128","categoryName":"韩国方便面","categoryCover":"http://food.oz24g.com/uploads/shop1/image/d9/91/d991839b72a7b70ec3f710c8ddad6e2e138587.jpg","sons":null},{"categoryId":"125","categoryName":"韩国饮品","categoryCover":"http://food.oz24g.com/uploads/shop1/image/6e/a6/6ea6ca1dd9040a5897747466ab61604d87244.jpg","sons":null},{"categoryId":"129","categoryName":"韩国酒水","categoryCover":"http://food.oz24g.com/uploads/shop1/image/9f/f3/9ff39ffe67a2efc544cd11da7ba6bf4d57081.jpg","sons":null},{"categoryId":"132","categoryName":"韩国茶饮","categoryCover":"http://food.oz24g.com/uploads/shop1/image/07/b8/07b864ecff423560b337122a6819bc3d124343.jpg","sons":null},{"categoryId":"133","categoryName":"韩国糖果","categoryCover":"http://food.oz24g.com/uploads/shop1/image/34/2d/342d5a4bf590e43ca4874070572c58cb89002.jpg","sons":null},{"categoryId":"134","categoryName":"韩国罐头","categoryCover":"http://food.oz24g.com/uploads/shop1/image/e6/3e/e63e64939e7aa92ae17c415eaf0c2bb1141485.jpg","sons":null},{"categoryId":"135","categoryName":"韩国紫菜","categoryCover":"http://food.oz24g.com/uploads/shop1/image/50/60/5060a084e9eae361339b0b7db6c2e963114029.jpg","sons":null},{"categoryId":"138","categoryName":"韩国米面","categoryCover":"http://food.oz24g.com/uploads/shop1/image/a6/d6/a6d671974762ee88887503808f6b90e088559.jpg","sons":null}]
         */

        private String categoryId;
        private String categoryName;
        private String categoryCover;

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

        public String getCategoryCover() {
            return categoryCover;
        }

        public void setCategoryCover(String categoryCover) {
            this.categoryCover = categoryCover;
        }

    }
}
