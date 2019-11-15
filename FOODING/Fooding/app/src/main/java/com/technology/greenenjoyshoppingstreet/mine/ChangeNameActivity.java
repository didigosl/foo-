package com.technology.greenenjoyshoppingstreet.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.utils.BaseBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
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

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_UPDATE;

/**
 * Created by Administrator on 2018/1/4.
 */

public class ChangeNameActivity extends BaseActivity {
    @BindView(R.id.finish_rl)
    RelativeLayout finishRl;
    @BindView(R.id.name_et)
    EditText nameEt;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        setBarTitle(getString(R.string.change_name));
        nameEt.setText(UserInfoManger.getLoginName());

    }

    @OnClick(R.id.finish_rl)
    public void onViewClicked() {
        updateName();
    }

    private void updateName() {


        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<BaseBean>() {

            @Override
            public void onComplete(BaseBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        finish();

                    } else {
                        tip(bean.getMsg());
                    }


                } else {
                    tip(netRequestStatus.getNote());

                }

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                if (!TextUtils.isEmpty(nameEt.getText())) {

                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.NAME, nameEt.getText().toString()));

                    return UserInfoManger.getSignList(list);

                } else {
                    tip(getString(R.string.enter_name_plase));
                }

                return null;


            }


            @Override
            public String createUrl() {

                return USER_UPDATE;
            }
        });
    }
}
