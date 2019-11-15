package com.technology.greenenjoyshoppingstreet.login;


import com.technology.greenenjoyshoppingstreet.BaseActivity;

/**
 * Created by Administrator on 2017/8/16.
 */

public class ForgetPasswordActivity extends BaseActivity {

//    @BindView(R.id.phone_et)
//    EditText phoneEt;
//    @BindView(R.id.get_sms_code_tv)
//    TextView getSmsCodeTv;
//    @BindView(R.id.sms_code_et)
//    EditText smsCodeEt;
//    @BindView(R.id.new_password_et)
//    EditText newPasswordEt;
//    @BindView(R.id.confirm_password_et)
//    EditText confirmPasswordEt;
//    @BindView(reset_password_bt)
//    Button resetPasswordBt;
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
//        setContentView(R.layout.activity_forget_password);
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
//    @OnClick({R.id.get_sms_code_tv, R.id.register_bt})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.get_sms_code_tv:
//                getIdentifyingCode();
//                break;
//            case reset_password_bt:
//                resetPassword();
//                break;
//        }
//    }
//
//
//    private void resetPassword() {
//        NetExcutor.executorCommonRequest(this, new SafeNetUIListener<BaseBean>() {
//
//            @Override
//            public void onComplete(BaseBean bean, NetUtils.NetRequestStatus netRequestStatus) {
//                cancelLoadingDialog();
//                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
//                    if (Constant.RESULT_OK.equals(bean.getSuccess())) {
//
//                        tip("修改成功");
//                        finish();
//                    } else {
//                        tip(bean.getMessage());
//                    }
//
//                } else {
//                    tip(netRequestStatus.getNote());
//
//                }
//            }
//
//            @Override
//            public Object submitNetParams() {
//                if (InputCheckUtil.checkPhoneNumber(phoneEt.getText().toString())) {
//
//                    if (!TextUtils.isEmpty(smsCodeEt.getText())) {
//                        if (!TextUtils.isEmpty(newPasswordEt.getText().toString())) {
//                            if (!TextUtils.isEmpty(confirmPasswordEt.getText().toString())) {
//
//
//                                if (TextUtils.equals(newPasswordEt.getText().toString(), confirmPasswordEt.getText().toString())) {
//
//
//                                    showLoadingDialog();
//                                    CommonParameter commonParameter = new CommonParameter();
//                                    commonParameter.setMobile(phoneEt.getText().toString());
//                                    commonParameter.setVercode(smsCodeEt.getText().toString());
//                                    commonParameter.setNewpassword(newPasswordEt.getText().toString());
//                                    return commonParameter;
//
//                                } else {
//                                    tip("新密码和确认密码请保持一致");
//                                }
//                            } else {
//
//                                tip("确认密码不能为空");
//                            }
//
//                        } else {
//
//                            tip("新密码不能为空");
//                        }
//
//
//                    } else {
//                        tip("请输入短信验证码");
//                    }
//
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
//                return RESET_PASSWORD;
//            }
//        });
//
//
//    }
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

}
