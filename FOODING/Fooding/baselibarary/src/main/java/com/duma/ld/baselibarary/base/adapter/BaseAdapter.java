package com.duma.ld.baselibarary.base.adapter;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.R;
import com.duma.ld.baselibarary.util.ZhuanHuanUtil;

/**
 * 能自动添加控制的空白页
 * 能够开启关闭下拉刷新 并且处理好了分页
 * Created by liudong on 2017/12/4.
 */

public class BaseAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private RecyclerView mRecyclerView;
    private Activity mActivity;
    private OnBaseAdapterListener<T> onBaseAdapterListener;

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        onBaseAdapterListener.convert(helper, item);
    }

    public BaseAdapter(int layoutResId) {
        super(layoutResId);
    }

    //普通的构造
    public BaseAdapter(Builder builder, OnBaseAdapterListener<T> onBaseAdapterListener) {
        super(builder.layoutResId);
        this.onBaseAdapterListener = onBaseAdapterListener;
        init(builder);
    }

    private void init(Builder builder) {
        this.mRecyclerView = builder.mRecyclerView;
        this.mActivity = builder.mActivity;
        mRecyclerView.setLayoutManager(builder.layoutManager);
        mRecyclerView.setAdapter(this);
        if (builder.isEmptyView) {
            setEmptyLayout(builder.title, builder.drawableId, builder.buttonString, builder.onClickListener);
        }
    }


//    public void setEmptyLayout() {
//        setEmptyLayout("哦~该列表空空的~", R.drawable.img_81, "");
//    }
//
//    public void setEmptyLayout(String title) {
//        setEmptyLayout(title, R.drawable.img_81, "");
//    }
//
//    public void setEmptyLayout(@DrawableRes int drawableId) {
//        setEmptyLayout("哦~该列表空空的~", drawableId, "");
//    }

    public void setEmptyLayout(String title, @DrawableRes int drawableId, String buttonString, View.OnClickListener onClickListener) {
        View view = getView(R.layout.include_no_data);
        TextView textView = view.findViewById(R.id.tv_adapter_name);
        TextView tv_button = view.findViewById(R.id.tv_button);
        ImageView imageView = view.findViewById(R.id.img_adapter_nodata);
        textView.setText(title);
        imageView.setImageDrawable(ZhuanHuanUtil.getDrawable(drawableId));
        setEmptyView(view);
        if (StringUtils.isEmpty(buttonString)) {
            tv_button.setVisibility(View.GONE);
        } else {
            tv_button.setVisibility(View.VISIBLE);
            tv_button.setText(buttonString);
            if (onClickListener != null) {
                tv_button.setOnClickListener(onClickListener);
            }
        }
    }

    //获得view
    public View getView(@LayoutRes int layout) {
        return mActivity.getLayoutInflater().inflate(layout, (ViewGroup) mRecyclerView.getParent(), false);
    }


    //建造器
    public static class Builder<T> {
        private RecyclerView mRecyclerView;
        private Activity mActivity;
        private int layoutResId;
        private String title;//空页面的title
        @DrawableRes
        private int drawableId;//空页面的图片
        private boolean isEmptyView;//是否需要空页面
        private boolean needHidden;


        private RecyclerView.LayoutManager layoutManager;
        private String buttonString;
        private View.OnClickListener onClickListener;

        public Builder(RecyclerView mRecyclerView, Activity mActivity, int layoutResId) {
            this.mRecyclerView = mRecyclerView;
            this.mActivity = mActivity;
            this.layoutResId = layoutResId;
            //默认
            isEmptyView = true;
            needHidden = false;
            title = "哦~该列表空空的~";
            buttonString = "";
            drawableId = R.drawable.ui70;
            layoutManager = new LinearLayoutManager(mActivity);
        }

        public Builder<T> setLayoutManager(RecyclerView.LayoutManager layoutManager) {
            this.layoutManager = layoutManager;
            return this;
        }

        //是否嵌套
        public Builder<T> isNested() {
            mRecyclerView.setFocusable(false);
            mRecyclerView.setNestedScrollingEnabled(false);
            return this;
        }

        //设置空页面的title
        public Builder<T> setTitle(String title) {
            this.title = title;
            return this;
        }

        //设置单独按钮
        public Builder<T> setButton(String buttonString, View.OnClickListener onClickListener) {
            this.buttonString = buttonString;
            this.onClickListener = onClickListener;
            return this;
        }

        //设置空页面的图片
        public Builder<T> setrawableId(@DrawableRes int drawableId) {
            this.drawableId = drawableId;
            return this;
        }


        //设置空页面图片和title
        public Builder<T> setTitleOrDrawableId(String title, @DrawableRes int drawableId) {
            this.title = title;
            this.drawableId = drawableId;
            return this;
        }

        //不需要空页面
        public Builder<T> setNoEnpty() {
            isEmptyView = false;
            return this;
        }

        public Builder<T> setHiddenEnpty() {
            needHidden = true;
            return this;
        }



        //不带分页
        public BaseAdapter<T> build(OnBaseAdapterListener<T> onBaseAdapterListener) {
            return new BaseAdapter<T>(this, onBaseAdapterListener);
        }
    }
}
