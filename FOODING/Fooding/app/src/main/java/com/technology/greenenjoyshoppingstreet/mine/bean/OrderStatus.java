package com.technology.greenenjoyshoppingstreet.mine.bean;

import android.text.TextUtils;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.utils.info.PhoneInfoManager;

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.ABNORMAL_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.ALL_ORDER_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.CANCLED_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.COMPLETED_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.PENDINGPAYMENT_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.TOBECOMMENTED_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.TOBEDELIVERED_CODE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.TOCONFIRMTHERECEIPT_CODE;

/**
 * Created by Administrator on 2018/1/1.
 */

public enum OrderStatus {
    /*************************CHINESE******************************/
    PENDINGPAYMENT(PENDINGPAYMENT_CODE, "待付款", R.drawable.ui40),
    TOBEDELIVERED(TOBEDELIVERED_CODE, "待发货", R.drawable.ui44),
    TOCONFIRMTHERECEIPT(TOCONFIRMTHERECEIPT_CODE, "待收货", R.drawable.ui17),
    TOBECOMMENTED(TOBECOMMENTED_CODE, "待评价", R.drawable.ic_transport_btn),
    COMPLETED(COMPLETED_CODE, "已完成", R.drawable.ic_transport_btn),
    ALL_ORDER(ALL_ORDER_CODE, "全部订单", R.drawable.ui50),
    REFUND(CANCLED_CODE, "退货退款", R.drawable.ui38),

    /************************SPANISH*****************************/

    PENDINGPAYMENT_ES(PENDINGPAYMENT_CODE, "Pendiente", R.drawable.ui40),
    TOBEDELIVERED_ES(TOBEDELIVERED_CODE, "Preparando", R.drawable.ui44),
    TOCONFIRMTHERECEIPT_ES(TOCONFIRMTHERECEIPT_CODE, "Enviado", R.drawable.ui17),
    TOBECOMMENTED_ES(TOBECOMMENTED_CODE, "En Evaluación", R.drawable.ic_transport_btn),
    COMPLETED_ES(COMPLETED_CODE, "Terminado", R.drawable.ic_transport_btn),
    ALL_ORDER_ES(ALL_ORDER_CODE, "Pedidos", R.drawable.ui50),
    REFUND_ES(CANCLED_CODE, "Devolución", R.drawable.ui38),


    ABNORMAL(ABNORMAL_CODE, "", R.drawable.ic_return_btn);



    private String statusCode;
    private String statusText;
    private int iamgeRes;

    OrderStatus(String statusCode, String statusText, int iamgeRes) {
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.iamgeRes = iamgeRes;
    }

    public static OrderStatus getStatus(String code) {
        if (!TextUtils.isEmpty(code)) {
            switch (code) {
                case PENDINGPAYMENT_CODE:
                    if (PhoneInfoManager.isChineseLanguage()){
                        return PENDINGPAYMENT;
                    }else {
                        return PENDINGPAYMENT_ES;
                    }
                case TOBEDELIVERED_CODE:
                    if (PhoneInfoManager.isChineseLanguage()){
                        return TOBEDELIVERED;
                    }else {
                        return TOBEDELIVERED_ES;
                    }
                case TOCONFIRMTHERECEIPT_CODE:
                    if (PhoneInfoManager.isChineseLanguage()){
                        return TOCONFIRMTHERECEIPT;
                    }else {
                        return TOCONFIRMTHERECEIPT_ES;
                    }
                case TOBECOMMENTED_CODE:
                    if (PhoneInfoManager.isChineseLanguage()){
                        return TOBECOMMENTED;
                    }else {
                        return TOBECOMMENTED_ES;
                    }
                case COMPLETED_CODE:
                    if (PhoneInfoManager.isChineseLanguage()){
                        return COMPLETED;
                    }else {
                        return COMPLETED_ES;
                    }
                case CANCLED_CODE:
                    if (PhoneInfoManager.isChineseLanguage()){
                        return REFUND;
                    }else {
                        return REFUND_ES;
                    }
                case ABNORMAL_CODE:
                    return ABNORMAL;


            }


        }
        return ABNORMAL;

    }

    public int getIamgeRes() {
        return iamgeRes;
    }

    public void setIamgeRes(int iamgeRes) {
        this.iamgeRes = iamgeRes;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
