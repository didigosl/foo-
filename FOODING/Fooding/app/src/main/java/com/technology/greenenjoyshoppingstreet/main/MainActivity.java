package com.technology.greenenjoyshoppingstreet.main;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TabHost;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.CategoryActivity;
import com.technology.greenenjoyshoppingstreet.ktnewui.CategoryKtActivity;
import com.technology.greenenjoyshoppingstreet.ktnewui.HomeKtActivity;
import com.technology.greenenjoyshoppingstreet.login.LoginActivity;
import com.technology.greenenjoyshoppingstreet.login.bean.UserInfoBean;
import com.technology.greenenjoyshoppingstreet.mine.MineActivity;
import com.technology.greenenjoyshoppingstreet.mine.bean.AddressListBean;
import com.technology.greenenjoyshoppingstreet.newui.view.MainFragmentActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.ShoppingCartActivity;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ADDRESS_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_GETINFO;


public class MainActivity extends ActivityGroup {

    /**
     * The Constant HOME_TAB.
     */
    private static final int MAIN_PAGE_TAB = 0;

    /**
     * Materiel tab.
     */
    private static final int CATEGORY_TAB = 1;

    /**
     * Mine tab.
     */
    private static final int CART_TAB = 2;
    /**
     * Mine tab.
     */
    private static final int MINE_TAB = 3;

    /**
     * Tags.
     */
    private final String[] tags = {"首页", "分类", "购物车", "我的"};

    /**
     * Classes.
     */
    private final Class<?>[] classes = {
//            MainPageActivity.class
            HomeKtActivity.class
            , CategoryKtActivity.class,
            ShoppingCartActivity.class
//            CartActivity.class
            , MineActivity.class};

    @BindView(R.id.main_page_rb)
    RadioButton mainPageRb;
    @BindView(R.id.category_rb)
    RadioButton categoryRb;
    @BindView(R.id.shopping_cart_rb)
    RadioButton shoppingCartRb;
    @BindView(R.id.red_point)
    View redPoint;
    @BindView(R.id.mine_rb)
    RadioButton mineRb;


    /**
     * Tab host.
     */
    private TabHost tabHost;


    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    protected void setFits(LinearLayout layout_boot_ob) {
        layout_boot_ob.setFitsSystemWindows(true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            if (isTargetCart) {
                int status = intent.getIntExtra(LOGIN_STATUS, 0);
                if (status == LOGIN_SUCCESS) {
                    onViewClicked(shoppingCartRb);
                }
            }

        }

    }

    /**
     * On start.
     */
    @Override
    protected void onStart() {
        super.onStart();
        isTargetCart = false;
        if (BaseActivity.isFinishAll()) {
            finish();
            System.exit(0);
        } else {
            if (!UserInfoManger.isHasToken()) {
//                startActivity(new Intent(this, LoginActivity.class));

            } else {
                getUserInfo();
                getAddressList();
            }
        }


    }

    /**
     * Gets user task.
     */
    private void getUserInfo() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<UserInfoBean>() {

            @Override
            public void onComplete(UserInfoBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        UserInfoManger.setUserInfo(bean.getData());


                    }

                }

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                List<KeyValuePair> list = new ArrayList<>();
                return UserInfoManger.getSignList(list);

            }


            @Override
            public String createUrl() {

                return USER_GETINFO;
            }
        });
    }

    private void getAddressList() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AddressListBean>() {

            @Override
            public void onComplete(AddressListBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        UserInfoManger.setAddressList(bean.getData());
                    }


                }
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                List<KeyValuePair> list = new ArrayList<>();
                return UserInfoManger.getSignList(list);

            }


            @Override
            public String createUrl() {

                return ADDRESS_LIST;
            }
        });
    }

    /**
     * Init views.
     */
    private void initViews() {


        tabHost = (TabHost) findViewById(R.id.tab_BinHaiBureau);
        tabHost.setup(this.getLocalActivityManager());

        for (int i = 0; i < tags.length; i++) {
            Intent intent = new Intent(this, classes[i]);
            tabHost.addTab(tabHost.newTabSpec(tags[i])
                    .setIndicator(getIndicator(0, tags[i])).setContent(intent));
        }

        mainPageRb.performClick();
    }


    /**
     * Change selected.
     *
     * @param checkedId the checked id
     */
    private void changeSelected(int checkedId) {
        mainPageRb.setChecked(checkedId == R.id.main_page_rb);
        categoryRb.setChecked(checkedId == R.id.category_rb);
        shoppingCartRb.setChecked(checkedId == R.id.shopping_cart_rb);
        mineRb.setChecked(checkedId == R.id.mine_rb);

    }

    /**
     * 用于tabWidget添加tabSpec时，给每个tabSpec设定图标和文字，.
     *
     * @param imgID  图标
     * @param textID 文字
     * @return the indicator
     */
    private View getIndicator(int imgID, String textID) {
        ImageView imgView = (ImageView) LayoutInflater.from(this).inflate(
                R.layout.indicator_tab_home, null);
        return imgView;
    }

    /**
     * On back pressed.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        System.exit(0);
    }


    @OnClick({R.id.main_page_rb, R.id.category_rb, R.id.shopping_cart_rb, R.id.mine_rb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_page_rb:
                changeSelected(view.getId());
                tabHost.setCurrentTab(MAIN_PAGE_TAB);
//                StartActivityUtil.getInstance().startActivity(this, GoodsListActivity.class);
                break;
            case R.id.category_rb:
                changeSelected(view.getId());
                tabHost.setCurrentTab(CATEGORY_TAB);
                break;
            case R.id.shopping_cart_rb:
                if (UserInfoManger.isLogin()) {
                    changeSelected(view.getId());
                    tabHost.setCurrentTab(CART_TAB);
                } else {
                    shoppingCartRb.setChecked(false);
                    isTargetCart = true;
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.mine_rb:
                changeSelected(view.getId());
                tabHost.setCurrentTab(MINE_TAB);
                break;
        }
    }

    private boolean isTargetCart = false;

    public static final int CART_REQUEST = 3453;
    public static final int LOGIN_SUCCESS = 563;
    public static final String LOGIN_STATUS = "LOGIN_STATUS";


}
