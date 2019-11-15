package com.technology.greenenjoyshoppingstreet.utils.net.task;


import android.text.TextUtils;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.NetResponseInfo;
import com.technology.greenenjoyshoppingstreet.utils.net.core.NetEngine;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.NetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.parameter.NetParameter;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.lang.reflect.Type;

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.USER_AUTH_FAILED;

/**
 * 通用的网络任务.
 *
 * @version V1.0
 *          2016.12.07
 */
public class CommonNetTask extends NetTask {
    /**
     * Instantiates a new Common net task.
     *
     * @param netRequest the net request
     */
    public CommonNetTask(NetRequestConfig netRequest) {
        super(netRequest);
    }

    /**
     * Run.
     */
    @Override
    public void run() {
        Object responseBean = null;
        NetUtils.NetRequestStatus netRequestStatus = NetUtils.NetRequestStatus.SERVER_ERROR;
        NetEngine engine = getNetEngine();


        NetRequestConfig netRequest = getNetRequest();
        NetResponseInfo netResponseInfo;
        NetRequestConfig.Method method = netRequest.getMethod();
        NetParameter netParameter = getParameter();
        if (netParameter.isReadExecute()) {
            netResponseInfo = engine.request
                    (getParameter(), netRequest.getMethod());
            String resultStr = null;
            NetUIListener netUIListener = getNetUiListener();
            Type type = netUIListener.getType();
            if (netResponseInfo != null) {

                if (netResponseInfo.isSuccess()) {
                    resultStr = netResponseInfo.getResult();
                    netUIListener.setOriginalData(resultStr);//保存原始字符
//                    LogUtils.log(NetUtils.TAG, "请求编号:" + netUIListener.getID(), resultStr);
                    responseBean = netRequest.getDataParser().parse(resultStr, type);
                    if (responseBean != null) {
                        netRequestStatus = NetUtils.NetRequestStatus.SUCCESS;
                        if (responseBean instanceof BaseBean) {
                            if (TextUtils.equals(((BaseBean) responseBean).getCode(), USER_AUTH_FAILED)) {
                                UserInfoManger.logOff();
                            }

                        }
                    } else {
                        netRequestStatus = NetUtils.NetRequestStatus.SERVER_ERROR;
                    }

                    callback(netRequestStatus, responseBean);
                } else if ("401".equals(netResponseInfo.getHttpCode())) {
                    UserInfoManger.logOff();
                } else {
                    netRequestStatus = NetUtils.NetRequestStatus.NET_ERROR;
                    callback(netRequestStatus, responseBean);
                }
            } else {
                netRequestStatus = NetUtils.NetRequestStatus.NET_ERROR;
                callback(netRequestStatus, responseBean);
            }
        }

    }
}
