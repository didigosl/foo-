package com.technology.greenenjoyshoppingstreet.newui.model;

import java.util.List;

public class GoodsCommentModel {
    private int total;
    private int totalPages;
    private int pageLimit;
    private int page;
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * orderCommentId : 1
         * content : 样式好看，质量不错
         * star : 5
         * userId : 2
         * userName : Jolin Wong
         * orderCreateTime : 2017-12-01 15:03:40
         */

        private String orderCommentId;
        private String content;
        private String star;
        private String userId;
        private String userName;
        private String orderCreateTime;

//        public ListBean() {
//            content = "111";
//            star = "4";
//            userName = "313";
//            orderCreateTime = "2222220";
//        }

        public String getOrderCommentId() {
            return orderCommentId;
        }

        public void setOrderCommentId(String orderCommentId) {
            this.orderCommentId = orderCommentId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getOrderCreateTime() {
            return orderCreateTime;
        }

        public void setOrderCreateTime(String orderCreateTime) {
            this.orderCreateTime = orderCreateTime;
        }
    }
}
