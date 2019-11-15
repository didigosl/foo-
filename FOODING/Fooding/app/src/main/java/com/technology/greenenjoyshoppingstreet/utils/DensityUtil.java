package com.technology.greenenjoyshoppingstreet.utils;

import android.content.Context;
import android.os.Build;

/**
 * Created by Administrator on 2017/8/22.
 */

public class DensityUtil {

    /**
     * dp转px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变.
     *
     * @param spValue
     *         the sp value
     *
     * @return the int
     */
    public static int sp2px(Context context, float spValue) {
        if (Build.VERSION.SDK_INT < 11) {
            return dip2px(context, spValue);
        }
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
