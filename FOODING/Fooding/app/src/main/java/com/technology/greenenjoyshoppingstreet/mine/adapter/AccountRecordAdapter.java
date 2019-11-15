package com.technology.greenenjoyshoppingstreet.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.AccountRecordBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.technology.greenenjoyshoppingstreet.mine.bean.AccountRecordBean.REBATE;

/**
 * Created by Administrator on 2018/1/14.
 */

public class AccountRecordAdapter extends BaseAdapter {

    /**
     * The maps.
     */
    private List<AccountRecordBean.DataBean.ListBean> dataList = new ArrayList<>();


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
    public AccountRecordAdapter(Context mContext) {
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
    public AccountRecordBean.DataBean.ListBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_account_record, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AccountRecordBean.DataBean.ListBean bean = dataList.get(position);
        if (bean != null) {
            String name = bean.getTypeText();
            if (!TextUtils.isEmpty(bean.getRemark())) {

                name = name + " (" + bean.getRemark() + ")";
            }
            holder.nameTv.setText(name);
            holder.timeTv.setText(bean.getCreateTime());
            holder.balanceTv.setText(Tools.concatAll(mContext.getString(R.string.balance)+"：", bean.getAmount()));
            if (!TextUtils.equals(REBATE, bean.getType())) {
                holder.priceTv.setText(Tools.concatAll("+ ", Tools.formatPriceText(bean.getMoney())));
                holder.priceTv.setTextColor(Color.parseColor("#CD2668"));
            } else {
                holder.priceTv.setText(Tools.concatAll("- ", Tools.formatPriceText(bean.getMoney())));
                holder.priceTv.setTextColor(Color.parseColor("#3C4151"));
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
    public void updateData(List<AccountRecordBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.time_tv)
        TextView timeTv;
        @BindView(R.id.balance_tv)
        TextView balanceTv;
        @BindView(R.id.price_tv)
        TextView priceTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
