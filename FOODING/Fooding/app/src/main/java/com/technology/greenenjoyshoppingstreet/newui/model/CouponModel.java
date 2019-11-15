package com.technology.greenenjoyshoppingstreet.newui.model;

import java.util.List;

public class CouponModel {

    /**
     * totalPages : 1
     * pageLimit : 20
     * page : 1
     * list : [{"couponUserId":"2","couponName":"代金券很牛逼","amount":"1.00","startTime":"2017-12-24","endTime":"2017-12-30","minLimit":"1.00","withRebate":"0","withDiscount":"0"}]
     */

    private int totalPages;
    private int pageLimit;
    private int page;
    private List<CouponListModel> list;

    public List<CouponListModel> getList() {
        return list;
    }

    public void setList(List<CouponListModel> list) {
        this.list = list;
    }

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

}
