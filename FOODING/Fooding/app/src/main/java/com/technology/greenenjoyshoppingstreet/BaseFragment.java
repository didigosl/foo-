package com.technology.greenenjoyshoppingstreet;

import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.technology.greenenjoyshoppingstreet.utils.RefreshListener;


/**
 * Created by Administrator on 2017/5/21.
 */

public class BaseFragment extends Fragment implements View.OnClickListener , RefreshListener {



    @Override
    public void onClick(View v) {

    }

    public void tip(String str) {
        getBaseActivity().tip(str);

    }

    public void tip(@StringRes int str) {
        getBaseActivity().tip(str);

    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public void showLoadingDialog() {
        getBaseActivity().showLoadingDialog();
    }

    public void cancelLoadingDialog() {
        getBaseActivity().cancelLoadingDialog();
    }



    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        initRefreshList(getView());
    }

    private void initRefreshList(View rootView) {
//        View view = rootView.findViewById(R.id.no_list_data_refresh_rl);
//        if (view != null) {
//
//
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    refresh();
//                }
//            });
//
//        }


    }
    public void setNoDataVisible(int visibility) {

//        View view = getView().findViewById(R.id.no_list_data_refresh_rl);
//        if (view!=null){
//            view.setVisibility(visibility);
//        }
    }
    @Override
    public void refresh() {
    }
}
