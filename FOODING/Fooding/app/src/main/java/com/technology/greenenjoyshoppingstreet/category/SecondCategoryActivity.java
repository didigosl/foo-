package com.technology.greenenjoyshoppingstreet.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.adapter.SecondGoodsAdapter;
import com.technology.greenenjoyshoppingstreet.category.bean.SecondCategoryGoodBean;
import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;
import com.technology.greenenjoyshoppingstreet.main.bean.ProductBean;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.PullMode;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity.GOOD_ID;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.FIRST_PAGE_INDEX;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_LIST;


/**
 * Created by Bern on 2017/12/14 0014.
 */

public class SecondCategoryActivity extends BaseActivity {

    public static final String CATEGORY_LIST = "CATEGORY_LIST";
    public static final String CATEGORY_INDEX = "CATEGORY_INDEX";
    public static final String CATEGORY_NAME = "CATEGORY_NAME";

    @BindView(R.id.category_tl)
    TabLayout categoryTl;
    @BindView(R.id.default_rb)
    RadioButton defaultRb;
    @BindView(R.id.sales_volume_rb)
    RadioButton salesVolumeRb;
    @BindView(R.id.price_rb)
    RadioButton priceRb;
    @BindView(R.id.time_rb)
    RadioButton timeRb;
    @BindView(R.id.data_gv)
    GridView dataGv;
    @BindView(R.id.refresh_fl)
    PtrClassicFrameLayout refreshFl;
    ArrayList<CategoryBean.DataBean> categoryDataList;

    SecondGoodsAdapter secondGoodsAdapter;
    @BindView(R.id.category_fl)
    FrameLayout categoryFl;

    private HashMap<String, SecondCategoryGoodBean> goodBeanHashMap = new HashMap<>();
    /**
     * Pull mode.
     */
    private PullMode pullMode = PullMode.HEADER;


    /**
     * Is get list running.
     */
    private AtomicBoolean isGetListRunning = new AtomicBoolean(false);


