package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.MyAccountBean;
import com.technology.greenenjoyshoppingstreet.utils.BaseBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
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

import static com.technology.greenenjoyshoppingstreet.R.id.price_tv;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.DRAW_CREATE;

/**
 * Created by Administrator on 2018/1/14.
 */

public class ApplyCashActivity extends BaseActivity {

    public static final String CASH_BEAN = "CASH_BEAN";

    @BindView(price_tv)
    TextView priceTv;
    @BindView(R.id.all_cash)
    RelativeLayout allCash;
    @BindView(R.id.balance_tv)
    TextView balanceTv;
    @BindView(R.id.confirm_cash_bt)
    Button confirmCashBt;
    @BindView(R.id.weixin_tv)
    TextView weixinTv;
    MyAccountBean.DataBean dataBean;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_cash);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {

            dataBean = intent.getParcelableExtra(CASH_BEAN);
            if (dataBean != null) {
                priceTv.setText(Tools.formatPriceText(dataBean.getMoney()));
                balanceTv.setText(Tools.concatAll(getString(R.string.balance_available)+"：", dataBean.getMoney()));

            }
        }


    }

    private void initViews() {
        setBarTitle("提现");


    }

    private void cash() {

        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<BaseBean>() {

            @Override
            public void onComplete(BaseBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        tip(R.string.send_successfull);
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
                showLoadingDialog();

                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.AMOUNT,
                        dataBean.getMoney()));

                return UserInfoManger.getSignList(list);


            }


            @Override
            public String createUrl() {

                return DRAW_CREATE;
            }
        });


    }

    @OnClick({R.id.all_cash, R.id.confirm_cash_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_cash:
                break;
            case R.id.confirm_cash_bt:
                cash();
                break;
        }
    }
}
