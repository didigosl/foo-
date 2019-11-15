package com.duma.ld.baselibarary.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.baselibarary.R;
import com.duma.ld.baselibarary.model.EventModel;
import com.duma.ld.baselibarary.util.EventBusUtil;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.lzy.okgo.OkGo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * 目前的功能
 * 配置一些公共ui
 * 封装了eventbus
 *
 * @author liudong
 * @date 2017/11/10
 */

public abstract class BaseActivity extends SupportActivity implements OnRefreshListener {
    protected BaseActivity mActivity;
    protected PublicConfig mPublicConfig;
    protected RefreshUtil mRefreshUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mRefreshUtil = new RefreshUtil(this);
        //设置根布局
        setContentView(R.layout.activity_root);
        LinearLayout layout_boot_ob = findViewById(R.id.layout_boot_ob);
        setFits(layout_boot_ob);
        //装载内容布局
        LayoutInflater.from(mActivity).inflate(setLayoutId(savedInstanceState), (FrameLayout) findViewById(R.id.layout_boot_content));
        //初始化config
        mPublicConfig = new PublicConfig(mActivity, LayoutInflater.from(mActivity), (FrameLayout) findViewById(R.id.layout_boot), (FrameLayout) findViewById(R.id.layout_boot_topBar), mRefreshUtil);
        //使activity初始化布局
        initConfig(savedInstanceState, mPublicConfig);
        //结束
        mPublicConfig.end();
        registerEventBus();
    }

    protected void setFits(LinearLayout layout_boot_ob) {
        layout_boot_ob.setFitsSystemWindows(true);
    }


    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {

    }

    @LayoutRes
    protected abstract int setLayoutId(Bundle savedInstanceState);


    /**
     * 设置下拉刷新
     */
    protected void setRefresh(SmartRefreshLayout refreshLayout) {
        setRefresh(refreshLayout, false);
    }

    protected void setRefresh(SmartRefreshLayout refreshLayout, boolean isLoadMore) {
        mRefreshUtil.setRefresh(refreshLayout, isLoadMore);
    }

    //加载更多 也就是分页 设置数据
    protected void setRefreshData(BaseQuickAdapter adapter, List data) {
        mRefreshUtil.setRefreshData(adapter, data);
    }


    //下拉刷新 加载更多 请求会再这
    @Override
    public void onHttpRefresh(int page) {

    }


    public void starRefresh() {
        mRefreshUtil.onRefresh();
    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(EventModel eventModel) {
        if (eventModel != null) {
            onReceiveEvent(eventModel);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param eventModel 事件
     */
    protected void onReceiveEvent(EventModel eventModel) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UnregisterEventBus();
        OkGo.getInstance().cancelTag(mActivity);
    }

    protected void UnregisterEventBus() {
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
    }

    protected void registerEventBus() {
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            onBack();
        }
    }

    protected void onBack() {
        finish();
    }

}
