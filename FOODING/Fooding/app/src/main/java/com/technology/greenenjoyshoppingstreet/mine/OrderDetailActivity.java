package com.technology.greenenjoyshoppingstreet.mine;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.CommitEvaluteActivity;
import com.technology.greenenjoyshoppingstreet.category.OrderPayActivity;
import com.technology.greenenjoyshoppingstreet.category.bean.CreateOrderBean;
import com.technology.greenenjoyshoppingstreet.mine.adapter.OrderProductAdapter;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderDetialBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderListBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderStatus;
import com.technology.greenenjoyshoppingstreet.utils.DialogConfigBean;
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
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_CANCEL;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_FINISH;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_GET;

/**
 * Created by Administrator on 2018/1/9.
 */

public class OrderDetailActivity extends BaseActivity {
    OrderDetialBean.DataBean dataBean;

    @BindView(R.id.order_status_iv)
    ImageView orderStatusIv;
    @BindView(R.id.order_status_tv)
    TextView orderStatusTv;
    @BindView(R.id.order_code_tv)
    TextView orderCodeTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.data_lv)
    ListViewInScroll dataLv;
    @BindView(R.id.total_price_tv)
    TextView totalPriceTv;
    @BindView(R.id.tarnsport_price_tv)
    TextView tarnsportPriceTv;
    @BindView(R.id.return_price_tv)
    TextView returnPriceTv;
    @BindView(R.id.order_number_tv)
    TextView orderNumberTv;
    @BindView(R.id.copy_order_number_tv)
    TextView copyOrderNumberTv;
    @BindView(R.id.pay_mode_tv)
    TextView payModeTv;

    @BindView(R.id.pay_number_tv)
    TextView payNumberTv;
    @BindView(R.id.copy_pay_number_tv)
    TextView copyPayNumberTv;
    @BindView(R.id.order_time_tv)
    TextView orderTimeTv;
    @BindView(R.id.opeartion_one_bt)
    Button opeartionOneBt;
    @BindView(R.id.opeartion_two_bt)
    Button opeartionTwoBt;
    OrderProductAdapter orderProductAdapter;
    @BindView(R.id.dai_price_tv)
    TextView daiPriceTv;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }


    private String orderId;

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra(ParameterConstant.ORDER_ID);

        }
    }

    private void initViews() {
        setBarTitle(getString(R.string.detail_order));
        orderProductAdapter = new OrderProductAdapter(this);
        dataLv.setAdapter(orderProductAdapter);

    }

    @OnClick({R.id.order_number_tv, R.id.copy_order_number_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_number_tv:
                break;
            case R.id.copy_order_number_tv:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UserInfoManger.isLogin()) {
            if (!TextUtils.isEmpty(orderId)) {
                getOrderDetail();

            }
        } else {
            tip(getString(R.string.first_login));
            finish();
        }

    }

    private void getOrderDetail() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<OrderDetialBean>() {

            @Override
            public void onComplete(final OrderDetialBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        dataBean = bean.getData();
                        if (dataBean != null) {
                            OrderStatus orderStatus = OrderStatus.getStatus(dataBean.getFlag());
                            orderStatusIv.setImageResource(R.drawable.ic_flag_payment);
                            if (orderStatus != null) {
                                switch (orderStatus) {
                                    case PENDINGPAYMENT:
                                        orderCodeTv.setVisibility(View.GONE);
                                        opeartionOneBt.setText(getString(R.string.pay_now));
                                        opeartionOneBt.setVisibility(View.VISIBLE);
                                        opeartionOneBt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                payOrder(bean);
                                            }
                                        });
                                        opeartionTwoBt.setText(getString(R.string.cancel_order));
                                        opeartionTwoBt.setVisibility(View.VISIBLE);
                                        opeartionTwoBt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                cancelOrder();
                                            }
                                        });
                                        orderStatusIv.setImageResource(R.drawable.ic_flag_payment);
                                        break;
                                    case TOBEDELIVERED:
                                        orderCodeTv.setVisibility(View.GONE);

