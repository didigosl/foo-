package com.duma.ld.baselibarary.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.duma.ld.baselibarary.R;

public class DialogUtil {
    public static AlertDialog.Builder getAlertDialog(Activity activity, String title, String message) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setTitle(title)
                .setMessage(message);
    }

    public static AlertDialog.Builder getAlertDialog_nessage(Activity activity, String message) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setMessage(message);
    }

    public static AlertDialog.Builder getAlertDialog(Activity activity, String title) {
        return new AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                .setTitle(title);
    }
}
