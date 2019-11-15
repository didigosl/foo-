package com.technology.greenenjoyshoppingstreet.utils.net.listener;


import com.technology.greenenjoyshoppingstreet.utils.DateUtils;
import com.technology.greenenjoyshoppingstreet.utils.LogUtils;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.parameter.NetParameter;
import com.technology.greenenjoyshoppingstreet.utils.net.safe.RsaDesPipeline;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.GsonUtil;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;
import java.util.List;

import static com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils.LOG_BLANK;

/**
 * Created by Administrator on 2017/8/16.
 */

public abstract class SafeNetUIListener<T> extends NetUIListener<T> {


    private RsaDesPipeline rsaDesPipeline = new RsaDesPipeline();

    @Override
    public NetParameter createNetParams() {
        Object object = submitNetParams();
        if (object != null) {
            String jsonStr = GsonUtil.fromObjectToJsonString(object);
            String encodeJson = rsaDesPipeline.encode(jsonStr);
            List<KeyValuePair> submitList = new ArrayList<>();
            submitList.add(new BasicKeyValuePair("subData", encodeJson));
            submitList.add(new BasicKeyValuePair("subTime", DateUtils.getSubTime()));
            LogUtils.log(NetUtils.TAG, "请求编号:" + getID() + LOG_BLANK + "请求URL: " + createUrl(), jsonStr, "加密后:" + encodeJson);
            ;
            return new NetParameter(createUrl(), submitList, "application/x-www-form-urlencoded");
        } else {
            return new NetParameter(createUrl(), object, "application/x-www-form-urlencoded");
        }
    }



}
