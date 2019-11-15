package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.R;

/**
 * Created by Administrator on 2017/12/30.
 */

public enum SelectMode {

    SELECT("1", R.color.select_color, R.color.select_color),

    UNSELECT("2", R.color.unselect_bg_color, R.color.unselect_color),

    UNENABLE("3", R.color.unselect_bg_color, R.color.unenable_select_color);

    private String statusCode;
    private int borderColor;
    private int textColor;

    SelectMode(String statusCode, int borderColor, int textColor) {
        this.statusCode = statusCode;
        this.borderColor = borderColor;
        this.textColor = textColor;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
