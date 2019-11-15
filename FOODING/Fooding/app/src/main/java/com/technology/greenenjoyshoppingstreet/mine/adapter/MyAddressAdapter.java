package com.technology.greenenjoyshoppingstreet.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.AddressListActivity;
import com.technology.greenenjoyshoppingstreet.mine.EditAddressActivity;
import com.technology.greenenjoyshoppingstreet.mine.bean.AddressListBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.technology.greenenjoyshoppingstreet.mine.EditAddressActivity.ADDRESS_BEAN;

/**
 * Created by Bern on 2017/12/13 0013.
 */
public class MyAddressAdapter extends BaseAdapter {
    private boolean displayCheck;

    /**
     * The maps.
     */
    private List<AddressListBean.DataBean> dataList = new ArrayList<>();


    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private AddressListActivity mContext;


    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public MyAddressAdapter(AddressListActivity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDisplayCheck(boolean displayCheck) {
        this.displayCheck = displayCheck;
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
    public AddressListBean.DataBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_address_list, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AddressListBean.DataBean bean = dataList.get(position);
        if (bean != null) {
            holder.nameTv.setText(bean.getMan());
            holder.phoneTv.setText(bean.getPhone());
            holder.addressTv.setText(Tools.concatAll(bean.getProvince(), " ", bean.getCity(), " ", bean.getAddress()));
            holder.defaultTv.setVisibility(bean.isDefaultAddress() ? View.VISIBLE : View.GONE);
            holder.selectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    mContext.selectAddress(dataList.get(position));

                }
            });
            holder.selectCb.setVisibility(displayCheck ? View.VISIBLE : View.GONE);
            holder.editTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editIntent = new Intent(mContext, EditAddressActivity.class);
                    editIntent.putExtra(ADDRESS_BEAN, bean);
                    mContext.startActivity(editIntent);
                }
            });

            holder.deleteTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mContext.deleteAddress(bean);
                }
            });

        }

        return convertView;

    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<AddressListBean.DataBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.edit_tv)
        TextView editTv;
        @BindView(R.id.delete_tv)
        TextView deleteTv;
        @BindView(R.id.select_cb)
        CheckBox selectCb;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.phone_tv)
        TextView phoneTv;
        @BindView(R.id.address_tv)
        TextView addressTv;

        @BindView(R.id.default_tv)
        TextView defaultTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
