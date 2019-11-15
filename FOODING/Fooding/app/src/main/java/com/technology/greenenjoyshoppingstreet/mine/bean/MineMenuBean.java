package com.technology.greenenjoyshoppingstreet.mine.bean;

/**
 * Created by Administrator on 2017/12/12.
 */

public class MineMenuBean {


    public static final String MY_COUPON = "我的优惠券";

    public static final String MY_ACCOUNT = "我的账户";
    public static final String PERSONAL_SETTING = "个人设置";
    public static final String OPINION_FEEDBACK = "意见反馈";
    public static final String HELP_CENTER = "帮助中心";

    public static final String INVITE_FRIEND = "邀请好友";



    public static final String PERSONAL_INFO = "个人资料";

    public static final String DELIVERY_ADDRESS = "收货地址";

    public static final String CLEAR_CACHE = "清除缓存";

    public static final String ABOUT_US = "关于我们";

    public static final String PERSONAL_INFO_ES = "Mis Datos";

    public static final String DELIVERY_ADDRESS_ES = "Direcciones";

    public static final String CLEAR_CACHE_ES = "Borrar Cache";

    public static final String ABOUT_US_ES = "Sobre nosotros";


    private String name;

    private Class targetClass;

    public MineMenuBean(String name, Class targetClass) {
        this.name = name;
        this.targetClass = targetClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }
}
