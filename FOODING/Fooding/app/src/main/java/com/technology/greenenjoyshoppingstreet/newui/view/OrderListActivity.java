package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.flyco.tablayout.SlidingTabLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyViewPagerAdapter;

import butterknife.BindView;

public class OrderListActivity extends BaseMyActivity {
    @BindView(R.id.tab_goodsType)
    SlidingTabLayout tabGoodsType;
    @BindView(R.id.layout_tab)
    FrameLayout layoutTab;
    @BindView(R.id.viewPager_goodsList)
    ViewPager viewPagerGoodsList;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_new_order_list;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.my_order));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        int position = getIntent().getIntExtra(Constants.intent_position, 0);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(OrderListFragment.newInstance(""), getString(R.string.all));
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("1"), getString(R.string.pending_pay));
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("3"), getString(R.string.transport));
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("4"), getString(R.string.completed));
        viewPagerAdapter.addFragment(OrderListFragment.newInstance("-1"), getString(R.string.canceled_all));
        viewPagerGoodsList.setAdapter(viewPagerAdapter);
        tabGoodsType.setViewPager(viewPagerGoodsList);
        viewPagerGoodsList.setCurrentItem(position);
    }

}
