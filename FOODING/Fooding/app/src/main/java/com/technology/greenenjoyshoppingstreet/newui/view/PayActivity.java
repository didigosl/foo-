package com.technology.greenenjoyshoppingstreet.newui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.duma.ld.baselibarary.model.EventModel;
import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.Ts;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.OrderPayInfoModel;
import com.technology.greenenjoyshoppingstreet.newui.model.PayHttpModel;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_READYTOPAY;

public class PayActivity extends BaseMyActivity {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.cb_paypal)
    CheckBox cbPaypal;
    @BindView(R.id.cb_xingyongka)
    CheckBox cbXingyongka;
    @BindView(R.id.cb_xianxia)
    CheckBox cbXianxia;
    @BindView(R.id.but_confirm)
    Button butConfirm;
    private OrderPayInfoModel orderPayInfoModel;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(EventModel eventModel) {
        switch (eventModel.getCode()) {
            case Constants.event_pay_h5:
                Ts.show(getString(R.string.payment_succesfull));
                StartActivityUtil.getInstance().goOrderList(mActivity, 0);
                finish();
                break;
        }
    }

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_pay;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.order_payment));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        orderPayInfoModel = (OrderPayInfoModel) getIntent().getSerializableExtra(Constants.intent_Model);
        tvMoney.setText("€ " + orderPayInfoModel.getTotalAmount());
    }


    @OnClick({R.id.cb_paypal, R.id.cb_xingyongka, R.id.cb_xianxia, R.id.but_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_paypal:
                setDefaut();
                cbPaypal.setChecked(true);
                break;
            case R.id.cb_xingyongka:
                setDefaut();
                cbXingyongka.setChecked(true);
                break;
            case R.id.cb_xianxia:
                setDefaut();
                cbXianxia.setChecked(true);
                break;
            case R.id.but_confirm:
                if (cbPaypal.isChecked()) {
                    // TODO: 2018/12/20
                } else if (cbXingyongka.isChecked()) {
                    toPayHttp();
                } else if (cbXianxia.isChecked()) {
                    StartActivityUtil.getInstance().goPay_xianxia(mActivity, orderPayInfoModel);
                }
                break;
        }
    }

    private void toPayHttp() {
        OkGo.<HttpResModel<PayHttpModel>>post(ORDER_READYTOPAY)
                .tag(this)
                .params("orderId", orderPayInfoModel.getOrderId())
                .execute(new MyJsonCallback<HttpResModel<PayHttpModel>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<PayHttpModel>> respons, HttpResModel<PayHttpModel> goodsModelHttpResModel) {
                        Intent intent = new Intent(mActivity, WebPayActivity.class);
                        intent.putExtra(Constants.intent_Model, new Gson().toJson(goodsModelHttpResModel.getData()));
                        StartActivityUtil.getInstance().startActivity(mActivity, intent);
                    }
                }.isDialog(mActivity));
    }

    private void setDefaut() {
        cbPaypal.setChecked(false);
        cbXianxia.setChecked(false);
        cbXingyongka.setChecked(false);
    }
}
