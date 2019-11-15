package com.technology.greenenjoyshoppingstreet.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seven on 2016/4/26.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm, List<? extends  Fragment> list) {
        super(fm);
        if(list!=null){
            mList.addAll(list);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
