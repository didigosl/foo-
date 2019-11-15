package com.technology.greenenjoyshoppingstreet.utils.info;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.technology.greenenjoyshoppingstreet.BaseApplication;
import com.technology.greenenjoyshoppingstreet.BuildConfig;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Locale;

/**
 * Created by Administrator on 2017/8/22.
 */

public class PhoneInfoManager {

    /**
     * Phone infomation.
     */
    private static PhoneInfomation phoneInfomation = new PhoneInfomation();

    /**
     * 设备唯一ID。GSM:IMEI    CDMA:MEID|ESN
     * <p>Requires Permission:
     * {@link android.Manifest.permission#READ_PHONE_STATE READ_PHONE_STATE}
     *
     * @return the device id
     */
    public static String getDeviceId() {
        synchronized (phoneInfomation) {
            if (TextUtils.isEmpty(phoneInfomation.getDeviceId())) {
                Object telephony = BaseApplication.getInstanceContext().getSystemService(Context
                        .TELEPHONY_SERVICE);
                if (telephony != null && telephony instanceof TelephonyManager) {
                    phoneInfomation.setDeviceId(((TelephonyManager) telephony).getDeviceId());
                }

            }
            return phoneInfomation.getDeviceId();

        }


    }

    public static boolean isChineseLanguage(){
        String languaje = Locale.getDefault().toString();
        if (languaje.contains("zh")){
            return true;
        }else {
            return false;
        }
    }



    /**
     * 获取WIFI MAC地址
     *
     * @return wifi mac address
     * @author perfect -sjt
     */
    public static String getWIFIMacAddress() {

        synchronized (phoneInfomation) {
            if (TextUtils.isEmpty(phoneInfomation.getWifiMacAddress())) {
                Object wifi = BaseApplication.getInstanceContext().getApplicationContext().getSystemService(Context
                        .WIFI_SERVICE);

                if (wifi != null && wifi instanceof WifiManager) {
                    WifiManager wifiManager = (WifiManager) wifi;
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    if (wifiInfo != null) {
                        phoneInfomation.setWifiMacAddress(wifiInfo.getMacAddress());
                    }
                }
            }
            return phoneInfomation.getWifiMacAddress();
        }


    }

    /**
     * 获取手机机型
     *
     * @return the phone model
     */
    public static String getPhoneModel() {

        return phoneInfomation.getModel();

    }

    /**
     * 获取手机品牌
     *
     * @return the phone brand
     */
    public static String getPhoneBrand() {
        return phoneInfomation.getBrand();
    }



