package com.duma.ld.baselibarary.util;

import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by liudong on 2018/3/16.
 */

public class Ts {
    public static void show(String res) {
        ToastUtils.showShort(res);
    }

    public static void showLogin() {
        show("需要注册登录才能使用哦");
    }
}
