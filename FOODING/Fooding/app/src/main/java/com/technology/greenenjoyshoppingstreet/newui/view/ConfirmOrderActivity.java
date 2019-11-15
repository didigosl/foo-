package com.technology.greenenjoyshoppingstreet.newui.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.EventModel;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.Ts;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.AddressListActivity;
import com.technology.greenenjoyshoppingstreet.mine.bean.AddressListBean;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.ConfirmOrderModel;
import com.technology.greenenjoyshoppingstreet.newui.model.CouponListModel;
import com.technology.greenenjoyshoppingstreet.newui.model.OrderPayInfoModel;
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel;
import com.technology.greenenjoyshoppingstreet.newui.util.Arith;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.ADDRESS_BEAN;
import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.SELECTADDRESS_REQUEST;
import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.SELECTADDRESS_SUCCESS;
import static com.technology.greenenjoyshoppingstreet.mine.AddressListActivity.SELECT_ADDRESS;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_BUYNOW;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_CREATE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_PREPARE;

public class ConfirmOrderActivity extends BaseMyActivity {
    @BindView(R.id.tv_address_user)
    TextView tvAddressUser;
    @BindView(R.id.tv_address_content)
    TextView tvAddressContent;
    @BindView(R.id.layout_address)
    LinearLayout layoutAddress;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.tv_goods_content)
    TextView tvGoodsContent;
    @BindView(R.id.tv_expressFee)
    TextView tvExpressFee;
    @BindView(R.id.tv_coupon_num)
    TextView tvCouponNum;
    @BindView(R.id.layout_coupon)
    LinearLayout layoutCoupon;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;
    @BindView(R.id.tv_fanLi)
    TextView tvFanLi;
    @BindView(R.id.tv_heJi)
    TextView tvHeJi;
    @BindView(R.id.tv_jiesuan)
    TextView tvJiesuan;
    private List<ShoppingCartModel.ListBean> listBeans;
    private List<String> cartList;
    private ConfirmOrderModel confirmOrderModel;
    private CouponListModel couponListModel;//当前选中的优惠券
    private String addressId = "";//当前页面的addressid

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_new_confirm_order;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.order_branch));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Type type = new TypeToken<ArrayList<ShoppingCartModel.ListBean>>() {
        }.getType();
        listBeans = new Gson().fromJson(getIntent().getStringExtra(Constants.intent_list_json), type);
        if (listBeans.get(0).getCartId().equals("-1")) {

        } else {
            cartList = new ArrayList<>();
            for (int i = 0; i < listBeans.size(); i++) {
                cartList.add(listBeans.get(i).getCartId());
            }
        }
        initAdapter();
        starRefresh();
    }

    private void initAdapter() {
        BaseAdapter<ShoppingCartModel.ListBean> mAdapter = new BaseAdapter.Builder<ShoppingCartModel.ListBean>(rvList, mActivity, R.layout.adapter_confirm_order_goods)
                .isNested()
                .build(new OnBaseAdapterListener<ShoppingCartModel.ListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, ShoppingCartModel.ListBean item) {
                        ImageLoader.with(item.getCover(), (ImageView) helper.getView(R.id.img_icon));
                        helper.setText(R.id.tv_title, item.getSpuName())
                                .setText(R.id.tv_num, getString(R.string.quantity)+"：" + item.getNum());
                        TextView tv_money = helper.getView(R.id.tv_money);
                        tv_money.setText("€ " + item.getPrice());
                        tv_money.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    }
                });
        mAdapter.setNewData(listBeans);
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        MyJsonCallback<HttpResModel<ConfirmOrderModel>> callback = new MyJsonCallback<HttpResModel<ConfirmOrderModel>>(mPublicConfig) {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<ConfirmOrderModel>> respons, HttpResModel<ConfirmOrderModel> goodsModelHttpResModel) {
                confirmOrderModel = goodsModelHttpResModel.getData();
                initData();
            }
        };
        if (cartList != null && cartList.size() > 0) {
            OkGo.<HttpResModel<ConfirmOrderModel>>post(ORDER_PREPARE)
                    .tag(this)
                    .params("addressId", addressId)
                    .params("carts", new Gson().toJson(cartList))
                    .execute(callback);
        } else {
            OkGo.<HttpResModel<ConfirmOrderModel>>post(ORDER_BUYNOW)
                    .tag(this)
                    .params("skuId", listBeans.get(0).getSkuId())
                    .params("num", listBeans.get(0).getNum())
                    .params("returnFmt", "full")
                    .params("addressId", addressId)
                    .execute(callback);
        }
    }

    private void initData() {
        if (confirmOrderModel.getCarts() != null && confirmOrderModel.getCarts().size() > 0) {
            cartList = confirmOrderModel.getCarts();
        }
        int allNum = 0;
        for (int i = 0; i < listBeans.size(); i++) {
            allNum = allNum + Integer.parseInt(listBeans.get(i).getNum());
        }
        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                .append(getString(R.string.total_simple) + allNum + getString(R.string.products))
                .setFontSize(ConvertUtils.sp2px(12))
                .append("€" + confirmOrderModel.getTotalFee())
                .setFontSize(ConvertUtils.sp2px(18))
                .setBold()
                .create();
        tvGoodsContent.setText(spannableStringBuilder);
        tvExpressFee.setText(getString(R.string.send)+"：€ " + confirmOrderModel.getExpressFee());
        tvCouponNum.setText(getString(R.string.can_use_coupons)+"（" + confirmOrderModel.getCoupons().size() + "）");
        refresh();
        //地址
        if (StringUtils.isEmpty(confirmOrderModel.getAddressId())) {
            //没选择地址
            tvAddressUser.setVisibility(View.GONE);
            tvAddressContent.setText(getString(R.string.select_address_send));
        } else {
            tvAddressUser.setVisibility(View.VISIBLE);
            tvAddressUser.setText(confirmOrderModel.getReceiveMan() + "  " + confirmOrderModel.getReceivePhone());
            tvAddressContent.setText(confirmOrderModel.getReceiveArea() + "  " + confirmOrderModel.getReceiveAddress());
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(EventModel eventModel) {
        switch (eventModel.getCode()) {
            case Constants.event_confirm_order_coupon:
                couponListModel = (CouponListModel) eventModel.getData();
                refresh();
                break;
        }
    }

    private void refresh() {
        String heji = confirmOrderModel.getTotalAmount();
        String amount = "0";
        if (couponListModel != null) {
            amount = couponListModel.getAmount();
            heji = Arith.sub(heji, amount) + "";
        }
        tvHeJi.setText("€ " + heji);
        tvCoupon.setText(getString(R.string.coupon)+"：€ " + amount);
        tvFanLi.setText("€ " + confirmOrderModel.getTotalRebate());
    }

    @OnClick({R.id.layout_address, R.id.layout_coupon, R.id.tv_jiesuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_address:
                Intent selectAddress = new Intent(this, AddressListActivity.class);
                selectAddress.putExtra(SELECT_ADDRESS, true);
                startActivityForResult(selectAddress, SELECTADDRESS_REQUEST);
                break;
            case R.id.layout_coupon:
                if (confirmOrderModel.getCoupons().size() == 0) {
                    Ts.show(getString(R.string.not_coupons_availables));
                    return;
                }
                Intent intent = new Intent(mActivity, CouponActivity.class);
                intent.putExtra(Constants.intent_list_json, new Gson().toJson(confirmOrderModel.getCoupons()));
                StartActivityUtil.getInstance().startActivity(mActivity, intent);
                break;
            case R.id.tv_jiesuan:
                if (StringUtils.isEmpty(confirmOrderModel.getAddressId())) {
                    Ts.show(getString(R.string.select_address_send));
                    return;
                }
                goPay();
                break;
        }
    }

    private void goPay() {
        PostRequest<HttpResModel<OrderPayInfoModel>> params = OkGo.<HttpResModel<OrderPayInfoModel>>post(ORDER_CREATE)
                .tag(this)
                .params("carts", new Gson().toJson(cartList))
                .params("addressId", confirmOrderModel.getAddressId())
                .params("userRemark", editContent.getText().toString());
        if (couponListModel != null) {
            params.params("couponUserId", couponListModel.getCouponUserId());
        }
        params.execute(new MyJsonCallback<HttpResModel<OrderPayInfoModel>>() {
            @Override
            protected void onJsonSuccess(Response<HttpResModel<OrderPayInfoModel>> respons, HttpResModel<OrderPayInfoModel> confirmOrderModelHttpResModel) {
                StartActivityUtil.getInstance().goPay(mActivity, confirmOrderModelHttpResModel.getData());
                finish();
            }
        }.isDialog(mActivity));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SELECTADDRESS_SUCCESS == resultCode) {
            if (data != null) {
                AddressListBean.DataBean defaultAddresBean = data.getParcelableExtra(ADDRESS_BEAN);
                if (defaultAddresBean != null) {
                    addressId = defaultAddresBean.getAddressId();
                    starRefresh();
                }
            }
        }
    }
}
