package com.technology.greenenjoyshoppingstreet.newui.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.Ts;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.main.MainActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.OrderPayInfoModel;
import com.technology.greenenjoyshoppingstreet.newui.model.XianXiaModel;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.MAIN_GETBANKINFO;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_OFFLINEPAY;

public class XianXiaPayActivity extends BaseMyActivity {
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.but_confirm)
    Button butConfirm;
    private OrderPayInfoModel orderPayInfoModel;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_xianxia_pay;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.offline_payment));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        orderPayInfoModel = (OrderPayInfoModel) getIntent().getSerializableExtra(Constants.intent_Model);
        tvMoney.setText("€ " + orderPayInfoModel.getTotalAmount());
        starRefresh();
    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<XianXiaModel>>post(MAIN_GETBANKINFO)
                .tag(this)
                .params("orderId", orderPayInfoModel.getOrderId())
                .execute(new MyJsonCallback<HttpResModel<XianXiaModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<XianXiaModel>> respons, HttpResModel<XianXiaModel> confirmOrderModelHttpResModel) {
                        tvUser.setText("银行账号：" + confirmOrderModelHttpResModel.getData().getBankAccount());
                        tvName.setText("开户行：" + confirmOrderModelHttpResModel.getData().getBank());
                        tvInfo.setText(confirmOrderModelHttpResModel.getData().getBankIntro());
                    }
                });
    }

    @OnClick(R.id.but_confirm)
    public void onViewClicked() {
        OkGo.<HttpResModel<Void>>post(ORDER_OFFLINEPAY)
                .tag(this)
                .params("orderId", orderPayInfoModel.getOrderId())
                .execute(new MyJsonCallback<HttpResModel<Void>>() {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<Void>> respons, HttpResModel<Void> confirmOrderModelHttpResModel) {
                        Ts.show("下单成功！");
                        Intent intent = new Intent(XianXiaPayActivity.this, MainActivity.class);
                        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                }.isDialog(mActivity));
    }
}
