package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Bern on 2018/2/12 0012.
 */

public class BuyNowBean extends BaseBean {


    /**
     * data : {"addressId":"5","areaId":"3300","receiveMan":"李小四","receivePhone":"13800138000","receiveArea":null,"receiveAddress":"XX街XX号","totalFee":"100.00","totalAmount":"109.95","expressFee":"10.00","totalDiscount":"0.05","totalRebate":"1.00","userId":"11","levelId":"2","coupons":[],"carts":["13"]}
     * code : 0
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
