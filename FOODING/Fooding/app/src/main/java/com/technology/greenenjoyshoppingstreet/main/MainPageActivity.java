package com.technology.greenenjoyshoppingstreet.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity;
import com.technology.greenenjoyshoppingstreet.main.adapter.CurrentSeasonProductAdapter;
import com.technology.greenenjoyshoppingstreet.main.adapter.FlashSaleAdapter;
import com.technology.greenenjoyshoppingstreet.main.adapter.HotSingleGoodAdapter;
import com.technology.greenenjoyshoppingstreet.main.adapter.HotSubCategoryAdapter;
import com.technology.greenenjoyshoppingstreet.main.adapter.IndexRecommendAdapter;
import com.technology.greenenjoyshoppingstreet.main.bean.AdBean;
import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;
import com.technology.greenenjoyshoppingstreet.main.bean.FlashSaleBean;
import com.technology.greenenjoyshoppingstreet.main.bean.IndexRecommendBean;
import com.technology.greenenjoyshoppingstreet.main.bean.MainPageBean;
import com.technology.greenenjoyshoppingstreet.main.bean.ProductBean;
import com.technology.greenenjoyshoppingstreet.mine.BuyVIPActivity;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.newui.view.MainFragmentActivity;
import com.technology.greenenjoyshoppingstreet.utils.DateUtils;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.PullMode;
import com.technology.greenenjoyshoppingstreet.utils.ZhuanHuanUtil;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.technology.greenenjoyshoppingstreet.utils.view.GridViewInScroll;
import com.technology.greenenjoyshoppingstreet.utils.view.HorizontalListView;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity.GOOD_ID;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.COUNT_DOWN_OVER;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.FIRST_PAGE_INDEX;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.GO_REQUEST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.MS_PER_SECOND;
import static com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant.CURRENT_SEASON_GOOD_LABEL_ID;
import static com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant.HOT_SINGLE_GOOD_LABEL_ID;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.AD_GET;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CATEGORY_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.FLASH_SALE_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.INDEX_RECOMMEND;

/**
 * Created by Administrator on 2017/12/10.
 */

public class MainPageActivity extends BaseActivity {


    /*@BindView(R.id.gift_rl)
    FrameLayout giftRl;*/
    @BindView(R.id.search_rl)
    FrameLayout searchRl;
    @BindView(R.id.banner_cb)
    ConvenientBanner bannerCb;
    @BindView(R.id.today_time_tv)
    TextView todayTimeTv;
    @BindView(R.id.refresh_fl)
    PtrClassicFrameLayout refreshFl;
    @BindView(R.id.category_tl)
    TabLayout categoryTl;
    @BindView(R.id.next_tv)
    TextView nextTv;
    @BindView(R.id.today_buy_root_rl)
    RelativeLayout todayBuyRootRl;
    @BindView(R.id.recomment_data_lv)
    ListViewInScroll recommentDataLv;
    @BindView(R.id.recomment_data_rl)
    RelativeLayout recommentDataRl;
    @BindView(R.id.recommen_root_rl)
    RelativeLayout recommenRootRl;
    @BindView(R.id.hot_data_gv)
    GridViewInScroll hotDataGv;
    @BindView(R.id.hot_category_rl)
    RelativeLayout hotCategoryRl;
    @BindView(R.id.hot_pin_data_lv)
    HorizontalListView hotPinDataLv;
    @BindView(R.id.hot_pin_rl)
    RelativeLayout hotPinRl;
    @BindView(R.id.new_season_data_gv)
    GridViewInScroll newSeasonDataGv;
    @BindView(R.id.new_season_rl)
    RelativeLayout newSeasonRl;
    @BindView(R.id.category_detail_root_rl)
    RelativeLayout categoryDetailRootRl;
    @BindView(R.id.root_sv)
    ScrollView rootSv;
    @BindView(R.id.flash_sale_lv)
    HorizontalListView flashSaleLv;

    IndexRecommendAdapter indexRecommendAdapter;

    HotSubCategoryAdapter subCategoryAdapter;

    HotSingleGoodAdapter hotSingleGoodAdapter;

