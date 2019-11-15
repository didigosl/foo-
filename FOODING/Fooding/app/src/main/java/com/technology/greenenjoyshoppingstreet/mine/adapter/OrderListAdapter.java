package com.technology.greenenjoyshoppingstreet.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderStatus;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderStatusBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class OrderListAdapter extends BaseAdapter {

    /**
     * The maps.
     */
    private List<OrderStatusBean> dataList = new ArrayList<>();


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
    public OrderListAdapter(Context mContext) {
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
    public OrderStatusBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_order_status, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final OrderStatusBean bean = dataList.get(position);
        if (bean != null) {
            OrderStatus orderStatus = bean.getOrderStatus();
            if (orderStatus != null) {
                holder.nameTv.setText(orderStatus.getStatusText());
                holder.statusIv.setBackgroundResource(orderStatus.getIamgeRes());


            }
            if (!TextUtils.isEmpty(bean.getCount())) {
                holder.numberTv.setVisibility(View.VISIBLE);
                holder.numberTv.setText(bean.getCount());
            } else {
                holder.numberTv.setVisibility(View.GONE);
            }

        }

        return convertView;

    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<OrderStatusBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.status_iv)
        ImageView statusIv;
        @BindView(R.id.number_tv)
        TextView numberTv;
        @BindView(R.id.name_tv)
        TextView nameTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
