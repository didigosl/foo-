package com.technology.greenenjoyshoppingstreet.utils;

import com.technology.greenenjoyshoppingstreet.BuildConfig;

import cc.tracyzhang.awesomelogger.ALogger;

/**
 * Created by Administrator on 2017/8/16.
 */
public class LogUtils {
    private static final String TAG = LogUtils.class.getSimpleName();
    private static Object lock = new Object();

    public static void log(Object... log) {
        if (BuildConfig.DEBUG) {
            synchronized (lock) {
                ALogger.d(TAG, log.toString());
            }
        }


    }

    public static void log(String tag, Object... log) {
        if (BuildConfig.DEBUG) {
            synchronized (lock) {
                if (log != null) {
                    for (Object object : log) {
                        if (object != null) {
                            ALogger.d(tag, object.toString());
                        }

                    }
                }
            }
        }


    }

}
