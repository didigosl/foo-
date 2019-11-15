package com.duma.ld.baselibarary.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.duma.ld.baselibarary.R;


public class LoadingDialog extends Dialog {

    private boolean isBack = true;

    private String text = "";
    private TextView textView;

    public LoadingDialog(Activity activit) {
        this(activit, "加载中...");
    }

    public LoadingDialog(Activity activity, String title) {
        this(activity, true, title);
    }

    public LoadingDialog(Activity activity, boolean isBack, String title) {
        super(activity);
        this.isBack = isBack;
        this.text = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading);
        this.setCancelable(isBack);
        textView = findViewById(R.id.tv_title);
        textView.setText(text);
    }

    public void setText(String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }
}
