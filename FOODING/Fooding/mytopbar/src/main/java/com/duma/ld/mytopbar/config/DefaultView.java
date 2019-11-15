package com.duma.ld.mytopbar.config;

/**
 * 默认视图的初始化
 * <p>
 * Created by liudong on 2018/3/16.
 */

public class DefaultView {
    public static synchronized DefaultView getInstance() {
        return DeviceListManagerHolder.instance;
    }

    private static class DeviceListManagerHolder {
        private static final DefaultView instance = new DefaultView();
    }

    private DefaultView() {
    }
}
