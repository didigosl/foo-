package com.technology.greenenjoyshoppingstreet.mine.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2018/1/1.
 */

public class ArticleDetailBean extends BaseBean {


    /**
     * data : {"articleId":"171","title":"我应该如何提现？","content":"<p>您好，目前账户余额暂无法直接提取到您的银行卡。但您可以将账户余额转入京东小金库，再从小金库提取至您的银行卡。若您未开通或无法开通京东小金库，您可以联系京东客服，京东客服验证您的身份后，请您提供银行卡信息，京东客服将为您处理。<\/p>"}
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
         * articleId : 171
         * title : 我应该如何提现？
         * content : <p>您好，目前账户余额暂无法直接提取到您的银行卡。但您可以将账户余额转入京东小金库，再从小金库提取至您的银行卡。若您未开通或无法开通京东小金库，您可以联系京东客服，京东客服验证您的身份后，请您提供银行卡信息，京东客服将为您处理。</p>
         */

        private String articleId;
        private String title;
        private String content;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
