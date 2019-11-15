package com.technology.greenenjoyshoppingstreet.newui.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyFragment;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.MainAdModel;
import com.technology.greenenjoyshoppingstreet.newui.model.MainClassModel;
import com.technology.greenenjoyshoppingstreet.newui.model.MainGoodsModel;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.AD_GET;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_LIST;

public class MainClassFragment extends BaseMyFragment {
    @BindView(R.id.banner_guide_content)
    BGABanner bannerGuideContent;
    @BindView(R.id.rv_class)
    RecyclerView rvClass;
    @BindView(R.id.rv_rexiao)
    RecyclerView rvRexiao;
    @BindView(R.id.layout_rexiao)
    LinearLayout layoutRexiao;
    @BindView(R.id.rv_dangji)
    RecyclerView rvDangji;
    @BindView(R.id.layout_dangji)
    LinearLayout layoutDangji;
    @BindView(R.id.rv_goods)
    RecyclerView rvGoods;
    @BindView(R.id.layout_goods)
    LinearLayout layoutGoods;
    @BindView(R.id.cardView_banner)
    CardView cardViewBanner;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_rexiao)
    TextView tvRexiao;
    @BindView(R.id.tv_dangji)
    TextView tvDangji;
    @BindView(R.id.tv_goods)
    TextView tvGoods;

    private BaseAdapter<MainGoodsModel.ListBean> mAdapter;

    private BaseAdapter<MainGoodsModel.ListBean> mReXiaoAdapter;
    private BaseAdapter<MainGoodsModel.ListBean> mDangjiAdapter;

    public static MainClassFragment newInstance(MainClassModel mainClassModel) {
        MainClassFragment mainTuiJianFragment = new MainClassFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.intent_Model, mainClassModel);
        mainTuiJianFragment.setArguments(args);
        return mainTuiJianFragment;
    }

    private MainClassModel mainClassModel;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_main_class;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        mainClassModel = (MainClassModel) getArguments().getSerializable(Constants.intent_Model);
        initBanner();
        initAdapter();
        setRefresh(refreshLayout, true);
        mPublicConfig.showLoading();
        tvClass.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvDangji.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvGoods.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tvRexiao.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    private void initAdapter() {
        //商品列表
        mAdapter = new BaseAdapter.Builder<MainGoodsModel.ListBean>(rvGoods, mActivity, R.layout.adapter_goods_list)
                .setLayoutManager(new GridLayoutManager(mActivity, 2))
                .isNested()
                .build(new OnBaseAdapterListener<MainGoodsModel.ListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, MainGoodsModel.ListBean item) {
                        ImageLoader.with(item.getCover(), (ImageView) helper.getView(R.id.img_icon));
                        helper.setText(R.id.tv_title, item.getSpuName());
                        helper.setText(R.id.tv_money, Tools.formatPriceText(item.getPrice()));
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StartActivityUtil.getInstance().goGoodsDetail(mActivity, mAdapter.getData().get(position).getSpuId());
            }
        });
        //热门分类
        final BaseAdapter<MainClassModel.SonsBeanX> classAdapter = new BaseAdapter.Builder<MainClassModel.SonsBeanX>(rvClass, mActivity, R.layout.adapter_main_class)
                .setLayoutManager(new GridLayoutManager(mActivity, 4))
                .isNested()
                .build(new OnBaseAdapterListener<MainClassModel.SonsBeanX>() {
                    @Override
                    public void convert(BaseViewHolder helper, MainClassModel.SonsBeanX item) {
                        ImageLoader.with(item.getCategoryCover(), (ImageView) helper.getView(R.id.img_icon));
                        helper.setText(R.id.tv_title, item.getCategoryName());
                    }
                });
        classAdapter.setNewData(mainClassModel.getSons());
        classAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StartActivityUtil.getInstance().goGoodsList(mActivity
                        , classAdapter.getData().get(position).getCategoryId()
                        , classAdapter.getData().get(position).getCategoryName());
            }
        });

        OnBaseAdapterListener<MainGoodsModel.ListBean> onBaseAdapterListener = new OnBaseAdapterListener<MainGoodsModel.ListBean>() {
            @Override
            public void convert(BaseViewHolder helper, final MainGoodsModel.ListBean item) {
                helper.setText(R.id.tv_title, item.getSpuName());
                helper.setText(R.id.tv_money, "€ " + item.getPrice());
                ImageView view = helper.getView(R.id.img_icon);
                ImageLoader.with(item.getCover(), view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartActivityUtil.getInstance().goGoodsDetail(mActivity, item.getSpuId());
                    }
                });
            }
        };
        mDangjiAdapter = new BaseAdapter.Builder<MainGoodsModel.ListBean>(rvDangji, mActivity, R.layout.adapter_main_tuijian_goods)
                .setNoEnpty()
                .isNested()
                .setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false))
                .build(onBaseAdapterListener);
        mReXiaoAdapter = new BaseAdapter.Builder<MainGoodsModel.ListBean>(rvRexiao, mActivity, R.layout.adapter_main_tuijian_goods)
                .setNoEnpty()
                .isNested()
                .setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false))
                .build(onBaseAdapterListener);
        cardViewBanner.setVisibility(View.GONE);
        layoutDangji.setVisibility(View.GONE);
        layoutRexiao.setVisibility(View.GONE);

    }

    private void initBanner() {
        bannerGuideContent.setAdapter(new BGABanner.Adapter<ImageView, MainAdModel>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, MainAdModel model, int position) {
                ImageLoader.with(model.getImg(), itemView);
            }
        });
        bannerGuideContent.setDelegate(new BGABanner.Delegate<ImageView, MainAdModel>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, MainAdModel model, int position) {
                StartActivityUtil.getInstance().goGoodsList(mActivity, model.getLinkId(), model.getAdName());
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
        if (page == 1) {
            initHttp();
        }
        OkGo.<HttpResModel<MainGoodsModel>>post(GOODS_SPU_LIST)
                .tag(this)
                .params("categoryId", mainClassModel.getCategoryId())
                .params("page", page)
                .params("pageLimit", "20")
                .execute(new MyJsonCallback<HttpResModel<MainGoodsModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<MainGoodsModel>> respons, HttpResModel<MainGoodsModel> goodsModelHttpResModel) {
                        setRefreshData(mAdapter, goodsModelHttpResModel.getData().getList());
                    }
                });
    }

    //    只加载一次的http
    private void initHttp() {
        OkGo.<HttpResModel<List<MainAdModel>>>post(AD_GET)
                .tag(this)
                .params("type", "category")
                .params("categoryId", mainClassModel.getCategoryId())
                .execute(new MyJsonCallback<HttpResModel<List<MainAdModel>>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<MainAdModel>>> respons, HttpResModel<List<MainAdModel>> goodsModelHttpResModel) {
                        List<MainAdModel> data = goodsModelHttpResModel.getData();
                        if (data != null && data.size() > 0) {
                            bannerGuideContent.setData(data, null);
                            cardViewBanner.setVisibility(View.VISIBLE);
                        }
                    }
                });
        OkGo.<HttpResModel<MainGoodsModel>>post(GOODS_SPU_LIST)
                .tag(this)
                .params("categoryId", mainClassModel.getCategoryId())
                .params("labelId", "2")
                .execute(new MyJsonCallback<HttpResModel<MainGoodsModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<MainGoodsModel>> respons, HttpResModel<MainGoodsModel> goodsModelHttpResModel) {
                        List<MainGoodsModel.ListBean> list = goodsModelHttpResModel.getData().getList();
                        if (list != null && list.size() > 0) {
                            mReXiaoAdapter.setNewData(list);
                            layoutRexiao.setVisibility(View.VISIBLE);
                        }
                    }
                });
        OkGo.<HttpResModel<MainGoodsModel>>post(GOODS_SPU_LIST)
                .tag(this)
                .params("categoryId", mainClassModel.getCategoryId())
                .params("labelId", "1")
                .execute(new MyJsonCallback<HttpResModel<MainGoodsModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<MainGoodsModel>> respons, HttpResModel<MainGoodsModel> goodsModelHttpResModel) {
                        List<MainGoodsModel.ListBean> list = goodsModelHttpResModel.getData().getList();
                        if (list != null && list.size() > 0) {
                            mDangjiAdapter.setNewData(list);
                            layoutDangji.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

}
