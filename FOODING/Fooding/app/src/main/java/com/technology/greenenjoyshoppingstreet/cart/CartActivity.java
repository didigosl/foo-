package com.technology.greenenjoyshoppingstreet.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.cart.adapter.CartItemAdapter;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartItemBean;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartUpdateBean;
import com.technology.greenenjoyshoppingstreet.cart.bean.RebateBean;
import com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity;
import com.technology.greenenjoyshoppingstreet.login.LoginActivity;
import com.technology.greenenjoyshoppingstreet.mine.BuyVIPActivity;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.ORDER_ITEMS;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_RENEW;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_RENEWRABATE;


/**
 * Created by Bern on 2017/11/9 0009.
 */

public class CartActivity extends BaseActivity {
    @BindView(R.id.pay_bt)
    Button payBt;
    @BindView(R.id.select_all_cb)
    CheckBox selectAllCb;
    @BindView(R.id.return_tv)
    TextView returnTv;
    @BindView(R.id.total_tv)
    TextView totalTv;
    @BindView(R.id.vip_hint)
    TextView vipHintTv;
    @BindView(R.id.data_lv)
    ListView dataLv;


    @BindView(R.id.vip_rl)
    RelativeLayout vipRl;


    private CartItemAdapter cartItemAdapter;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        initViews();

    }

    public void updateBottomBar() {
        String total = getString(R.string.total);
        String rebate = getString(R.string.rebate);
        totalTv.setText(Tools.concatAll(total, Tools.formatPriceText(cartItemAdapter.getSelectTotalPrice())));
        returnTv.setText(Tools.concatAll(rebate+": ", Tools.formatPriceText(cartItemAdapter
                .getSelectTotalRebate())));
        refreshRebate(cartItemAdapter.getSelectGoods());
    }

    @Override
    public void refresh() {
        super.refresh();
        getCartList();

    }

    private void getCartList() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CartItemBean>() {

            @Override
            public void onComplete(CartItemBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        CartItemBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {
                            cartItemAdapter.updateData(dataBean.getList(), true);
                        }


                    }

                }
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (UserInfoManger.isLogin()) {
                    List<KeyValuePair> list = new ArrayList<>();
                    return UserInfoManger.getSignList(list);
                }
                return null;

            }


            @Override
            public String createUrl() {

                return CART_LIST;
            }
        });
    }

    private void initViews() {
        setBarTitle(getString(R.string.cart));
        setBackVisible(View.GONE);
        cartItemAdapter = new CartItemAdapter(this);
        dataLv.setAdapter(cartItemAdapter);
        selectAllCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cartItemAdapter.setAllGoodsCheck(isChecked);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UserInfoManger.isLogin()) {
            refresh();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }


    }

    public void refreshRebate(final ArrayList<CartItemBean.DataBean.ListBean> dataList) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<RebateBean>() {

            @Override
            public void onComplete(RebateBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        String update = getString(R.string.update);
                        String update2 = getString(R.string.member_might_descount);
                        vipRl.setVisibility(View.GONE);
                        RebateBean.DataBean dataBean = bean.getData();
                        List<String> list = new ArrayList<String>();
                        if (dataBean != null) {
                            List<RebateBean.DataBean.VipsBean> vipsBeanList = dataBean.getVips();
                            if (vipsBeanList != null && !vipsBeanList.isEmpty()) {
                                for (RebateBean.DataBean.VipsBean vipsBean : vipsBeanList) {
                                    list.add(Tools.concatAll(getString(R.string.update), vipsBean.getLevelName(), getString(R.string.member_might_descount)+"：€ ", vipsBean.getRebate()));
                                }

                            }
                        }

                        if (list != null && !list.isEmpty()) {
                            vipRl.setVisibility(View.VISIBLE);
                            vipHintTv.setText(TextUtils.join("\n", list));
                        }

                    }

                }
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (UserInfoManger.isLogin()) {
                    if (dataList != null && !dataList.isEmpty()) {

                        List<CartUpdateBean> cartUpdateBeanList = new ArrayList<>();
                        for (CartItemBean.DataBean.ListBean listBean : dataList) {

                            if (!TextUtils.equals(listBean.getNum(), "0")) {

                                cartUpdateBeanList.add(new CartUpdateBean(listBean
                                        .getCartId(), listBean.getSpuId(), listBean.getNum()));
                            }
                        }
                        showLoadingDialog();
                        List<KeyValuePair> list = new ArrayList<>();
                        list.add(new BasicKeyValuePair(ParameterConstant.DATA, GsonUtil
                                .fromObjectToJsonString(cartUpdateBeanList)));
                        return UserInfoManger.getSignList(list);

                    }

                }
                return null;

            }


            @Override
            public String createUrl() {

                return CART_RENEWRABATE;
            }
        });

    }

    public void updateSku(final List<CartItemBean.DataBean.ListBean> dataList) {

        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CartItemBean>() {

            @Override
            public void onComplete(CartItemBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        getCartList();


                    }

                }
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (UserInfoManger.isLogin()) {
                    if (dataList != null && !dataList.isEmpty()) {

                        List<CartUpdateBean> cartUpdateBeanList = new ArrayList<>();
                        for (CartItemBean.DataBean.ListBean listBean : dataList) {

                            if (!TextUtils.equals(listBean.getNum(), "0")) {

                                cartUpdateBeanList.add(new CartUpdateBean(listBean
                                        .getCartId(), listBean.getSpuId(), listBean.getNum()));
                            }
                        }
                        showLoadingDialog();
                        List<KeyValuePair> list = new ArrayList<>();
                        list.add(new BasicKeyValuePair(ParameterConstant.DATA, GsonUtil
                                .fromObjectToJsonString(cartUpdateBeanList)));
                        return UserInfoManger.getSignList(list);

                    }

                }
                return null;

            }


            @Override
            public String createUrl() {

                return CART_RENEW;
            }
        });
    }


    @OnClick({R.id.pay_bt, R.id.vip_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.vip_rl:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, BuyVIPActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.pay_bt:
                if (UserInfoManger.isLogin()) {
                    ArrayList<CartItemBean.DataBean.ListBean> list = cartItemAdapter.getSelectGoods();
                    if (list != null && !list.isEmpty()) {
                        Intent intent = new Intent(this, ConfirmOrderActivity.class);
                        intent.putParcelableArrayListExtra(ORDER_ITEMS, list);
                        startActivity(intent);

                    } else {
                        tip(R.string.tip_select_product);
                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }

}
