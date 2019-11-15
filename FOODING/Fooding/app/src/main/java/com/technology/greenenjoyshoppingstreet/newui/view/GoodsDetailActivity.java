package com.technology.greenenjoyshoppingstreet.newui.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.LogUtil;
import com.duma.ld.baselibarary.util.Ts;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartItemBean;
import com.technology.greenenjoyshoppingstreet.category.AddCartActivity;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.login.LoginActivity;
import com.technology.greenenjoyshoppingstreet.mine.BuyVIPActivity;
import com.technology.greenenjoyshoppingstreet.newui.adapter.CommentAdapter;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.GoodsDetailMode;
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel;
import com.technology.greenenjoyshoppingstreet.newui.model.addGoodsModel;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
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
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_ADD;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_GET;

public class GoodsDetailActivity extends BaseMyActivity {
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_money2)
    TextView tvMoney2;
    @BindView(R.id.cardView_vip)
    CardView cardViewVip;
    @BindView(R.id.webview_detail)
    WebView webviewDetail;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.tv_all_comment)
    TextView tvAllComment;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    @BindView(R.id.layout_add_cart)
    FrameLayout layoutAddCart; //derecha
    @BindView(R.id.layout_shopping)
    FrameLayout layoutShopping; //izquierda

    private String goodsId;
    private ProductDetailBean.DataBean data;
    private CommentAdapter mAdapter;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.product_detail));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        goodsId = getIntent().getStringExtra(Constants.intent_id);
        mAdapter = new CommentAdapter(rvList, mActivity);
        starRefresh();
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<ProductDetailBean.DataBean>>post(GOODS_SPU_GET)
                .tag(this)
                .params("spu_id", goodsId)
                .execute(new MyJsonCallback<HttpResModel<ProductDetailBean.DataBean>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ProductDetailBean.DataBean>> respons, HttpResModel<ProductDetailBean.DataBean> goodsModelHttpResModel) {
                        initData(goodsModelHttpResModel.getData());
                    }
                });
    }

    private void initData(ProductDetailBean.DataBean data) {
        this.data = data;
        ImageLoader.with(data.getCover(), imgIcon);
        tvTitle.setText(data.getSpuName());
        tvMoney.setText("€ " + data.getPrice());
        try {
            if (Double.parseDouble(data.getOriginPrice()) > 0) {
                tvMoney2.setText(data.getOriginPrice());
                tvMoney2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            } else {
                tvMoney2.setVisibility(View.GONE);
            }
        } catch (NumberFormatException e) {
            tvMoney2.setVisibility(View.GONE);
        }
        initWebView();
        String content = data.getContent();
        if (StringUtils.isEmpty(content)) {
            content = "<p>"+getString(R.string.without_detail)+"</p>";
        }
        String replace = content
                .replace("<img", "<img style=\"display: inline-block;width: 100%;height: auto;\"");
        LogUtil.e(replace);
        webviewDetail.loadData(replace, "text/html", "UTF-8");
        mAdapter.getmAdapter().setNewData(data.getComments().getList());
        tvCommentNum.setText(getString(R.string.comment)+"（" + mAdapter.getmAdapter().getData().size() + "）");
    }


    private void initWebView() {
        WebSettings mWebSettings = webviewDetail.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
//        mWebSettings.setUseWideViewPort(true);
//        mWebSettings.setLoadWithOverviewMode(true);
    }




    @OnClick({R.id.cardView_vip, R.id.tv_all_comment, R.id.layout_add_cart, R.id.layout_shopping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.cardView_vip:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, BuyVIPActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;*/
            case R.id.tv_all_comment:
                if (UserInfoManger.isLogin()) {
                    StartActivityUtil.getInstance().goGoodsComment(mActivity, goodsId);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.layout_add_cart:
                if (UserInfoManger.isLogin()) {
                    /*Intent addCartIntent = new Intent(this, AddCartActivity.class);
                    addCartIntent.putExtra(AddCartActivity.PRODUCT_DETAIL, data);
                    startActivity(addCartIntent);*/
                    addCartList(data.getSkus().get(0));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.layout_shopping:
                if (UserInfoManger.isLogin()) {
                    StartActivityUtil.getInstance().goShoppingCartDetail(mActivity);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }

    private void addCartList(final ProductDetailBean.DataBean.SkusBean skusBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CartItemBean>() {

            @Override
            public void onComplete(CartItemBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        Toast.makeText(mActivity, getString(R.string.add_succesfull), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, bean.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(mActivity, netRequestStatus.getNote(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (skusBean != null) {
                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.SKU_ID, skusBean.getSkuId()));
                    list.add(new BasicKeyValuePair(ParameterConstant.NUM, "1"));
                    return UserInfoManger.getSignList(list);
                }
                return null;
            }
            @Override
            public String createUrl() {
                return CART_ADD;
            }
        });
    }

}
