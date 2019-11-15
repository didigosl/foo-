package com.technology.greenenjoyshoppingstreet.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.PayModeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class PayModeAdapter extends BaseAdapter {


    /**
     * The maps.
     */
    private List<PayModeBean> dataList = new ArrayList<>();


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
    public PayModeAdapter(Context mContext) {
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
    public PayModeBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_pay_mode, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final PayModeBean bean = dataList.get(position);
        if (bean != null) {
            holder.selectRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!bean.isCheck() && isChecked) {
                        setPositionCheck(position);
                    }
                }
            });
            holder.nameTv.setText(bean.getPayMode());
            holder.selectRb.setChecked(bean.isCheck());


        }

        return convertView;

    }

    public PayModeBean getSelectMode() {
        for (PayModeBean bean : dataList) {
            if (bean.isCheck()) {
                return bean;
            }
        }
        return null;

    }


    public void setPositionCheck(int position) {
        for (int i = 0; i < dataList.size(); i++) {
            PayModeBean bean = dataList.get(i);
            bean.setCheck(i == position);

        }

        notifyDataSetChanged();

    }

    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<PayModeBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.select_rb)
        RadioButton selectRb;
        @BindView(R.id.name_tv)
        TextView nameTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
