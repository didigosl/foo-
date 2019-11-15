package com.technology.greenenjoyshoppingstreet.login.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bern on 2017/5/31 0031.
 */

public class WebBean implements Parcelable {


    private String title;
    private String targetUrl;
    private String data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }


    public WebBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.targetUrl);
        dest.writeString(this.data);
    }

    protected WebBean(Parcel in) {
        this.title = in.readString();
        this.targetUrl = in.readString();
        this.data = in.readString();
    }

    public static final Creator<WebBean> CREATOR = new Creator<WebBean>() {
        @Override
        public WebBean createFromParcel(Parcel source) {
            return new WebBean(source);
        }

        @Override
        public WebBean[] newArray(int size) {
            return new WebBean[size];
        }
    };
}
