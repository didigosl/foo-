package com.technology.greenenjoyshoppingstreet.category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.braintreepayments.api.dropin.DropInActivity;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.bean.CreateOrderBean;
import com.technology.greenenjoyshoppingstreet.category.bean.OrderPayBean;
import com.technology.greenenjoyshoppingstreet.login.WebActivity;
import com.technology.greenenjoyshoppingstreet.login.bean.WebBean;
import com.technology.greenenjoyshoppingstreet.mine.adapter.PayModeAdapter;
import com.technology.greenenjoyshoppingstreet.mine.bean.PayModeBean;
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

import static com.darsh.multipleimageselect.helpers.Constants.REQUEST_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_PAY;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_READYTOPAY;

/**
 * Created by Administrator on 2018/1/1.
 */

public class OrderPayActivity extends BaseActivity {

    public static final String ORDER_BEAN = "ORDER_BEAN";


    @BindView(R.id.confirm_pay_bt)
    Button confirmPayBt;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.data_lv)
    ListView dataLv;

    PayModeAdapter payModeAdapter;
    CreateOrderBean.DataBean dataBean;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay);
        ButterKnife.bind(this);

        initViews();
        getIntentData();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            dataBean = intent.getParcelableExtra(ORDER_BEAN);
            if (dataBean != null) {
                priceTv.setText(Tools.formatPriceText(dataBean.getTotalAmount()));

            }
        }
    }

    private void initViews() {
        setBarTitle(getString(R.string.order_payment));
        payModeAdapter = new PayModeAdapter(this);
        dataLv.setAdapter(payModeAdapter);
        ArrayList<PayModeBean> payModeList = new ArrayList<>();
        payModeList.add(new PayModeBean(PayModeBean.PAYPAL, true));
//        payModeList.add(new PayModeBean(PayModeBean.IDEN_CARD, false));
        payModeList.add(new PayModeBean(PayModeBean.OFFLINE_PAY, false));
        payModeAdapter.updateData(payModeList, true);

    }


    public static final int PAYPAL_REQUEST_CODE = 3453;

    @OnClick(R.id.confirm_pay_bt)
    public void onViewClicked() {
        PayModeBean payModeBean = payModeAdapter.getSelectMode();
        if (payModeBean != null) {
            switch (payModeBean.getPayMode()) {
                case PayModeBean.PAYPAL:
                    getPayPalToken(false);

                    break;
                case PayModeBean.IDEN_CARD:
                    getPayPalToken(true);
                    break;
                case PayModeBean.OFFLINE_PAY:
                    Intent offlinePay = new Intent(this, OfflinePayActivity.class);
                    offlinePay.putExtra(OrderPayActivity.ORDER_BEAN, dataBean);
                    startActivity(offlinePay);
                    finish();
                    break;
            }

        } else {
            tip(getString(R.string.select_payment_method));
        }
    }

    private void getPayPalToken(final boolean isCredit) {


        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<OrderPayBean>() {

            @Override
            public void onComplete(OrderPayBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        OrderPayBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {

                            OrderPayBean.DataBean.RedsysBean redsysBean = dataBean.getRedsys();
                            if (redsysBean != null) {
                                List<KeyValuePair> keyValuePairs = new ArrayList<>();
                                keyValuePairs.add(new BasicKeyValuePair("Ds_MerchantParameters", redsysBean.getDs_MerchantParameters()));
                                keyValuePairs.add(new BasicKeyValuePair("Ds_Signature", redsysBean.getDs_Signature()));
                                keyValuePairs.add(new BasicKeyValuePair("Ds_SignatureVersion", redsysBean.getDs_SignatureVersion()));

                                WebBean webBean = new WebBean();
                                webBean.setTargetUrl(redsysBean.getForm_action());
                                webBean.setData(UserInfoManger.formatList(keyValuePairs));
                                webBean.setTitle(getString(R.string.pending));

                                Intent intent = new Intent(OrderPayActivity.this, WebActivity.class);
                                intent.putExtra(Constant.WEB_BEAN, webBean);
                                startActivity(intent);


                            }
                        }
//
//                        PaypalTokenBean.DataBean dataBean = bean.getData();
//                        if (dataBean != null && !TextUtils.isEmpty(dataBean.getClientToken())) {
////                            Intent intent = new Intent(OrderPayActivity.this, BraintreePaymentActivity.class);
////                            intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, dataBean.getClientToken());
////                            startActivityForResult(intent, PAYPAL_REQUEST_CODE);
//                            DropInRequest dropInRequest = new DropInRequest()
//                                    .clientToken(dataBean.getClientToken());
//                            dropInRequest.disableAndroidPay();
//                            dropInRequest.disableVenmo();
//                            if (isCredit) {
//                                dropInRequest.disablePayPal();
//                            }
//                            startActivityForResult(dropInRequest.getIntent(OrderPayActivity.this), REQUEST_CODE);
//                        } else {
//                            tip("token 为空");
//                        }


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
                if (dataBean != null) {
                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, dataBean.getOrderId()));
                    return UserInfoManger.getSignList(list);


                }
                return null;


            }


            @Override
            public String createUrl() {

                return ORDER_READYTOPAY;
            }
        });


    }

    private void payOrder(final String nonce) {


        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<BaseBean>() {

            @Override
            public void onComplete(BaseBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {

                        tip(R.string.payment_succesfull);
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
                if (dataBean != null) {
                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.AMOUNT,
                            dataBean.getTotalAmount()));
                    list.add(new BasicKeyValuePair(ParameterConstant.NONCE,
                            nonce));
                    list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, dataBean.getOrderId()));
                    return UserInfoManger.getSignList(list);


                }
                return null;


            }


            @Override
            public String createUrl() {

                return ORDER_PAY;
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                DropInResult result = data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                if (result != null) {
                    PaymentMethodNonce paymentMethodNonce = result.getPaymentMethodNonce();
                    if (paymentMethodNonce != null) {
                        payOrder(paymentMethodNonce.getNonce());
                    }

                }
                // use the result to update your UI and send the payment method nonce to your server
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // the user canceled
            } else {
                // handle errors here, an exception may be available in
                Exception error = (Exception) data.getSerializableExtra(DropInActivity.EXTRA_ERROR);
            }
        }

    }
}
