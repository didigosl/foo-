package com.technology.greenenjoyshoppingstreet.category;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.bean.BankInfoBean;
import com.technology.greenenjoyshoppingstreet.category.bean.CreateOrderBean;
import com.technology.greenenjoyshoppingstreet.category.bean.OfflineBean;
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

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.MAIN_GETBANKINFO;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_OFFLINEPAY;

/**
 * Created by Administrator on 2018/1/2.
 */

public class OfflinePayActivity extends BaseActivity {


    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.hint_tv)
    TextView hintTv;
    @BindView(R.id.bank)
    TextView bank;
    @BindView(R.id.bank_code_tv)
    TextView bankCodeTv;
    @BindView(R.id.remark_tv)
    TextView remarkTv;
    @BindView(R.id.confirm_pay_bt)
    Button confirmPayBt;

    CreateOrderBean.DataBean dataBean;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_pay);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }


    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = intent.getParcelableExtra(OrderPayActivity.ORDER_BEAN);
            if (dataBean != null) {
                priceTv.setText(Tools.formatPriceText(dataBean.getTotalAmount()));
                geBankInfo();

            }
        }
    }

    private void initViews() {
        setBarTitle(getString(R.string.offline_payment));


    }

    @OnClick(R.id.confirm_pay_bt)
    public void onViewClicked() {
        offlinePay();
    }

    private void geBankInfo() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<BankInfoBean>() {

            @Override
            public void onComplete(BankInfoBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        BankInfoBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {
                            bank.setText(Tools.concatAll(getString(R.string.bank)+"：", dataBean.getBank()));
                            bankCodeTv.setText(Tools.concatAll(getString(R.string.bank_account)+"：", dataBean.getBankAccount()));
                            remarkTv.setText(dataBean.getBankIntro());

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
                if (dataBean != null && !TextUtils.isEmpty(dataBean.getOrderId())) {
                    showLoadingDialog();
                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, dataBean.getOrderId()));
                    return UserInfoManger.getSignList(list);
                }
                return null;


            }


            @Override
            public String createUrl() {

                return MAIN_GETBANKINFO;
            }
        });


    }

    private void offlinePay() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<OfflineBean>() {

            @Override
            public void onComplete(OfflineBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
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
                if (dataBean != null && !TextUtils.isEmpty(dataBean.getOrderId())) {
                    showLoadingDialog();
                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, dataBean.getOrderId()));
                    return UserInfoManger.getSignList(list);
                }
                return null;


            }


            @Override
            public String createUrl() {

                return ORDER_OFFLINEPAY;
            }
        });


    }

}
