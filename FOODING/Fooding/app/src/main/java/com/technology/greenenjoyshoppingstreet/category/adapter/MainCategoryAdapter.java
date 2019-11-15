package com.technology.greenenjoyshoppingstreet.category.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.CategoryActivity;
import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class MainCategoryAdapter extends BaseAdapter {
    /**
     * The maps.
     */
    private List<CategoryBean.DataBean> dataList = new ArrayList<>();


    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private CategoryActivity mContext;


    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public MainCategoryAdapter(CategoryActivity mContext) {
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
    public CategoryBean.DataBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_main_category, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CategoryBean.DataBean bean = dataList.get(position);
        if (bean != null) {
            holder.nameRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && !bean.isChecked()) {
                        checkItem(position);
                        mContext.updateSub(bean);

                    }
                }
            });
            holder.nameRb.setText(bean.getCategoryName());
            holder.nameRb.setChecked(bean.isChecked());

        }

        return convertView;

    }

    public void checkItem(int position) {

        int length = dataList.size();
        for (int i = 0; i < length; i++) {
            CategoryBean.DataBean dataBean = dataList.get(i);
            if (dataBean != null) {
                dataBean.setChecked(position == i);
            }
        }
        notifyDataSetChanged();

    }

    public CategoryBean.DataBean getSelectCategory() {
        for (CategoryBean.DataBean dataBean : dataList) {
            if (dataBean.isChecked()) {
                return dataBean;
            }
        }
        return null;


    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<CategoryBean.DataBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.name_rb)
        RadioButton nameRb;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
