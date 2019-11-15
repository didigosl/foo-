package com.technology.greenenjoyshoppingstreet.newui.util;

import android.app.Activity;
import android.content.Intent;

import com.duma.ld.baselibarary.util.Constants;
import com.google.gson.Gson;
import com.technology.greenenjoyshoppingstreet.newui.model.OrderPayInfoModel;
import com.technology.greenenjoyshoppingstreet.newui.model.ShoppingCartModel;
import com.technology.greenenjoyshoppingstreet.newui.view.ConfirmOrderActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.GoodsCommentActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.GoodsDetailActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.GoodsListActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.OrderListActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.PayActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.ShoppingCartDetailActivity;
import com.technology.greenenjoyshoppingstreet.newui.view.XianXiaPayActivity;

import java.util.List;

/**
 * 跳转
 * Created by liudong on 2018/3/21.
 */

public class StartActivityUtil {
    public static synchronized StartActivityUtil getInstance() {
        return StartActivityUtil.StartActivityUtilHolder.instance;
    }

    private static class StartActivityUtilHolder {
        private static final StartActivityUtil instance = new StartActivityUtil();
    }


    public void startActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(activity, intent);
    }

    public void startActivityBottom(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        startActivity(activity, intent);
        activity.overridePendingTransition(com.duma.ld.baselibarary.R.anim.v_enter, com.duma.ld.baselibarary.R.anim.v_exit);
    }

    //最终跳转都会进入这
    public void startActivity(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    //        ----------------------------------------------------

    public void goGoodsList(Activity activity, String categoryId, String intent_Name) {
        Intent intent = new Intent(activity, GoodsListActivity.class);
        intent.putExtra(Constants.intent_id, categoryId);
        intent.putExtra(Constants.intent_Name, intent_Name);
        startActivity(activity, intent);
    }

    //商品详情页
    public void goGoodsDetail(Activity activity, String goodsId) {
        Intent intent = new Intent(activity, GoodsDetailActivity.class);
        intent.putExtra(Constants.intent_id, goodsId);
        startActivity(activity, intent);
    }

    //商品评论列表页
    public void goGoodsComment(Activity activity, String id) {
        Intent intent = new Intent(activity, GoodsCommentActivity.class);
        intent.putExtra(Constants.intent_id, id);
        startActivity(activity, intent);
    }

    //确认订单
    public void goConfirmOrder(Activity activity, List<ShoppingCartModel.ListBean> listBeans) {
        Intent intent = new Intent(activity, ConfirmOrderActivity.class);
        intent.putExtra(Constants.intent_list_json, new Gson().toJson(listBeans));
        startActivity(activity, intent);
    }

    //支付
    public void goPay(Activity activity, OrderPayInfoModel orderPayInfoModel) {
        Intent intent = new Intent(activity, PayActivity.class);
        intent.putExtra(Constants.intent_Model, orderPayInfoModel);
        startActivity(activity, intent);
    }

    //线下支付
    public void goPay_xianxia(Activity activity, OrderPayInfoModel orderPayInfoModel) {
        Intent intent = new Intent(activity, XianXiaPayActivity.class);
        intent.putExtra(Constants.intent_Model, orderPayInfoModel);
        startActivity(activity, intent);
    }

    //订单列表
    public void goOrderList(Activity activity, int position) {
        Intent intent = new Intent(activity, OrderListActivity.class);
        intent.putExtra(Constants.intent_position, position);
        startActivity(activity, intent);
    }

    //Detalle del carrito
    public void goShoppingCartDetail(Activity activity) {
        Intent intent = new Intent(activity, ShoppingCartDetailActivity.class);
        startActivity(activity, intent);
    }
}
