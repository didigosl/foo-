package com.duma.ld.mytopbar.config;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.ld.mytopbar.R;
import com.duma.ld.mytopbar.listener.OnConfigListener;
import com.duma.ld.mytopbar.listener.OnTopBarLeftListener;
import com.duma.ld.mytopbar.listener.OnTopBarRightListener;

/**
 * 1 没有设置loading 和 error 的视图
 * <p>
 * 2 设置了
 * <p>
 * Created by liudong on 2017/11/28.
 */

public class PublicConfig {
    private View layoutContent, layoutLoading, layoutError;
    //根视图容器
    private FrameLayout mLayoutBootView;
    //topBar容器
    private FrameLayout mLayoutTopBarView;
    //用于设置视图
    private LayoutInflater mLayoutInflater;
    private Activity mActivity;
    /**
     * 配置的监听
     */
    private OnConfigListener onConfigListener;

    private TextView mTvTopBarTitle;
    //topbar 阴影效果
    private View yinYinView;
    //是否已经有数据了
    private boolean isHttpSuccess;
    //是否需要loading和error页面
    private boolean isNoLoadingOrError;

    public PublicConfig(Activity mActivity, LayoutInflater mLayoutInflater, FrameLayout mLayoutBootView, FrameLayout mLayoutTopBarView, OnConfigListener onConfigListener) {
        this.mActivity = mActivity;
        this.mLayoutBootView = mLayoutBootView;
        this.onConfigListener = onConfigListener;
        this.mLayoutTopBarView = mLayoutTopBarView;
        this.mLayoutInflater = mLayoutInflater;
        isHttpSuccess = false;
        isNoLoadingOrError = true;
    }

    public PublicConfig setLoadOrErrorRootLayout(int loadOrErrorRootLayoutId) {
        ViewGroup loadOrErrorRootViewGroup = mLayoutBootView.findViewById(loadOrErrorRootLayoutId);
        if (loadOrErrorRootViewGroup == null) {
            throw new RuntimeException("获取 viewGroup 为 Null 请检查 loadOrErrorRootLayoutId 是否是 ViewGroup 的id");
        }
        setLoadOrErrorRootLayout(loadOrErrorRootViewGroup);
        return this;
    }

