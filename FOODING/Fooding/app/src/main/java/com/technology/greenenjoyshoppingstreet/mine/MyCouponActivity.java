package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.adapter.CouponAdapter;
import com.technology.greenenjoyshoppingstreet.mine.bean.MyCouponBean;
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

import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.COUPON_BEAN;
import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.SELECTCOUPON_SUCCESS;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.COUPON_LIST;

/**
 * Created by Administrator on 2017/12/12.
 */

public class MyCouponActivity extends BaseActivity {
    public static final String GET_COUPON = "GET_COUPON";
    List<MyCouponBean.DataBean.ListBean> conpouList = new ArrayList<>();
    @BindView(R.id.data_lv)
    ListView dataLv;

    CouponAdapter couponAdapter;

    private boolean getCoupon;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    private void getIntentData() {
        conpouList.clear();
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<MyCouponBean.DataBean.ListBean> getCouponList = intent.getParcelableArrayListExtra(GET_COUPON);
            if (getCouponList != null) {
                getCoupon = true;
                conpouList.addAll(getCouponList);
            }
        }
        couponAdapter.updateData(conpouList,true);
        if(!getCoupon){
            getMyCoupon();
        }

    }

    private void initViews() {
        setBarTitle(getString(R.string.my_coupon));
        couponAdapter = new CouponAdapter(this);
        dataLv.setAdapter(couponAdapter);
        dataLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getCoupon) {
                    MyCouponBean.DataBean.ListBean data = couponAdapter.getItem(position);
                    if (data != null) {

                        Intent intent = new Intent();
                        intent.putExtra(COUPON_BEAN, data);
                        setResult(SELECTCOUPON_SUCCESS, intent);
                        finish();
                    }


                }
            }
        });
    }


    private void getMyCoupon() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<MyCouponBean>() {

            @Override
            public void onComplete(MyCouponBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        MyCouponBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {
                            couponAdapter.updateData(dataBean.getList(), true);
                        }


                    } else {
                        tip(bean.getMsg());
                    }


                } else {
                    tip(netRequestStatus.getNote());

                }
                cancelLoadingDialog();
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                showLoadingDialog();
                ArrayList<KeyValuePair> list = new ArrayList<KeyValuePair>();
                return UserInfoManger.getSignList(list);


            }


            @Override
            public String createUrl() {

                return COUPON_LIST;
            }
        });

    }
}
