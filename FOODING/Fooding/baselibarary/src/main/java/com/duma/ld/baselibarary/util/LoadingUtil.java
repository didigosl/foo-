package com.duma.ld.baselibarary.util;


import android.app.Activity;


/**
 * Created by 79953 on 2016/9/30.
 */

public class LoadingUtil {

    private LoadingUtil() {
    }

    public static synchronized LoadingUtil getInstance() {
        return DialogUtilHolder.instance;
    }

    private static class DialogUtilHolder {
        private static final LoadingUtil instance = new LoadingUtil();
    }

    private LoadingDialog loadingDialog;

    public void show(Activity activity) {
        hide();
        loadingDialog = new LoadingDialog(activity);
        loadingDialog.show();
    }

    public void show_noBack(Activity activity) {
        hide();
        loadingDialog = new LoadingDialog(activity, false, "加载中...");
        loadingDialog.show();
    }

    public void show(Activity activity, String title) {
        hide();
        loadingDialog = new LoadingDialog(activity, title);
        loadingDialog.show();
    }

    public void show_noBack(Activity activity, String title) {
        hide();
        loadingDialog = new LoadingDialog(activity, false, title);
        loadingDialog.show();
    }

    public void setTiTle(String title) {
        if (loadingDialog != null) {
            loadingDialog.setText(title);
        }
    }

    public void hide() {
        if (loadingDialog != null)
            if (loadingDialog.isShowing()) {
                try {
                    loadingDialog.dismiss();
                    loadingDialog = null;
                } catch (Exception e) {
                }
            }
    }
}
