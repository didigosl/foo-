package com.technology.greenenjoyshoppingstreet.newui.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.DialogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.OrderDetailActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyFragment;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.OrderListModel;
import com.technology.greenenjoyshoppingstreet.newui.model.OrderPayInfoModel;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.ZhuanHuanUtil;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;

import butterknife.BindView;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_CANCEL;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_FINISH;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_LIST;

public class OrderListFragment extends BaseMyFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private BaseAdapter<OrderListModel.ListBean> mAdapter;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.fragment_order_list;
    }

    public static OrderListFragment newInstance(String id) {
        OrderListFragment goodsListFragment = new OrderListFragment();
        Bundle args = new Bundle();
        args.putString(Constants.intent_id, id);
        goodsListFragment.setArguments(args);
        return goodsListFragment;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initAdapter();
        setRefresh(refreshLayout, true);
        mPublicConfig.showLoading();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<OrderListModel.ListBean>(rvList, mActivity, R.layout.adapter_order_goods)
                .setTitle(getString(R.string.without_order_rel))
                .build(new OnBaseAdapterListener<OrderListModel.ListBean>() {
                    @Override
                    public void convert(BaseViewHolder helper, final OrderListModel.ListBean item) {
                        helper.setText(R.id.tv_order_time, item.getCreateTime());
                        TextView tv_order_type = helper.getView(R.id.tv_order_type);
                        TextView tv_orderMoney = helper.getView(R.id.tv_orderMoney);
                        final TextView tv_hui1 = helper.getView(R.id.tv_hui1);
                        TextView tv_hui2 = helper.getView(R.id.tv_hui2);
                        TextView tv_hong = helper.getView(R.id.tv_hong);
                        //判断状态
                        tv_order_type.setText(item.getFlagText());
                        tv_order_type.setTextColor(ZhuanHuanUtil.getColor(R.color.textColor_hei));
                        tv_hui1.setVisibility(View.GONE);
                        tv_hui2.setVisibility(View.GONE);
                        tv_hong.setVisibility(View.GONE);
                        tv_hui1.setOnClickListener(new tvBut(tv_hui1, helper.getLayoutPosition()));
                        tv_hui2.setOnClickListener(new tvBut(tv_hui2, helper.getLayoutPosition()));
                        tv_hong.setOnClickListener(new tvBut(tv_hong, helper.getLayoutPosition()));
                        switch (item.getFlag()) {
                            case "-1":
                                //已取消
//                                setTextBtn(tv_hong, Constants.Order_shopping);
//                                setTextBtn(tv_hui1, Constants.Order_delete);
                                break;
                            case "1":
                                //待付款
                                setTextBtn(tv_hong, Constants.Order_pay);
                                setTextBtn(tv_hui1, Constants.Order_cancel);
                                tv_order_type.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                                break;
                            case "2":
                                //已付款=待发货
                                break;
                            case "3":
                                //已发货=待收货
                                setTextBtn(tv_hui1, Constants.Order_ChaKanWuLiu);
                                setTextBtn(tv_hui2, Constants.Order_ConfirmGoods);
                                tv_order_type.setTextColor(ZhuanHuanUtil.getColor(R.color.hong));
                                break;
                            case "4":
                                //已完成
                                setTextBtn(tv_hui1, Constants.Order_delete);
                                setTextBtn(tv_hui2, Constants.Order_comment);
                                break;
                            case "5":
                                //已评价
                                setTextBtn(tv_hui1, Constants.Order_delete);
                                break;
                        }
                        //商品数量价格
                        int allNum = 0;
                        for (int i = 0; i < item.getGoods().size(); i++) {
                            allNum = allNum + Integer.parseInt(item.getGoods().get(i).getNum());
                        }
                        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                                .append(getString(R.string.total_simple) + allNum + getString(R.string.products)+"：")
                                .setFontSize(ConvertUtils.sp2px(12))
                                .append("€" + item.getTotalAmount())
                                .setFontSize(ConvertUtils.sp2px(18))
                                .setBold()
                                .create();
                        tv_orderMoney.setText(spannableStringBuilder);
                        //商品列表
                        BaseAdapter<OrderListModel.ListBean.GoodsBean> goodsBeanBaseAdapter =
                                new BaseAdapter.Builder<OrderListModel.ListBean.GoodsBean>((RecyclerView) helper.getView(R.id.rv_goodsList), mActivity, R.layout.adapter_order_goods_list)
                                        .isNested()
                                        .build(new OnBaseAdapterListener<OrderListModel.ListBean.GoodsBean>() {
                                            @Override
                                            public void convert(BaseViewHolder helper, OrderListModel.ListBean.GoodsBean item) {
                                                ImageLoader.with(item.getCover(), (ImageView) helper.getView(R.id.img_icon));
                                                helper.setText(R.id.tv_title, item.getSpuName());
                                            }
                                        });
                        goodsBeanBaseAdapter.setNewData(item.getGoods());
                        goodsBeanBaseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(mActivity, OrderDetailActivity.class);
                                intent.putExtra(ParameterConstant.ORDER_ID, item.getOrderId());
                                startActivity(intent);
                            }
                        });
                    }
                });
    }


    private void setTextBtn(TextView textView, String s) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(s);
    }

    private void toBut(TextView textView, int position) {
        switch (textView.getText().toString()) {
            case Constants.Order_ConfirmGoods:
                ConfirmGoodsHttp(position);
                break;
            case Constants.Order_ChaKanWuLiu:
                break;
            case Constants.Order_delete:
                break;
            case Constants.Order_comment:
                //
                break;
            case Constants.Order_cancel:
                CancelHttp(position);
                break;
            case Constants.Order_pay:
                OrderPayInfoModel orderPayInfoModel = new OrderPayInfoModel();
                OrderListModel.ListBean listBean = mAdapter.getData().get(position);
                orderPayInfoModel.setOrderId(listBean.getOrderId());
                orderPayInfoModel.setSn(listBean.getSn());
                orderPayInfoModel.setTotalAmount(listBean.getTotalAmount());
                StartActivityUtil.getInstance().goPay(mActivity, orderPayInfoModel);
                break;
            case Constants.Order_shopping:
                break;
        }
    }

    //取消订单
    private void CancelHttp(final int position) {
        DialogUtil.getAlertDialog(mActivity, getString(R.string.alert), "是否确认取消订单？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<HttpResModel<Void>>post(ORDER_CANCEL)
                                .tag(this)
                                .params("orderId", mAdapter.getData().get(position).getOrderId())
                                .execute(new MyJsonCallback<HttpResModel<Void>>() {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<Void>> respons, HttpResModel<Void> goodsModelHttpResModel) {
                                        starRefresh();
                                    }
                                }.isDialog(mActivity));
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .setCancelable(false)
                .show();
    }

    //确认收货
    private void ConfirmGoodsHttp(final int position) {
        DialogUtil.getAlertDialog(mActivity, getString(R.string.alert), "是否确认收货？")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OkGo.<HttpResModel<Void>>post(ORDER_FINISH)
                                .tag(this)
                                .params("orderId", mAdapter.getData().get(position).getOrderId())
                                .execute(new MyJsonCallback<HttpResModel<Void>>() {
                                    @Override
                                    protected void onJsonSuccess(Response<HttpResModel<Void>> respons, HttpResModel<Void> goodsModelHttpResModel) {
                                        starRefresh();
                                    }
                                }.isDialog(mActivity));
                    }
                })
                .setNegativeButton(getString(R.string.cancel), null)
                .setCancelable(false)
                .show();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        starRefresh();
    }

    class tvBut implements View.OnClickListener {
        TextView textView;
        int position;

        public tvBut(TextView textView, int position) {
            this.textView = textView;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            toBut(textView, position);
        }
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<OrderListModel>>post(ORDER_LIST)
                .tag(this)
                .params("flag", getArguments().getString(Constants.intent_id, ""))
                .params("page", page)
                .params("pageLimit", "20")
                .execute(new MyJsonCallback<HttpResModel<OrderListModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<OrderListModel>> respons, HttpResModel<OrderListModel> goodsModelHttpResModel) {
                        setRefreshData(mAdapter, goodsModelHttpResModel.getData().getList());
                    }
                });
    }
}
