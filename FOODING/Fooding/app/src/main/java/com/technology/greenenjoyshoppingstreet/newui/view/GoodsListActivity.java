package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.EventBusUtil;
import com.duma.ld.baselibarary.widget.CheckBoxGoodsList;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.base.MyViewPagerAdapter;
import com.technology.greenenjoyshoppingstreet.newui.model.CategoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CATEGORY_LIST;

public class GoodsListActivity extends BaseMyActivity {
    @BindView(R.id.tab_goodsType)
    SlidingTabLayout tabGoodsType;
    @BindView(R.id.viewPager_goodsList)
    ViewPager viewPagerGoodsList;
    @BindView(R.id.cb_diQu)
    CheckBoxGoodsList cbDiQu;
    @BindView(R.id.cb_xiaoLiang)
    CheckBoxGoodsList cbXiaoLiang;
    @BindView(R.id.cb_jiaGe)
    CheckBoxGoodsList cbJiaGe;
    @BindView(R.id.cb_shijian)
    CheckBoxGoodsList cbShijian;
    @BindView(R.id.view_show)
    LinearLayout viewShow;
    @BindView(R.id.layout_tab)
    FrameLayout layoutTab;
    private String categoryId;
    private String orderString = "";//排序

    public String getOrderString() {
        return orderString;
    }

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_goods_list;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getIntent().getStringExtra(Constants.intent_Name));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        categoryId = getIntent().getStringExtra(Constants.intent_id);
        OkGo.<HttpResModel<List<CategoryModel>>>post(CATEGORY_LIST)
                .tag(this)
                .params("parentId", categoryId)
                .execute(new MyJsonCallback<HttpResModel<List<CategoryModel>>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<CategoryModel>>> respons, HttpResModel<List<CategoryModel>> goodsModelHttpResModel) {
                        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
                        viewPagerAdapter.addFragment(GoodsListFragment.newInstance(categoryId), getString(R.string.all));
                        int size = goodsModelHttpResModel.getData().size();
                        for (int i = 0; i < size; i++) {
                            CategoryModel categoryModel = goodsModelHttpResModel.getData().get(i);
                            viewPagerAdapter.addFragment(GoodsListFragment.newInstance(categoryModel.getCategoryId()), categoryModel.getCategoryName());
                        }
                        viewPagerGoodsList.setAdapter(viewPagerAdapter);
                        tabGoodsType.setViewPager(viewPagerGoodsList);
                        if (size == 0) {
                            layoutTab.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public String getCategoryId() {
        return categoryId;
    }


    @OnClick({R.id.cb_diQu, R.id.cb_xiaoLiang, R.id.cb_jiaGe, R.id.cb_shijian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_diQu:
                if (!cbDiQu.isChecked()) {
                    setDefaut();
                    cbDiQu.setChecked(true);
                    orderString = "";
                    refresh();
                }
                break;
            case R.id.cb_xiaoLiang:
                if (!cbXiaoLiang.isChecked()) {
                    setDefaut();
                    cbXiaoLiang.setChecked(true);
                    orderString = "";
                    refresh();
                }
                break;
            case R.id.cb_jiaGe:
                if (!cbJiaGe.isChecked()) {
                    setDefaut();
                    cbJiaGe.setChecked(true, true);
                } else {
                    setDefaut();
                    cbJiaGe.setChecked(true, !cbJiaGe.isTypeTop());
                }
                if (cbJiaGe.isTypeTop()) {
                    orderString = "priceAsc";
                } else {
                    orderString = "priceDesc";
                }
                refresh();
                break;
            case R.id.cb_shijian:
                if (!cbShijian.isChecked()) {
                    setDefaut();
                    cbShijian.setChecked(true, true);
                } else {
                    setDefaut();
                    cbShijian.setChecked(true, !cbShijian.isTypeTop());
                }
                if (cbShijian.isTypeTop()) {
                    orderString = "onsaleAsc";
                } else {
                    orderString = "onsaleDesc";
                }
                refresh();
                break;
        }
    }

    private void refresh() {
        EventBusUtil.sendModel(Constants.event_refresh);
    }

    private void setDefaut() {
        cbDiQu.setChecked(false);
        cbJiaGe.setChecked(false);
        cbShijian.setChecked(false);
        cbXiaoLiang.setChecked(false);
    }

}
