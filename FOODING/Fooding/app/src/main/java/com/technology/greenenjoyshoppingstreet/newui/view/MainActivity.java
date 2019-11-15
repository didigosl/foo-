package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;

import butterknife.BindView;

public class MainActivity extends BaseMyActivity {
    @BindView(R.id.rdoBtn_home)
    RadioButton rdoBtnHome;
    @BindView(R.id.rdoBtn_classify)
    RadioButton rdoBtnClassify;
    @BindView(R.id.rdoBtn_shopping_cart)
    RadioButton rdoBtnShoppingCart;
    @BindView(R.id.rdoBtn_me)
    RadioButton rdoBtnMe;
    @BindView(R.id.radioGroup_p)
    RadioGroup radioGroupP;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_new_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        radioGroupP.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            }
        });
    }
}
