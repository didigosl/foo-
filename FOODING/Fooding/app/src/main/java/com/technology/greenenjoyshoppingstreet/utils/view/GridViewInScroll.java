package com.technology.greenenjoyshoppingstreet.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/9/10.
 */

public class GridViewInScroll extends GridView {
    public GridViewInScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public GridViewInScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public GridViewInScroll(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    MyScrollChanged myScrollChanged;

    public MyScrollChanged getMyScrollChanged() {
        return myScrollChanged;
    }

    public void setMyScrollChanged(MyScrollChanged myScrollChanged) {
        this.myScrollChanged = myScrollChanged;
    }

    public static interface MyScrollChanged {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (myScrollChanged != null) {
            myScrollChanged.onScrollChanged(l, t, oldl, oldt);
        }
    }
}
