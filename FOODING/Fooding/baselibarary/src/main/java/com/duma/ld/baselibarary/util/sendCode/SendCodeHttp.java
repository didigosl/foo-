package com.duma.ld.baselibarary.util.sendCode;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.duma.ld.baselibarary.model.HttpResModel;
import com.lzy.okgo.request.GetRequest;


/**
 * Created by liudong on 2018/3/26.
 */

public class SendCodeHttp {
    private SendCodeUtil sendCodeUtil;
    private Activity mActivity;
    private OnSendSuccessListener onSendSuccessListener;
    private int type = 0;// 0.运输端注册 1 忘记密码 2用户端注册

    public SendCodeHttp(TextView codeText, EditText editTextPhone, Activity mActivity, OnSendSuccessListener onSendSuccessListener) {
        this.mActivity = mActivity;
        this.onSendSuccessListener = onSendSuccessListener;
        sendCodeUtil = new SendCodeUtil(codeText, editTextPhone, new OnSendHttpListener() {
            @Override
            public void onClick(String phone) {
                sendCode(phone);
            }
        });
    }

    public void setType(int type) {
        this.type = type;
    }

    private void sendCode(String phone) {
        GetRequest<HttpResModel<String>> httpResModelGetRequest;
//        if (type == 1) {
//            // 忘记密码
//            httpResModelGetRequest = OkGo.<HttpResModel<String>>get(sendCodeForTransportyForgetPwd);
//        } else if (type == 2) {
//            //用户端注册
//            httpResModelGetRequest = OkGo.<HttpResModel<String>>get(appUserSendCode);
//        } else {
//            httpResModelGetRequest = OkGo.<HttpResModel<String>>get(sendCode);
//        }
//        httpResModelGetRequest
//                .tag(mActivity)
//                .params("telephone", phone)
//                .execute(new MyJsonCallback<HttpResModel<String>>() {
//                    @Override
//                    protected void onJsonSuccess(Response<HttpResModel<String>> respons, HttpResModel<String> listHttpResModel) {
//                        sendCodeUtil.starTime(Constants.SendCode);
//                        onSendSuccessListener.onSuccess(listHttpResModel.getData());
//                    }
//                }.isDialog(mActivity));
    }

    public void destroy() {
        sendCodeUtil.destroy();
    }
}
