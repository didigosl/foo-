package com.technology.greenenjoyshoppingstreet.mine;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.wheelpicker.WheelPicker;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.bean.AddressListBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.AreaListBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
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

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ADDRESS_UPDATE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.AREA_LIST;

/**
 * Created by Bern on 2017/12/13 0013.
 */

public class EditAddressActivity extends BaseActivity {

    public static final String ADDRESS_BEAN = "ADDRESS_BEAN";
    @BindView(R.id.recipients_et)
    EditText recipientsEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.province_tv)
    TextView provinceTv;
    @BindView(R.id.city_tv)
    TextView cityTv;
    @BindView(R.id.street_et)
    EditText streetEt;
    @BindView(R.id.select_cb)
    CheckBox selectCb;
    @BindView(R.id.save_bt)
    Button saveBt;
    @BindView(R.id.province_rl)
    FrameLayout provinceRl;
    @BindView(R.id.city_rl)
    FrameLayout cityRl;
    private AddressListBean.DataBean dataBean;


    @BindView(R.id.content_sv)
    ScrollView contentSv;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        getIntentData();
        getProvinceArea();

    }


    public void showSelectCity() {

        if (!isOver()) {

            if (chooseDialog != null && chooseDialog.isShowing()) {
                chooseDialog.dismiss();
            }

            chooseDialog = new Dialog(this, R.style.HintDialog);
            final View dialogChooseView = LayoutInflater.from(this).inflate(
                    R.layout.dialog_select_area, null);
            chooseDialog.setContentView(dialogChooseView);
            chooseDialog.setCancelable(true);
            TextView cancelTv = (TextView) dialogChooseView.findViewById(R.id.wheel_cancel_tv);
            cancelTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chooseDialog.dismiss();
                }
            });


            final WheelPicker provinceWp = (WheelPicker) dialogChooseView.findViewById(R.id.province_wp);
            final WheelPicker cityWp = (WheelPicker) dialogChooseView.findViewById(R.id.city_wp);
            provinceWp.setData(convertStrList(provinceList));
            AreaListBean.DataBean dataBean = provinceList.get(0);
            if (dataBean != null && dataBean.getSons() != null && !dataBean.getSons().isEmpty()) {
                cityWp.setData(dataBean.getSons());
            } else {
                cityWp.setData(new ArrayList<String>());

            }
            provinceWp.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                @Override
                public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
                    AreaListBean.DataBean provinceTempBean = provinceList.get(i);
                    if (provinceTempBean != null) {
                        cityWp.setData(convertStrList(provinceTempBean.getSons()));
                        cityWp.setSelectedItemPosition(0);
                        cityWp.run();
                    }
                }
            });

            cityWp.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
                @Override
                public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
