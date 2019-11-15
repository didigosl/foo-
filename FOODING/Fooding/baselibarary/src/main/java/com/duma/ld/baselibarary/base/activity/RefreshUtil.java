package com.duma.ld.baselibarary.base.activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.duma.ld.mytopbar.listener.OnConfigListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 刷新封装类
 * Created by liudong on 2018/3/20.
 */

public class RefreshUtil implements OnConfigListener {
    private int page;
    private SmartRefreshLayout mRefreshLayout;
    private boolean isLoadMore = false;
    private com.duma.ld.baselibarary.base.activity.OnRefreshListener onRefreshListener;
    public static final int Default_Page = 1;
    //是否已经有数据了
    private boolean isHttpSuccess;

    public RefreshUtil(com.duma.ld.baselibarary.base.activity.OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
        isHttpSuccess = false;
    }

    public void setRefresh(SmartRefreshLayout refreshLayout, boolean isLoadMore) {
        page = Default_Page;
        this.isLoadMore = isLoadMore;
        this.mRefreshLayout = refreshLayout;
        if (refreshLayout.isEnableRefresh()) {
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshLayout) {
                    //下拉刷新
                    initRefresh();
                }
            });
        }
        if (isLoadMore) {
            refreshLayout.setEnableLoadMore(true);
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshLayout) {
                    //上啦加载
                    onRefreshListener.onHttpRefresh(page);
                }
            });
        } else {
            refreshLayout.setEnableLoadMore(false);
        }
    }

    //加载更多 数据获取成功 的回调
    public void setRefreshData(BaseQuickAdapter adapter, List data) {
        if (!isLoadMore) {
            throw new RuntimeException("没有开启加载更多开关!");
        }
        if (data == null) {
            data = new ArrayList();
        }
//        if (data.size() == 0 && page != 0) {
        //修改 去掉 page 只要数据为空 就提示没数据了
        if (data.size() == 0) {
            //不是第一页又是空数据 说明加载到最后一页了
            //完成加载并标记没有更多数据
            mRefreshLayout.finishLoadMoreWithNoMoreData();
            if (page == Default_Page) {
                //第一页都为空的 吧缓存去掉
                adapter.setNewData(null);
            }
        } else {
            if (page == Default_Page) {
                //说明是第一次加载数据
                adapter.setNewData(data);
            } else {
                //添加最新数据
                adapter.addData(data);
            }
            //正常加载 页数才加1
            page++;
            mRefreshLayout.finishLoadMore();
        }
    }

    public void onRefresh() {
//        //设置了下拉刷新 没设置loading 说明页面本来就有数据
//        if (mRefreshLayout != null &&) {
//            mRefreshLayout.autoRefresh();
//            return;
//        }
        //没设置下拉刷新 或 现在页面没数据
        if (mRefreshLayout == null || !isHttpSuccess) {
            initRefresh();
            return;
        }
        if (mRefreshLayout.isEnableRefresh()) {
            mRefreshLayout.autoRefresh();
        } else {
            page = Default_Page;
            onRefreshListener.onHttpRefresh(page);
        }

    }

    //初始加载 下拉刷新
    private void initRefresh() {
        page = Default_Page;
        onRefreshListener.onHttpRefresh(page);
        if (mRefreshLayout == null) {
            return;
        }
        mRefreshLayout.setNoMoreData(false);
    }

    @Override
    public void configHttpRefresh() {
        initRefresh();
    }

    @Override
    public void finishRefresh(boolean isSuccess) {
        if (mRefreshLayout == null) {
            return;
        }
        if (mRefreshLayout.isEnableRefresh()) {
            mRefreshLayout.finishRefresh(isSuccess);
        }
    }

    @Override
    public void finishAllRefresh(boolean isSuccess) {
        if (mRefreshLayout == null) {
            return;
        }
        if (mRefreshLayout.isEnableLoadMore()) {
            mRefreshLayout.finishLoadMore(isSuccess);
        }
        if (mRefreshLayout.isEnableRefresh()) {
            mRefreshLayout.finishRefresh(isSuccess);
        }

    }

    @Override
    public void setIsHttpSuccess(boolean isSuccess) {
        isHttpSuccess = isSuccess;
    }


}
