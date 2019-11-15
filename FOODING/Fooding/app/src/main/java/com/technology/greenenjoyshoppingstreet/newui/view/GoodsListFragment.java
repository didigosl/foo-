package com.technology.greenenjoyshoppingstreet.newui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.EventModel;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.AddCartActivity;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.login.LoginActivity;
import com.technology.greenenjoyshoppingstreet.newui.api.ServiceGenerator;
import com.technology.greenenjoyshoppingstreet.newui.api.service.ICartClient;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyFragment;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.CartHttpModel;
import com.technology.greenenjoyshoppingstreet.newui.model.GoodsModel;
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel;
import com.technology.greenenjoyshoppingstreet.newui.model.cartShop.CartShop;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.technology.greenenjoyshoppingstreet.ktnewui.util.ConstantsKt.BUTTON;
import static com.technology.greenenjoyshoppingstreet.ktnewui.util.ConstantsKt.LAYOUT;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_RENEW;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_GET;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_LIST;

public class GoodsListFragment extends BaseMyFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private BaseAdapter<GoodsModel.ListBean> mAdapter;
    private GoodsListActivity goodsListActivity;
    private String id;

    public static GoodsListFragment newInstance(String id) {
        GoodsListFragment goodsListFragment = new GoodsListFragment();
        Bundle args = new Bundle();
        args.putString(Constants.intent_id, id);
        goodsListFragment.setArguments(args);
        return goodsListFragment;
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(EventModel eventModel) {
        super.onEventBusCome(eventModel);
        if (eventModel.getCode() == Constants.event_refresh) {
            starRefresh();
        }
    }

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_goods_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Bundle args = getArguments();
        getCartList();
        id = args.getString(Constants.intent_id, "0");
        goodsListActivity = (GoodsListActivity) mActivity;
        setRefresh(refreshLayout, true);
        mAdapter = new BaseAdapter.Builder<GoodsModel.ListBean>(rvList, mActivity, R.layout.adapter_goods_list)
                .setLayoutManager(new GridLayoutManager(mActivity, 2))
                .build(new OnBaseAdapterListener<GoodsModel.ListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, GoodsModel.ListBean item) {

                        ShoppingCartModel.ListBean itemCart = getItemCart(item.getSpuId());

                        FrameLayout frBtnLayout = helper.getView(R.id.frBtnLayout);
                        LinearLayout lyManageCart = helper.getView(R.id.lyManageCart);
                        ImageButton ibDelete = helper.getView(R.id.btn_delete);
                        Button btnPlus = helper.getView(R.id.btn_plus);
                        Button btnMinus = helper.getView(R.id.btn_minus);

                        ImageLoader.with(item.getCover(), (ImageView) helper.getView(R.id.img_icon));
                        helper.setText(R.id.tv_title, item.getSpuName());
                        helper.setText(R.id.tv_money, Tools.formatPriceText(item.getSkus().get(0).getPrice()));
                        if (itemCart != null){
                            int number = Integer.parseInt(itemCart.getNum());
//                            showView(LAYOUT);
                            frBtnLayout.setVisibility(GONE);
                            lyManageCart.setVisibility(VISIBLE);

                            if (number < 2){
                                btnMinus.setVisibility(GONE);
                                ibDelete.setVisibility(VISIBLE);
                            }else {
                                ibDelete.setVisibility(GONE);
                                btnMinus.setVisibility(VISIBLE);
                            }

                            helper.setText(R.id.num, itemCart.getNum());

                            ibDelete.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String sku = itemCart.getSkuId();
                                    restCart("0",sku);
                                }
                            });

                            btnPlus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    /*String spu = item.getSpuId();
                                    getGood(spu);*/
                                    addCart(itemCart.getSkuId());
                                }
                            });
                            btnMinus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int n = number - 1;
                                    String sku = itemCart.getSkuId();
                                    restCart(Integer.toString(n),sku);
                                }
                            });

                        }else{
//                            showView(BUTTON);
                            frBtnLayout.setVisibility(VISIBLE);
                            lyManageCart.setVisibility(GONE);

                            final Button btnAdd = helper.getView(R.id.btnRecommend);
                            btnAdd.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (UserInfoManger.isLogin()){
//                                        showView(LAYOUT);
                                        frBtnLayout.setVisibility(GONE);
                                        lyManageCart.setVisibility(VISIBLE);

                                        /*String spu = item.getSpuId();
                                        getGood(spu);*/
                                        addCart(item.getSkus().get(0).getSkuId());
                                    }else {
                                        startActivity(new Intent(getActivity(), LoginActivity.class));
                                    }
                                }
                            });
                        }
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StartActivityUtil.getInstance().goGoodsDetail(mActivity, mAdapter.getData().get(position).getSpuId());
            }
        });
        mPublicConfig.showLoading();
    }

    private ShoppingCartModel.ListBean getItemCart(String spuId) {
        ShoppingCartModel.ListBean item = null;
        if (cartList != null && !cartList.isEmpty()){
            for (ShoppingCartModel.ListBean cart : cartList){
                if (cart.getSpuId().equals(spuId)){
                    item = cart;
                }
            }
        }
        return item;
    }

    /*private void showView(int view){
        switch (view){
            case BUTTON:
                frBtnLayout.setVisibility(VISIBLE);
                lyManageCart.setVisibility(GONE);
                break;
            case LAYOUT:
                frBtnLayout.setVisibility(GONE);
                lyManageCart.setVisibility(VISIBLE);
                break;
        }
    }*/



    private List<ShoppingCartModel.ListBean> cartList = new ArrayList<>();
    private void getCartList(){
        OkGo.<HttpResModel<ShoppingCartModel>>post(CART_LIST)
                .tag(this)
                .execute(new MyJsonCallback<HttpResModel<ShoppingCartModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ShoppingCartModel>> respons, HttpResModel<ShoppingCartModel> goodsModelHttpResModel) {
                        cartList.clear();
                        cartList.addAll(goodsModelHttpResModel.getData().getList());
                        getGoodModel();
                    }
                });
    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        starRefresh();
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<GoodsModel>>post(GOODS_SPU_LIST)
                .tag(this)
                .params("categoryId", id)
                .params("order", goodsListActivity.getOrderString())
                .params("page", page)
                .params("pageLimit", "20")
                .execute(new MyJsonCallback<HttpResModel<GoodsModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<GoodsModel>> respons, HttpResModel<GoodsModel> goodsModelHttpResModel) {
                        setRefreshData(mAdapter, goodsModelHttpResModel.getData().getList());
                    }
                });
    }

    private void getGoodModel(){
        OkGo.<HttpResModel<GoodsModel>>post(GOODS_SPU_LIST)
                .tag(this)
                .params("categoryId", id)
                .params("order", goodsListActivity.getOrderString())
                .params("page", "1")
                .params("pageLimit", "20")
                .execute(new MyJsonCallback<HttpResModel<GoodsModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<GoodsModel>> respons, HttpResModel<GoodsModel> goodsModelHttpResModel) {
                        //setRefreshData(mAdapter, goodsModelHttpResModel.getData().getList());
                        mAdapter.replaceData(goodsModelHttpResModel.getData().getList());
                    }
                });
    }

    private void addCart(String skuId) {
        ICartClient client = ServiceGenerator.createService(ICartClient.class);

        LinkedHashMap<String,String> map1 = new LinkedHashMap<>();
        map1.put("num","1");
        map1.put("skuId",skuId);
        String[] signTime = UserInfoManger.getSignRetrofit(map1);
        String sign = signTime[0];
        String time = signTime[1];
        String token = signTime[2];

        Call<CartShop> call = client.addCart("1",sign,skuId,time,token);
        call.enqueue(new Callback<CartShop>() {
            @Override
            public void onResponse(Call<CartShop> call, retrofit2.Response<CartShop> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("SUCCESSS")){
                        getCartList();
                        //getGoodModel();
                        //starRefresh();
                    }else if (response.body().getStatus().equals("fail")){
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<CartShop> call, Throwable t) {
                Log.e("GoodListFragment", "onFailure: ", t);
            }
        });
    }

    private void restCart(String num,String skuId){

        List<ShoppingCartModel.ListBean> data1 = new ArrayList<>(cartList);
        for (int i=0;i<cartList.size();i++){
            if (cartList.get(i).getSkuId().equals(skuId)){
                if (num.equals("0")){
                    data1.remove(i);
                }else{
                    data1.get(i).setNum(num);
                }
            }
        }

        List<CartHttpModel> list1 = new ArrayList<>();
        for (int j=0; j<data1.size();j++){
            CartHttpModel cartHttpModel1 = new CartHttpModel(data1.get(j).getCartId(), data1.get(j).getNum(), data1.get(j).getSpuId());
            list1.add(cartHttpModel1);
        }

        OkGo.<HttpResModel<Void>>post(CART_RENEW)
                .tag(this)
                .params("data", new Gson().toJson(list1))
                .execute(new MyJsonCallback<HttpResModel<Void>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<Void>> respons, HttpResModel<Void> goodsModelHttpResModel) {
                        getCartList();
                    }
                });

    }

    private ProductDetailBean.DataBean data;

    private void getGood(String goodsId){
        OkGo.<HttpResModel<ProductDetailBean.DataBean>>post(GOODS_SPU_GET)
                .tag(this)
                .params("spu_id", goodsId)
                .execute(new MyJsonCallback<HttpResModel<ProductDetailBean.DataBean>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<ProductDetailBean.DataBean>> respons, HttpResModel<ProductDetailBean.DataBean> goodsModelHttpResModel) {
                        /*if (goodsModelHttpResModel.getData().getSpecs().isEmpty()){
                            addCart(goodsModelHttpResModel.getData().getSkus().get(0).getSkuId());
                        }else {
                            data = goodsModelHttpResModel.getData();
                            Intent addCartIntent = new Intent(getActivity(), AddCartActivity.class);
                            addCartIntent.putExtra(AddCartActivity.PRODUCT_DETAIL, data);
                            startActivity(addCartIntent);
                        }*/
                        addCart(goodsModelHttpResModel.getData().getSkus().get(0).getSkuId());
                    }
                });
    }

}
