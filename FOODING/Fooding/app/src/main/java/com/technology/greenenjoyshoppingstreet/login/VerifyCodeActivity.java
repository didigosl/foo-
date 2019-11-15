package com.technology.greenenjoyshoppingstreet.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.login.bean.LoginBean;
import com.technology.greenenjoyshoppingstreet.main.MainActivity;
import com.technology.greenenjoyshoppingstreet.main.bean.AdBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
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

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.COUNT_DOWN_OVER;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.MS_PER_SECOND;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.SECOND_PER_MINUTE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GET_PHONE_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.LOGIN;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class VerifyCodeActivity extends BaseActivity {

    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String PHONE_code = "PHONE_code";
    @BindView(R.id.send_hint_tv)
    TextView sendHintTv;
    @BindView(R.id.verify_pw)
    GridPasswordView verifyPw;
    @BindView(R.id.resend_tv)
    TextView resendTv;
    @BindView(R.id.resend_rl)
    RelativeLayout resendRl;
    @BindView(R.id.login_bt)
    Button loginBt;
    /**
     * 倒计时计时器.
     */
    private CountDownTimer timer;
    private String phoneNumber;
    private String codeString;
    @BindView(R.id.verify_code_hint)
    TextView verify_code_hint;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);
        codeString = getIntent().getStringExtra(PHONE_code);
        ButterKnife.bind(this);
        initViews();
        getIntentData();
        verify_code_hint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            phoneNumber = intent.getStringExtra(PHONE_NUMBER);
            if (!TextUtils.isEmpty(phoneNumber)) {
                sendHintTv.setText(getString(R.string.send_to)+" " + codeString + " " + phoneNumber + getString(R.string.verify_code));
                getCode();
            }
        }
    }

    /**
     * 倒计时时间展示.
     *
     * @param millisUntilFinished the millis until finished
     */
    public void calculateTimeDisplay(long millisUntilFinished) {
        long lastTime = millisUntilFinished / MS_PER_SECOND;
        if (millisUntilFinished > COUNT_DOWN_OVER) {

            resendTv.setText(lastTime +getString(R.string.scond_recover));
            resendRl.setEnabled(false);
        } else {
            resendTv.setText(getString(R.string.get_code_verify));
            resendRl.setEnabled(true);
        }
    }

    private void initViews() {
        setBarTitle("");
        verifyPw.setPasswordVisibility(true);
    }

    @OnClick({R.id.resend_rl, R.id.login_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.resend_rl:
                getCode();
                break;
            case R.id.login_bt:
                login();
                break;
        }
    }

    private void login() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<LoginBean>() {

            @Override
            public void onComplete(LoginBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        LoginBean.DataBean dataBean = bean.getData();
                        if (dataBean != null) {
                            UserInfoManger.login(bean.getData());
                            Tools.clearTopBack(VerifyCodeActivity.this, MainActivity.class);
                            Intent intent = new Intent();
                            intent.setClass(VerifyCodeActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra(MainActivity.LOGIN_STATUS, MainActivity.LOGIN_SUCCESS);
                            startActivity(intent);
                            finish();
                        }


                    } else {
                        tip(R.string.invalid_verify_code);
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
                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.PHONE, phoneNumber));
                list.add(new BasicKeyValuePair(ParameterConstant.CODE, verifyPw.getPassWord()));
                list.add(new BasicKeyValuePair("countryCode", codeString));
                return list;

            }


            @Override
            public String createUrl() {

                return LOGIN;
            }
        });

    }

    private void getCode() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AdBean>() {

            @Override
            public void onComplete(AdBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        if (timer != null) {
                            timer.cancel();
                        }
                        timer = new CountDownTimer(SECOND_PER_MINUTE
                                * MS_PER_SECOND,
                                MS_PER_SECOND) {

                            @Override
                            public void onTick(
                                    long millisUntilFinished) {
                                calculateTimeDisplay(millisUntilFinished);
                            }

                            @Override
                            public void onFinish() {
                                calculateTimeDisplay(COUNT_DOWN_OVER);

                            }
                        };
                        timer.start();
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
                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.PHONE, phoneNumber));
                list.add(new BasicKeyValuePair("countryCode", codeString));
                return list;
            }

            @Override
            public String createUrl() {
                return GET_PHONE_CODE;
            }
        });
    }
}
