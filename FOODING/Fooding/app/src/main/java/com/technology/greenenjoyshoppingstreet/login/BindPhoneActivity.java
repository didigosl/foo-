package com.technology.greenenjoyshoppingstreet.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bern on 2017/12/14 0014.
 */

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.phone_delete_rl)
    RelativeLayout phoneDeleteRl;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.verify_code_tv)
    TextView verifyCodeTv;
    @BindView(R.id.verify_code_et)
    EditText verifyCodeEt;
    @BindView(R.id.next_step_bt)
    Button nextStepBt;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        setBarTitle("");
    }

    @OnClick(R.id.verify_code_tv)
    public void onViewClicked() {
    }
}
