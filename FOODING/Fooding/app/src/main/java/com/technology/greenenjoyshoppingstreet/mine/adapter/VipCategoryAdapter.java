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
import com.technology.greenenjoyshoppingstreet.mine.bean.VipPriceBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class VipCategoryAdapter extends BaseAdapter {

    /**
     * The maps.
     */
    private List<VipPriceBean.DataBean.ListBean> dataList = new ArrayList<>();


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
    public VipCategoryAdapter(Context mContext) {
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
    public VipPriceBean.DataBean.ListBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_vip_category, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final VipPriceBean.DataBean.ListBean bean = dataList.get(position);
        if (bean != null) {

            if (VipPriceBean.VIP_AVAILABLE.equals(bean.getAvailable())) {
                holder.nameTv.setText(bean.getLevelName());
                holder.selectRb.setEnabled(true);
                holder.selectRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (!bean.isCheck() && isChecked) {
                            setPositionCheck(position);
                        }
                    }
                });
            } else {
                holder.nameTv.setText(Tools.concatAll(bean.getLevelName(), "（不可用）"));
                holder.selectRb.setEnabled(false);
                holder.selectRb.setOnCheckedChangeListener(null);
            }
            holder.priceTv.setText("€ " + bean.getPrice());
            holder.selectRb.setChecked(bean.isCheck());

        }

        return convertView;

    }

    public VipPriceBean.DataBean.ListBean selectCategory() {
        for (VipPriceBean.DataBean.ListBean bean : dataList) {
            if (bean.isCheck()) {
                return bean;
            }
        }
        return null;

    }

    public void setPositionCheck(int position) {
        for (int i = 0; i < dataList.size(); i++) {
            VipPriceBean.DataBean.ListBean bean = dataList.get(i);
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
    public void updateData(List<VipPriceBean.DataBean.ListBean> data, boolean isClearOld) {
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
        @BindView(R.id.price_tv)
        TextView priceTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
