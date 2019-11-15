package com.technology.greenenjoyshoppingstreet.mine.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.OrderDetailActivity;
import com.technology.greenenjoyshoppingstreet.mine.OrderListActivity;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderListBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderStatus;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/1/2.
 */

public class OrderOperationListAdapter extends BaseAdapter {

    /**
     * The maps.
     */
    private List<OrderListBean.DataBean.ListBean> dataList = new ArrayList<>();


    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private OrderListActivity mContext;


    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public OrderOperationListAdapter(OrderListActivity mContext) {
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
    public OrderListBean.DataBean.ListBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_order_operation, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final OrderListBean.DataBean.ListBean bean = dataList.get(position);
        if (bean != null) {
            holder.payTv.setVisibility(View.GONE);
            holder.cancelOrderTv.setVisibility(View.GONE);
            holder.confirmReceiveTv.setVisibility(View.GONE);
            holder.lookProgressTv.setVisibility(View.GONE);
            holder.refundProgressTv.setVisibility(View.GONE);
            holder.lookDetailTv.setVisibility(View.GONE);
            holder.evaluteTv.setVisibility(View.GONE);


            holder.rootRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (UserInfoManger.isLogin()) {
                        Intent intent = new Intent(mContext, OrderDetailActivity.class);
                        intent.putExtra(ParameterConstant.ORDER_ID, bean.getOrderId());
                        mContext.startActivity(intent);
                    } else {
                        mContext.tip(R.string.first_login);
                        mContext.finish();
                    }

                }
            });
            holder.dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    if (UserInfoManger.isLogin()) {
                        Intent intent = new Intent(mContext, OrderDetailActivity.class);
                        intent.putExtra(ParameterConstant.ORDER_ID, bean.getOrderId());
                        mContext.startActivity(intent);
                    } else {
                        mContext.tip(R.string.first_login);
                        mContext.finish();
                    }

                }
            });

            holder.payTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mContext.payOrder(bean);

                }
            });
            holder.cancelOrderTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.cancelOrder(bean);


                }
            });
            holder.confirmReceiveTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.confirmReceive(bean);
                }
            });
            holder.lookProgressTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.lookProgress(bean);
                }
            });
            holder.evaluteTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.evaluteOrder(bean);
                }
            });
            holder.orderStatusTv.setText(bean.getFlagText());
            holder.dateTv.setText(bean.getCreateTime());
            holder.payAmountTv.setText(Tools.formatPriceText(bean.getTotalAmount()));
            OrderProductAdapter orderProductAdapter= new OrderProductAdapter(mContext);
//            if (holder.dataLv.getAdapter() != null) {
//                orderProductAdapter = (OrderProductAdapter) holder.dataLv.getAdapter();
//
//            } else {
//                orderProductAdapter = new OrderProductAdapter(mContext);
//                holder.dataLv.setAdapter(orderProductAdapter);
//            }
            holder.dataLv.setAdapter(orderProductAdapter);
            orderProductAdapter.updateData(bean.getGoods(), true);

            holder.totalCountTv.setText(Tools.concatAll(mContext.getString(R.string.total_simple), orderProductAdapter.getGoodsTotalCount(), "件"));

            OrderStatus orderStatus = OrderStatus.getStatus(bean.getFlag());
            if (orderStatus != null) {
                switch (orderStatus) {
                    case PENDINGPAYMENT:
                        holder.payTv.setVisibility(View.VISIBLE);
                        holder.cancelOrderTv.setVisibility(View.VISIBLE);
                        break;
                    case TOBEDELIVERED:
//                        holder.cancelOrderTv.setVisibility(View.VISIBLE);
                        break;
                    case TOCONFIRMTHERECEIPT:
                        holder.confirmReceiveTv.setVisibility(View.VISIBLE);
                        holder.lookProgressTv.setVisibility(View.VISIBLE);
                        break;
                    case TOBECOMMENTED:
                        holder.evaluteTv.setVisibility(View.VISIBLE);
                        break;
                    case COMPLETED:
                        break;


                }


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
    public void updateData(List<OrderListBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {


        @BindView(R.id.date_tv)
        TextView dateTv;
        @BindView(R.id.order_status_tv)
        TextView orderStatusTv;
        @BindView(R.id.data_lv)
        ListViewInScroll dataLv;
        @BindView(R.id.total_count_tv)
        TextView totalCountTv;
        @BindView(R.id.pay_amount_tv)
        TextView payAmountTv;
        @BindView(R.id.pay_tv)
        TextView payTv;
        @BindView(R.id.cancel_order_tv)
        TextView cancelOrderTv;
        @BindView(R.id.confirm_receive_tv)
        TextView confirmReceiveTv;
        @BindView(R.id.look_progress_tv)
        TextView lookProgressTv;
        @BindView(R.id.refund_progress_tv)
        TextView refundProgressTv;
        @BindView(R.id.look_detail_tv)
        TextView lookDetailTv;
        @BindView(R.id.evalute_tv)
        TextView evaluteTv;
        @BindView(R.id.root_rl)
        RelativeLayout rootRl;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
