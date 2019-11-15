package com.technology.greenenjoyshoppingstreet.login;


import com.technology.greenenjoyshoppingstreet.BaseActivity;

/**
 * Created by Administrator on 2017/8/16.
 */

public class RegisterActivity extends BaseActivity {
//
//    @BindView(R.id.phone_et)
//    EditText phoneEt;
//    @BindView(R.id.iden_card_et)
//    EditText idenCardEt;
//    @BindView(R.id.password_et)
//    EditText passwordEt;
//    @BindView(R.id.uername_et)
//    EditText uernameET;
//    @BindView(R.id.confirm_password_et)
//    EditText confirmPasswordEt;
//    @BindView(R.id.get_sms_code_tv)
//    TextView getSmsCodeTv;
//    @BindView(R.id.sms_code_et)
//    EditText smsCodeEt;
//    @BindView(R.id.register_bt)
//    Button registerBt;
//    /**
//     * 倒计时计时器.
//     */
//    private CountDownTimer timer;
//
//    /**
//     * On create.
//     *
//     * @param savedInstanceState the saved instance state
//     */
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        ButterKnife.bind(this);
//
//    }
//
//    /**
//     * 倒计时时间展示.
//     *
//     * @param millisUntilFinished the millis until finished
//     */
//    public void calculateTimeDisplay(long millisUntilFinished) {
//        long lastTime = millisUntilFinished / MS_PER_SECOND;
//        if (millisUntilFinished > COUNT_DOWN_OVER) {
//            getSmsCodeTv.setText(lastTime + "秒重新获取");
//            getSmsCodeTv.setEnabled(false);
//        } else {
//            getSmsCodeTv.setText("获取验证码");
//            getSmsCodeTv.setEnabled(true);
//        }
//    }
//
//
//    /**
//     * Gets identifying code.
//     */
//    private void getIdentifyingCode() {
//        NetExcutor.executorCommonRequest(this, new SafeNetUIListener<BaseBean>() {
//
//            @Override
//            public void onComplete(BaseBean bean, NetUtils.NetRequestStatus netRequestStatus) {
//                cancelLoadingDialog();
//                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
//                    if (Constant.RESULT_OK.equals(bean.getSuccess())) {
//                        if (timer != null) {
//                            timer.cancel();
//                        }
//                        timer = new CountDownTimer(SECOND_PER_MINUTE
//                                * MS_PER_SECOND,
//                                MS_PER_SECOND) {
//
//                            @Override
//                            public void onTick(
//                                    long millisUntilFinished) {
//                                calculateTimeDisplay(millisUntilFinished);
//                            }
//
//                            @Override
//                            public void onFinish() {
//                                calculateTimeDisplay(COUNT_DOWN_OVER);
//
//                            }
//                        };
//                        timer.start();
//                    } else {
//                        tip(bean.getMessage());
//                    }
//
//
//                } else {
//                    tip(netRequestStatus.getNote());
//
//                }
//            }
//
//            @Override
//            public Object submitNetParams() {
//
//                String phoneNumber = phoneEt.getText().toString();
//
//                if (checkPhoneNumber(phoneNumber)) {
//                    showLoadingDialog();
//                    CommonParameter commonParameter = new CommonParameter();
//                    commonParameter.setMobile(phoneNumber);
//                    commonParameter.setVercode("0000");
//                    return commonParameter;
//                } else {
//                    tip("请输入正确的手机号码");
//                }
//
//                return null;
//
//            }
//
//
//            @Override
//            public String createUrl() {
//                return DEAL_VERCODE;
//            }
//        });
//    }
//
//    /**
//     * Register.
//     */
//
//    private void register() {
//        NetExcutor.executorCommonRequest(this, new SafeNetUIListener<RegisterBean>() {
//            @Override
//            public String createUrl() {
//                return REGISTER;
//            }
//
//            @Override
//            public void onComplete(RegisterBean bean, NetUtils.NetRequestStatus netRequestStatus) {
//                cancelLoadingDialog();
//                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
//                    if (Constant.RESULT_OK.equals(bean.getSuccess())) {
//                        tip("注册成功");
//                        finish();
//                    } else {
//                        showChooseSingleDialog(bean.getMessage(), "确定", null, true);
//                    }
//
//                } else {
//                    tip(netRequestStatus.getNote());
//                }
//
//            }
//
//            @Override
//            public Object submitNetParams() {
//
//                String phoneNumber = phoneEt.getText().toString();
//                String idCard = idenCardEt.getText().toString();
//                String identifyingCode = smsCodeEt.getText().toString();
//                String password = passwordEt.getText().toString();
//                String confirmPassword = confirmPasswordEt.getText().toString();
//
//                if (InputCheckUtil.checkPhoneNumber(phoneNumber)) {
//                    if (!TextUtils.isEmpty(idCard) && IDCardUtil.IDCardValidate
//                            (idCard.toLowerCase())) {
//
//                        if (!TextUtils.isEmpty(identifyingCode)) {
//
//                            if (!TextUtils.isEmpty(uernameET.getText().toString())) {
//                                if (!TextUtils.isEmpty(password)) {
//
//                                    if (!TextUtils.isEmpty(password)) {
//                                        if (TextUtils.equals(password, confirmPassword)) {
//                                            showLoadingDialog();
//                                            CommonParameter commonParameter = new CommonParameter();
//                                            commonParameter.setMobile(phoneNumber);
//                                            commonParameter.setIDNumber(idCard.toUpperCase());
//                                            commonParameter.setUserName(uernameET.getText().toString());
//                                            commonParameter.setPassword(password);
//                                            commonParameter.setSmsCode(identifyingCode);
//
//                                            return commonParameter;
//                                        } else {
//                                            tip("请保证密码一致");
//                                        }
//
//
//                                    } else {
//                                        tip("请输入确认密码");
//                                    }
//
//                                } else {
//                                    tip("请输入密码");
//                                }
//
//                            } else {
//                                tip("用户名不能为空");
//                            }
//
//
//                        } else {
//                            tip("请输入短信验证码");
//                        }
//                    } else {
//                        tip("请输入正确的身份证号码");
//                    }
//                } else {
//                    tip("请输入正确的手机号码");
//                }
//
//                return null;
//
//            }
//        });
//
//    }
//
//    @OnClick({R.id.get_sms_code_tv, R.id.register_bt})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.get_sms_code_tv:
//                getIdentifyingCode();
//                break;
//            case R.id.register_bt:
//                register();
//                break;
//        }
//    }
}
