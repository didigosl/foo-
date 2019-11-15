package com.technology.greenenjoyshoppingstreet.mine.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Administrator on 2018/1/2.
 */

public class VipCategoryBean extends BaseBean {
    public static final String VIP_YEAR = "全年卡 (365天)";
    public static final String VIP = "会员卡";
    private String category;
    private String price;
    private boolean isCheck;

    public VipCategoryBean(String category, String price, boolean isCheck) {
        this.category = category;
        this.price = price;
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
