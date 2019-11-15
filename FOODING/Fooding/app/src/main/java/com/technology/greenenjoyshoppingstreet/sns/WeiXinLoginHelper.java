package com.technology.greenenjoyshoppingstreet.sns;

import android.content.Context;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.sns.bean.AccessTokenBean;
import com.technology.greenenjoyshoppingstreet.sns.bean.WeiXinUserInfoBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/11/28.
 */
public class WeiXinLoginHelper {
    private IWXAPI mApi;
    private static final String WEIXIN_SCOPE = "snsapi_userinfo";// 用于请求用户信息的作用域
    private static final String WEIXIN_STATE = "login_state"; // 自定义
    private Context context;
    private AccessTokenBean accessTokenBean;
    private WeiXinUserInfoBean userInfoBean;

    //换起微信登录界面
    private void sendAuth() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = WEIXIN_SCOPE;
        req.state = WEIXIN_STATE;
        mApi.sendReq(req);
    }

    public WeiXinLoginHelper(Context context) {
        this.context = context;
//        this.loginCallBackI = loginCallBackI;
        mApi = WXAPIFactory.createWXAPI(context, Constant.APP_ID);
        mApi.registerApp(Constant.APP_ID);

    }


    //获取微信token
    private void doGetToken(final String code) {


        NetExcutor.executorCommonRequest(context, new CommonNetUIListener<AccessTokenBean>() {
            @Override
            public void onComplete(AccessTokenBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    accessTokenBean = bean;
                    String token = accessTokenBean.getAccess_token();
                    String openid = accessTokenBean.getOpenid();
                    doGetPerson(token, openid);
                }
            }

            @Override
            public String createUrl() {
                return String
                        .format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                                Constant.APP_ID, Constant.APP_KEY, code);
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.GET;
            }

            @Override
            public List<KeyValuePair> submitNetParams() {
                return new ArrayList<KeyValuePair>();
            }
        });

    }

    //获取微信的个人信息
    private void doGetPerson(final String token, final String openid) {
        NetExcutor.executorCommonRequest(context, new CommonNetUIListener<WeiXinUserInfoBean>() {
            @Override
            public void onComplete(WeiXinUserInfoBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    userInfoBean = bean;
//                    loginCallBackI.onLoginComplete(userInfoBean);

                }
            }

            @Override
            public String createUrl() {
                return String
                        .format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s",
                                token, openid);
            }

            @Override
            public List<KeyValuePair> submitNetParams() {
                return new ArrayList<KeyValuePair>();
            }
        });


    }

    public void login() {
        if (!mApi.isWXAppInstalled()) {
            ((BaseActivity) context).tip("您还未安装微信，请先安装");
        } else {
            sendAuth();
        }


    }


}
