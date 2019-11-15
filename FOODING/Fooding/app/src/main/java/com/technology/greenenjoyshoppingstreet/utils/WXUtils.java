package com.technology.greenenjoyshoppingstreet.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by Bern on 2017/10/26 0026.
 */

public class WXUtils {
    private static int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private static IWXAPI api;

    private static void init(Context mContext) {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(mContext, Constant.APP_ID, false);
            api.registerApp(Constant.APP_ID);
        }


    }

    public static void shareWebPageToSession(Context mContext, String webUrl, String title, String
            description) {
        init(mContext);
        shareWebPage(mContext, webUrl, title, description, SendMessageToWX.Req.WXSceneSession);
    }

    public static void shareWebPageToTimeLine(Context mContext, String webUrl, String title, String description) {
        init(mContext);
        shareWebPage(mContext, webUrl, title, description, SendMessageToWX.Req.WXSceneTimeline);

    }

    private static void shareWebPage(Context mContext, String webUrl, String title, String
            description, int mTargetScene) {

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = webUrl;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = description;
        Bitmap bmp = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap
                .ic_launcher);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 60, 60, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = mTargetScene;
        api.sendReq(req);

    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
