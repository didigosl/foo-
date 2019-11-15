package com.duma.ld.baselibarary.base;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;
import com.duma.ld.baselibarary.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import okhttp3.OkHttpClient;

/**
 * 常用库的初始化操作
 *
 * @author liudong
 */
public abstract class BaseApplication extends Application {
    private static BaseApplication instance;
    protected String Tag = "liudong";
    public static final long DEFAULT_MILLISECONDS = 10 * 1000;

    public static BaseApplication getInstance() {
        return instance;
    }

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.mainColor, R.color.mainColor);//全局设置主题颜色
                layout.setEnableOverScrollBounce(false);
                layout.setEnableOverScrollDrag(false);
                layout.setEnableAutoLoadMore(false);
                return new MaterialHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setEnableOverScrollBounce(false);
                layout.setEnableOverScrollDrag(false);
                layout.setEnableAutoLoadMore(true);
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /**
         * 常用工具
         */
        Utils.init(this);

        /**
         * 日志
         */
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                //是否打印线程信息
                .showThreadInfo(false)
                //要显示多少个方法行。默认2
                .methodCount(1)
                //隐藏内部方法调用直到偏移。默认5
//                .methodOffset(7)
                //更改日志策略以打印输出。默认logcat的
//                .logStrategy(customLog)
                .tag(Tag)
                .build();
        AndroidLogAdapter adapter = new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return isDebug();
            }
        };
        Logger.addLogAdapter(adapter);

        /**
         * fragment
         */
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.NONE)
                // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
                // false时，不会抛出，会捕获，可以在handleException()里监听到
                .debug(isDebug())
                // 在debug=false时，即线上环境时，上述异常会被捕获并回调ExceptionHandler
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 建议在该回调处上传至我们的Crash监测服务器
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
        /**
         * okhttp
         */
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (isDebug()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
//log打印级别，决定了log显示的详细程度
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            //log颜色级别，决定了log在控制台显示的颜色
            loggingInterceptor.setColorLevel(Level.WARNING);
            builder.addInterceptor(loggingInterceptor);
        }
        //全局的读取超时时间
        builder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", "application/json;charset=utf-8");
        OkGo
                .getInstance()
                .init(this)
                .addCommonHeaders(headers)
                .setRetryCount(0)
                .setOkHttpClient(builder.build());
    }

    public abstract boolean isDebug();

}
