package com.technology.greenenjoyshoppingstreet.utils.net.core;


import android.content.Context;

import com.technology.greenenjoyshoppingstreet.utils.net.Canceller;
import com.technology.greenenjoyshoppingstreet.utils.net.NetResponseInfo;
import com.technology.greenenjoyshoppingstreet.utils.net.parameter.NetParameter;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;


/**
 * 网络请求引擎，实现类一般是各种网络框架okhttp,httpclient.
 *
 * @version V1.0
 */
public interface NetEngine {
    /**
     * Init context.
     *
     * @param context the context
     */
    void initContext(Context context);

     NetResponseInfo request(NetParameter params, NetRequestConfig.Method method);


    /**
     * Add task tag.
     *
     * @param taskTag the task tag
     */
    void addTaskTag(Object taskTag);

    /**
     * Cancel task.
     *
     * @param canceller the canceller
     */
    void cancelTask(Canceller canceller);


}
