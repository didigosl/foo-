package com.technology.greenenjoyshoppingstreet.newui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.main.SearchActivity;
import com.technology.greenenjoyshoppingstreet.mine.BuyVIPActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.base.MyViewPagerAdapter;
import com.technology.greenenjoyshoppingstreet.newui.model.MainClassModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CATEGORY_LIST;

public class MainFragmentActivity extends BaseMyActivity {
    /*@BindView(R.id.gift_rl)
    FrameLayout giftRl;*/
    @BindView(R.id.search_rl)
    FrameLayout searchRl;
    @BindView(R.id.tab_goodsType)
    SlidingTabLayout tabGoodsType;
    @BindView(R.id.layout_tab)
    FrameLayout layoutTab;
    @BindView(R.id.viewPager_goodsList)
    ViewPager viewPagerGoodsList;
    @BindView(R.id.layout_root)
    FrameLayout layoutRoot;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_fragment_main;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setLoadOrErrorRootLayout(R.id.layout_root);
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void setFits(LinearLayout layout_boot_ob) {
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        starRefresh();
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<List<MainClassModel>>>post(CATEGORY_LIST)
                .tag(this)
                .execute(new MyJsonCallback<HttpResModel<List<MainClassModel>>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<List<MainClassModel>>> respons, HttpResModel<List<MainClassModel>> goodsModelHttpResModel) {
                        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
                        List<MainClassModel> data = goodsModelHttpResModel.getData();
                        viewPagerAdapter.addFragment(MainTuiJianFragment.newInstance(), "推荐");
                        for (int i = 0; i < data.size(); i++) {
                            viewPagerAdapter.addFragment(MainClassFragment.newInstance(data.get(i)), data.get(i).getCategoryName());
                        }
                        viewPagerGoodsList.setAdapter(viewPagerAdapter);
                        tabGoodsType.setViewPager(viewPagerGoodsList);
                    }
                });
    }

    @OnClick({R.id.search_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.gift_rl:
                startActivity(new Intent(mActivity, BuyVIPActivity.class));
                break;*/
            case R.id.search_rl:
                startActivity(new Intent(mActivity, SearchActivity.class));
                break;
        }
    }

}
