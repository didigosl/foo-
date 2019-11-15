package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.adapter.MenuAdapter;
import com.technology.greenenjoyshoppingstreet.utils.DialogConfigBean;
import com.technology.greenenjoyshoppingstreet.utils.GlideCatchUtil;
import com.technology.greenenjoyshoppingstreet.utils.info.PhoneInfoManager;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.ABOUT_US;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.ABOUT_US_ES;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.CLEAR_CACHE;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.CLEAR_CACHE_ES;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.DELIVERY_ADDRESS;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.DELIVERY_ADDRESS_ES;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.PERSONAL_INFO;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.PERSONAL_INFO_ES;

/**
 * Created by Administrator on 2017/12/12.
 */

public class PersonalSettingActivity extends BaseActivity {

    @BindView(R.id.menu_lv)
    ListViewInScroll menuLv;
    @BindView(R.id.log_off_bt)
    Button logOffBt;

    MenuAdapter minuAdapter;


    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_setting);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        setBarTitle(getString(R.string.personal_settings));
        logOffBt.setVisibility(UserInfoManger.isLogin() ? View.VISIBLE : View.GONE);
        minuAdapter = new MenuAdapter(this);
        menuLv.setAdapter(minuAdapter);
        menuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleMenu(minuAdapter.getItem(position));
            }

        });
        ArrayList<String> menuList = new ArrayList<>();
        if (PhoneInfoManager.isChineseLanguage()){
            menuList.add(PERSONAL_INFO);
            menuList.add(DELIVERY_ADDRESS);
            menuList.add(CLEAR_CACHE);
            menuList.add(ABOUT_US);
        }else {
            menuList.add(PERSONAL_INFO_ES);
            menuList.add(DELIVERY_ADDRESS_ES);
            menuList.add(CLEAR_CACHE_ES);
            menuList.add(ABOUT_US_ES);
        }
        minuAdapter.updateData(menuList, true);
    }

    private void handleMenu(String menuName) {
        if (!TextUtils.isEmpty(menuName)) {
            switch (menuName) {
                case PERSONAL_INFO:
                    startActivity(new Intent(this, PersonalInfoActivity.class));
                    break;
                case DELIVERY_ADDRESS:
                    startActivity(new Intent(this, AddressListActivity.class));
                    break;
                case CLEAR_CACHE:
                    String size = GlideCatchUtil.getInstanceContext().getCacheSize();
                    DialogConfigBean dialogConfigBean = DialogConfigBean.getDefaultDoubleConfig(this);
                    dialogConfigBean.setContentText(getString(R.string.actual_cache_size) + size + getString(R.string.ask_sure_clean));
                    dialogConfigBean.setRightClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tip(R.string.cleaned);
                            GlideCatchUtil.getInstanceContext().clearCacheDiskSelf();
                            dismissChooseDialog();
                        }
                    });
                    showOperationDialog(dialogConfigBean);
                    break;
                case ABOUT_US:
                    startActivity(new Intent(this, AboutUsActivity.class));
                    break;

                case PERSONAL_INFO_ES:
                    startActivity(new Intent(this, PersonalInfoActivity.class));
                    break;
                case DELIVERY_ADDRESS_ES:
                    startActivity(new Intent(this, AddressListActivity.class));
                    break;
                case CLEAR_CACHE_ES:
                    String size1 = GlideCatchUtil.getInstanceContext().getCacheSize();
                    DialogConfigBean dialogConfigBean1 = DialogConfigBean.getDefaultDoubleConfig(this);
                    dialogConfigBean1.setContentText(getString(R.string.actual_cache_size) + size1 + getString(R.string.ask_sure_clean));
                    dialogConfigBean1.setRightClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tip(R.string.cleaned);
                            GlideCatchUtil.getInstanceContext().clearCacheDiskSelf();
                            dismissChooseDialog();
                        }
                    });
                    showOperationDialog(dialogConfigBean1);
                    break;
                case ABOUT_US_ES:
                    startActivity(new Intent(this, AboutUsActivity.class));
                    break;


            }

        }
    }

    @OnClick(R.id.log_off_bt)
    public void onViewClicked() {
        UserInfoManger.logOff();
        finish();
    }
}
