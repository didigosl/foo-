package com.technology.greenenjoyshoppingstreet.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import com.technology.greenenjoyshoppingstreet.BaseApplication;
import com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.List;

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.EURO;

/**
 * Created by Administrator on 2017/5/22.
 */

public class Tools {
    private static final String TAG = Tools.class.getSimpleName();
    /**
     * 上传文件最大尝试次数
     */
    private static final int MAX_TRIES = 3;


    public static String hidePhone(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber) && phoneNumber.length() > 7) {

            return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(6);


        }
        return "";

    }


    public static boolean isZeroString(String str) {

        if (!TextUtils.isEmpty(str) && isDouble(str)) {
            return Double.valueOf(str).compareTo(new Double("0")) == 0;

        }
        return false;

    }

    public static boolean isInScreen(ScrollView scrollView, View childView) {
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);
        return childView.getLocalVisibleRect(scrollBounds);
    }

    public static String convertMD5(String val) {
        if (!TextUtils.isEmpty(val)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(val.getBytes());
                byte[] m = md5.digest();// 加密
                return getHexString(m);
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
        return "";

    }

    // 将指定byte数组以16进制生成String
    public static String getHexString(byte[] b) {
        String hexString = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString += hex.toLowerCase();
        }
        return hexString;
    }

    public static String formatPriceText(String str) {

        if (!TextUtils.isEmpty(str) && Tools.isDouble(str)) {
            BigDecimal bigDecimal = new BigDecimal(str).setScale(2, BigDecimal.ROUND_HALF_UP);
            return EURO + " " + bigDecimal.toString();
        }

        return str;
    }

    public static String multipleNumber(String numberOne, String numberTwo) {
        if (!TextUtils.isEmpty(numberOne) && isDouble(numberOne) && !TextUtils.isEmpty(numberTwo) &&
                isDouble(numberTwo)) {

            BigDecimal bigDecimalOne = new BigDecimal(numberOne);
            BigDecimal bigDecimalTwo = new BigDecimal(numberTwo);
            return bigDecimalOne.multiply(bigDecimalTwo).toString();

        }
        return "0";
    }

    /**
     * 读取assets下的txt文件，返回utf-8 String
     *
     * @param context
     * @param fileName 不包括后缀
     * @return
     */
    public static String readAssetsTxt(Context context, String fileName) {
        try {
            //Return an AssetManager instance for your application's package
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert the buffer into a string.
            String text = new String(buffer, "utf-8");
            // Finally stick the string into the text view.
            return text;
        } catch (IOException e) {
            // Should never happen!
//            throw new RuntimeException(e);
            e.printStackTrace();
        }
        return "读取错误，请检查文件名";
    }

    public static String getRealImageUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("/")) {
                url = url.substring(1);
            }


            return (URLConstant.BASE_URL + url).replace(",", "");
        }
        return (URLConstant.BASE_URL + url).replace(",", "");
    }


    /**
     * Is foreground boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isForeground(Context context) {
        // Get the Activity Manager
        ActivityManager manager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        // Get a list of running tasks, we are only interested in the last one,
        // the top most so we give a 1 as parameter so we only get the topmost.
        List<ActivityManager.RunningTaskInfo> task = manager.getRunningTasks(1);

        // Get the info we need for comparison.
        ComponentName componentInfo = task.get(0).topActivity;

        // Check if it matches our package name.
        return componentInfo.getPackageName().equals(context.getPackageName());

    }

    public static File getCacheDir(String dirName, Context context) {
        File result;
        if (existsSdcard()) {
            File cacheDir = context.getExternalCacheDir();
            if (cacheDir == null) {
                result = new File(Environment.getExternalStorageDirectory(),
                        "Android/data/" + context.getPackageName() + "/cache/" + dirName);
            } else {
                result = new File(cacheDir, dirName);
            }
        } else {
            result = new File(context.getCacheDir(), dirName);
        }
        if (result.exists() || result.mkdirs()) {
            return result;
        } else {
            return null;
        }
    }

    /**
     * Double pattern.
     */
    private static final String DOUBLE_PATTERN = "^[-\\+]?[.\\d]*$";

    /**
     * Is double boolean.
     *
     * @param str the str
     * @return the boolean
     */
/*
      * 判断是否为浮点数，包括double和float
      * @param str 传入的字符串
      * @return 是浮点数返回true,否则返回false
    */
    public static boolean isDouble(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.matches(DOUBLE_PATTERN);
        }
        return false;

    }

    public static String concatAll(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        if (args != null && args.length > 0) {
            for (String item : args) {
                if (!TextUtils.isEmpty(item)) {
                    stringBuilder.append(item);
                }
            }
        }
        return stringBuilder.toString();
    }

    public static Boolean existsSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
//
//    public static void dialPhone(final BaseActivity baseActivity, final String phoneNumebr) {
//        baseActivity.showChooseDoubleDialog("拨打客服电话 " + phoneNumebr, "取消", null, "确认", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ActivityCompat.checkSelfPermission(baseActivity, Manifest.permission
//                        .CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//
//                    Intent intent = new Intent(Intent
//                            .ACTION_CALL, Uri.parse("tel:" + phoneNumebr));
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    BaseApplication.getInstanceContext().startActivity(intent);
//
//                }
//
//                baseActivity.dismissChooseDialog();
//            }
//        }, true);
//
//    }


    public static String phoneFormat(String phone) {
        if (!TextUtils.isEmpty(phone) && phone.length() == 11) {

            return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
        }
        return phone;


    }

    public static void clearTopBack(Context context, Class targetClass) {
        Intent intent = new Intent();
        intent.setClass(context, targetClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

    }


    public static String getNotNull(Object object) {
        if (object != null) {
            return object.toString();
        } else {
            return "";
        }
    }

    public static void startSchemeActivity(Context context, String url) {
        if (context != null && !TextUtils.isEmpty(url)) {
            try {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                PackageManager packageManager = context.getPackageManager();
                ComponentName componentName = intent.resolveActivity(packageManager);
                if (componentName != null) {
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static String uploadFile(UploadStatusDelegate delegate, String path, String url, String key, List<? extends KeyValuePair> list) {
        if (!TextUtils.isEmpty(path) && !TextUtils.isEmpty(url)) {

            try {
                MultipartUploadRequest req = new MultipartUploadRequest(BaseApplication.getInstanceContext(), url)
                        .addFileToUpload(path, key)
                        .setAutoDeleteFilesAfterSuccessfulUpload(true)
                        .setUsesFixedLengthStreamingMode(true)
                        .setUtf8Charset().setMaxRetries(MAX_TRIES);
                if (list != null && !list.isEmpty()) {
                    for (KeyValuePair keyValuePair : list) {
                        req.addParameter(keyValuePair.getKey(), keyValuePair.getValue());
                    }
                }
                req.setDelegate(delegate);
                return req.startUpload();
            } catch (Exception e) {
                Log.d(TAG, "上传错误 :" + e.toString());
            }


        }
        return null;

    }

    public static String generateRandomString(int length) {


        java.util.Random random = new java.util.Random();// 定义随机类
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < length; j++) {
            //根据ASCII编码生成随机的字符
            switch (random.nextInt(3) + 1) {
                case 1:
                    // 生成随机数
                    builder.append(random.nextInt(10));
                    break;
                case 2:
                    // 生成大写字母
                    builder.append((char) (random.nextInt(26) + 65));
                    break;
                case 3:
                    // 生成小写字母
                    builder.append((char) (random.nextInt(26) + 97));
                    break;
            }
        }
        return builder.toString();
    }
}
