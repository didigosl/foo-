package com.technology.greenenjoyshoppingstreet.utils.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.technology.greenenjoyshoppingstreet.utils.LogUtils;
import com.technology.greenenjoyshoppingstreet.utils.net.core.OkHttpNetEngine;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.NetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.parser.JsonDataParser;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.task.CommonNetTask;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.URLEncodedUtils;

import java.util.List;


/**
 * The Class CttripNetExcutor.
 *
 * @author OneOfBillions
 * @version 1.0
 *          2015 -11-5
 */
public class NetExcutor {
    /**
     * The Constant TAG.
     */
    public static final String TAG = NetExcutor.class.getSimpleName();


    /**
     * Instantiates a new cttrip net excutor.
     */
    private NetExcutor() {
        super();

    }


    public static String createGetUrl(String url, List<KeyValuePair> list) {
        if (!TextUtils.isEmpty(url)) {

            if (list != null && !list.isEmpty()) {
                url += "?"
                        + URLEncodedUtils.format(list, "UTF-8");
            }
        }
        return url;
    }

    private static String hello = "hello";

    public static String createGetUrl(String url, Object list) {
        if (!TextUtils.isEmpty(url)) {

            if (list != null && list instanceof List) {
                try {
                    url += "?"
                            + URLEncodedUtils.format((List<KeyValuePair>) list, "UTF-8");
                } catch (Exception e) {

                    LogUtils.log(NetUtils.TAG,"get请求URL生成出错:"+e.toString());
                }

            }
        }
        return url;
    }

    /**
     * <pre>
     *
     * 通用的网络请求实现方法。
     *
     * 网络引擎:OkHttpNetEngine
     *
     * 返回数据解析:JsonDataParser
     *
     * 网络方式:post
     *
     * </pre>
     *
     * @param <T>        the type parameter
     * @param context    the context
     * @param uiListener the ui listener
     * @return the canceller
     */
    public static <T> Canceller executorCommonRequest(final Context context,
                                                      final NetUIListener<T> uiListener) {
        return executorCommonRequest(context, uiListener, NetOptions.DEFAULT);
    }

    /**
     * <pre>
     *
     * 通用的网络请求实现方法。
     *
     * 网络引擎:{@link OkHttpNetEngine}
     *
     * 返回数据解析:{@link JsonDataParser}
     *
     * 网络方式: POST {@link NetRequestConfig.Method}
     *
     *
     * </pre>
     *
     * @param <T>        the type parameter
     * @param context    the context
     * @param uiListener the ui listener
     * @param options    the options
     * @return the canceller
     */
    public static <T> Canceller executorCommonRequest(final Context context,
                                                      final NetUIListener<T> uiListener, final NetOptions
                                                              options) {
        Canceller canceller = new Canceller(null);
        try {
            if (uiListener != null) {
                OkHttpNetEngine engine = new OkHttpNetEngine(context);//网络引擎
                NetRequestConfig.Builder builder = new NetRequestConfig.Builder();//设置请求参数
                builder.setContext(context);
                builder.setNetUIListener(uiListener);
                builder.setOptions(options);
                builder.setNetEngine(engine);
                builder.setMethod(uiListener.getMethod());
                builder.setDataParser(new JsonDataParser());
                canceller = new Canceller(engine);
                new CommonNetTask(builder.build()).start();
            }
        } catch (Exception e) {

            Log.d(NetUtils.TAG, "网络请求错误: " + e.toString());
        }


        return canceller;

    }


    /**
     * @version V1.0
     *          2016.08.15
     */
    public enum RunMode {
        /**
         * Current thread.
         */
        CURRENT_THREAD, /**
         * Single thread pool.
         */
        SINGLE_THREAD_POOL, /**
         * Mutiple thread pool.
         */
        MUTIPLE_THREAD_POOL

    }


}
