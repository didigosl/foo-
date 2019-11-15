package com.technology.greenenjoyshoppingstreet.newui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.duma.ld.baselibarary.base.activity.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.utils.ZhuanHuanUtil;

import butterknife.ButterKnife;

/**
 * @author liudong
 * @date 2017/11/28
 */

public abstract class BaseMyActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        ButterKnife.bind(this);
        init(savedInstanceState);
    }


    protected void setStatusBar() {
        switchStatusBar(true);
    }

    /**
     * 状态栏 亮色 和 暗色 切换
     *
     * @param isDark 是否使用 暗色
     */
    protected void switchStatusBar(boolean isDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isDark) {
                StatusBarUtil.setLightMode(mActivity);
            } else {
                StatusBarUtil.setDarkMode(mActivity);
            }
        } else {
            StatusBarUtil.setColor(mActivity, ZhuanHuanUtil.getColor(R.color.white));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RxDisposableUtil.getInstance().clearDisposable(mActivity);
    }

    protected void init(Bundle savedInstanceState) {

    }
}
