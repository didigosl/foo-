package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Ts;
import com.duma.ld.baselibarary.widget.NumInputLayout;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.CartHttpModel;
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel;
import com.technology.greenenjoyshoppingstreet.newui.util.Arith;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_RENEW;

public class ShoppingCartActivity extends BaseMyActivity {
    @BindView(R.id.cardView_vip)
    CardView cardViewVip;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(R.id.tv_fanLi)
    TextView tvFanLi;
    @BindView(R.id.tv_heJi)
    TextView tvHeJi;
    @BindView(R.id.tv_jiesuan)
    TextView tvJiesuan;
    BaseAdapter<ShoppingCartModel.ListBean> mAdapter;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void setFits(LinearLayout layout_boot_ob) {
    }

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_shopping_cart;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.cart), 0);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setRefresh(refreshLayout);
        initAdapter();

    }

    @Override
    protected void onResume() {
        super.onResume();
        starRefresh();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<ShoppingCartModel.ListBean>(rvList, mActivity, R.layout.adapter_shopping_cart)
                .setTitle(getString(R.string.cart_empty))
                .isNested()
                .build(new OnBaseAdapterListener<ShoppingCartModel.ListBean>() {
                    @Override
                    public void convert(final BaseViewHolder helper, final ShoppingCartModel.ListBean item) {
                        ImageLoader.with(item.getCover(), (ImageView) helper.getView(R.id.img_icon));
                        helper.setText(R.id.tv_title, item.getSpuName())
                                .setText(R.id.tv_money, "€ " + item.getPrice());
                        NumInputLayout inputLayout = helper.getView(R.id.input_num);
                        inputLayout.setNum(Integer.parseInt(item.getNum()));
                        inputLayout.setNoInput();
                        inputLayout.setOnTextClickListener(new NumInputLayout.OnTextClickListener() {
                            @Override
                            public void onClick(int num) {
                                UpdateGoods(helper.getLayoutPosition(), num);
                            }
                        });
                        final CheckBox cb_select_all = helper.getView(R.id.cb_select_all);
                        cb_select_all.setChecked(item.isSelect());
                        cb_select_all.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                item.setSelect(cb_select_all.isChecked());
                                countGoods();
                            }
                        });
                        FrameLayout layout_right = helper.getView(R.id.layout_right);
                        layout_right.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UpdateGoods(helper.getLayoutPosition(), 0);
                            }
                        });
                    }
                });
    }

    private void UpdateGoods(final int layoutPosition, final int num) {
        List<ShoppingCartModel.ListBean> data = new ArrayList<>(mAdapter.getData());
        if (num == 0) {
            data.remove(layoutPosition);
        } else {
            data.get(layoutPosition).setNum(num + "");
        }
        List<CartHttpModel> list = new ArrayList<>();
        CartHttpModel cartHttpModel;
        for (int i = 0; i < data.size(); i++) {
            cartHttpModel = new CartHttpModel(data.get(i).getCartId(), data.get(i).getNum(), data.get(i).getSpuId());
            list.add(cartHttpModel);
        }
        OkGo.<HttpResModel<Void>>post(CART_RENEW)
                .tag(this)
                .params("data", new Gson().toJson(list))
                .execute(new MyJsonCallback<HttpResModel<Void>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<Void>> respons, HttpResModel<Void> goodsModelHttpResModel) {
                        if (num == 0) {
                            mAdapter.remove(layoutPosition);
                            isHide();
                        } else {
                            mAdapter.getData().get(layoutPosition).setNum(num + "");
                            mAdapter.notifyItemChanged(layoutPosition);
                        }
                        countGoods();
                    }
                }.isDialog(mActivity));
    }

    private void countGoods() {
        List<ShoppingCartModel.ListBean> data = mAdapter.getData();
        String sum = "0";
        boolean isAllSelect = true;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                String price = data.get(i).getPrice();
                String num = data.get(i).getNum();
                sum = Arith.add(sum, Arith.mul(price, num) + "") + "";
            } else {
                isAllSelect = false;
            }
        }
        tvHeJi.setText("€ " + sum);
        cbSelectAll.setChecked(isAllSelect);
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<ShoppingCartModel>>post(CART_LIST)
                .tag(this)
                .execute(new MyJsonCallback<HttpResModel<ShoppingCartModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingCartModel>> respons, HttpResModel<ShoppingCartModel> goodsModelHttpResModel) {
                        mAdapter.setNewData(goodsModelHttpResModel.getData().getList());
                        countGoods();
                        isHide();
                    }
                });
    }

    private void isHide() {
        if (mAdapter.getData().size() == 0) {
            layoutBottom.setVisibility(View.GONE);
        } else {
            layoutBottom.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.cardView_vip, R.id.tv_jiesuan, R.id.cb_select_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.cardView_vip:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, BuyVIPActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;*/
            case R.id.tv_jiesuan:
                List<ShoppingCartModel.ListBean> data = new ArrayList<>();
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    if (mAdapter.getData().get(i).isSelect()) {
                        data.add(mAdapter.getData().get(i));
                    }
                }
                if (data.size() == 0) {
                    Ts.show(getString(R.string.not_elements_result));
                    return;
                }
                StartActivityUtil.getInstance().goConfirmOrder(mActivity, data);
                break;
            case R.id.cb_select_all:
                for (int i = 0; i < mAdapter.getData().size(); i++) {
                    mAdapter.getData().get(i).setSelect(cbSelectAll.isChecked());
                }
                mAdapter.notifyDataSetChanged();
                countGoods();
                break;
        }
    }


}
