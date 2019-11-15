package com.technology.greenenjoyshoppingstreet.main;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Bern on 2017/7/19 0019.
 */

public class WelcomeActivity extends BaseActivity implements Animator.AnimatorListener {

    @BindView(R.id.welcome_image_view)
    ImageView welcomeImageView;
    /**
     * Intent.
     */
    private Intent intent;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initViews();

    }


    /**
     * On start.
     */
    @Override
    protected void onStart() {
        super.onStart();
    }


    /**
     * Init views.
     */
    private void initViews() {

        ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(welcomeImageView, "alpha", 1f, 1f)
                .setDuration(3000);
        mObjectAnimator.addListener(this);
        mObjectAnimator.start();
    }

    /**
     * On animation start.
     *
     * @param animation the animation
     */
    @Override
    public void onAnimationStart(Animator animation) {

    }

    /**
     * On animation end.
     *
     * @param animation the animation
     */
    @Override
    public void onAnimationEnd(Animator animation) {
        startActivity(new Intent(this, MainActivity.class));
//        if (PreferencesUtil.getSharedBooleanData(UserInfoManger.HIDE_GUIDE)) {
//            startActivity(new Intent(this, MainActivity.class));
//        } else {
//            startActivity(new Intent(this, GuideActivity.class));
//        }
        finish();
    }

    /**
     * On animation cancel.
     *
     * @param animation the animation
     */
    @Override
    public void onAnimationCancel(Animator animation) {

    }

    /**
     * On animation repeat.
     *
     * @param animation the animation
     */
    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}