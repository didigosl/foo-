package com.technology.greenenjoyshoppingstreet.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.login.bean.ProtocolBean;
import com.technology.greenenjoyshoppingstreet.login.bean.WebBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.ArticleDetailBean;
import com.technology.greenenjoyshoppingstreet.sns.WeiXinLoginHelper;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.Constant.GO_REQUEST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ARTICLE_GET;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ARTICLE_GETREGTERM;


/**
 * @version V1.0
 * @Title: Login activity
 * @date 2017.05.16
 * @Title: 登录界面
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.weixin_loging_ll)
    LinearLayout weixinLogingLl;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.confirm_bt)
    Button confirmBt;
    @BindView(R.id.phone_delete_rl)
    RelativeLayout phoneDeleteRl;
    @BindView(R.id.protocol_tv)
    TextView protocolTv;
    @BindView(R.id.pre_phone_tv)
    TextView pre_phone_tv;
    @BindView(R.id.phone_login_hint)
    TextView phone_login_hint;
    WeiXinLoginHelper weiXinLoginHelper;
    private OptionsPickerView pvOptions;
    private List<String> mList;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
        mList = getCodeList();
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                pre_phone_tv.setText(mList.get(options1));
            }
        }).build();
        pvOptions.setPicker(mList, null, null);
        pre_phone_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvOptions.show();
            }
        });
        phone_login_hint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }

    public List<String> getCodeList() {
        List<String> list = new ArrayList<>();
        list.add("+86");
        list.add("+34");
        /*list.add("+351");
        list.add("+33");
        list.add("+39");
        list.add("+30");
        list.add("+31");
        list.add("+32");
        list.add("+350");
        list.add("+352");
        list.add("+353");
        list.add("+355");
        list.add("+356");
        list.add("+357");
        list.add("+358");
        list.add("+359");
        list.add("+36");
        list.add("+370");
        list.add("+371");
        list.add("+372");
        list.add("+373");
        list.add("+374");
        list.add("+374 47");
        list.add("+376");
        list.add("+377");
        list.add("+378");
        list.add("+379");
        list.add("+380");
        list.add("+381");
        list.add("+382");
        list.add("+383");
        list.add("+385");
        list.add("+386");
        list.add("+387");
        list.add("+389");
        list.add("+40");
        list.add("+41");
        list.add("+420");
        list.add("+421");
        list.add("+423");
        list.add("+43");
        list.add("+44");
        list.add("+44 1481");
        list.add("+44 1534");
        list.add("+44 1624");*/
        return list;
    }

    private void initViews() {
        setBarTitle("");
        getProtocol();
        weiXinLoginHelper = new WeiXinLoginHelper(this);
    }

    @OnClick({R.id.phone_delete_rl, R.id.confirm_bt, R.id.protocol_tv, R.id.weixin_loging_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin_loging_ll:
                weiXinLoginHelper.login();
                break;
            case R.id.phone_delete_rl:
                phoneEt.setText("");
                break;
            case R.id.confirm_bt:
                if (!TextUtils.isEmpty(phoneEt.getText().toString())) {
                    Intent intent = new Intent(this, VerifyCodeActivity.class);
                    intent.putExtra(VerifyCodeActivity.PHONE_NUMBER, phoneEt.getText().toString());
                    intent.putExtra(VerifyCodeActivity.PHONE_code, pre_phone_tv.getText().toString());
                    startActivity(intent);
                } else {
                    tip(R.string.enter_correct_phone);
                }
                break;

            case R.id.protocol_tv:
                Intent protocolIntent = new Intent(this, WebActivity.class);
                protocolIntent.putExtra(Constant.WEB_BEAN, webBean);
                startActivity(protocolIntent);
                break;
        }
    }

    private WebBean webBean;
    private void getProtocol() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ArticleDetailBean>() {
            @Override
            public void onComplete(ArticleDetailBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        webBean = new WebBean();
                        webBean.setData(bean.getData().getContent());
                        webBean.setTitle(bean.getData().getTitle());
                    }
                }
            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.ARTICLE_ID, "104"));
                return list;
            }


            @Override
            public String createUrl() {
                return ARTICLE_GET;
            }
        });
    }

//    private void getProtocol() {
//        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ProtocolBean>() {
//            @Override
//            public void onComplete(ProtocolBean bean, NetUtils.NetRequestStatus netRequestStatus) {
//                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
//                    if (Constant.RESULT_OK.equals(bean.getCode())) {
//                        webBean = new WebBean();
//                        webBean.setData(bean.getData());
//                        webBean.setTitle(getString(R.string.privacy));
//                    }
//                }
//            }
//
//            @Override
//            public NetRequestConfig.Method getMethod() {
//                return NetRequestConfig.Method.POST;
//            }
//
//            @Override
//            public Object submitNetParams() {
//                return GO_REQUEST;
//            }
//
//
//            @Override
//            public String createUrl() {
//                return ARTICLE_GETREGTERM;
//            }
//        });
//    }

}
