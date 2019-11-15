package com.technology.greenenjoyshoppingstreet.mine;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.AboutUsBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GET_ABOUT_US;

/**
 * Created by Bern on 2017/12/13 0013.
 */

public class AboutUsActivity extends BaseActivity {


    @BindView(R.id.web_view)
    WebView web_view;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        WebSettings wSet = web_view.getSettings();
        wSet.setJavaScriptEnabled(true);
        setBarTitle(getString(R.string.about_us));
        getAboutUs();
    }


    private void getAboutUs() {


        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AboutUsBean>() {

            @Override
            public void onComplete(AboutUsBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
//                        aboutUsContentTv.setText(Html.fromHtml(bean.getData()));
                        web_view.loadData(bean.getData(), "text/html", "UTF-8");
                    } else {
                        tip(bean.getMsg());
                    }


                } else {
                    tip(netRequestStatus.getNote());

                }

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                showLoadingDialog();
                List<KeyValuePair> list = new ArrayList<>();
                return UserInfoManger.getSignList(list);


            }


            @Override
            public String createUrl() {

                return GET_ABOUT_US;
            }
        });
    }
}
