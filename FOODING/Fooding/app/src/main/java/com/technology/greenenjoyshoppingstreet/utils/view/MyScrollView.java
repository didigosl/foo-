package com.technology.greenenjoyshoppingstreet.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Bern on 2018/7/25 0025.
 */

public class MyScrollView extends ScrollView {
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        boolean onInterceptTouchEvent =super.onInterceptTouchEvent(ev);
        boolean ontouchEvent  = mGestureDetector.onTouchEvent(ev);
        return onInterceptTouchEvent
                || ontouchEvent;
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            boolean result  = false;
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                result =   true;
            }
            return result;
        }
    }
}