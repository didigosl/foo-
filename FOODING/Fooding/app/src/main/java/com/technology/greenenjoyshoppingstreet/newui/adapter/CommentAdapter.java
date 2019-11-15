package com.technology.greenenjoyshoppingstreet.newui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.widget.SimpleRatingBar;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.newui.model.GoodsCommentModel;

public class CommentAdapter {
    private BaseAdapter<ProductDetailBean.DataBean.CommentsBean.ListBean> mAdapter;

    public CommentAdapter(RecyclerView mRecyclerView, Activity mActivity) {
        mAdapter = new BaseAdapter.Builder<ProductDetailBean.DataBean.CommentsBean.ListBean>(mRecyclerView, mActivity, R.layout.adapter_goods_comment)
                .setTitle("暂无评论")
                .setNoEnpty()
                .isNested()
                .build(new OnBaseAdapterListener<ProductDetailBean.DataBean.CommentsBean.ListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, ProductDetailBean.DataBean.CommentsBean.ListBean item) {
                        helper.setText(R.id.tv_Nick_name, item.getUserName())
                                .setText(R.id.tv_content, item.getContent())
                                .setText(R.id.tv_commentTime, item.getOrderCreateTime());
                        SimpleRatingBar rating_rank = helper.getView(R.id.rating_rank);
                        rating_rank.setRating(Float.parseFloat(item.getStar()));
                    }
                });
    }

    public BaseAdapter<ProductDetailBean.DataBean.CommentsBean.ListBean> getmAdapter() {
        return mAdapter;
    }
    /*private BaseAdapter<GoodsCommentModel.ListBean> mAdapter;

    public CommentAdapter(RecyclerView mRecyclerView, Activity mActivity) {
        mAdapter = new BaseAdapter.Builder<GoodsCommentModel.ListBean>(mRecyclerView, mActivity, R.layout.adapter_goods_comment)
                .setTitle("暂无评论")
                .setNoEnpty()
                .isNested()
                .build(new OnBaseAdapterListener<GoodsCommentModel.ListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, GoodsCommentModel.ListBean item) {
                        helper.setText(R.id.tv_Nick_name, item.getUserName())
                                .setText(R.id.tv_content, item.getContent())
                                .setText(R.id.tv_commentTime, item.getOrderCreateTime());
                        SimpleRatingBar rating_rank = helper.getView(R.id.rating_rank);
                        rating_rank.setRating(Float.parseFloat(item.getStar()));
                    }
                });
    }

    public BaseAdapter<GoodsCommentModel.ListBean> getmAdapter() {
        return mAdapter;
    }*/

}
