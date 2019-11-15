package com.technology.greenenjoyshoppingstreet.mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/1.
 */

public class ArticleListBean extends BaseBean {


    /**
     * data : {"totalPages":1,"pageLimit":20,"page":1,"list":[{"articleId":"171","title":"我应该如何提现？"}]}
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
         * totalPages : 1
         * pageLimit : 20
         * page : 1
         * list : [{"articleId":"171","title":"我应该如何提现？"}]
         */

        private String totalPages;
        private String pageLimit;
        private String page;
        private List<ListBean> list;

        public String getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(String totalPages) {
            this.totalPages = totalPages;
        }

        public String getPageLimit() {
            return pageLimit;
        }

        public void setPageLimit(String pageLimit) {
            this.pageLimit = pageLimit;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * articleId : 171
             * title : 我应该如何提现？
             */

            private String articleId;
            private String title;

            public String getArticleId() {
                return articleId;
            }

            public void setArticleId(String articleId) {
                this.articleId = articleId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.articleId);
                dest.writeString(this.title);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.articleId = in.readString();
                this.title = in.readString();
            }

            public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }
    }
}
