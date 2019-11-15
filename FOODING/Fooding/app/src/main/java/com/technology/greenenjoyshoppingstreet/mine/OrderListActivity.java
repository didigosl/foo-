package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.CommitEvaluteActivity;
import com.technology.greenenjoyshoppingstreet.category.OrderPayActivity;
import com.technology.greenenjoyshoppingstreet.category.bean.CreateOrderBean;
import com.technology.greenenjoyshoppingstreet.mine.adapter.OrderOperationListAdapter;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderListBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderStatus;
import com.technology.greenenjoyshoppingstreet.utils.DialogConfigBean;
import com.technology.greenenjoyshoppingstreet.utils.PullMode;
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
import java.util.concurrent.atomic.AtomicBoolean;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.FIRST_PAGE_INDEX;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_CANCEL;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_FINISH;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_LIST;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class OrderListActivity extends BaseActivity {

    public static final String ORDER_TYPE = "ORDER_TYPE";

    @BindView(R.id.data_lv)
    ListView dataLv;
    @BindView(R.id.refresh_fl)
    PtrClassicFrameLayout refreshFl;
    OrderOperationListAdapter orderOperationListAdapter;
    private OrderStatus orderStatus;
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
        setContentView(R.layout.activity_order_list);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            orderStatus = (OrderStatus) intent.getSerializableExtra(ORDER_TYPE);
            setBarTitle(orderStatus.getStatusText());
        }
    }

    private void initViews() {
        orderOperationListAdapter = new OrderOperationListAdapter(this);
        dataLv.setAdapter(orderOperationListAdapter);
        initRefreList();
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

    @Override
    protected void onStart() {
        super.onStart();
        headRefresh();

    }

    /**
     * Refresh.
     */
    @Override
    public void refresh() {
        super.refresh();
        headRefresh();
    }

    public void payOrder(final OrderListBean.DataBean.ListBean listBean) {

        Intent payIntent = new Intent(this, OrderPayActivity.class);
        CreateOrderBean.DataBean dataBean = new CreateOrderBean.DataBean();
        dataBean.setOrderId(listBean.getOrderId());
        dataBean.setSn(listBean.getSn());
        dataBean.setTotalAmount(listBean.getTotalAmount());
        payIntent.putExtra(OrderPayActivity.ORDER_BEAN, dataBean);
        startActivity(payIntent);


    }

    public void evaluteOrder(final OrderListBean.DataBean.ListBean listBean) {
        if (listBean != null && !TextUtils.isEmpty(listBean.getOrderId())) {

            Intent intent = new Intent(this, CommitEvaluteActivity.class);
            intent.putExtra(ParameterConstant.ORDER_ID, listBean.getOrderId());
            startActivity(intent);

        }

    }

    public void lookProgress(final OrderListBean.DataBean.ListBean listBean) {

        tip(R.string.Logistics);
    }

    public void confirmReceive(final OrderListBean.DataBean.ListBean listBean) {

        DialogConfigBean dialogConfigBean = DialogConfigBean.getDefaultDoubleConfig(this);
        dialogConfigBean.setContentText(getString(R.string.confirm_order));
        dialogConfigBean.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissChooseDialog();
                NetExcutor.executorCommonRequest(OrderListActivity
                        .this, new
                        CommonNetUIListener<OrderListBean>() {

                            @Override
                            public void onComplete(OrderListBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                                        headRefresh();

                                    } else {
                                        tip(bean.getMsg());
                                    }


                                } else {
                                    tip(netRequestStatus.getNote());

                                }
                                cancelLoadingDialog();
                            }

                            @Override
                            public NetRequestConfig.Method getMethod() {
                                return NetRequestConfig.Method.POST;
                            }

                            @Override
                            public Object submitNetParams() {
                                if (listBean != null && !TextUtils.isEmpty(listBean.getOrderId())) {
                                    showLoadingDialog();
                                    List<KeyValuePair> list = new ArrayList<>();
                                    list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, listBean.getOrderId()));

                                    return UserInfoManger.getSignList(list);
                                }
                                return null;

                            }

                            @Override
                            public String createUrl() {

                                return ORDER_FINISH;
                            }
                        });

            }
        });

        showOperationDialog(dialogConfigBean);

    }

    public void cancelOrder(final OrderListBean.DataBean.ListBean listBean) {
        DialogConfigBean dialogConfigBean = DialogConfigBean.getDefaultDoubleConfig(this);
        dialogConfigBean.setContentText("是否取消订单");
        dialogConfigBean.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissChooseDialog();
                NetExcutor.executorCommonRequest(OrderListActivity
                        .this, new
                        CommonNetUIListener<OrderListBean>() {

                            @Override
                            public void onComplete(OrderListBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                                        headRefresh();

                                    } else {
                                        tip(bean.getMsg());
                                    }


                                } else {
                                    tip(netRequestStatus.getNote());

                                }
                                cancelLoadingDialog();
                            }

                            @Override
                            public NetRequestConfig.Method getMethod() {
                                return NetRequestConfig.Method.POST;
                            }

                            @Override
                            public Object submitNetParams() {
                                if (listBean != null && !TextUtils.isEmpty(listBean.getOrderId())) {
                                    showLoadingDialog();
                                    List<KeyValuePair> list = new ArrayList<>();
                                    list.add(new BasicKeyValuePair(ParameterConstant.ORDER_ID, listBean.getOrderId()));

                                    return UserInfoManger.getSignList(list);
                                }
                                return null;

                            }

                            @Override
                            public String createUrl() {

                                return ORDER_CANCEL;
                            }
                        });

            }
        });

        showOperationDialog(dialogConfigBean);


    }

    private void refreshList() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<OrderListBean>() {

            @Override
            public void onComplete(OrderListBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        OrderListBean.DataBean data = bean.getData();
                        if (data != null) {
                            List<OrderListBean.DataBean.ListBean> dataBeanList = data.getList();


                            //请求第一页的数据，需要清除旧的数据
                            boolean isClearOld = (currentPageNum == FIRST_PAGE_INDEX);
                            orderOperationListAdapter.updateData(dataBeanList, isClearOld);
                            if (isClearOld && !orderOperationListAdapter.isEmpty()) {
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
                    if (orderStatus != null) {
                        if (orderStatus == OrderStatus.REFUND) {
                            list.add(new BasicKeyValuePair(ParameterConstant.REFOUND_FLAG, "refound"));
                        } else {
                            list.add(new BasicKeyValuePair(ParameterConstant.FLAG, orderStatus.getStatusCode()));

                        }

                    }
                    list.add(new BasicKeyValuePair(ParameterConstant.PAGE, String.valueOf(currentPageNum)));
                    list.add(new BasicKeyValuePair(ParameterConstant.PAGELIMIT, Constant.PER_PAGE_COUNT));
                    return UserInfoManger.getSignList(list);
                }
                return null;
            }

            @Override
            public String createUrl() {

                return ORDER_LIST;
            }
        });
    }

    /**
     * 根据页面数据情况，展示提示页.
     */
    private void checkAndDisplayErrorPage() {
        refreshFl.setVisibility(orderOperationListAdapter.isEmpty() ? View.GONE : View.VISIBLE);
        setNoDataVisible(orderOperationListAdapter.isEmpty() ? View.VISIBLE : View.GONE);
    }

}