    /**
     * 给定的的viewGroup中添加 loadingView 和 errorView
     * 并获取contentView 就是主要显示的内容View
     */
    public PublicConfig setLoadOrErrorRootLayout(ViewGroup loadOrErrorRootViewGroup) {
        int childCount = loadOrErrorRootViewGroup.getChildCount();
        if (childCount != 1) {
            throw new RuntimeException("loadOrErrorRootViewGroup 的子view只能是一个");
        }
        //获取内容view 和 添加 loadView errorView
        layoutContent = loadOrErrorRootViewGroup.getChildAt(0);
        View loadingView = mLayoutInflater.inflate(R.layout.base_loading, loadOrErrorRootViewGroup);
        View errorView = mLayoutInflater.inflate(R.layout.base_error, loadOrErrorRootViewGroup);
        layoutLoading = loadingView.findViewById(R.id.layout_loading);
        layoutError = errorView.findViewById(R.id.layout_error);
        /**
         * 初始化errorView里面的方法
         */
        TextView mTvErrorBtn = layoutError.findViewById(R.id.tv_refresh);
        mTvErrorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                onConfigListener.configHttpRefresh();
            }
        });
        //默认显示内容
        showContent();
        return this;
    }


    /**
     * 设置topbar
     * 默认带有阴影效果
     *
     * @param name                  topbar的标题
     * @param rightText             右边的文字
     * @param liftImg               左边的图片
     * @param rightImg              右边的图片
     * @param onTopBarLeftListener  监听
     * @param onTopBarRightListener 监听
     */
    public PublicConfig setTopBar(String name, String rightText, @DrawableRes int liftImg, @DrawableRes int rightImg
            , final OnTopBarLeftListener onTopBarLeftListener, final OnTopBarRightListener onTopBarRightListener) {
        //有topbar
        View topBarView = mLayoutInflater.inflate(R.layout.base_topbar, mLayoutTopBarView);
        yinYinView = topBarView.findViewById(R.id.base_view);
        setYinyin(true);
        if (liftImg != 0) {
            ((ImageView) topBarView.findViewById(R.id.img_left)).setImageDrawable(ContextCompat.getDrawable(mActivity, liftImg));
        }
        if (rightImg != 0) {
            ((ImageView) topBarView.findViewById(R.id.img_right)).setImageDrawable(ContextCompat.getDrawable(mActivity, rightImg));
        }
        if (rightText != null) {
            ((TextView) topBarView.findViewById(R.id.tv_right)).setText(rightText);
        }
        mTvTopBarTitle = topBarView.findViewById(R.id.topbar_tv_title);
        mTvTopBarTitle.setText(name);
        //设置点击事件
        LinearLayout liftLayout = topBarView.findViewById(R.id.layout_left);
        if (liftImg != 0) {
            liftLayout.setVisibility(View.VISIBLE);
            liftLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopBarLeftListener != null) {
                        onTopBarLeftListener.onClick();
                    } else {
                        mActivity.finish();
                    }
                }
            });
        } else {
            liftLayout.setVisibility(View.GONE);
        }

        //如果没有
        LinearLayout rightLayout = topBarView.findViewById(R.id.layout_right);
        if (rightText != null || rightImg != 0) {
            rightLayout.setVisibility(View.VISIBLE);
            rightLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTopBarRightListener != null) {
                        onTopBarRightListener.onClick();
                    }
                }
            });
        } else {
            rightLayout.setVisibility(View.GONE);
        }
        return this;
    }


    /**
     * 结束
     * 主要是添加默认的一些东西
     */
    public void end() {
        //是否已经设置了loading 和 error 的视图
        if (layoutContent == null) {
            //没设置 在根布局 添加
            if (isNoLoadingOrError) {
                setLoadOrErrorRootLayout(mLayoutBootView);
            }
        }
    }

    //只显示loading
    public void showLoading() {
        //有数据后加载就不显示loading了
        //没有数据就显示
        if (!isHttpSuccess) {
            if (layoutContent != null) {
                layoutContent.setVisibility(View.GONE);
            }
            if (layoutLoading != null) {
                layoutLoading.setVisibility(View.VISIBLE);
            }
            if (layoutError != null) {
                layoutError.setVisibility(View.GONE);
            }
        }
    }

    //只显示ErrorView
    public void showError() {
        //加载失败 关闭所有的刷新
        onConfigListener.finishAllRefresh(false);
        if (!isHttpSuccess) {
            //有数据后就不显示ErrorView
            //没有数据就显示
            if (layoutContent != null) {
                layoutContent.setVisibility(View.GONE);
            }
            if (layoutLoading != null) {
                layoutLoading.setVisibility(View.GONE);
            }
            if (layoutError != null) {
                layoutError.setVisibility(View.VISIBLE);
            }
        }
    }

    //显示 contentView
    public void showContent() {
        //加载成功 关闭下拉刷新 加载更多会再baseActivity 中关闭
        onConfigListener.finishRefresh(true);
        if (layoutContent != null) {
            layoutContent.setVisibility(View.VISIBLE);
        }
        if (layoutLoading != null) {
            layoutLoading.setVisibility(View.GONE);
        }
        if (layoutError != null) {
            layoutError.setVisibility(View.GONE);
        }
    }

    public PublicConfig setTopBar(String name) {
        setTopBar(name, null, R.drawable.ui4, 0, null, null);
        return this;
    }

    public PublicConfig setTopBar(String name, @DrawableRes int liftImg) {
        setTopBar(name, null, liftImg, 0, null, null);
        return this;
    }

    public PublicConfig setTopBar(String name, OnTopBarLeftListener onTopBarLeftListener) {
        setTopBar(name, null, R.drawable.ui4, 0, onTopBarLeftListener, null);
        return this;
    }

    public PublicConfig setTopBar(String name, String rightName, OnTopBarRightListener onTopBarRightListener) {
        setTopBar(name, rightName, R.drawable.ui4, 0, null, onTopBarRightListener);
        return this;
    }

    public PublicConfig setNoLoadingOrError() {
        isNoLoadingOrError = false;
        //不需要loading了
        setHttpSuccess(true);
        return this;
    }

    //设置标题
    public PublicConfig setTitle(String title) {
        if (mTvTopBarTitle != null) {
            mTvTopBarTitle.setText(title);
        }
        return this;
    }

    //是否需要标题下面的线
    public PublicConfig setYinyin(boolean isYinyin) {
        if (yinYinView != null) {
            if (isYinyin) {
                yinYinView.setVisibility(View.VISIBLE);
            } else {
                yinYinView.setVisibility(View.GONE);
            }
        }
        return this;
    }

    public void setHttpSuccess(boolean httpSuccess) {
        isHttpSuccess = httpSuccess;
        onConfigListener.setIsHttpSuccess(httpSuccess);
    }


    public boolean isHttpSuccess() {
        return isHttpSuccess;
    }
}