//                    selectCitPosition = i;
//                    AreaListBean.DataBean dataBean = provinceList.get(selectProvincePosition);
//                    if (dataBean != null) {
//                        List<AreaListBean.DataBean> sons = dataBean.getSons();
//                        if (sons != null && !sons.isEmpty()) {
//                            cityBean = sons.get(i);
//                            refreshPC();
//
//                        }
//
//
//                    }
                }
            });

            TextView finishTv = (TextView) dialogChooseView.findViewById(R.id.wheel_finish_tv);
            finishTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AreaListBean.DataBean provinceBean = provinceList.get(provinceWp.getCurrentItemPosition());
                    if (provinceBean != null) {
                        provinceId = provinceBean.getAreaId();
                        provinceTv.setText(provinceBean.getName());
                        if (provinceBean.getSons() != null && !provinceBean.getSons().isEmpty()) {
                            AreaListBean.DataBean cityBean = provinceBean.getSons().get(cityWp.getCurrentItemPosition());
                            if (cityBean != null) {
                                cityId = cityBean.getAreaId();
                                cityTv.setText(cityBean.getName());

                            } else {
                                cityId = "";
                                cityTv.setText("");
                            }

                        } else {
                            cityId = "";
                            cityTv.setText("");
                        }

                    }
                    dismissChooseDialog();


                }
            });
            chooseDialog.setCanceledOnTouchOutside(true);
            Window window = chooseDialog.getWindow();
            if (window != null) {
                // 设置大小
                WindowManager.LayoutParams layoutParams = window
                        .getAttributes();
                DisplayMetrics dm = new DisplayMetrics();
                // 取得窗口属性
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                // 设置动画
                window.setWindowAnimations(R.style.dialogWindowAnim);
                // 窗口的宽度
                int screenWidth = dm.widthPixels;
                layoutParams.width = screenWidth;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                layoutParams.gravity = Gravity.BOTTOM;
                window.setAttributes(layoutParams);
            }
            chooseDialog.show();


        }
    }


    @OnClick({R.id.province_rl, R.id.city_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.province_rl:
                hideKeybroad();
                showSelectCity();
                break;
            case R.id.city_rl:
                hideKeybroad();
                showSelectCity();
                break;

        }
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {

            dataBean = intent.getParcelableExtra(ADDRESS_BEAN);
            if (dataBean != null) {
                recipientsEt.setText(dataBean.getMan());
                phoneEt.setText(dataBean.getPhone());
                streetEt.setText(dataBean.getAddress());
                selectCb.setChecked(dataBean.isDefaultAddress());
                provinceTv.setText(dataBean.getProvince());
                cityTv.setText(dataBean.getCity());

                cityId = dataBean.getCityId();
                provinceId = dataBean.getProvinceId();
                setBarTitle(getString(R.string.edit_address));
            } else {
                setBarTitle(getString(R.string.add_address));
            }
        }
    }


    private List<AreaListBean.DataBean> provinceList = new ArrayList<>();

    private List<String> convertStrList(List<AreaListBean.DataBean> dataList) {
        ArrayList<String> result = new ArrayList<String>();

        if (dataList != null && !dataList.isEmpty()) {
            for (AreaListBean.DataBean dataBean : dataList) {
                result.add(dataBean.getName());

            }

        }
        return result;


    }


    private String provinceId;
    private String cityId;

    @OnClick(R.id.save_bt)
    public void onViewClicked() {
        addAddress();

    }


    private void getProvinceArea() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AreaListBean>() {

            @Override
            public void onComplete(AreaListBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        provinceList.clear();
                        List<AreaListBean.DataBean> list = bean.getData();
                        if (list != null && !list.isEmpty()) {
                            provinceList.addAll(list);


                        }


                    } else {
                        tip(netRequestStatus.getNote());

                    }

                }
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                showLoadingDialog();

                List<KeyValuePair> list = new ArrayList<>();
                return UserInfoManger.getSignList(list);

            }


            @Override
            public String createUrl() {

                return AREA_LIST;
            }
        });

    }

    private void addAddress() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AreaListBean>() {

            @Override
            public void onComplete(AreaListBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        if (dataBean != null) {
                            tip(R.string.edit_successfull);
                        } else {
                            tip(R.string.add_sucessfull);
                        }
                        finish();

                    } else {
                        tip(bean.getMsg());
                    }


                } else {
                    tip(netRequestStatus.getNote());

                }

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                if (!TextUtils.isEmpty(streetEt.getText())) {

                    if (!TextUtils.isEmpty(recipientsEt.getText())) {

                        if (!TextUtils.isEmpty(phoneEt.getText())) {

                            if (!TextUtils.isEmpty(provinceId) && !TextUtils.isEmpty(cityId)) {
                                showLoadingDialog();
                                List<KeyValuePair> list = new ArrayList<>();
                                list.add(new BasicKeyValuePair(ParameterConstant.PROVINCEID, provinceId));
                                list.add(new BasicKeyValuePair(ParameterConstant.CITYID, cityId));
                                list.add(new BasicKeyValuePair(ParameterConstant.ADDRESS, streetEt.getText()
                                        .toString()));
                                list.add(new BasicKeyValuePair(ParameterConstant.MAN, recipientsEt.getText
                                        ().toString()));
                                if (dataBean != null) {
                                    list.add(new BasicKeyValuePair(ParameterConstant.ADDRESSID, dataBean
                                            .getAddressId()));
                                }
                                list.add(new BasicKeyValuePair(ParameterConstant.PHONE, phoneEt
                                        .getText().toString()));
                                list.add(new BasicKeyValuePair(ParameterConstant.DEFAULT_FLAG,
                                        selectCb.isChecked() ? AddressListBean
                                                .DEFAULT_ADDRESS : AddressListBean.NO_DEFAULT_ADDRESS));
                                return UserInfoManger.getSignList(list);
                            } else {
                                tip(R.string.select_region);
                            }


                        } else {
                            tip(R.string.enter_phone);
                        }


                    } else {
                        tip(R.string.enter_reciver);
                    }


                } else {
                    tip(R.string.complete_address);
                }

                return null;


            }


            @Override
            public String createUrl() {

                return ADDRESS_UPDATE;
            }
        });


    }


}
