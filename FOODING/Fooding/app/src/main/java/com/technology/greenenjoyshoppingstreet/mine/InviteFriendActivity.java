package com.technology.greenenjoyshoppingstreet.mine;

import android.os.Bundle;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/12.
 */

public class InviteFriendActivity extends BaseActivity {
    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
    }
}
