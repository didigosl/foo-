package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.widget.NumInputLayout;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.CartHttpModel;
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_RENEW;

public class ShoppingCartDetailActivity extends BaseActivity {

    @BindView(R.id.recycler_shopping)
    RecyclerView rvList;
    @BindView(R.id.imageView)
    ImageView logo;
    @BindView(R.id.view)
    View view;

    protected PublicConfig mPublicConfig;

    BaseAdapter<ShoppingCartModel.ListBean> mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_detail);
        ButterKnife.bind(this);
        initAdapter();
        getList();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<ShoppingCartModel.ListBean>(rvList, mActivity, R.layout.adapter_shopping_cart)
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
                        CheckBox cb_select_all = helper.getView(R.id.cb_select_all);
                        cb_select_all.setVisibility(View.GONE);
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
                        } else {
                            mAdapter.getData().get(layoutPosition).setNum(num + "");
                            mAdapter.notifyItemChanged(layoutPosition);
                        }
                        //countGoods();
                    }
                }.isDialog(mActivity));
    }


    private void getList(){
        OkGo.<HttpResModel<ShoppingCartModel>>post(CART_LIST)
                .tag(this)
                .execute(new MyJsonCallback<HttpResModel<ShoppingCartModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingCartModel>> respons, HttpResModel<ShoppingCartModel> goodsModelHttpResModel) {
                        mAdapter.setNewData(goodsModelHttpResModel.getData().getList());
                    }
                });
    }
}