    private String orderFlag;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_category);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            categoryDataList = intent.getParcelableArrayListExtra(CATEGORY_LIST);
            int index = intent.getIntExtra(CATEGORY_INDEX, 0);
            categoryTl.removeAllTabs();
            if (categoryDataList != null && !categoryDataList.isEmpty()) {
                setBarTitle(intent.getStringExtra(CATEGORY_NAME));

                for (int i = 0; i < categoryDataList.size(); i++) {
                    CategoryBean.DataBean dataBean = categoryDataList.get(i);
                    TabLayout.Tab tab = categoryTl.newTab();
                    View view = LayoutInflater.from(this).inflate(R
                                    .layout.tab_category,
                            null);
                    TextView tv = (TextView) view.findViewById(R.id.main_tv);
                    if (tv != null) {
                        tv.setText(dataBean.getCategoryName());
                        tab.setText(dataBean.getCategoryName());
                    }
                    SecondCategoryGoodBean secondCategoryGoodBean = new SecondCategoryGoodBean();
                    secondCategoryGoodBean.setDataBean(dataBean);
                    goodBeanHashMap.put(dataBean.getCategoryName(), secondCategoryGoodBean);
                    tab.setCustomView(view);
                    categoryTl.addTab(tab);
                    setCheck(tab, index == i);

                }
                TabLayout.Tab categoryTab = categoryTl.getTabAt(index);
                if (categoryTab != null) {
                    categoryTab.select();
                }
//                categoryFl.setVisibility(categoryDataList.size() == 1 ? View.GONE : View.VISIBLE);


            }
        }
    }

    private void setCheck(TabLayout.Tab tab, boolean isCheck) {
        if (tab != null) {
            View rootView = tab.getCustomView();
            if (rootView != null) {
                TextView tv = (TextView) rootView.findViewById(R.id.main_tv);
                if (tv != null) {
                    tv.setEnabled(isCheck);

                }
                ImageView iv = (ImageView) rootView.findViewById(R.id.main_iv);
                if (iv != null) {
                    iv.setEnabled(isCheck);

                }

            }
        }


    }

    private void initViews() {
        secondGoodsAdapter = new SecondGoodsAdapter(this);
        dataGv.setAdapter(secondGoodsAdapter);
        dataGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductBean.DataBean.ListBean listBean = secondGoodsAdapter.getItem(position);
                if (listBean != null) {
                    StartActivityUtil.getInstance().goGoodsDetail(SecondCategoryActivity.this,  listBean.getSpuId());
                }
            }
        });


        categoryTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCheck(tab, true);
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
    }

    private void changeTabRefresh() {
        TabLayout.Tab tab = categoryTl.getTabAt(categoryTl.getSelectedTabPosition());
        if (tab != null) {
            SecondCategoryGoodBean secondCategoryGoodBean = goodBeanHashMap.get(tab.getText());
            if (secondCategoryGoodBean != null && secondCategoryGoodBean.getDataBeanList()
                    != null && !secondCategoryGoodBean.getDataBeanList().isEmpty
                    ()) {
                secondGoodsAdapter.updateData(secondCategoryGoodBean.getDataBeanList(), true);

            } else {

                getGoods(secondCategoryGoodBean);
            }
        }


    }

    private void changeSelectStat(int id) {
        defaultRb.setChecked(id == R.id.default_rb);
        salesVolumeRb.setChecked(id == R.id.sales_volume_rb);
        priceRb.setChecked(id == R.id.price_rb);
        timeRb.setChecked(id == R.id.time_rb);
        TabLayout.Tab tab = categoryTl.getTabAt(categoryTl.getSelectedTabPosition());
        if (tab != null) {
            SecondCategoryGoodBean secondCategoryGoodBean = goodBeanHashMap.get(tab.getText());
            secondCategoryGoodBean.clearList();
            changeTabRefresh();
        }

    }


    @OnClick({R.id.default_rb, R.id.sales_volume_rb, R.id.price_rb, R.id.time_rb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.default_rb:
                orderFlag = null;
                changeSelectStat(view.getId());
                break;
            case R.id.sales_volume_rb:
                changeSelectStat(view.getId());
                break;
            case R.id.price_rb:
                if (Constant.PRICEASC_ORDER.equals(orderFlag)) {
                    orderFlag = Constant.PRICEDESC_ORDER;
                } else if (Constant.PRICEDESC_ORDER.equals(orderFlag)) {
                    orderFlag = Constant.PRICEASC_ORDER;
                } else {
                    orderFlag = Constant.PRICEASC_ORDER;
                }
                changeSelectStat(view.getId());
                break;
            case R.id.time_rb:
                if (Constant.ONSALEASC_ORDER.equals(orderFlag)) {
                    orderFlag = Constant.ONSALEDESC_ORDER;
                } else if (Constant.ONSALEDESC_ORDER.equals(orderFlag)) {
                    orderFlag = Constant.ONSALEASC_ORDER;
                } else {
                    orderFlag = Constant.ONSALEASC_ORDER;
                }
                changeSelectStat(view.getId());
                break;
        }
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

                return super.checkCanDoLoadMore(frame, dataGv, footer);

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, dataGv, header);
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
        goodBeanHashMap.clear();
        pullMode = PullMode.HEADER;
        changeTabRefresh();

    }

    /**
     * Foot refresh.
     */
    private void footRefresh() {
        pullMode = PullMode.FOOTER;

        TabLayout.Tab tab = categoryTl.getTabAt(categoryTl.getSelectedTabPosition());
        if (tab != null) {
            SecondCategoryGoodBean secondCategoryGoodBean = goodBeanHashMap.get(tab.getText());
            getGoods(secondCategoryGoodBean);
        }

    }

    private void getGoods(final SecondCategoryGoodBean secondCategoryGoodBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ProductBean>() {

            @Override
            public void onComplete(ProductBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        ProductBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {

                            List<ProductBean.DataBean.ListBean> dataBeanList = dataBean.getList();


                            //请求第一页的数据，需要清除旧的数据
                            boolean isClearOld = (secondCategoryGoodBean.getIndex() == FIRST_PAGE_INDEX);
                            secondGoodsAdapter.updateData(dataBeanList, isClearOld);
                            if (isClearOld && !secondGoodsAdapter.isEmpty()) {
                                //刷新列表滑动到首行
                                dataGv.smoothScrollToPosition(0);
                            }
                            if (dataBeanList != null && !dataBeanList.isEmpty()) {
                                //准备获取下一页数据
                                if (secondCategoryGoodBean != null) {
                                    secondCategoryGoodBean.incrementIndex();

                                }
                            } else {
                                if (pullMode == PullMode.FOOTER) {
                                    //上滑刷新，没有数据需要提示用户
                                    tip(R.string.hint_no_content);
                                }
                            }
                            if (secondCategoryGoodBean != null) {
                                secondCategoryGoodBean.updateData(dataBeanList, true);
                            }

                        }


                    }

                }
                isGetListRunning.set(false);
                refreshFl.refreshComplete();
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                if (secondCategoryGoodBean != null) {
                    CategoryBean.DataBean categoryBean = secondCategoryGoodBean.getDataBean();
                    if (categoryBean != null) {

                        if (!isGetListRunning.get()) {
                            isGetListRunning.set(true);
                            showLoadingDialog();
                            List<KeyValuePair> list = new ArrayList<>();
                            if (!TextUtils.isEmpty(orderFlag)) {
                                list.add(new BasicKeyValuePair(ParameterConstant.ORDER, orderFlag));
                            }
                            list.add(new BasicKeyValuePair(ParameterConstant.CATEGORYID, categoryBean.getCategoryId()));
                            list.add(new BasicKeyValuePair(ParameterConstant.PAGE, String.valueOf
                                    (secondCategoryGoodBean.getIndex())));
                            list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT, Constant.PER_PAGE_COUNT));
                            return list;
                        }


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
}
