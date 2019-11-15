package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyFragment;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.MainAdModel;
import com.technology.greenenjoyshoppingstreet.newui.model.MainTuiJianModel;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.AD_GET;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.INDEX_RECOMMEND;

public class MainTuiJianFragment extends BaseMyFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.banner_guide_content)
    BGABanner bannerGuideContent;
    @BindView(R.id.cardView_banner)
    CardView cardViewBanner;

    private BaseAdapter<MainTuiJianModel> mAdapter;
    private BaseAdapter<MainTuiJianModel.ListBean> mAdapterDetail;

    public static MainTuiJianFragment newInstance() {
        return new MainTuiJianFragment();
    }

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_main_tuijian;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initAdapter();
        initBanner();
        setRefresh(refreshLayout);
        mPublicConfig.showLoading();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<MainTuiJianModel>(rvList, mActivity, R.layout.adapter_main_tuijian)
                .isNested()
                .build(new OnBaseAdapterListener<MainTuiJianModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, final MainTuiJianModel item) {
                        ImageView view = helper.getView(R.id.img_icon);
                        //ImageLoader.with(item.getRecommendPic(), view);
                        ImageLoader.with_rounded(item.getRecommendPic(),view);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StartActivityUtil.getInstance().goGoodsList(mActivity, item.getCategoryId(), item.getCategoryName());
                            }
                        });
                        new BaseAdapter.Builder<MainTuiJianModel.ListBean>((RecyclerView) helper.getView(R.id.rv_goods), mActivity, R.layout.adapter_main_tuijian_goods)
                                .setNoEnpty()
                                .isNested()
                                .setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false))
                                .build(new OnBaseAdapterListener<MainTuiJianModel.ListBean>() {
                                    @Override
                                    public void convert(BaseViewHolder helper, final MainTuiJianModel.ListBean item) {
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

                                }).setNewData(item.getList());

                    }
                });
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
        cardViewBanner.setVisibility(View.GONE);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        starRefresh();
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<List<MainTuiJianModel>>>post(INDEX_RECOMMEND)
                .tag(this)
                .execute(new MyJsonCallback<HttpResModel<List<MainTuiJianModel>>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<MainTuiJianModel>>> respons, HttpResModel<List<MainTuiJianModel>> goodsModelHttpResModel) {
                        mAdapter.setNewData(goodsModelHttpResModel.getData());
                    }
                });
        OkGo.<HttpResModel<List<MainAdModel>>>post(AD_GET)
                .tag(this)
                .params("type", "index")
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
    }

}
