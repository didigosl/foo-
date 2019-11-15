package com.technology.greenenjoyshoppingstreet.category;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartItemBean;
import com.technology.greenenjoyshoppingstreet.category.adapter.ConfirmProductAdapter;
import com.technology.greenenjoyshoppingstreet.category.bean.CreateOrderBean;
import com.technology.greenenjoyshoppingstreet.category.bean.PrepareOrderBean;
import com.technology.greenenjoyshoppingstreet.mine.AddressListActivity;
import com.technology.greenenjoyshoppingstreet.mine.MyCouponActivity;
import com.technology.greenenjoyshoppingstreet.mine.bean.AddressListBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.MyCouponBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.GsonUtil;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.mine.AddressListActivity.SELECT_ADDRESS;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_CREATE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_PREPARE;

/**
 * Created by Administrator on 2018/1/1.
 */

public class ConfirmOrderActivity extends BaseActivity {
    public static final String ORDER_ITEMS = "ORDER_ITEMS";


    @BindView(R.id.pay_bt)
    Button payBt;
    @BindView(R.id.return_income_tv)
    TextView returnIncomeTv;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.address_rl)
    RelativeLayout addressRl;
    @BindView(R.id.data_lv)
    ListViewInScroll dataLv;
    @BindView(R.id.coupon_price_tv)
    TextView couponPriceTv;
    @BindView(R.id.coupon_tv)
    TextView couponTv;
    @BindView(R.id.coupon_ll)
    LinearLayout couponLl;
    @BindView(R.id.message_et)
    EditText messageEt;
    MyCouponBean.DataBean.ListBean couponBean;

    AddressListBean.DataBean defaultAddresBean;
    ConfirmProductAdapter confirmProductAdapter;
    ArrayList<CartItemBean.DataBean.ListBean> dataList = new ArrayList<>();
    @BindView(R.id.trans_fee_tv)
    TextView transFeeTv;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<CartItemBean.DataBean.ListBean> data = intent
                    .getParcelableArrayListExtra(ORDER_ITEMS);
            if (data != null && !data.isEmpty()) {
                dataList.clear();
                dataList.addAll(data);
                confirmProductAdapter.updateData(dataList, true);
                totalTv.setText(confirmProductAdapter.getSelectTotalPrice());
            }
        }
        if (defaultAddresBean != null) {
            getPrepaerOrder();
        }
    }


    private ArrayList<MyCouponBean.DataBean.ListBean> selectCouponList = new ArrayList<>();

    private void getPrepaerOrder() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<PrepareOrderBean>() {

            @Override
            public void onComplete(PrepareOrderBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        selectCouponList.clear();
                        PrepareOrderBean.DataBean preDataBean = bean.getData();
                        if (preDataBean != null) {
                            List<MyCouponBean.DataBean.ListBean> couponsBeanList = preDataBean.getCoupons();
                            if (couponsBeanList != null) {
                                selectCouponList.addAll(couponsBeanList);
                            }


                        }
                        totalTv.setText(Tools.formatPriceText(preDataBean.getTotalAmount()));
                        returnIncomeTv.setText(Tools.formatPriceText(preDataBean.getTotalRebate()));
                        String useCoupons = getString(R.string.can_use_coupons);
                        couponTv.setText(Tools.concatAll(useCoupons+"（", String.valueOf(selectCouponList.size()), "）"));


                        if (!TextUtils.isEmpty(preDataBean.getExpressFee()) && Tools.isDouble(preDataBean.getExpressFee())) {
                            transFeeTv.setText(Tools.concatAll(getString(R.string.send)+"：€ ", preDataBean.getExpressFee()));


                        } else {
                            transFeeTv.setText(Tools.concatAll(getString(R.string.send)+"：€ 0"));

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
                if (defaultAddresBean != null) {
                    if (dataList != null) {
                        List<KeyValuePair> list = new ArrayList<>();
                        List<String> ids = new ArrayList<>();
                        for (CartItemBean.DataBean.ListBean data : dataList) {
                            ids.add(data.getCartId());
                        }
                        list.add(new BasicKeyValuePair(ParameterConstant.CARTS, GsonUtil
                                .fromObjectToJsonString(ids)));
                        list.add(new BasicKeyValuePair(ParameterConstant.ADDRESS_ID,
                                defaultAddresBean.getAddressId()));
                        if (couponBean != null) {
                            list.add(new BasicKeyValuePair(ParameterConstant.COUPONUSERID,
                                    couponBean.getCouponUserId()));
                        }
                        return UserInfoManger.getSignList(list);
                    }

                } else {
                    tip(R.string.select_address_send);
                }
                return null;

            }


            @Override
            public String createUrl() {

                return ORDER_PREPARE;
            }
        });

    }

    private void initViews() {
        setBarTitle(getString(R.string.order_branch));
        confirmProductAdapter = new ConfirmProductAdapter(this);
        dataLv.setAdapter(confirmProductAdapter);
        defaultAddresBean = UserInfoManger.getDefaultAddress();
        if (defaultAddresBean != null) {
            nameTv.setText(defaultAddresBean.getMan());
            phoneTv.setText(defaultAddresBean.getPhone());
            addressTv.setText(Tools.concatAll(defaultAddresBean.getProvince(), " ", defaultAddresBean.getCity(), " ", defaultAddresBean.getAddress()));

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.pay_bt, R.id.coupon_ll, R.id.address_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pay_bt:
                createOrder();
                break;
            case R.id.coupon_ll:
                if (!selectCouponList.isEmpty()) {
                    Intent selectCouponIntent = new Intent(this, MyCouponActivity.class);
                    selectCouponIntent.putExtra(MyCouponActivity.GET_COUPON, selectCouponList);
                    startActivityForResult(selectCouponIntent, SELECTCOUPON_REQUEST);
                } else {
                    tip(R.string.without_coupons);
                }
                break;
            case R.id.address_rl:
                Intent selectAddress = new Intent(this, AddressListActivity.class);
                selectAddress.putExtra(SELECT_ADDRESS, true);
                startActivityForResult(selectAddress, SELECTADDRESS_REQUEST);
                break;
        }
    }

    private void createOrder() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CreateOrderBean>() {

            @Override
            public void onComplete(CreateOrderBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        finish();
                        Intent payIntent = new Intent(ConfirmOrderActivity.this,
                                OrderPayActivity.class);
                        payIntent.putExtra(OrderPayActivity.ORDER_BEAN, bean.getData());
                        startActivity(payIntent);


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
                if (defaultAddresBean != null) {
                    if (dataList != null) {
                        List<KeyValuePair> list = new ArrayList<>();
                        List<String> ids = new ArrayList<>();
                        for (CartItemBean.DataBean.ListBean data : dataList) {
                            ids.add(data.getCartId());
                        }
                        list.add(new BasicKeyValuePair(ParameterConstant.CARTS, GsonUtil
                                .fromObjectToJsonString(ids)));
                        list.add(new BasicKeyValuePair(ParameterConstant.ADDRESS_ID,
                                defaultAddresBean.getAddressId()));
                        if (couponBean != null) {
                            list.add(new BasicKeyValuePair(ParameterConstant.COUPON_USER_ID,
                                    couponBean.getCouponUserId()));
                        }
                        if (!TextUtils.isEmpty(messageEt.getText())) {
                            list.add(new BasicKeyValuePair(ParameterConstant.USER_REMARK,
                                    messageEt.getText().toString()));
                        }
                        return UserInfoManger.getSignList(list);
                    }

                } else {
                    tip(getString(R.string.select_address_send));
                }
                return null;
            }

            @Override
            public String createUrl() {

                return ORDER_CREATE;
            }
        });
    }

    public static final String COUPON_BEAN = "COUPON_BEAN";
    public static final String ADDRESS_BEAN = "ADDRESS_BEAN";
    public static final int SELECTADDRESS_REQUEST = 1112;
    public static final int SELECTADDRESS_SUCCESS = 454;
    public static final int SELECTCOUPON_REQUEST = 1116;
    public static final int SELECTCOUPON_SUCCESS = 452;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SELECTADDRESS_SUCCESS == resultCode) {
            if (data != null) {
                defaultAddresBean = data.getParcelableExtra(ADDRESS_BEAN);
                if (defaultAddresBean != null) {
                    nameTv.setText(defaultAddresBean.getMan());
                    phoneTv.setText(defaultAddresBean.getMan());
                    addressTv.setText(defaultAddresBean.getAddress());
                    getPrepaerOrder();
                }
            }
        } else if (SELECTCOUPON_SUCCESS == resultCode) {
            if (data != null) {
                couponBean = data.getParcelableExtra(COUPON_BEAN);

                if (couponBean != null) {

                    couponPriceTv.setText(Tools.concatAll(getString(R.string.coupon)+"：", Tools.formatPriceText
                            (couponBean.getAmount())));
                } else {
                    couponPriceTv.setText(Tools.concatAll(getString(R.string.coupon)+"：€ 0"));
                }
            }

        }
    }
}
