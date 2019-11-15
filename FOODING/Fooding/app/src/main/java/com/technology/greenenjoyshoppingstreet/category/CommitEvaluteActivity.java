package com.technology.greenenjoyshoppingstreet.category;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartItemBean;
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

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_ADDCOMMENT;

/**
 * Created by Administrator on 2018/1/9.
 */

public class CommitEvaluteActivity extends BaseActivity {


    @BindView(R.id.star_rb)
    RatingBar starRb;
    @BindView(R.id.commit_bt)
    Button commitBt;
    @BindView(R.id.evalute_et)
    EditText evaluteEt;

    private String orderId;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_evaluate);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra(ParameterConstant.ORDER_ID);

        }
    }

    private void initViews() {
        setBarTitle(getString(R.string.send_evaluate));
    }

    private void commit() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CartItemBean>() {

            @Override
            public void onComplete(CartItemBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        tip(R.string.pusblish_successfull);
                        finish();


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
                if (!TextUtils.isEmpty(evaluteEt.getText())) {
                    showLoadingDialog();
                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, orderId));
                    list.add(new BasicKeyValuePair(ParameterConstant.STAR, String.valueOf((int) starRb.getRating())));
                    list.add(new BasicKeyValuePair(ParameterConstant.CONTENT, evaluteEt.getText().toString()));

                    return UserInfoManger.getSignList(list);
                } else {
                    tip(R.string.complete_review);
                }


                return null;

            }


            @Override
            public String createUrl() {

                return ORDER_ADDCOMMENT;
            }
        });
    }

    @OnClick(R.id.commit_bt)
    public void onViewClicked() {
        commit();
    }
}
