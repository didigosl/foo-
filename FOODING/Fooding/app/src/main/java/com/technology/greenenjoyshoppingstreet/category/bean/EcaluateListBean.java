package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/21.
 */

public class EcaluateListBean extends BaseBean {


    /**
     * data : {"total":1,"totalPages":1,"pageLimit":20,"page":1,"list":{"orderCommentId":"1","content":"样式好看，质量不错","star":"5","userId":"2","userName":"Jolin Wong","orderCreateTime":"2017-12-11 10:52:10"}}
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
         * total : 1
         * totalPages : 1
         * pageLimit : 20
         * page : 1
         * list : {"orderCommentId":"1","content":"样式好看，质量不错","star":"5","userId":"2","userName":"Jolin Wong","orderCreateTime":"2017-12-11 10:52:10"}
         */

        private int total;
        private int totalPages;
        private int pageLimit;
        private int page;
        private List<ProductDetailBean.DataBean.CommentsBean.ListBean> list;

        public int getTotal() {
            return total;
        }

        public List<ProductDetailBean.DataBean.CommentsBean.ListBean> getList() {
            return list;
        }

        public void setList(List<ProductDetailBean.DataBean.CommentsBean.ListBean> list) {
            this.list = list;
        }

        public void setTotal(int total) {
            this.total = total;
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
}
