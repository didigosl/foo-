package com.technology.greenenjoyshoppingstreet.utils.net.listener;


import com.technology.greenenjoyshoppingstreet.utils.net.parameter.NetParameter;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public abstract  class CommonNetUIListener<T>  extends NetUIListener<T> {
    @Override
    public NetParameter createNetParams() {
        return new NetParameter(createUrl(),submitNetParams(),"application/json");
    }





    /**
     * 提交网络请求参数
     * return NULL，不发送网络请求，也不会有结果回调
     * UI线程运行
     *
     * @return the common params bean
     */
    public abstract Object submitNetParams();

}