    CurrentSeasonProductAdapter currentSeasonProductAdapter;
    CurrentSeasonProductAdapter categoryGoodsAdapter;
    @BindView(R.id.category_goods_data_gv)
    GridViewInScroll categoryGoodsDataGv;
    @BindView(R.id.category_goods_rl)
    RelativeLayout categoryGoodsRl;

    @BindView(R.id.hot_category_tv)
    TextView hot_category_tv;
    @BindView(R.id.hot_pin_tv)
    TextView hot_pin_tv;
    @BindView(R.id.new_season_tv)
    TextView new_season_tv;
    @BindView(R.id.category_goods_tv)
    TextView category_goods_tv;

    /**
     * Pull mode.
     */
    private PullMode pullMode = PullMode.HEADER;


    /**
     * Current page num.
     */
    public int currentPageNum = FIRST_PAGE_INDEX;
    /**
     * Is get list running.
     */
    private AtomicBoolean isGetListRunning = new AtomicBoolean(false);


    private FlashSaleAdapter flashSaleAdapter;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(this);
        initViews();
        hot_category_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        hot_pin_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        new_season_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        category_goods_tv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    private String tabText = RECOMMEND_TAB;

    private void initViews() {
        flashSaleAdapter = new FlashSaleAdapter(this);
        flashSaleLv.setAdapter(flashSaleAdapter);
        flashSaleLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FlashSaleBean.DataBean.GoodsBean listBean = flashSaleAdapter.getItem(position);
                if (listBean != null) {
                    /*Intent intent = new Intent(MainPageActivity.this, ProductDetailActivity.class);
                    intent.putExtra(GOOD_ID, listBean.getSpuId());
                    startActivity(intent);*/
                    StartActivityUtil.getInstance().goGoodsDetail(MainPageActivity.this, listBean.getSpuId());
                }
            }
        });
        categoryGoodsAdapter = new CurrentSeasonProductAdapter(this);
        categoryGoodsDataGv.setAdapter(categoryGoodsAdapter);
        categoryGoodsDataGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductBean.DataBean.ListBean listBean = categoryGoodsAdapter.getItem(position);
                if (listBean != null) {
//                    Intent intent = new Intent(MainPageActivity.this, ProductDetailActivity.class);
//                    intent.putExtra(GOOD_ID, listBean.getSpuId());
//                    startActivity(intent);
                    StartActivityUtil.getInstance().goGoodsDetail(MainPageActivity.this, listBean.getSpuId());
                }
            }
        });

        indexRecommendAdapter = new IndexRecommendAdapter(this);
        recommentDataLv.setAdapter(indexRecommendAdapter);


        subCategoryAdapter = new HotSubCategoryAdapter(this);
        hotDataGv.setAdapter(subCategoryAdapter);
        hotDataGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<CategoryBean.DataBean> dataBeans = subCategoryAdapter.getDataList();
                if (dataBeans != null && !dataBeans.isEmpty()) {
//                    Intent intent = new Intent(MainPageActivity.this, ThreeCateGoryActivity.class);
//                    intent.putExtra(CATEGORY_DATA, dataBeans.get(position));
//                    startActivity(intent);
                    CategoryBean.DataBean dataBean = dataBeans.get(position);
                    StartActivityUtil.getInstance().goGoodsList(MainPageActivity.this, dataBean.getCategoryId(), dataBean.getCategoryName());
                }

            }
        });

        hotSingleGoodAdapter = new HotSingleGoodAdapter(this);
        hotPinDataLv.setAdapter(hotSingleGoodAdapter);
        hotPinDataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductBean.DataBean.ListBean listBean = hotSingleGoodAdapter.getItem(position);
                if (listBean != null) {
//                    Intent intent = new Intent(MainPageActivity.this, ProductDetailActivity.class);
//                    intent.putExtra(GOOD_ID, listBean.getSpuId());
//                    startActivity(intent);
                    StartActivityUtil.getInstance().goGoodsDetail(MainPageActivity.this, listBean.getSpuId());
                }
            }
        });


        currentSeasonProductAdapter = new CurrentSeasonProductAdapter(this);
        newSeasonDataGv.setAdapter(currentSeasonProductAdapter);
        newSeasonDataGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductBean.DataBean.ListBean listBean = currentSeasonProductAdapter.getItem(position);
                if (listBean != null) {
//                    Intent intent = new Intent(MainPageActivity.this, ProductDetailActivity.class);
//                    intent.putExtra(GOOD_ID, listBean.getSpuId());
//                    startActivity(intent);
                    StartActivityUtil.getInstance().goGoodsDetail(MainPageActivity.this, listBean.getSpuId());
                }
            }
        });


        categoryTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCheck(tab, true);
                tabText = tab.getText().toString();
                if (RECOMMEND_TAB.equals(tab.getText())) {
                    recommenRootRl.setVisibility(View.VISIBLE);
                    categoryDetailRootRl.setVisibility(View.GONE);
                } else {
                    recommenRootRl.setVisibility(View.GONE);
                    categoryDetailRootRl.setVisibility(View.VISIBLE);
                }
                changeTabRefresh();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                setCheck(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        initRefreList();
        getCategoryList();
    }


    /**
     * Init refre list.
     */
    public void initRefreList() {
        refreshFl.setPtrHandler(new PtrDefaultHandler2() {

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                footRefresh();

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                headRefresh();
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                if (TextUtils.equals(tabText, RECOMMEND_TAB)) {
                    return false;
                } else {
                    return super.checkCanDoLoadMore(frame, rootSv, footer);

                }

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, rootSv, header);
            }
        });
        // the following are default settings
        refreshFl.setResistance(1.7f); // you can also set foot and header separately
        refreshFl.setRatioOfHeaderHeightToRefresh(1.2f);
        refreshFl.setDurationToClose(1000);  // you can also set foot and header separately
        // default is false
        refreshFl.setPullToRefresh(false);

        // default is true
        refreshFl.setKeepHeaderWhenRefresh(true);
    }

    /**
     * Head refresh.
     */
    private void headRefresh() {
        mainPageBeanHashMap.clear();
        pullMode = PullMode.HEADER;
        currentPageNum = FIRST_PAGE_INDEX;
        getCategoryList();

    }

    /**
     * Foot refresh.
     */
    private void footRefresh() {
        pullMode = PullMode.FOOTER;
        boolean isRecommendTab = TextUtils.equals(tabText, RECOMMEND_TAB);
        if (isRecommendTab) {
            refreshFl.refreshComplete();

        } else {
            MainPageBean mainPageBean = mainPageBeanHashMap.get(tabText);
            if (mainPageBean != null) {
                getCurrentSeason(mainPageBean);
                getCategoryeGood(mainPageBean);
            } else {
                refreshFl.refreshComplete();
            }

        }
    }

    private void changeTabRefresh() {
        boolean isRecommendTab = TextUtils.equals(tabText, RECOMMEND_TAB);
        if (isRecommendTab) {
            MainPageBean mainPageBean = mainPageBeanHashMap.get(tabText);

            if (mainPageBean != null) {

                if (mainPageBean.isGetData()) {
                    refreshBanner(mainPageBean.getAdList());
                    indexRecommendAdapter.updateData(mainPageBean.getIndexRecommendList(), true);

                } else {
                    getAD(AdBean.INDEX_TYPE, mainPageBean);

                    getRecommendList(mainPageBean);
                    mainPageBean.setGetData(true);
                }


            }
            getFlashSale();
        } else {
            MainPageBean mainPageBean = mainPageBeanHashMap.get(tabText);
            if (mainPageBean != null) {
                CategoryBean.DataBean categoryBean = mainPageBean.getCategoryBean();
                if (categoryBean != null) {
                    if (mainPageBean.isGetData()) {
                        refreshBanner(mainPageBean.getAdList());

                        subCategoryAdapter.updateData(mainPageBean.getSubCategoryList(), true);
                        hotCategoryRl.setVisibility(subCategoryAdapter.isEmpty() ? View.GONE : View.VISIBLE);

                        hotSingleGoodAdapter.updateData(mainPageBean.getHotSingleList(), true);
                        hotPinRl.setVisibility(currentSeasonProductAdapter.isEmpty() ? View.GONE : View.VISIBLE);


                        categoryGoodsAdapter.updateData(mainPageBean.getCategoryGoodList(), true);
                        categoryDetailRootRl.setVisibility(categoryGoodsAdapter.isEmpty() ? View.GONE :
                                View.VISIBLE);


                        currentSeasonProductAdapter.updateData(mainPageBean.getCurrentSeasonList
                                (), true);


                        newSeasonRl.setVisibility(hotSingleGoodAdapter.isEmpty() ? View.GONE :
                                View.VISIBLE);
                    } else {
                        getAD(AdBean.CATEGORY_TYPE, mainPageBean);
                        getSubCategoryList(mainPageBean);
                        getHotSingleGood(mainPageBean);
                        getCategoryeGood(mainPageBean);
                        getCurrentSeason(mainPageBean);
                        mainPageBean.setGetData(true);
                    }


                }
            }


        }
        rootSv.smoothScrollTo(0, 0);
    }


    List<AdBean.DataBean> bannerList = new ArrayList<>();

    private void refreshBanner(final List<AdBean.DataBean> data) {
        bannerList.clear();
        if (data != null && !data.isEmpty()) {
            bannerList.addAll(data);

        }
        if (bannerList.isEmpty()) {
            bannerCb.setVisibility(View.GONE);
        } else {
            bannerCb.setVisibility(View.VISIBLE);
            bannerCb.setPages(
                    new CBViewHolderCreator<Holder>() {
                        @Override
                        public Holder createHolder() {
                            return new Holder<AdBean.DataBean>() {
                                private ImageView imageView;

                                @Override
                                public View createView(Context context) {
                                    View root = LayoutInflater.from(context)
                                            .inflate(R
                                                    .layout
                                                    .item_banner, null);
                                    imageView = (ImageView) root.findViewById(R.id
                                            .banner_ic);

                                    return root;
                                }

                                @Override
                                public void UpdateUI(Context context, int position, AdBean.DataBean data) {
//                                    if (data != null && !TextUtils.isEmpty(data.getImg())) {
//
//                                        Glide.with(context).load(data.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shape_defualt_bg)
//                                                .into
//                                                        (imageView);
//                                    } else {
//                                        Glide.with(context).load(R.drawable.shape_defualt_bg).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shape_defualt_bg)
//                                                .into
//                                                        (imageView);
//                                    }
                                    ImageLoader.with(data.getImg(), imageView);

                                }
                            };
                        }
                    }, data);
            bannerCb.startTurning(5000);
            bannerCb.setPageIndicator(new int[]{R.drawable.ui39, R
                    .drawable.ui35});
            //设置指示器位置（左、中、右）
            bannerCb.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            bannerCb.setPointViewVisible(true);
            bannerCb.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    AdBean.DataBean listBean = data.get(position);
                    if (listBean != null) {
                        if (TextUtils.equals(AdBean.GOODS, listBean.getLinkType())) {
//                            Intent intent = new Intent(MainPageActivity.this, ProductDetailActivity.class);
//                            intent.putExtra(GOOD_ID, listBean.getLinkId());
//                            startActivity(intent);
                            StartActivityUtil.getInstance().goGoodsDetail(MainPageActivity.this, listBean.getLinkId());
                        } else if (TextUtils.equals(AdBean.CATEGORY, listBean.getLinkType())) {
                            CategoryBean.DataBean dataBean = new CategoryBean.DataBean();
                            dataBean.setCategoryId(listBean.getLinkId());
                            dataBean.setCategoryName(listBean.getAdName());
                            ArrayList<CategoryBean.DataBean> arrayList = new ArrayList<>();
                            arrayList.add(dataBean);
//                            Intent intent = new Intent(MainPageActivity.this, SecondCategoryActivity.class);
//                            intent.putParcelableArrayListExtra(SecondCategoryActivity.CATEGORY_LIST, arrayList);
//                            intent.putExtra(CATEGORY_INDEX, 0);
//                            intent.putExtra(CATEGORY_NAME, listBean.getAdName());
//                            startActivity(intent);
                            StartActivityUtil.getInstance().goGoodsList(MainPageActivity.this, dataBean.getCategoryId(), dataBean.getCategoryName());
                        }

                    }
                }
            });
        }


    }

    private void setCheck(TabLayout.Tab tab, boolean isCheck) {
        if (tab != null) {
            View rootView = tab.getCustomView();
            if (rootView != null) {
                TextView tv = (TextView) rootView.findViewById(R.id.main_tv);
                if (tv != null) {
                    if (isCheck) {
                        tv.setTextColor(ZhuanHuanUtil.getColor(R.color.main));
                    } else {
                        tv.setTextColor(ZhuanHuanUtil.getColor(R.color.textColor_hei));
                    }
                }
                ImageView iv = (ImageView) rootView.findViewById(R.id.main_iv);
                if (iv != null) {
                    if (isCheck) {
                        iv.setVisibility(View.VISIBLE);
                    } else {
                        iv.setVisibility(View.INVISIBLE);
                    }

                }

            }
        }


    }


    private void getCurrentSeason(final MainPageBean mainPageBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ProductBean>() {

            @Override
            public void onComplete(ProductBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        ProductBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {

                            List<ProductBean.DataBean.ListBean> dataBeanList = dataBean.getList();


                            //请求第一页的数据，需要清除旧的数据
                            boolean isClearOld = (mainPageBean.getCurrentSeasonNextPage() == FIRST_PAGE_INDEX);
                            currentSeasonProductAdapter.updateData(dataBeanList, isClearOld);
                            if (isClearOld && !currentSeasonProductAdapter.isEmpty()) {
                                //刷新列表滑动到首行
                                rootSv.smoothScrollTo(0, 0);
                            }
                            if (dataBeanList != null && !dataBeanList.isEmpty()) {
                                //准备获取下一页数据
                                if (mainPageBean != null) {
                                    mainPageBean.incrementPage();

                                }
                            } else {
                                if (pullMode == PullMode.FOOTER) {
                                    //上滑刷新，没有数据需要提示用户
//                                    tip(R.string.hint_no_content);
                                }
                            }
                            if (mainPageBean != null) {
                                mainPageBean.updateCurrentSeasonList(currentSeasonProductAdapter
                                        .getDataList(), true);
                            }

                        }


                    }

                }
                refreshFl.refreshComplete();
                newSeasonRl.setVisibility(hotSingleGoodAdapter.isEmpty() ? View.GONE : View.VISIBLE);
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (mainPageBean != null) {
                    CategoryBean.DataBean categoryBean = mainPageBean.getCategoryBean();
                    if (categoryBean != null) {
                        showLoadingDialog();

                        List<KeyValuePair> list = new ArrayList<>();
                        list.add(new BasicKeyValuePair(ParameterConstant.CATEGORYID, categoryBean.getCategoryId()));
                        list.add(new BasicKeyValuePair(ParameterConstant.LABEL_ID, CURRENT_SEASON_GOOD_LABEL_ID));
                        list.add(new BasicKeyValuePair(ParameterConstant.PAGE, String.valueOf(mainPageBean.getCurrentSeasonNextPage())));
                        list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT, Constant.PER_PAGE_COUNT));
                        return list;

                    }

                }
                return null;


            }


            @Override
            public String createUrl() {

                return GOODS_SPU_LIST;
            }
        });

    }

    private void getCategoryeGood(final MainPageBean mainPageBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ProductBean>() {

            @Override
            public void onComplete(ProductBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        ProductBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {

                            List<ProductBean.DataBean.ListBean> dataBeanList = dataBean.getList();


                            //请求第一页的数据，需要清除旧的数据
                            boolean isClearOld = (mainPageBean.getCurrentCategoryGoodPage() == FIRST_PAGE_INDEX);
                            categoryGoodsAdapter.updateData(dataBeanList, isClearOld);
                            if (isClearOld && !categoryGoodsAdapter.isEmpty()) {
                                //刷新列表滑动到首行
                                rootSv.smoothScrollTo(0, 0);
                            }
                            if (dataBeanList != null && !dataBeanList.isEmpty()) {
                                //准备获取下一页数据
                                if (mainPageBean != null) {
                                    mainPageBean.incrementCategoryGoodsPage();

                                }
                            } else {
                                if (pullMode == PullMode.FOOTER) {
                                    //上滑刷新，没有数据需要提示用户
//                                    tip(R.string.hint_no_content);
                                }
                            }
                            if (mainPageBean != null) {
                                mainPageBean.updateCategoryGoodsList(categoryGoodsAdapter
                                        .getDataList(), true);
                            }

                        }


                    }

                }
                refreshFl.refreshComplete();
                categoryGoodsRl.setVisibility(categoryGoodsAdapter.isEmpty() ? View.GONE : View.VISIBLE);
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (mainPageBean != null) {
                    CategoryBean.DataBean categoryBean = mainPageBean.getCategoryBean();
                    if (categoryBean != null) {
                        showLoadingDialog();

                        List<KeyValuePair> list = new ArrayList<>();
                        list.add(new BasicKeyValuePair(ParameterConstant.CATEGORYID, categoryBean.getCategoryId()));
                        list.add(new BasicKeyValuePair(ParameterConstant.PAGE, String.valueOf(mainPageBean.getCurrentCategoryGoodPage())));
                        list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT, Constant.PER_PAGE_COUNT));
                        return list;

                    }

                }
                return null;


            }


            @Override
            public String createUrl() {

                return GOODS_SPU_LIST;
            }
        });

    }


    private void getHotSingleGood(final MainPageBean mainPageBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ProductBean>() {

            @Override
            public void onComplete(ProductBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        ProductBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {
                            hotSingleGoodAdapter.updateData(dataBean.getList(), true);
                            if (mainPageBean != null) {
                                mainPageBean.updateHotSingleList(dataBean.getList(), true);

                            }
                        }


                    }

                }

                hotPinRl.setVisibility(currentSeasonProductAdapter.isEmpty() ? View.GONE : View.VISIBLE);
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (mainPageBean != null) {
                    CategoryBean.DataBean dataBean = mainPageBean.getCategoryBean();
                    if (dataBean != null) {
                        List<KeyValuePair> list = new ArrayList<>();
                        list.add(new BasicKeyValuePair(ParameterConstant.CATEGORYID, dataBean.getCategoryId()));
                        list.add(new BasicKeyValuePair(ParameterConstant.LABEL_ID, HOT_SINGLE_GOOD_LABEL_ID));
                        list.add(new BasicKeyValuePair(ParameterConstant.PAGE,
                                Constant.FIRST_PAGE_INDEX_STR));
                        list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT,
                                Constant.PER_PAGE_COUNT));

                        return list;


                    }
                }
                return null;


            }


            @Override
            public String createUrl() {

                return GOODS_SPU_LIST;
            }
        });
    }


    private void getRecommendList(final MainPageBean mainPageBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<IndexRecommendBean>() {

            @Override
            public void onComplete(IndexRecommendBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        indexRecommendAdapter.updateData(bean.getData(), true);
                        if (mainPageBean != null) {
                            mainPageBean.updateIndexRecommendList(bean.getData(), true);

                        }
                        rootSv.smoothScrollTo(0, 0);
                    }

                }
                recommentDataRl.setVisibility(indexRecommendAdapter.isEmpty() ? View.GONE : View.VISIBLE);


            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                return GO_REQUEST;


            }


            @Override
            public String createUrl() {

                return INDEX_RECOMMEND;
            }
        });


    }

    /**
     * 倒计时计时器.
     */
    private CountDownTimer timer;
    private HashMap<String, MainPageBean> mainPageBeanHashMap = new HashMap<>();
    private static final String RECOMMEND_TAB = "推荐";


    private void restartCountDown(long endTime) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(endTime,
                MS_PER_SECOND) {

            @Override
            public void onTick(
                    long millisUntilFinished) {

                calculateTimeDisplay(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                calculateTimeDisplay(COUNT_DOWN_OVER);

            }
        };
        timer.start();
    }

    /**
     * 倒计时时间展示.
     *
     * @param millisUntilFinished the millis until finished
     */
    public void calculateTimeDisplay(long millisUntilFinished) {
        if (millisUntilFinished > COUNT_DOWN_OVER) {
            long hour = millisUntilFinished / HOUR_MILL;
            long min = millisUntilFinished % HOUR_MILL / MIN_MILL;
            long second = millisUntilFinished % HOUR_MILL % MIN_MILL / 1000;
            todayBuyRootRl.setVisibility(View.VISIBLE);
            String hourStr = String.valueOf(hour);
            String content = String.format("%02d 时 %02d 分 %02d 秒", hour, min, second);
            SpannableString spannableString = new SpannableString(content);
            int length = hourStr.length();
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_purple)), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_text)), length, length + 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_purple)), length + 3, length + 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_text)), length + 5, length + 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_purple)), length + 8, length + 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_text)), length + 10, content.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            todayTimeTv.setText(spannableString);
        } else {
            todayBuyRootRl.setVisibility(View.GONE);
            getFlashSale();
        }
    }

    private LinkedList<FlashSaleBean.DataBean> flashList = new LinkedList<>();

    private void getFlashSale() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<FlashSaleBean>() {

            @Override
            public void onComplete(FlashSaleBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                todayBuyRootRl.setVisibility(View.GONE);
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        flashList.clear();
                        List<FlashSaleBean.DataBean> dataBeans = bean.getData();
                        flashList.addAll(dataBeans);
                        circleFlashSale(false);


                    }

                }
                rootSv.smoothScrollTo(0, 0);

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                return GO_REQUEST;


            }


            @Override
            public String createUrl() {

                return FLASH_SALE_LIST;
            }
        });

    }

    public static final long HOUR_MILL = 3600 * 1000;
    public static final long MIN_MILL = 60 * 1000;

    private boolean circleFlashSale(boolean isNext) {
        boolean result = false;
        FlashSaleBean.DataBean dataBean = null;
        if (!flashList.isEmpty()) {
            if (isNext) {
                dataBean = flashList.removeFirst();

            }
            if (!flashList.isEmpty()) {
                FlashSaleBean.DataBean judgeBean = flashList.getFirst();
                if (judgeBean != null) {
                    long time = DateUtils.parseDateMS(judgeBean.getEndTime()) - System.currentTimeMillis();
                    if (time > 0) {
                        result = true;
                        todayBuyRootRl.setVisibility(View.VISIBLE);
                        flashSaleAdapter.updateData(judgeBean.getGoods(), true);
//                        todayTimeTv.setText(judgeBean.getEndTime());
                        restartCountDown(time);

                    }
                }


            }
            if (isNext) {
                flashList.addLast(dataBean);
            }
        }
        return result;

    }

    private void getSubCategoryList(final MainPageBean mainPageBean) {

        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CategoryBean>() {

            @Override
            public void onComplete(CategoryBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        subCategoryAdapter.updateData(bean.getData(), true);
                        mainPageBean.updateSubCategoryList(bean.getData(), true);
                    }

                }
                cancelLoadingDialog();
                hotCategoryRl.setVisibility(subCategoryAdapter.isEmpty() ? View.GONE : View.VISIBLE);
                rootSv.smoothScrollTo(0, 0);
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (mainPageBean != null) {
                    CategoryBean.DataBean categoryBean = mainPageBean.getCategoryBean();
                    if (categoryBean != null) {
                        showLoadingDialog();
                        List<KeyValuePair> list = new ArrayList<>();
                        list.add(new BasicKeyValuePair(ParameterConstant.TYPE, ParameterConstant.SON_TYPE));
                        list.add(new BasicKeyValuePair(ParameterConstant.PARENTID, categoryBean.getCategoryId()));
                        return list;
                    }

                }
                return null;


            }


            @Override
            public String createUrl() {

                return CATEGORY_LIST;
            }
        });


    }


    private void getCategoryList() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CategoryBean>() {

            @Override
            public void onComplete(CategoryBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                String lastTabText = tabText;
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        mainPageBeanHashMap.clear();
                        List<CategoryBean.DataBean> dataBeanList = bean.getData();
                        categoryTl.removeAllTabs();
                        if (dataBeanList != null) {
                            TabLayout.Tab recommendTab = categoryTl.newTab();

                            View recommendView = LayoutInflater.from(MainPageActivity.this).inflate(R
                                            .layout.tab_category,
                                    null);
                            TextView recommendTv = (TextView) recommendView.findViewById(R.id
                                    .main_tv);
                            if (recommendTv != null) {
                                recommendTv.setText(RECOMMEND_TAB);
                                recommendTab.setText(RECOMMEND_TAB);

                            }
                            MainPageBean recommendMB = new MainPageBean();
                            mainPageBeanHashMap.put(RECOMMEND_TAB, recommendMB);

                            recommendTab.setCustomView(recommendView);
                            categoryTl.addTab(recommendTab);
                            setCheck(recommendTab, true);
                            int index = 0;

                            for (int i = 0; i < dataBeanList.size(); i++) {
                                CategoryBean.DataBean dataBean = dataBeanList.get(i);
                                TabLayout.Tab tab = categoryTl.newTab();
                                View view = LayoutInflater.from(MainPageActivity.this).inflate(R
                                                .layout.tab_category,
                                        null);
                                TextView tv = (TextView) view.findViewById(R.id.main_tv);
                                if (tv != null) {
                                    tv.setText(dataBean.getCategoryName());
                                    tab.setText(dataBean.getCategoryName());
                                }
                                MainPageBean mainPageBean = new MainPageBean();
                                mainPageBean.setCategoryBean(dataBean);
                                mainPageBeanHashMap.put(dataBean.getCategoryName(), mainPageBean);
                                tab.setCustomView(view);
                                categoryTl.addTab(tab);
                                setCheck(tab, false);
                                if (TextUtils.equals(lastTabText, dataBean.getCategoryName())) {

                                    index = i + 1;
                                }
                            }
                            final TabLayout.Tab categoryTab = categoryTl.getTabAt(index);
                            if (categoryTab != null) {
                                categoryTl.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        categoryTab.select();
                                    }
                                });

                            } else {
                                if (categoryTl.getTabAt(0) != null) {
                                    categoryTl.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            categoryTl.getTabAt(0).select();
                                        }
                                    });
                                }
                            }


                        }
