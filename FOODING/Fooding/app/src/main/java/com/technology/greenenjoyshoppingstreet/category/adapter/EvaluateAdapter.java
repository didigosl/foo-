package com.technology.greenenjoyshoppingstreet.category.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Bern on 2017/12/29 0029.
 */

public class EvaluateAdapter extends BaseAdapter {

    /**
     * The maps.
     */
    private List<ProductDetailBean.DataBean.CommentsBean.ListBean> dataList = new ArrayList<>();


    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private Context mContext;


    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public EvaluateAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    /**
     * Gets count.
     *
     * @return the count
     */
    @Override
    public int getCount() {
        return dataList.size();
    }

    /**
     * Gets item.
     *
     * @param position the position
     * @return the item
     */
    @Override
    public ProductDetailBean.DataBean.CommentsBean.ListBean getItem(int position) {
        return dataList.get(position);
    }


    /**
     * Gets item id.
     *
     * @param position the position
     * @return the item id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Gets view.
     *
     * @param position    the position
     * @param convertView the convert view
     * @param parent      the parent
     * @return the view
     */
/*
         * (non-Javadoc)
         *
         * @see android.widget.SimpleAdapter#getView(int, android.view.View,
         * android.view.ViewGroup)
         */
    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_evaluate, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ProductDetailBean.DataBean.CommentsBean.ListBean bean = dataList.get(position);
        if (bean != null) {
            holder.nameTv.setText(bean.getUserName());
            holder.contentTv.setText(bean.getContent());
            String url = bean.getAvatar();
//            if (!TextUtils.isEmpty(url)) {
//                Glide.with(mContext).load(url)
//                        .into
//                                (holder.profileImage);
//            } else {
//                Glide.with(mContext).load(R.mipmap.ic_app_launcher)
//                        .into
//                                (holder.profileImage);
//            }
            ImageLoader.with(url, holder.profileImage);
            String star = bean.getStar();
            if (!TextUtils.isEmpty(star) && TextUtils.isDigitsOnly(star)) {
                holder.starRb.setRating(Integer.valueOf(star));
            } else {
                holder.starRb.setRating(0);
            }


            holder.timeTv.setText(bean.getOrderCreateTime());

        }

        return convertView;

    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<ProductDetailBean.DataBean.CommentsBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.profile_image)
        CircleImageView profileImage;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.time_tv)
        TextView timeTv;
        @BindView(R.id.star_rb)
        RatingBar starRb;
        @BindView(R.id.content_tv)
        TextView contentTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
