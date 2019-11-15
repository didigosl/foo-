package com.technology.greenenjoyshoppingstreet;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.technology.greenenjoyshoppingstreet.utils.MySenderFactroy;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;

import net.gotev.uploadservice.UploadService;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.annotation.ReportsCrashes;


/**
 * Created by Administrator on 2017/5/17 0017.
 */
@ReportsCrashes(
        reportSenderFactoryClasses = {MySenderFactroy.class},
        customReportContent = {ReportField.CUSTOM_DATA, ReportField.PACKAGE_NAME, ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.BRAND, ReportField.DEVICE_ID, ReportField.DEVICE_FEATURES, ReportField.PRODUCT, ReportField.USER_IP, ReportField.USER_EMAIL, ReportField.STACK_TRACE, ReportField.LOGCAT}
)
public class BaseApplication extends com.duma.ld.baselibarary.base.BaseApplication {
    private static BaseApplication mInstance = null;
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        instance = this;
        UserInfoManger.initLoginState();
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
        ACRA.init(this);
//        ShoppingCartManager.initCart();
    }

    @Override
    public boolean isDebug() {
        return true;
    }


    public static Context getInstanceContext() {
        if (mInstance != null) {
            return mInstance.getApplicationContext();
        }
        return null;
    }

    public void getUserInfo() {

    }
}
