package com.technology.greenenjoyshoppingstreet.main.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.main.bean.FlashSaleBean;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/12/25 0025.
 */

public class FlashSaleAdapter extends BaseAdapter {


    /**
     * The maps.
     */
    private List<FlashSaleBean.DataBean.GoodsBean> dataList = new ArrayList<>();


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
    public FlashSaleAdapter(Context mContext) {
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
    public FlashSaleBean.DataBean.GoodsBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_flash_sale, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final FlashSaleBean.DataBean.GoodsBean bean = dataList.get(position);
        if (bean != null) {
            holder.nameTv.setText(bean.getSpuName());
            String url = bean.getCover();
//            if (!TextUtils.isEmpty(url)) {
//                Glide.with(mContext).load(url)
//                        .into
//                                (holder.iconIv);
//            } else {
//                Glide.with(mContext).load(R.mipmap.ic_app_launcher)
//                        .into
//                                (holder.iconIv);
//            }
            ImageLoader.with(url, holder.iconIv);
            holder.priceTv.setText(Tools.formatPriceText(bean.getPrice()));

        }

        return convertView;

    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<FlashSaleBean.DataBean.GoodsBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.icon_iv)
        ImageView iconIv;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.price_tv)
        TextView priceTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
