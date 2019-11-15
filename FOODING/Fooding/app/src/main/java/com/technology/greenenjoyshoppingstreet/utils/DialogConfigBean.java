package com.technology.greenenjoyshoppingstreet.utils;

import android.content.Context;
import android.view.View;

import com.technology.greenenjoyshoppingstreet.R;

/**
 * Created by Bern on 2017/11/2 0002.
 */

public class DialogConfigBean {
    private String title;

    private String singleButtonText;
    private String leftText;
    private String rightText;
    private String contentText;
    private boolean hasTitle;
    private boolean isSingle;
    private boolean isCanBack;
    private boolean isCanceledOnTouchOutside;

    private View.OnClickListener singleClick;
    private View.OnClickListener leftClick;
    private View.OnClickListener rightClick;


    public static DialogConfigBean getDefaultSingleConfig() {
        DialogConfigBean dialogConfigBean = new DialogConfigBean();
        dialogConfigBean.setTitle("提示");
        dialogConfigBean.setSingleButtonText("好");
        dialogConfigBean.setHasTitle(true);
        dialogConfigBean.setSingle(true);
        dialogConfigBean.setLeftText("取消");
        dialogConfigBean.setRightText("确定");
        return dialogConfigBean;
    }

    public static DialogConfigBean getDefaultDoubleConfig(Context context) {
        DialogConfigBean dialogConfigBean = new DialogConfigBean();
        dialogConfigBean.setTitle(context.getString(R.string.alert));
        dialogConfigBean.setSingleButtonText(context.getString(R.string.ok));
        dialogConfigBean.setHasTitle(true);
        dialogConfigBean.setSingle(false);
        dialogConfigBean.setLeftText(context.getString(R.string.cancel));
        dialogConfigBean.setRightText(context.getString(R.string.ok));
        return dialogConfigBean;
    }

    public static DialogConfigBean getDefaultSingleNoTitleConfig() {
        DialogConfigBean dialogConfigBean = new DialogConfigBean();
        dialogConfigBean.setTitle("");
        dialogConfigBean.setSingleButtonText("确定");
        dialogConfigBean.setHasTitle(false);
        dialogConfigBean.setSingle(true);
        dialogConfigBean.setLeftText("取消");
        dialogConfigBean.setRightText("确定");
        return dialogConfigBean;
    }

    public boolean isCanceledOnTouchOutside() {
        return isCanceledOnTouchOutside;
    }

    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        isCanceledOnTouchOutside = canceledOnTouchOutside;
    }

    public boolean isCanBack() {
        return isCanBack;
    }

    public void setCanBack(boolean canBack) {
        isCanBack = canBack;
    }

    public boolean isHasTitle() {
        return hasTitle;
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingleButtonText() {
        return singleButtonText;
    }

    public void setSingleButtonText(String singleButtonText) {
        this.singleButtonText = singleButtonText;
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public View.OnClickListener getSingleClick() {
        return singleClick;
    }

    public void setSingleClick(View.OnClickListener singleClick) {
        this.singleClick = singleClick;
    }

    public View.OnClickListener getLeftClick() {
        return leftClick;
    }

    public void setLeftClick(View.OnClickListener leftClick) {
        this.leftClick = leftClick;
    }

    public View.OnClickListener getRightClick() {
        return rightClick;
    }

    public void setRightClick(View.OnClickListener rightClick) {
        this.rightClick = rightClick;
    }
}
