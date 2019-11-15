package com.technology.greenenjoyshoppingstreet.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.ArticleDetailBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.FEEDBACK_ADD;

/**
 * Created by Administrator on 2017/12/12.
 */

public class OpinionFeedbackActivity extends BaseActivity {
    @BindView(R.id.title_et)
    EditText titleEt;
    @BindView(R.id.content_et)
    EditText contentEt;
    @BindView(R.id.commit_bt)
    Button commitBt;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opinion_feedback);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        setBarTitle(getString(R.string.Feedback));
    }

    @OnClick(R.id.commit_bt)
    public void onViewClicked() {
        commit();
    }

    private void commit() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ArticleDetailBean>() {

            @Override
            public void onComplete(ArticleDetailBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {

                        tip(getString(R.string.send_successfull));
                        finish();

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

                String title = titleEt.getText().toString();
                String content = contentEt.getText().toString();
                if (!TextUtils.isEmpty(title)) {
                    if (!TextUtils.isEmpty(content)) {
                        showLoadingDialog();

                        List<KeyValuePair> list = new ArrayList<>();
                        list.add(new BasicKeyValuePair(ParameterConstant.TITLE, title));
                        list.add(new BasicKeyValuePair(ParameterConstant.CONTENT, content));
                        return UserInfoManger.getSignList(list);

                    } else {
                        tip(R.string.set_title);
                    }

                } else {
                    tip(R.string.enter_comment);
                }
                return null;


            }


            @Override
            public String createUrl() {

                return FEEDBACK_ADD;
            }
        });

    }
}