//                                        opeartionTwoBt.setText("取消订单");
//                                        opeartionTwoBt.setVisibility(View.VISIBLE);
//                                        opeartionTwoBt.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                cancelOrder();
//                                            }
//                                        });
                                        break;
                                    case TOCONFIRMTHERECEIPT:
                                        opeartionOneBt.setText("查看物流");
                                        opeartionOneBt.setVisibility(View.VISIBLE);
                                        opeartionOneBt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                lookProgress();
                                            }
                                        });
                                        opeartionTwoBt.setText(getString(R.string.confirm_check)    );
                                        opeartionTwoBt.setVisibility(View.VISIBLE);
                                        opeartionTwoBt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                confirmReceive();
                                            }
                                        });

                                        break;
                                    case TOBECOMMENTED:
                                        break;
                                    case COMPLETED:
                                        orderStatusIv.setImageResource(R.drawable.ic_flag_complete);
                                        opeartionTwoBt.setText(getString(R.string.comments));
                                        opeartionTwoBt.setVisibility(View.VISIBLE);
                                        opeartionTwoBt.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                evaluteOrder();
                                            }
                                        });
                                        break;
                                    case REFUND:
                                        orderStatusIv.setImageResource(R.drawable.ic_flag_cancel);

                                        break;
                                    default:
                                        orderCodeTv.setVisibility(View.GONE);
                                        break;


                                }


                            }
                            orderStatusTv.setText(dataBean.getFlagText());
                            if (!TextUtils.isEmpty(dataBean.getExpressCorpName()) && !TextUtils.isEmpty(dataBean.getExpressNo())) {
                                orderCodeTv.setText(Tools.concatAll(getString(R.string.info_status)+":", dataBean.getExpressCorpName(), "  \n", "单号:", dataBean.getExpressNo()));

                            } else {
                                orderCodeTv.setText("");

                            }
                            nameTv.setText(dataBean.getReceiveMan());
                            phoneTv.setText(dataBean.getReceivePhone());
                            addressTv.setText(dataBean.getReceiveAddress());
                            orderProductAdapter.updateData(dataBean.getGoods(), true);
                            daiPriceTv.setText(Tools.formatPriceText(dataBean.getTotalCoupon()));
                            totalPriceTv.setText(Tools.formatPriceText(dataBean.getTotalAmount()));
                            tarnsportPriceTv.setText(Tools.formatPriceText(dataBean.getExpressFee()));
                            returnPriceTv.setText(Tools.formatPriceText(dataBean.getTotalRebate()));
                            orderNumberTv.setText(Tools.concatAll(getString(R.string.order_number)+":", dataBean.getSn()));
                            copyOrderNumberTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    // 将文本内容放到系统剪贴板里。
                                    cm.setText(dataBean.getOrderId());
                                    tip(R.string.copied);
                                }
                            });
                            payModeTv.setText(Tools.concatAll(getString(R.string.pay_method), dataBean.getPaymethodText()));
                            payNumberTv.setText(Tools.concatAll(getString(R.string.number_order_pay), dataBean.getTransactionId()));
                            orderTimeTv.setText(Tools.concatAll(getString(R.string.time_order), dataBean.getCreateTime()));
                            copyPayNumberTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    // 将文本内容放到系统剪贴板里。
                                    cm.setText(dataBean.getTransactionId());
                                    tip(getString(R.string.copied));
                                }
                            });


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

                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, orderId));
                return UserInfoManger.getSignList(list);


            }


            @Override
            public String createUrl() {

                return ORDER_GET;
            }
        });


    }


    public void payOrder(OrderDetialBean bean) {

        Intent payIntent = new Intent(this, OrderPayActivity.class);
        CreateOrderBean.DataBean dataBean = new CreateOrderBean.DataBean();
        dataBean.setOrderId(bean.getData().getOrderId());
        dataBean.setSn(bean.getData().getSn());
        dataBean.setTotalAmount(bean.getData().getTotalAmount());
        payIntent.putExtra(OrderPayActivity.ORDER_BEAN, dataBean);
        startActivity(payIntent);

//        OrderPayInfoModel orderPayInfoModel = new OrderPayInfoModel();
//        OrderDetialBean.DataBean data = bean.getData();
//        orderPayInfoModel.setOrderId(data.getOrderId());
//        orderPayInfoModel.setSn(data.getSn());
//        orderPayInfoModel.setTotalAmount(data.getTotalAmount());
//        StartActivityUtil.getInstance().goPay(mActivity, orderPayInfoModel);

    }

    public void evaluteOrder() {


        Intent intent = new Intent(this, CommitEvaluteActivity.class);
        intent.putExtra(ParameterConstant.ORDER_ID, orderId);
        startActivity(intent);


    }

    public void lookProgress() {

        tip("查看物流");
    }

    public void confirmReceive() {

        DialogConfigBean dialogConfigBean = DialogConfigBean.getDefaultDoubleConfig(this);
        dialogConfigBean.setContentText(getString(R.string.confirm_order));
        dialogConfigBean.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissChooseDialog();
                NetExcutor.executorCommonRequest(OrderDetailActivity
                        .this, new
                        CommonNetUIListener<OrderListBean>() {

                            @Override
                            public void onComplete(OrderListBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                                cancelLoadingDialog();
                                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                                        tip(R.string.confirm_successfull);
                                        getOrderDetail();

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
                                list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, orderId));

                                return UserInfoManger.getSignList(list);


                            }

                            @Override
                            public String createUrl() {

                                return ORDER_FINISH;
                            }
                        });

            }
        });

        showOperationDialog(dialogConfigBean);

    }

    public void cancelOrder() {
        DialogConfigBean dialogConfigBean = DialogConfigBean.getDefaultDoubleConfig(this);
        dialogConfigBean.setContentText("是否取消订单");
        dialogConfigBean.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissChooseDialog();
                NetExcutor.executorCommonRequest(OrderDetailActivity
                        .this, new
                        CommonNetUIListener<OrderListBean>() {

                            @Override
                            public void onComplete(OrderListBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                                cancelLoadingDialog();
                                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                                        tip(R.string.canceled);
                                        getOrderDetail();

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
                                list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, orderId));

                                return UserInfoManger.getSignList(list);


                            }

                            @Override
                            public String createUrl() {

                                return ORDER_CANCEL;
                            }
                        });

            }
        });

        showOperationDialog(dialogConfigBean);


    }

}
