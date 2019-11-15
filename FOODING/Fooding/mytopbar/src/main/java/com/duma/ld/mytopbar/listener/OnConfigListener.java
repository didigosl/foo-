package com.duma.ld.mytopbar.listener;

/**
 * @author liudong
 * @date 2017/11/28
 */

public interface OnConfigListener {
    /**
     * 点击404页面的刷新事件
     */
    void configHttpRefresh();

    /**
     * 结束下拉刷新
     *
     * @param isSuccess true 表示加载成功后的结束刷新 false 表示加载失败后的结束刷新
     */
    void finishRefresh(boolean isSuccess);

    /**
     * 结束刷新
     * 包括下拉 和 加载更多
     *
     * @param isSuccess true 表示加载成功后的结束刷新 false 表示加载失败后的结束刷新
     */
    void finishAllRefresh(boolean isSuccess);

    //是否已经有数据了
    void setIsHttpSuccess(boolean isSuccess);
}
