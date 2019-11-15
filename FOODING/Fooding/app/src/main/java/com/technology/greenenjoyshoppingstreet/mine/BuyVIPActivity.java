package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.adapter.VipCategoryAdapter;
import com.technology.greenenjoyshoppingstreet.mine.bean.VipPriceBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.category.OrderPayActivity.PAYPAL_REQUEST_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_BUYVIP;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_PAYVIP;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class BuyVIPActivity extends BaseActivity {
    //
    @BindView(R.id.select_vip_data_lv)
    ListViewInScroll selectVipDataLv;
    //    @BindView(R.id.select_pay_data_lv)
//    ListViewInScroll selectPayDataLv;
//    PayModeAdapter payModeAdapter;
    VipCategoryAdapter vipCategoryAdapter;
    @BindView(R.id.confirm_pay_bt)
    Button confirmPayBt;
    @BindView(R.id.vip_des_tv)
    TextView vipDesTv;
//

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_vip);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        setBarTitle("VIP");


        vipCategoryAdapter = new VipCategoryAdapter(this);
        selectVipDataLv.setAdapter(vipCategoryAdapter);
        getVipPrice();

    }

    private VipPriceBean.DataBean dataBean;

    private void getVipPrice() {

        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<VipPriceBean>() {

            @Override
            public void onComplete(VipPriceBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        dataBean = bean.getData();
                        if (dataBean != null) {
                            vipCategoryAdapter.updateData(dataBean.getList(), true);
                            if (!TextUtils.isEmpty(dataBean.getIntro())) {
                                vipDesTv.setText(Html.fromHtml(dataBean.getIntro()));
                            }

                        }


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
                list.add(new BasicKeyValuePair(ParameterConstant.PAGE,
                        Constant.FIRST_PAGE_INDEX_STR));
                list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT,
                        Constant.PER_PAGE_COUNT));
                return UserInfoManger.getSignList(list);


            }


            @Override
            public String createUrl() {

                return USER_BUYVIP;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE) {
//            switch (resultCode) {
//                case BraintreePaymentActivity.RESULT_OK:
//                    String paymentMethodNonce = data
//                            .getStringExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);
//                    payVipSuccess(paymentMethodNonce);
//                    break;
//                case BraintreePaymentActivity.BRAINTREE_RESULT_DEVELOPER_ERROR:
//                case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_ERROR:
//                case BraintreePaymentActivity.BRAINTREE_RESULT_SERVER_UNAVAILABLE:
//                    tip("支付失败");
//                    // handle errors here, a throwable may be available in
//                    // data.getSerializableExtra(BraintreePaymentActivity.EXTRA_ERROR_MESSAGE)
//                    break;
//                default:
//                    break;
//            }
        }
    }


    private void payVipSuccess(final String nonce) {


        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<VipPriceBean>() {

            @Override
            public void onComplete(VipPriceBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {

                        tip("支付成功");
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
                list.add(new BasicKeyValuePair(ParameterConstant.LEVELID,
                        selectBean.getLevelId()));
                list.add(new BasicKeyValuePair(ParameterConstant.AMOUNT,
                        selectBean.getPrice()));
                list.add(new BasicKeyValuePair(ParameterConstant.NONCE,
                        nonce));
                return UserInfoManger.getSignList(list);


            }


            @Override
            public String createUrl() {

                return USER_PAYVIP;
            }
        });

    }

    //
//
    VipPriceBean.DataBean.ListBean selectBean;

    @OnClick(R.id.confirm_pay_bt)
    public void onViewClicked() {

        selectBean = vipCategoryAdapter.selectCategory();
        if (selectBean != null) {

//            Intent intent = new Intent(this, BraintreePaymentActivity.class);
//            intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, dataBean.getClientToken());
//            startActivityForResult(intent, PAYPAL_REQUEST_CODE);


        } else {
            tip("请选择需要购买的会员");
        }


    }
}