    /**
     * Init phone info.
     */
    public static void initPhoneInfo() {
        phoneInfomation = new PhoneInfomation();


    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public static String getPhoneNumber() {
        synchronized (phoneInfomation) {
            Object telephony = BaseApplication.getInstanceContext().getSystemService(Context
                    .TELEPHONY_SERVICE);
            if (telephony != null && telephony instanceof TelephonyManager) {

                phoneInfomation.setPhoneNumber(((TelephonyManager) telephony).getLine1Number());


            }
            return phoneInfomation.getPhoneNumber();


        }

    }

    /**
     * Gets width pixels.
     *
     * @return the width pixels
     */
    public static int getWidthPixels() {
        synchronized (phoneInfomation) {
            if (phoneInfomation.getWidthPixels() == 0) {

                Object window = BaseApplication.getInstanceContext().getSystemService(Context
                        .WINDOW_SERVICE);
                if (window != null && window instanceof WindowManager) {
                    WindowManager windowManager = (WindowManager) window;
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                    setPhoneScreenInfo(displayMetrics);

                }
            }
            return phoneInfomation.getWidthPixels();

        }
    }

    /**
     * Gets height pixels.
     *
     * @return the height pixels
     */
    public static int getHeightPixels() {
        synchronized (phoneInfomation) {
            if (phoneInfomation.getHeightPixels() == 0) {

                Object window = BaseApplication.getInstanceContext().getSystemService(Context
                        .WINDOW_SERVICE);
                if (window != null && window instanceof WindowManager) {
                    WindowManager windowManager = (WindowManager) window;
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                    setPhoneScreenInfo(displayMetrics);

                }
            }
            return phoneInfomation.getHeightPixels();

        }
    }

    /**
     * Sets phone screen info.
     *
     * @param displayMetrics the display metrics
     */
    private static void setPhoneScreenInfo(DisplayMetrics displayMetrics) {
        if (displayMetrics != null) {

            phoneInfomation.setWidthPixels(displayMetrics.widthPixels);
            phoneInfomation.setHeightPixels(displayMetrics.heightPixels);
            phoneInfomation.setDensity(displayMetrics.density);
            phoneInfomation.setDensityDpi(displayMetrics.densityDpi);
            phoneInfomation.setScaledDensity(displayMetrics.scaledDensity);
            phoneInfomation.setXdpi(displayMetrics.xdpi);
            phoneInfomation.setYdpi(displayMetrics.ydpi);
        }

    }

    /**
     * Gets meta data string.
     *
     * @param key the key
     * @return the meta data string
     */
    public static Object getMetaDataString(String key) {


        ApplicationInfo appInfo;
        try {
            appInfo = BaseApplication.getInstanceContext().getPackageManager()
                    .getApplicationInfo(BuildConfig.APPLICATION_ID,
                            PackageManager.GET_META_DATA);
            if (appInfo != null && appInfo.metaData != null) {
                return appInfo.metaData.getString(key);

            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return null;
    }

    /**
     * 获取手机服务商信息
     *
     * @return the providers name
     */
    public static String getProvidersName() {

        synchronized (phoneInfomation) {
            Object telephony = BaseApplication.getInstanceContext().getSystemService(Context
                    .TELEPHONY_SERVICE);
            if (telephony != null && telephony instanceof TelephonyManager) {
                TelephonyManager telephonyManager = (TelephonyManager) telephony;
                phoneInfomation.setSubscriberId(telephonyManager.getSubscriberId());
            }
            return getProvidersNameBySubscriberId(phoneInfomation.getSubscriberId());

        }

    }

    /**
     * Gets providers name by subscriber id.
     *
     * @param subscriberId the subscriber id
     * @return the providers name by subscriber id
     */
    private static String getProvidersNameBySubscriberId(String subscriberId) {
        if (!TextUtils.isEmpty(subscriberId)) {
            if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
                return "中国移动";
            } else if (subscriberId.startsWith("46001")) {
                return "中国联通";
            } else if (subscriberId.startsWith("46003")) {
                return "中国电信";
            }
        }
        return null;

    }

    /**
     * Gets network type.
     *
     * @return the network type
     */
    public static String getNetworkType() {

        String networkType = null;
        Object connectivity = BaseApplication.getInstanceContext().getSystemService(Context
                .CONNECTIVITY_SERVICE);
        if (connectivity != null && connectivity instanceof ConnectivityManager) {
            ConnectivityManager connectivityManager = (ConnectivityManager) connectivity;
            NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
            if (networkinfo != null) {
                synchronized (phoneInfomation) {
                    phoneInfomation.setNetworkType(networkType = networkinfo.getTypeName());
                }

            }
        }
        return networkType;
    }



    /**
     * Gets ip address.
     *
     * @return the ip address
     */
    public static String getIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        String ipAddress = inetAddress.getHostAddress();
                        phoneInfomation.setPhoneIp(ipAddress);

                        return ipAddress;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;


    }

    /**
     * 获取手机的系统的版本号
     *
     * @return the android sdk version
     */
    public static int getAndroidSdkVersion() {

        return phoneInfomation.getAndroidSdk();
    }



    /**
     * @version V1.0
     * @Title: Phone infomation
     * @date 2017.04.11
     * @Title: Phone infomation
     */
    private static class PhoneInfomation {


        /**
         * End point code bak.
         */
        private String endPointCodeBak;

        /**
         * Unique id.
         */
        private String uniqueId;

        /**
         * Device id.
         */
        private String deviceId;

        /**
         * Bluetooth address.
         */
        private String bluetoothAddress;

        /**
         * Wifi mac address.
         */
        private String wifiMacAddress;

        /**
         * Cup info.
         */
        private String cupInfo;

        /**
         * Subscriber id.
         */
        private String SubscriberId;


        /**
         * Phone ip.
         */
        private String phoneIp;


        /**
         * Width pixels.
         */
        private int widthPixels;

        /**
         * Height pixels.
         */
        private int heightPixels;

        /**
         * Density.
         */
        private float density;
        /**
         * Density dpi.
         */
        private int densityDpi;

        /**
         * Scaled density.
         */
        private float scaledDensity;

        /**
         * Xdpi.
         */
        private float xdpi;

        /**
         * Ydpi.
         */
        private float ydpi;

        /**
         * Network type.
         */
        private String networkType;

        /**
         * Phone number.
         */
        private String phoneNumber;

        /**
         * Model.
         */
        private String model = android.os.Build.MODEL.replace(" ", "");

        /**
         * Android sdk.
         */
        private int androidSdk = android.os.Build.VERSION.SDK_INT;

        /**
         * Brand.
         */
        private String brand = android.os.Build.BRAND;

        /**
         * Meta data.
         */
        private Bundle metaData;

        /**
         * Gets meta data.
         *
         * @return the meta data
         */
        public Bundle getMetaData() {
            return metaData;
        }

        /**
         * Sets meta data.
         *
         * @param metaData the meta data
         */
        public void setMetaData(Bundle metaData) {
            this.metaData = metaData;
        }

        /**
         * Gets network type.
         *
         * @return the network type
         */
        public String getNetworkType() {
            return networkType;
        }

        /**
         * Sets network type.
         *
         * @param networkType the network type
         */
        public void setNetworkType(String networkType) {
            this.networkType = networkType;
        }

        /**
         * Gets phone ip.
         *
         * @return the phone ip
         */
        public String getPhoneIp() {
            return phoneIp;
        }

        /**
         * Sets phone ip.
         *
         * @param phoneIp the phone ip
         */
        public void setPhoneIp(String phoneIp) {
            this.phoneIp = phoneIp;
        }

        /**
         * Gets width pixels.
         *
         * @return the width pixels
         */
        public int getWidthPixels() {
            return widthPixels;
        }

        /**
         * Sets width pixels.
         *
         * @param widthPixels the width pixels
         */
        public void setWidthPixels(int widthPixels) {
            this.widthPixels = widthPixels;
        }

        /**
         * Gets height pixels.
         *
         * @return the height pixels
         */
        public int getHeightPixels() {
            return heightPixels;
        }

        /**
         * Sets height pixels.
         *
         * @param heightPixels the height pixels
         */
        public void setHeightPixels(int heightPixels) {
            this.heightPixels = heightPixels;
        }

        /**
         * Gets density.
         *
         * @return the density
         */
        public float getDensity() {
            return density;
        }

        /**
         * Sets density.
         *
         * @param density the density
         */
        public void setDensity(float density) {
            this.density = density;
        }

        /**
         * Gets density dpi.
         *
         * @return the density dpi
         */
        public int getDensityDpi() {
            return densityDpi;
        }

        /**
         * Sets density dpi.
         *
         * @param densityDpi the density dpi
         */
        public void setDensityDpi(int densityDpi) {
            this.densityDpi = densityDpi;
        }

        /**
         * Gets scaled density.
         *
         * @return the scaled density
         */
        public float getScaledDensity() {
            return scaledDensity;
        }

        /**
         * Sets scaled density.
         *
         * @param scaledDensity the scaled density
         */
        public void setScaledDensity(float scaledDensity) {
            this.scaledDensity = scaledDensity;
        }

        /**
         * Gets xdpi.
         *
         * @return the xdpi
         */
        public float getXdpi() {
            return xdpi;
        }

        /**
         * Sets xdpi.
         *
         * @param xdpi the xdpi
         */
        public void setXdpi(float xdpi) {
            this.xdpi = xdpi;
        }

        /**
         * Gets ydpi.
         *
         * @return the ydpi
         */
        public float getYdpi() {
            return ydpi;
        }

        /**
         * Sets ydpi.
         *
         * @param ydpi the ydpi
         */
        public void setYdpi(float ydpi) {
            this.ydpi = ydpi;
        }

        /**
         * Gets phone number.
         *
         * @return the phone number
         */
        public String getPhoneNumber() {

            return phoneNumber;
        }

        /**
         * Sets phone number.
         *
         * @param phoneNumber the phone number
         */
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        /**
         * Gets brand.
         *
         * @return the brand
         */
        public String getBrand() {
            return brand;
        }

        /**
         * Sets brand.
         *
         * @param brand the brand
         */
        public void setBrand(String brand) {
            this.brand = brand;
        }

        /**
         * Gets model.
         *
         * @return the model
         */
        public String getModel() {
            return model;
        }

        /**
         * Sets model.
         *
         * @param model the model
         */
        public void setModel(String model) {
            this.model = model;
        }

        /**
         * Gets android sdk.
         *
         * @return the android sdk
         */
        public int getAndroidSdk() {
            return androidSdk;
        }

        /**
         * Sets android sdk.
         *
         * @param androidSdk the android sdk
         */
        public void setAndroidSdk(int androidSdk) {
            this.androidSdk = androidSdk;
        }

        /**
         * Gets subscriber id.
         *
         * @return the subscriber id
         */
        public String getSubscriberId() {
            return SubscriberId;
        }

        /**
         * Sets subscriber id.
         *
         * @param subscriberId the subscriber id
         */
        public void setSubscriberId(String subscriberId) {
            SubscriberId = subscriberId;
        }

        /**
         * Gets cup info.
         *
         * @return the cup info
         */
        public String getCupInfo() {
            return cupInfo;
        }

        /**
         * Sets cup info.
         *
         * @param cupInfo the cup info
         */
        public void setCupInfo(String cupInfo) {
            this.cupInfo = cupInfo;
        }

        /**
         * Gets wifi mac address.
         *
         * @return the wifi mac address
         */
        public String getWifiMacAddress() {
            return wifiMacAddress;
        }

        /**
         * Sets wifi mac address.
         *
         * @param wifiMacAddress the wifi mac address
         */
        public void setWifiMacAddress(String wifiMacAddress) {
            this.wifiMacAddress = wifiMacAddress;
        }

        /**
         * Gets bluetooth address.
         *
         * @return the bluetooth address
         */
        public String getBluetoothAddress() {
            return bluetoothAddress;
        }

        /**
         * Sets bluetooth address.
         *
         * @param bluetoothAddress the bluetooth address
         */
        public void setBluetoothAddress(String bluetoothAddress) {
            this.bluetoothAddress = bluetoothAddress;
        }

        /**
         * Gets end point code bak.
         *
         * @return the end point code bak
         */
        public String getEndPointCodeBak() {
            return endPointCodeBak;
        }

        /**
         * Sets end point code bak.
         *
         * @param endPointCodeBak the end point code bak
         */
        public void setEndPointCodeBak(String endPointCodeBak) {
            this.endPointCodeBak = endPointCodeBak;
        }

        /**
         * Gets unique id.
         *
         * @return the unique id
         */
        public String getUniqueId() {
            return uniqueId;
        }

        /**
         * Sets unique id.
         *
         * @param uniqueId the unique id
         */
        public void setUniqueId(String uniqueId) {
            this.uniqueId = uniqueId;
        }

        /**
         * Gets device id.
         *
         * @return the device id
         */
        public String getDeviceId() {
            return deviceId;
        }

        /**
         * Sets device id.
         *
         * @param deviceId the device id
         */
        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }


    }
}
