package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duma.ld.baselibarary.base.adapter.BaseAdapter;
import com.duma.ld.baselibarary.base.adapter.OnBaseAdapterListener;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.EventBusUtil;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.CouponListModel;
import com.technology.greenenjoyshoppingstreet.newui.model.CouponModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.duma.ld.baselibarary.util.Constants.event_confirm_order_coupon;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.COUPON_LIST;

public class CouponActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    BaseAdapter<CouponListModel> mAdapter;
    List<CouponListModel> mList;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_coupon;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.my_coupon));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initAdapter();
        Type type = new TypeToken<ArrayList<CouponListModel>>() {
        }.getType();
        mList = new Gson().fromJson(getIntent().getStringExtra(Constants.intent_list_json), type);
        if (mList != null) {
            mAdapter.setNewData(mList);
            return;
        }
        setRefresh(refreshLayout, true);
        starRefresh();
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter.Builder<CouponListModel>(rvList, mActivity, R.layout.adapter_coupon)
                .setTitle(getString(R.string.not_coupons_availables))
                .build(new OnBaseAdapterListener<CouponListModel>() {
                    @Override
                    public void convert(BaseViewHolder helper, CouponListModel item) {
                        helper.setText(R.id.coupon_name, getString(R.string.full)+"€" + item.getMinLimit() + getString(R.string.available));
                        helper.setText(R.id.coupon_content, item.getCouponName());
                        helper.setText(R.id.tv_time, item.getStartTime() + "-" + item.getEndTime());
                        SpannableStringBuilder spannableStringBuilder = new SpanUtils()
                                .append("€")
                                .setFontSize(ConvertUtils.sp2px(16))
                                .append(item.getAmount())
                                .setFontSize(ConvertUtils.sp2px(24))
                                .create();
                        helper.setText(R.id.tv_money, spannableStringBuilder);
                    }
                });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mList != null) {
                    EventBusUtil.sendModel(event_confirm_order_coupon, mAdapter.getData().get(position));
                    finish();
                }
            }
        });
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        if (mList != null) {
            mPublicConfig.showContent();
            return;
        }
        OkGo.<HttpResModel<CouponModel>>post(COUPON_LIST)
                .tag(this)
                .params("page", page)
                .params("pageLimit", "20")
                .execute(new MyJsonCallback<HttpResModel<CouponModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<CouponModel>> respons, HttpResModel<CouponModel> goodsModelHttpResModel) {
                        setRefreshData(mAdapter, goodsModelHttpResModel.getData().getList());
                    }
                });
    }
}
