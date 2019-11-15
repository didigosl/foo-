package com.duma.ld.baselibarary.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

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

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 目前的功能
 * 配置一些公共ui
 * 封装了eventbus
 *
 * @author liudong
 * @date 2017/11/10
 */

public abstract class BaseFragment extends SupportFragment implements OnRefreshListener {
    protected BaseActivity mActivity;
    protected PublicConfig mPublicConfig;
    protected RefreshUtil mRefreshUtil;
    protected View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRefreshUtil = new RefreshUtil(this);
        View mRootView = inflater.inflate(R.layout.activity_root, container, false);
        //装载内容布局
        inflater.inflate(setLayoutId(savedInstanceState), (FrameLayout) mRootView.findViewById(R.id.layout_boot_content));
        //初始化
        mPublicConfig = new PublicConfig(mActivity, inflater, (FrameLayout) mRootView.findViewById(R.id.layout_boot), (FrameLayout) mRootView.findViewById(R.id.layout_boot_topBar), mRefreshUtil);
        //使activity初始化布局
        initConfig(savedInstanceState, mPublicConfig);
        //结束
        mPublicConfig.end();
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        this.mRootView = mRootView;
        return mRootView;
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

    //设置数据
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }
        onHttpDestroy();
    }

    protected void onHttpDestroy() {
        OkGo.getInstance().cancelTag(mActivity);
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

}
