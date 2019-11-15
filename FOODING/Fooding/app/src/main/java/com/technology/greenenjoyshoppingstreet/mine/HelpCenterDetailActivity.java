package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.ArticleDetailBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.ArticleListBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ARTICLE_GET;

/**
 * Created by Bern on 2017/12/13 0013.
 */

public class HelpCenterDetailActivity extends BaseActivity {

    public static final String ARTICLE_BEAN = "ARTICLE_BEAN";
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.content_tv)
    HtmlTextView contentTv;
    private ArticleListBean.DataBean.ListBean articleBean;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center_detail);
        ButterKnife.bind(this);
        initViews();
        getIntentData();


    }


    private void initViews() {


    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            articleBean = intent.getParcelableExtra(ARTICLE_BEAN);
            if (articleBean != null) {
                titleTv.setText(articleBean.getTitle());
                setBarTitle(articleBean.getTitle());
                getArticle();

            }
        }

    }

    private void getArticle() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ArticleDetailBean>() {

            @Override
            public void onComplete(ArticleDetailBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        ArticleDetailBean.DataBean data = bean.getData();
                        if (data != null) {
                            HtmlHttpImageGetter getter = new HtmlHttpImageGetter
                                    (contentTv, null, true);

//                                getter.enableCompressImage(false);
                            contentTv.setHtml(data.getContent(),
                                    getter);
                        }

                    } else {
                        tip(bean.getMsg());
                    }


                } else {
                    tip(netRequestStatus.getNote());

                }
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (articleBean != null) {
                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.ARTICLE_ID, articleBean.getArticleId()));
                    return list;

                }
                return null;

            }


            @Override
            public String createUrl() {

                return ARTICLE_GET;
            }
        });
    }
}
