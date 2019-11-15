package com.technology.greenenjoyshoppingstreet.cart.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.cart.CartActivity;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartItemBean;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/11/28 0028.
 */

public class CartItemAdapter extends BaseAdapter {


    /**
     * The maps.
     */
    private List<CartItemBean.DataBean.ListBean> dataList = new ArrayList<>();


    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private CartActivity mContext;


    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public CartItemAdapter(CartActivity mContext) {
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
    public CartItemBean.DataBean.ListBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_cart, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CartItemBean.DataBean.ListBean bean = dataList.get(position);
        if (bean != null) {
//            if (!TextUtils.isEmpty(bean.getCover())) {
//                Glide.with(mContext).load(bean.getCover())
//                        .into
//                                (holder.iconIv);
//            } else {
//                Glide.with(mContext).load(R.mipmap.ic_app_launcher)
//                        .into
//                                (holder.iconIv);
//            }
            ImageLoader.with(bean.getCover(), holder.iconIv);
            holder.nameTv.setText(bean.getSpuName());
            holder.priceTv.setText(Tools.formatPriceText(bean.getPrice()));
            holder.selectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    bean.setChecked(isChecked);
                    mContext.updateBottomBar();
                }
            });
            holder.selectCb.setChecked(bean.isChecked());
            holder.countTv.setText(bean.getNum());
            holder.deleteRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.remove();
                    mContext.updateSku(dataList);

                }
            });
            holder.plusRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.increment();
                    mContext.updateSku(dataList);

                }
            });
            holder.minusRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.decrement();
                    mContext.updateSku(dataList);

                }
            });


        }

        return convertView;

    }

    public void setAllGoodsCheck(boolean isChecked) {
        for (CartItemBean.DataBean.ListBean bean : dataList) {
            bean.setChecked(isChecked);
        }
        mContext.updateBottomBar();
        notifyDataSetChanged();


    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<CartItemBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }

    public Set<String> getSelectSku() {
        Set<String> keys = new HashSet<>();
        for (CartItemBean.DataBean.ListBean bean : dataList) {
            if (bean.isChecked()) {
                keys.add(bean.getSpuId());
            }
        }
        return keys;

    }

    public String getSelectTotalPrice() {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (CartItemBean.DataBean.ListBean bean : dataList) {
            if (bean.isChecked()) {
                bigDecimal = bigDecimal.add(new BigDecimal(Tools.multipleNumber(bean.getNum(),
                        bean.getPrice())));

            }
        }
        return bigDecimal.toString();

    }

    public String getSelectTotalRebate() {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (CartItemBean.DataBean.ListBean bean : dataList) {
            if (bean.isChecked()) {
                bigDecimal = bigDecimal.add(new BigDecimal(Tools.multipleNumber(bean.getNum(),
                        bean.getRebate())));

            }
        }
        return bigDecimal.toString();

    }

    public ArrayList<CartItemBean.DataBean.ListBean> getSelectGoods() {

        ArrayList<CartItemBean.DataBean.ListBean> list = new ArrayList<>();
        for (CartItemBean.DataBean.ListBean bean : dataList) {
            if (bean.isChecked()) {
                list.add(bean);
            }
        }

        return list;
    }


    static class ViewHolder {
        @BindView(R.id.select_cb)
        CheckBox selectCb;
        @BindView(R.id.icon_iv)
        ImageView iconIv;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.delete_rl)
        RelativeLayout deleteRl;
        @BindView(R.id.plus_rl)
        RelativeLayout plusRl;
        @BindView(R.id.count_tv)
        TextView countTv;
        @BindView(R.id.minus_rl)
        RelativeLayout minusRl;
        @BindView(R.id.price_tv)
        TextView priceTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
