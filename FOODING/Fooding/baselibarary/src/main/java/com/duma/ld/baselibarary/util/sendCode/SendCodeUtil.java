package com.duma.ld.baselibarary.util.sendCode;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.duma.ld.baselibarary.R;
import com.duma.ld.baselibarary.util.Ts;
import com.duma.ld.baselibarary.util.ZhuanHuanUtil;

/**
 * 发送短信
 * Created by liudong on 2017/12/7.
 */

public class SendCodeUtil {
    private TextView mCodeText;
    private EditText mEditText;
    private String defaultString = "获取验证码";
    private CountDownTimer countDownTimer;
    private OnSendHttpListener onSendHttpListener;


    public SendCodeUtil(final TextView codeText, EditText editTextPhone, OnSendHttpListener onSendHttpListener) {
        this.mCodeText = codeText;
        this.mEditText = editTextPhone;
        this.onSendHttpListener = onSendHttpListener;
        ininData();
    }

    public SendCodeUtil(final TextView codeText, OnSendHttpListener onSendHttpListener) {
        this.mCodeText = codeText;
        this.onSendHttpListener = onSendHttpListener;
        ininData();
    }

    private void ininData() {
        initTextView();
        mCodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //能不能发送
                if (mCodeText.getText().toString().equals(defaultString)) {
                    //有手机编辑框 就验证手机号码
                    String editPhone = null;
                    if (mEditText != null) {
                        editPhone = mEditText.getText().toString();
                        if (!RegexUtils.isMobileExact(editPhone)) {
                            Ts.show("请输入正确的手机号码!");
                            return;
                        }
                    }
                    if (onSendHttpListener != null) {
                        onSendHttpListener.onClick(editPhone);
                    }
                } else {
                    Ts.show("请稍后再试!");
                }
            }
        });

    }

    private void initTimer(int time) {
        countDownTimer = new CountDownTimer(time * 1000, 1 + 1000) {
            @Override
            public void onTick(long l) {
                mCodeText.setText("重新发送(" + (int) (l / 1000) + "s)");
                mCodeText.setTextColor(ZhuanHuanUtil.getColor(R.color.textColorHint));
            }

            @Override
            public void onFinish() {
                initTextView();
            }
        };
    }


    private void initTextView() {
        mCodeText.setText(defaultString);
        mCodeText.setTextColor(ZhuanHuanUtil.getColor(R.color.colorPrimary));
    }


    public void starTime(int time) {
        initTextView();
        if (countDownTimer == null) {
            initTimer(time);
        }
        countDownTimer.start();
    }

    public void destroy() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

}
