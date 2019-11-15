package com.technology.greenenjoyshoppingstreet.utils;


/**
 * Created by Administrator on 2017/5/20.
 */

public class CommonParameter {


    private String type;
    private String categoryId;
    private String parentId;
    private String label_id;
    private String order;
    private String page;
    private String pageLimit;

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setPageLimit(String pageLimit) {
        this.pageLimit = pageLimit;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
