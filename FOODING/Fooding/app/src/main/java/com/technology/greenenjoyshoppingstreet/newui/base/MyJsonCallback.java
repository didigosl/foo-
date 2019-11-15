package com.technology.greenenjoyshoppingstreet.newui.base;

import android.app.Activity;

import com.duma.ld.baselibarary.base.Http.JsonCallback;
import com.duma.ld.baselibarary.util.LoadingUtil;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger.formatList;
import static com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger.getSecretKey;
import static com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger.getToken;
import static com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger.isLogin;

/**
 * Created by liudong on 2017/6/5.
 */

public abstract class MyJsonCallback<T> extends JsonCallback<T> {
    private PublicConfig mPublicConfig;
    private Activity mActivity;

    public MyJsonCallback(PublicConfig mPublicConfig) {
        this.mPublicConfig = mPublicConfig;
    }

    public MyJsonCallback() {
    }

    public MyJsonCallback<T> isDialog(Activity activity) {
        mActivity = activity;
        return this;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        showLoading();
        if (isLogin()) {
            HttpParams params = request.getParams();
            List<KeyValuePair> list = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : params.urlParamsMap.entrySet()) {
                list.add(new BasicKeyValuePair(entry.getKey(), entry.getValue().get(0)));
            }
            Collections.sort(list, new Comparator<KeyValuePair>() {
                public int compare(KeyValuePair arg0, KeyValuePair arg1) {
                    return arg0.getKey().compareTo(arg1.getKey());
                }
            });
            long second = System.currentTimeMillis() / 1000;

            String string = formatList(list)
                    + getSecretKey()
                    + second;
            String md5 = Tools.convertMD5(string);
            params.put(ParameterConstant.SIGN, md5);
            params.put(ParameterConstant.TOKEN, getToken());
            params.put(ParameterConstant.TIME, String.valueOf(second));
        }
    }

    @Override
    public void onSuccess(Response<T> response) {
        showContent();
        if (mPublicConfig != null) {
            mPublicConfig.setHttpSuccess(true);
        }
        if (mActivity != null) {
            LoadingUtil.getInstance().hide();
        }
        super.onSuccess(response);
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        showError();
    }

    private void showLoading() {
        if (mPublicConfig != null) {
            mPublicConfig.showLoading();
        }
        if (mActivity != null) {
            LoadingUtil.getInstance().show_noBack(mActivity);
        }
    }

    private void showContent() {
        if (mPublicConfig != null) {
            mPublicConfig.showContent();
        }
    }

    private void showError() {
        if (mPublicConfig != null) {
            mPublicConfig.showError();
        }
        LoadingUtil.getInstance().hide();
    }

}