//

                    }

                }
                rootSv.smoothScrollTo(0, 0);
                cancelLoadingDialog();
                refreshFl.refreshComplete();

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                showLoadingDialog();
                return GO_REQUEST;


            }


            @Override
            public String createUrl() {

                return CATEGORY_LIST;
            }
        });

    }


    private void getAD(final String type, final MainPageBean mainPageBean) {

        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AdBean>() {

            @Override
            public void onComplete(AdBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {

                        refreshBanner(bean.getData());
                        if (mainPageBean != null) {
                            mainPageBean.updateAdList(bean.getData(), true);

                        }

                    }


                }
                rootSv.smoothScrollTo(0, 0);
                cancelLoadingDialog();

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.TYPE, type));
                if (TextUtils.equals(type, AdBean.CATEGORY_TYPE)) {
                    if (mainPageBean != null) {

                        CategoryBean.DataBean dataBean = mainPageBean.getCategoryBean();
                        if (dataBean != null) {
                            list.add(new BasicKeyValuePair(ParameterConstant.CATEGORYID, dataBean.getCategoryId()));
                        }
                    }
                }
                return list;

            }


            @Override
            public String createUrl() {

                return AD_GET;
            }
        });
    }


    @OnClick({R.id.search_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.gift_rl:
                startActivity(new Intent(this, BuyVIPActivity.class));
                break;*/
            case R.id.search_rl:
                startActivity(new Intent(this, SearchActivity.class));
                StartActivityUtil.getInstance().startActivity(mActivity, MainFragmentActivity.class);
                break;
        }
    }

    @OnClick(R.id.next_tv)
    public void onViewClicked() {
        if (!circleFlashSale(true)) {
            tip("暂无下一场抢购");
        }
    }
}
