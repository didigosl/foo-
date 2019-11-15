package com.technology.greenenjoyshoppingstreet.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RadioButton;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.adapter.SecondGoodsAdapter;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.FIRST_PAGE_INDEX;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_SEARCH;

/**
 * Created by Administrator on 2018/1/7.
 */

public class SearchResultActivity extends BaseActivity {


    public static final String KEY_NAME = "KEY_NAME";
    private String keyName;


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
    SecondGoodsAdapter secondGoodsAdapter;

    private int currentIndex = FIRST_PAGE_INDEX;
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
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    @Override
    public void refresh() {
        super.refresh();
        headRefresh();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            keyName = intent.getStringExtra(KEY_NAME);
            if (!TextUtils.isEmpty(keyName)) {

                setBarTitle(keyName);
                changeSelectStat(R.id.default_rb);
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
//                    Intent intent = new Intent(SearchResultActivity.this, ProductDetailActivity.class);
//                    intent.putExtra(GOOD_ID, listBean.getSpuId());
//                    startActivity(intent);
                    StartActivityUtil.getInstance().goGoodsDetail(SearchResultActivity.this, listBean.getSpuId());
                }

            }
        });
        initRefreList();
    }


    private void changeSelectStat(int id) {
        defaultRb.setChecked(id == R.id.default_rb);
        salesVolumeRb.setChecked(id == R.id.sales_volume_rb);
        priceRb.setChecked(id == R.id.price_rb);
        timeRb.setChecked(id == R.id.time_rb);
        headRefresh();

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
        currentIndex = FIRST_PAGE_INDEX;
        pullMode = PullMode.HEADER;
        getGoods();

    }

    /**
     * Foot refresh.
     */
    private void footRefresh() {
        pullMode = PullMode.FOOTER;
        getGoods();

    }

    private void getGoods() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ProductBean>() {

            @Override
            public void onComplete(ProductBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        ProductBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {

                            List<ProductBean.DataBean.ListBean> dataBeanList = dataBean.getList();


                            //请求第一页的数据，需要清除旧的数据
                            boolean isClearOld = (currentIndex == FIRST_PAGE_INDEX);
                            secondGoodsAdapter.updateData(dataBeanList, isClearOld);
                            if (isClearOld && !secondGoodsAdapter.isEmpty()) {
                                //刷新列表滑动到首行
                                dataGv.smoothScrollToPosition(0);
                            }
                            if (dataBeanList != null && !dataBeanList.isEmpty()) {
                                //准备获取下一页数据
                                currentIndex++;
                            } else {
                                if (pullMode == PullMode.FOOTER) {
                                    //上滑刷新，没有数据需要提示用户
                                    tip(R.string.hint_no_content);
                                }
                            }

                        }


                    }

                }
                isGetListRunning.set(false);
                refreshFl.refreshComplete();
                checkAndDisplayErrorPage();
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                if (!isGetListRunning.get()) {

                    isGetListRunning.set(true);
                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    if (!TextUtils.isEmpty(orderFlag)) {
                        list.add(new BasicKeyValuePair(ParameterConstant.ORDER, orderFlag));
                    }
                    list.add(new BasicKeyValuePair(ParameterConstant.KEYWORD, keyName));
                    list.add(new BasicKeyValuePair(ParameterConstant.PAGE, String.valueOf
                            (currentIndex)));
                    list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT, Constant.PER_PAGE_COUNT));

                    return list;
                }
                return null;


            }


            @Override
            public String createUrl() {

                return GOODS_SPU_SEARCH;
            }
        });

    }

    /**
     * 根据页面数据情况，展示提示页.
     */
    private void checkAndDisplayErrorPage() {
        refreshFl.setVisibility(secondGoodsAdapter.isEmpty() ? View.GONE : View.VISIBLE);
        setNoDataVisible(secondGoodsAdapter.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
