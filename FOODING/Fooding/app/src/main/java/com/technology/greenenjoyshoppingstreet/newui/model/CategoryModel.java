package com.technology.greenenjoyshoppingstreet.newui.model;

public class CategoryModel {
    /**
     * categoryId : 139
     * categoryName : 韩国零食
     * categoryCover : http://food.oz24g.com/uploads/shop1/image/f6/f7/f6f78cb99ab67b48df7f8688552feab6133698.jpg
     * sons : null
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
