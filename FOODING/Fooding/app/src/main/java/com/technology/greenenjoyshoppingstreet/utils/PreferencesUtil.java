package com.technology.greenenjoyshoppingstreet.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.technology.greenenjoyshoppingstreet.BaseApplication;


public class PreferencesUtil {

    private static SharedPreferences sp;

    private static void init(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }


    public static void setSharedStringData(String key, String value) {
        if (sp == null) {
            init(BaseApplication.getInstanceContext());
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getSharedStringData(String key) {
        if (sp == null) {
            init(BaseApplication.getInstanceContext());
        }
        String value = sp.getString(key, "");


        return value;
    }

    public static boolean getSharedBooleanData(String key) {
        if (sp == null) {
            init(BaseApplication.getInstanceContext());
        }
        return sp.getBoolean(key, false);
    }

    public static void setSharedBooleanData(String key, boolean value) {
        if (sp == null) {
            init(BaseApplication.getInstanceContext());
        }
        sp.edit().putBoolean(key, value).commit();
    }
}
