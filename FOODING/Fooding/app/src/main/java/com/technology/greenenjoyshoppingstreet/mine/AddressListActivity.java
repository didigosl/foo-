package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.mine.adapter.MyAddressAdapter;
import com.technology.greenenjoyshoppingstreet.mine.bean.AddressListBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.SELECTADDRESS_SUCCESS;
import static com.technology.greenenjoyshoppingstreet.mine.EditAddressActivity.ADDRESS_BEAN;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ADDRESS_DELETE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ADDRESS_LIST;

/**
 * Created by Bern on 2017/12/13 0013.
 */

public class AddressListActivity extends BaseActivity {
    public static final String SELECT_ADDRESS = "SELECT_ADDRESS";
    @BindView(R.id.data_lv)
    ListViewInScroll dataLv;
    MyAddressAdapter myAddressAdapter;
    @BindView(R.id.add_address_bt)
    Button addAddressBt;


    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAddressList();
    }

    private void initViews() {
        setBarTitle(getString(R.string.directions_list));
        myAddressAdapter = new MyAddressAdapter(this);
        Intent intent = getIntent();
        if (intent != null) {
            myAddressAdapter.setDisplayCheck(intent.getBooleanExtra(SELECT_ADDRESS, false));
        }
        dataLv.setAdapter(myAddressAdapter);

    }

    @OnClick(R.id.add_address_bt)
    public void onViewClicked() {
        startActivity(new Intent(this, EditAddressActivity.class));
    }

    public void selectAddress(AddressListBean.DataBean orderAddressInfo) {
        Intent intent = new Intent();
        intent.putExtra(ADDRESS_BEAN, orderAddressInfo);
        setResult(SELECTADDRESS_SUCCESS, intent);
        finish();
    }


    public void deleteAddress(final AddressListBean.DataBean addressBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AddressListBean>() {

            @Override
            public void onComplete(AddressListBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        getAddressList();
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
                if (addressBean != null) {
                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.ADDRESS_ID, addressBean
                            .getAddressId()));
                    return UserInfoManger.getSignList(list);

                }
                return null;

            }


            @Override
            public String createUrl() {

                return ADDRESS_DELETE;
            }
        });

    }

    private void getAddressList() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AddressListBean>() {

            @Override
            public void onComplete(AddressListBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        myAddressAdapter.updateData(bean.getData(), true);
                        UserInfoManger.setAddressList(bean.getData());
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
                showLoadingDialog();

                List<KeyValuePair> list = new ArrayList<>();
                return UserInfoManger.getSignList(list);

            }


            @Override
            public String createUrl() {

                return ADDRESS_LIST;
            }
        });
    }


}
