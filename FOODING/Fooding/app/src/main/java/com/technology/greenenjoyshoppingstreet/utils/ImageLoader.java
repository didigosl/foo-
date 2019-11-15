package com.technology.greenenjoyshoppingstreet.utils;

import android.widget.ImageView;

import com.blankj.utilcode.util.FileUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.technology.greenenjoyshoppingstreet.BaseApplication;
import com.technology.greenenjoyshoppingstreet.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by 79953 on 2016/7/29.
 */
public class ImageLoader {
    public static void with(Object url, ImageView imageView) {
        with(url, imageView, 0);
    }

    public static void with(Object url, ImageView imageView, int roundingRadius) {
        RequestOptions options = getDifautImg();
        if (roundingRadius != 0) {
            options = options.transform(new RoundedCorners(roundingRadius)).centerCrop();
        }
        loadImg(url, imageView, options);
    }


    public static void with_noCache(Object url, ImageView imageView) {
        RequestOptions options = getDifautImg()
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        loadImg(url, imageView, options);
    }

    public static void with_head(Object url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ui55)
                .circleCrop();
        loadImg(url, imageView, options);
    }

    public static void with_rounded(Object url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ui55)
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(10,0, RoundedCornersTransformation.CornerType.ALL)));
        loadImg(url, imageView, options);
    }

    public static void loadImg(Object url, ImageView imageView, RequestOptions options) {
        if (url != null) {
            if (url instanceof String) {
                if (!((String) url).contains("http")) {
                    //把本地文件路径给过滤了去
                    boolean fileExists = FileUtils.isFile((String) url);
                    if (!fileExists) {
                        url = "" + url;
                    }
                }
            }
        }
        //去掉动画效果就不会有拉伸 或者 去掉占位图
        Glide.with(BaseApplication.getInstance())
//                .asBitmap()
                .load(url)
//                .transition(BitmapTransitionOptions.withCrossFade())
                .apply(options)
                .into(imageView);
    }

    private static RequestOptions getDifautImg() {
        return new RequestOptions()
                .placeholder(R.drawable.ic_app_launcher);
    }
//    public static void with(Object url, ImageView imageView) {
////        Glide.with(BaseApplication.getInstance())
////                .load(url)
////                .error(R.drawable.ic_app_launcher)//load失敗的Drawable
////                .placeholder(R.drawable.ic_app_launcher)//loading時候的Drawable
////                .diskCacheStrategy(DiskCacheStrategy.RESULT)
////                .centerCrop()
////                .dontAnimate()
////                .into(imageView);
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.ic_app_launcher)
//                .circleCrop();
//        loadImg(url, imageView, options);
//    }


//    public static void with_head(Object url, ImageView imageView) {
////        Glide.with(BaseApplication.getInstance())
////                .load(url)
////                .error(R.drawable.ui55)//load失敗的Drawable
////                .placeholder(R.drawable.ui55)//loading時候的Drawable
////                .transform(new GlideCircleTransform(BaseApplication.getInstance()))
////                .into(imageView);
//        RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.ui55)
//                .circleCrop();
//        loadImg(url, imageView, options);
//    }
//
//    public static void loadImg(Object url, ImageView imageView, RequestOptions options) {
////        if (url != null) {
////            if (url instanceof String) {
////                if (!((String) url).contains("http")) {
////                    //把本地文件路径给过滤了去
////                    boolean fileExists = FileUtils.isFile((String) url);
////                    if (!fileExists) {
////                        url = "http://guanlouyi.b0.upaiyun.com" + url;
////                    }
////                }
////            }
////        }
//        //去掉动画效果就不会有拉伸 或者 去掉占位图
//        Glide.with(BaseApplication.getInstance())
////                .asBitmap()
//                .load(url)
////                .transition(BitmapTransitionOptions.withCrossFade())
//                .apply(options)
//                .into(imageView);
//    }
}
