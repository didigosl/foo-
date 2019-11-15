package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.IntentUtils;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.login.LoginActivity;
import com.technology.greenenjoyshoppingstreet.login.bean.UserInfoBean;
import com.technology.greenenjoyshoppingstreet.mine.adapter.MenuAdapter;
import com.technology.greenenjoyshoppingstreet.mine.adapter.OrderListAdapter;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderStatus;
import com.technology.greenenjoyshoppingstreet.mine.bean.OrderStatusBean;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.newui.view.CouponActivity;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.ZhuanHuanUtil;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.info.PhoneInfoManager;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.HELP_CENTER;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.INVITE_FRIEND;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.MY_ACCOUNT;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.MY_COUPON;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.OPINION_FEEDBACK;
import static com.technology.greenenjoyshoppingstreet.mine.bean.MineMenuBean.PERSONAL_SETTING;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_GETINFO;

/**
 * Created by Administrator on 2017/12/10.
 */

public class MineActivity extends BaseActivity {
    @BindView(R.id.vip_tv)
    TextView vipTv;
    @BindView(R.id.user_iv)
    ImageView userIv;
    @BindView(R.id.order_gv)
    GridView orderGv;
    @BindView(R.id.menu_lv)
    ListViewInScroll menuLv;
    MenuAdapter minuAdapter;
    OrderListAdapter orderStatusAdapter;
    @BindView(R.id.user_name_tv)
    TextView userNameTv;
    @BindView(R.id.layout_user)
    LinearLayout layout_user;
    @BindView(R.id.head_rl)
    FrameLayout headRl;
    @BindView(R.id.layout_youhuiquan)
    LinearLayout layoutYouhuiquan;
    @BindView(R.id.layout_zhanghu)
    LinearLayout layoutZhanghu;
    @BindView(R.id.layout_bangzhu)
    LinearLayout layoutBangzhu;
    @BindView(R.id.layout_haoyou)
    LinearLayout layoutHaoyou;
    @BindView(R.id.layout_shezhi)
    LinearLayout layoutShezhi;
    @BindView(R.id.layout_yijian)
    LinearLayout layoutYijian;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UserInfoManger.isLogin()) {
            String url = UserInfoManger.getFace();
//            if (!TextUtils.isEmpty(url)) {
//                Glide.with(this).load(url)
//                        .into
//                                (userIv);
//            } else {
//                Glide.with(this).load(R.drawable.shape_defualt_bg)
//                        .into
//                                (userIv);
//            }
            ImageLoader.with_head(url, userIv);
            userNameTv.setText(UserInfoManger.getLoginName());
            vipTv.setVisibility(UserInfoManger.isVip() ? View.GONE : View.VISIBLE);
            getUserInfo();
        } else {
            vipTv.setVisibility(View.VISIBLE);
            userNameTv.setText(getString(R.string.login_register));
//            Glide.with(this).load(R.drawable.shape_defualt_bg)
//                    .into(userIv);
            userIv.setImageDrawable(ZhuanHuanUtil.getDrawable(R.drawable.ui55));
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
                        String url = UserInfoManger.getFace();
//                        if (!TextUtils.isEmpty(url)) {
//                            Glide.with(MineActivity.this).load(url)
//                                    .into
//                                            (userIv);
//                        } else {
//                            Glide.with(MineActivity.this).load(R.drawable.shape_defualt_bg)
//                                    .into
//                                            (userIv);
//                        }
                        ImageLoader.with_head(url, userIv);
                        userNameTv.setText(UserInfoManger.getLoginName());
                        vipTv.setVisibility(UserInfoManger.isVip() ? View.GONE : View.VISIBLE);

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

    private void initViews() {
        minuAdapter = new MenuAdapter(this);
        menuLv.setAdapter(minuAdapter);
        menuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleMenu(minuAdapter.getItem(position));
            }
        });
        ArrayList<String> menuList = new ArrayList<>();
        menuList.add(MY_COUPON);
        menuList.add(MY_ACCOUNT);
        menuList.add(PERSONAL_SETTING);
        menuList.add(OPINION_FEEDBACK);
        menuList.add(HELP_CENTER);
        menuList.add(INVITE_FRIEND);
        minuAdapter.updateData(menuList, true);
        orderStatusAdapter = new OrderListAdapter(this);

        orderGv.setAdapter(orderStatusAdapter);
        orderGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (UserInfoManger.isLogin()) {
                    OrderStatusBean orderStatusBean = orderStatusAdapter.getItem(position);
                    if (orderStatusBean != null) {
//                        Intent intent = new Intent(MineActivity.this, OrderListActivity.class);
//                        intent.putExtra(OrderListActivity.ORDER_TYPE, orderStatusBean.getOrderStatus());
//                        startActivity(intent);
                        StartActivityUtil.getInstance().goOrderList(MineActivity.this, orderStatusBean.getPosition());
                    }
                } else {
                    startActivity(new Intent(MineActivity.this, LoginActivity.class));
                }


            }
        });
        ArrayList<OrderStatusBean> orderStatusList = new ArrayList<>();
        if (PhoneInfoManager.isChineseLanguage()){
            orderStatusList.add(new OrderStatusBean(OrderStatus.PENDINGPAYMENT, "",1));
            orderStatusList.add(new OrderStatusBean(OrderStatus.TOBEDELIVERED, "",0));
            orderStatusList.add(new OrderStatusBean(OrderStatus.TOCONFIRMTHERECEIPT, "",2));
            orderStatusList.add(new OrderStatusBean(OrderStatus.REFUND, "",0));
            orderStatusList.add(new OrderStatusBean(OrderStatus.ALL_ORDER, "",0));
        }else {
            orderStatusList.add(new OrderStatusBean(OrderStatus.PENDINGPAYMENT_ES, "",1));
            orderStatusList.add(new OrderStatusBean(OrderStatus.TOBEDELIVERED_ES, "",0));
            orderStatusList.add(new OrderStatusBean(OrderStatus.TOCONFIRMTHERECEIPT_ES, "",2));
            orderStatusList.add(new OrderStatusBean(OrderStatus.REFUND_ES, "",0));
            orderStatusList.add(new OrderStatusBean(OrderStatus.ALL_ORDER_ES, "",0));
        }
        /*orderStatusList.add(new OrderStatusBean(OrderStatus.PENDINGPAYMENT, "", 1));
        orderStatusList.add(new OrderStatusBean(OrderStatus.TOBEDELIVERED, "", 0));
        orderStatusList.add(new OrderStatusBean(OrderStatus.TOCONFIRMTHERECEIPT, "", 2));
        orderStatusList.add(new OrderStatusBean(OrderStatus.REFUND, "", 0));
        orderStatusList.add(new OrderStatusBean(OrderStatus.ALL_ORDER, "", 0));*/
        orderStatusAdapter.updateData(orderStatusList, true);

    }

    private void handleMenu(String menuName) {
        if (!TextUtils.isEmpty(menuName)) {
            switch (menuName) {
                case MY_COUPON:
                    if (UserInfoManger.isLogin()) {
//                        startActivity(new Intent(this, MyCouponActivity.class));
                        StartActivityUtil.getInstance().startActivity(mActivity, CouponActivity.class);
                    } else {
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    break;
                case MY_ACCOUNT:
                    if (UserInfoManger.isLogin()) {
                        startActivity(new Intent(this, MyAccountActivity.class));

                    } else {
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    break;
                case PERSONAL_SETTING:
                    if (UserInfoManger.isLogin()) {
                        startActivity(new Intent(this, PersonalSettingActivity.class));

                    } else {
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    break;
                case OPINION_FEEDBACK:
                    if (UserInfoManger.isLogin()) {
                        startActivity(new Intent(this, OpinionFeedbackActivity.class));

                    } else {
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    break;
                case HELP_CENTER:
                    startActivity(new Intent(this, HelpCenterActivity.class));
                    break;
                case INVITE_FRIEND:
                    showShare();
//                    startActivity(new Intent(this, InviteFriendActivity.class));

                    break;


            }

        }

    }


    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//        oks.setTitle("标题");
//
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");

// 启动分享GUI
//        oks.show(this);
        Intent shareTextIntent = IntentUtils.getShareTextIntent("一品电器 http://food.oz24g.com/w/share/to/003186c18ab7554deb41c61715abea0b");
        startActivity(shareTextIntent);
    }

    @OnClick({R.id.vip_tv, R.id.layout_user, R.id.layout_youhuiquan, R.id.layout_zhanghu, R.id.layout_bangzhu, R.id.layout_haoyou, R.id.layout_shezhi, R.id.layout_yijian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_youhuiquan:
                if (UserInfoManger.isLogin()) {
//                    startActivity(new Intent(this, MyCouponActivity.class));
                    StartActivityUtil.getInstance().startActivity(mActivity, CouponActivity.class);
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.layout_zhanghu:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, MyAccountActivity.class));

                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.layout_bangzhu:
                startActivity(new Intent(this, HelpCenterActivity.class));
                break;
            case R.id.layout_haoyou:
                showShare();
                break;
            case R.id.layout_shezhi:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, PersonalSettingActivity.class));

                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.layout_yijian:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, OpinionFeedbackActivity.class));

                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.vip_tv:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, BuyVIPActivity.class));

                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.layout_user:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, PersonalInfoActivity.class));

                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }
}
