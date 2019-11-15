package com.technology.greenenjoyshoppingstreet.category;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.adapter.EvaluateAdapter;
import com.technology.greenenjoyshoppingstreet.category.bean.EcaluateListBean;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
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
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.FIRST_PAGE_INDEX;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_COMMENT_LIST;

/**
 * Created by Administrator on 2018/1/21.
 */

public class EvaluateListActivity extends BaseActivity {
    public static final String SPU_ID = "SPU_ID";

    @BindView(R.id.data_lv)
    ListView dataLv;
    EvaluateAdapter dataAdapter;
    @BindView(R.id.refresh_fl)
    PtrClassicFrameLayout refreshFl;

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

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
        initViews();

        getIntentData();

    }

    private String spuId;

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            spuId = intent.getStringExtra(SPU_ID);
            if (!TextUtils.isEmpty(spuId)) {
                refreshList();
            }
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
                return super.checkCanDoLoadMore(frame, dataLv, footer);

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, dataLv, header);
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
        pullMode = PullMode.HEADER;
        currentPageNum = FIRST_PAGE_INDEX;
        refreshList();// 请求订单列表

    }

    /**
     * Foot refresh.
     */
    private void footRefresh() {
        pullMode = PullMode.FOOTER;
        refreshList();
    }

    /**
     * Refresh.
     */
    @Override
    public void refresh() {
        super.refresh();
        headRefresh();
    }

    private void initViews() {
        setBarTitle(getString(R.string.evaluation_list));
        dataAdapter = new EvaluateAdapter(this);
        dataLv.setAdapter(dataAdapter);
        dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        initRefreList();
    }


    private void refreshList() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<EcaluateListBean>() {

            @Override
            public void onComplete(EcaluateListBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        EcaluateListBean.DataBean data = bean.getData();
                        if (data != null) {
                            List<ProductDetailBean.DataBean.CommentsBean.ListBean> dataBeanList = data.getList();


                            //请求第一页的数据，需要清除旧的数据
                            boolean isClearOld = (currentPageNum == FIRST_PAGE_INDEX);
                            dataAdapter.updateData(dataBeanList, isClearOld);
                            if (isClearOld && !dataAdapter.isEmpty()) {
                                //刷新列表滑动到首行
                            }
                            if (dataBeanList != null && !dataBeanList.isEmpty()) {
                                //准备获取下一页数据
                                currentPageNum++;
                            } else {
                                if (pullMode == PullMode.FOOTER) {
                                    //上滑刷新，没有数据需要提示用户
                                    tip(R.string.hint_no_content);
                                }
                            }
                        }

                    } else {
                        tip(bean.getMsg());
                    }


                } else {
                    tip(netRequestStatus.getNote());

                }
                refreshFl.refreshComplete();
                isGetListRunning.set(false);
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
                    list.add(new BasicKeyValuePair(ParameterConstant.SPU_ID, spuId));
                    list.add(new BasicKeyValuePair(ParameterConstant.PAGE, String.valueOf(currentPageNum)));
                    list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT, Constant.PER_PAGE_COUNT));
                    return list;
                }
                return null;
            }


            @Override
            public String createUrl() {

                return ORDER_COMMENT_LIST;
            }
        });
    }

    /**
     * 根据页面数据情况，展示提示页.
     */
    private void checkAndDisplayErrorPage() {
        refreshFl.setVisibility(dataAdapter.isEmpty() ? View.GONE : View.VISIBLE);
        setNoDataVisible(dataAdapter.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
