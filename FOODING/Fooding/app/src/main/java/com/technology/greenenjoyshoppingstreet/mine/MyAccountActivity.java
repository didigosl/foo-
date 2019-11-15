package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.MyAccountBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_GETMONEY;

/**
 * Created by Administrator on 2017/12/12.
 */

public class MyAccountActivity extends BaseActivity {
    @BindView(R.id.withdraw_bt)
    FrameLayout withdrawBt;
    @BindView(R.id.account_record_bt)
    FrameLayout accountRecordBt;
    @BindView(R.id.balance_tv)
    TextView balanceTv;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        ButterKnife.bind(this);
        initViews();
        getMyAccount();

    }

    private void initViews() {
        setBarTitle(getString(R.string.my_account));
    }

    @OnClick({R.id.withdraw_bt, R.id.account_record_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.withdraw_bt:
                if (Double.compare(Double.valueOf(dataBean.getMoney()), 0) != 0) {
                    Intent intent = new Intent(this, ApplyCashActivity.class);
                    intent.putExtra(ApplyCashActivity.CASH_BEAN, dataBean);

                    startActivity(intent);
                } else {
                    tip(R.string.not_current_balance_available);
                }
                break;
            case R.id.account_record_bt:
                startActivity(new Intent(this, AccountRecordActivity.class));
                break;
        }
    }

    MyAccountBean.DataBean dataBean;

    private void getMyAccount() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<MyAccountBean>() {

            @Override
            public void onComplete(MyAccountBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        dataBean = bean.getData();
                        if (dataBean != null) {
                            if (!TextUtils.isEmpty(dataBean.getMoney())) {

                                balanceTv.setText(Tools.formatPriceText(dataBean.getMoney()));
                            } else {
                                balanceTv.setText(Tools.formatPriceText("0"));

                            }


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

                showLoadingDialog();
                ArrayList<KeyValuePair> list = new ArrayList<KeyValuePair>();
                return UserInfoManger.getSignList(list);


            }


            @Override
            public String createUrl() {

                return USER_GETMONEY;
            }
        });

    }
}
