package com.duma.ld.baselibarary.util;

import android.support.annotation.StringDef;

public class Constants {
    // 再点一次退出程序时间设置
    public static final long WAIT_TIME = 2000L;
    public static long TOUCH_TIME = 0;
    //短信验证码
    public static int SendCode = 10;
    /**
     * intent
     */
    public static final String intent_id = "intent_id";
    public static final String intent_list_json = "intent_list_json";
    public static final String intent_position = "intent_position";
    public static final String intent_key = "intent_key";
    public static final String intent_Name = "intent_name";
    public static final String intent_Type = "intent_Type";
    public static final String intent_Res = "intent_Res";
    public static final String intent_Url = "intent_Url";
    public static final String intent_Model = "intent_model";
    public static final String intent_IsOneApp = "intent_IsOneApp";

    /**
     * sp
     */
    public static final String sp_User = "sp_User"; //用户信息
    public static final String sp_City = "sp_City"; //当前城市

    /**
     * apptype
     * 当前使用的是那个app
     */
    @StringDef({houseType_floor, houseType_house})
    public @interface houseType {
    }

    public static final String houseType_floor = "houseType_floor"; //楼盘找房
    public static final String houseType_house = "houseType_house"; //房源找房


    /**
     * event
     */
    public static final int event_refresh = 0; //刷新
    public static final int event_confirm_order_coupon = 1; //点击优惠券回调
    public static final int event_pay_h5 = 2; //h5支付成功回调
    /**
     * order
     */
    public static final String Order_ConfirmGoods = "确认收货"; //确认收货
    public static final String Order_ChaKanWuLiu = "查看物流"; //查看物流
    public static final String Order_delete = "删除"; //删除订单
    public static final String Order_comment = "评价"; //评价
    public static final String Order_cancel = "取消订单"; //取消
    public static final String Order_pay = "立即付款"; //去支付
    public static final String Order_shopping = "再次购买"; //再次购买
}
