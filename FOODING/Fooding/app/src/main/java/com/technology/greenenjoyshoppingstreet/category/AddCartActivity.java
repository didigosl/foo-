package com.technology.greenenjoyshoppingstreet.category;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartItemBean;
import com.technology.greenenjoyshoppingstreet.category.adapter.SpecificationAdapter;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.newui.model.GoodsDetailMode;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity.SELECTSEPC_TYPE;
import static com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity.SEPCIFICATION_SELECT_SUCCESS;
import static com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity.SKU_BEAN;
import static com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity.TARGET_TYPE;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_ADD;

/**
 * Created by Administrator on 2017/12/30.
 */

public class AddCartActivity extends BaseActivity {

    public static final String PRODUCT_DETAIL = "PRODUCT_DETAIL";


    @BindView(R.id.icon_iv)
    ImageView iconIv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.select_mode_tv)
    TextView selectModeTv;
    @BindView(R.id.data_lv)
    ListView dataLv;
    @BindView(R.id.minus_rl)
    RelativeLayout minusRl;
    @BindView(R.id.count_tv)
    TextView countTv;
    @BindView(R.id.plus_rl)
    RelativeLayout plusRl;
    @BindView(R.id.select_count_rl)
    RelativeLayout selectCountRl;
    @BindView(R.id.confirm_add_bt)
    Button confirmAddBt;
    ProductDetailBean.DataBean dataBean;
    @BindView(R.id.root_v_rl)
    RelativeLayout rootVRl;
    SpecificationAdapter specificationAdapter;


    private String targetType;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);
        ButterKnife.bind(this);
        initViews();
        getIntentData();
    }

    public void updateSelectStr(String str) {

        if (!TextUtils.isEmpty(str)) {
            selectModeTv.setText(str);

        }

    }

    private int minIncart = 1;
    private int minToBuy = 1;

    private void initViews() {
        refreshCount(0);
        specificationAdapter = new SpecificationAdapter(this);
        dataLv.setAdapter(specificationAdapter);

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            targetType = intent.getStringExtra(TARGET_TYPE);
//            specificationIntent.putExtra(TARGET_TYPE, SELECTSEPC_TYPE);
            dataBean = intent.getParcelableExtra(PRODUCT_DETAIL);
            if (dataBean != null) {
                priceTv.setText(Tools.formatPriceText(dataBean.getPrice()));
                String url = dataBean.getCover();
                if (!TextUtils.isEmpty(url)) {
                    Glide.with(this).load(url)
                            .into
                                    (iconIv);
                } else {
                    Glide.with(this).load(R.mipmap.ic_app_launcher)
                            .into
                                    (iconIv);
                }
                specificationAdapter.setTotalKeys(getKeysBySKU(dataBean.getSkus()));
                specificationAdapter.updateData(dataBean.getSpecs(), true);
                if (specificationAdapter.isEmpty() && dataBean.getSkus() != null && dataBean.getSkus
                        ().size() == 1) {
                    List<ProductDetailBean.DataBean.SkusBean> selectList = new ArrayList<>();
                    selectList.add(dataBean.getSkus().get(0));
                    specificationAdapter.setSelectedSkuList(selectList);

                }

                if (!TextUtils.isEmpty(dataBean.getMinInCart()) && TextUtils.isDigitsOnly(dataBean
                        .getMinInCart())) {
                    minIncart = Integer.valueOf(dataBean.getMinInCart());
                }
                if (!TextUtils.isEmpty(dataBean.getMinToBuy()) && TextUtils.isDigitsOnly(dataBean
                        .getMinToBuy())) {
                    minToBuy = Integer.valueOf(dataBean.getMinToBuy());
                }
                productCount = minToBuy;
                countTv.setText(String.valueOf(minToBuy));
            }
        }


    }


    public Set<String> getKeysBySKU(List<ProductDetailBean.DataBean.SkusBean> skusBeanList) {
        Set<String> sets = new HashSet<>();
        if (skusBeanList != null && !skusBeanList.isEmpty()) {
            for (ProductDetailBean.DataBean.SkusBean skusBean : skusBeanList) {
                sets.addAll(skusBean.getSepcLst());
            }


        }
        return sets;

    }

    public List<ProductDetailBean.DataBean.SkusBean> getContainsSkuList(Set<String> keys) {
        List<ProductDetailBean.DataBean.SkusBean> beanList = new ArrayList<>();
        List<ProductDetailBean.DataBean.SkusBean> skusBeanList = dataBean.getSkus();
        if (skusBeanList != null && !skusBeanList.isEmpty()) {

            for (ProductDetailBean.DataBean.SkusBean skusBean : skusBeanList) {

                if (containsAll(skusBean.getSpecInfo(), keys)) {
                    beanList.add(skusBean);

                }
            }

        }

        return beanList;

    }


    public boolean containsAll(String info, Set<String> keys) {
        boolean contains = true;
        if (!TextUtils.isEmpty(info)) {
            if (keys != null && !keys.isEmpty()) {
                for (String str : keys) {
                    if (!info.contains(str)) {
                        return false;
                    }
                }


            }

        }
        return contains;
    }


    private void refreshCount(int delta) {
        if (productCount + delta >= 1) {
            productCount = productCount + delta;
        }
        countTv.setText(String.valueOf(productCount));
        minusRl.setEnabled(productCount > minToBuy);

    }

    private int productCount = 1;

    @OnClick({R.id.minus_rl, R.id.plus_rl, R.id.confirm_add_bt, R.id.root_v_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.minus_rl:
                refreshCount(-minIncart);
                break;
            case R.id.plus_rl:
                refreshCount(minIncart);
                break;
            case R.id.confirm_add_bt:


                List<ProductDetailBean.DataBean.SkusBean> beanList = specificationAdapter.getSelectedSkuList();
                if (beanList != null && beanList.size() == 1) {
                    if (TextUtils.equals(targetType, SELECTSEPC_TYPE)) {
                        ProductDetailBean.DataBean.SkusBean resultBean = beanList.get(0);
                        if (resultBean != null) {
                            resultBean.setSelectCount(String.valueOf(productCount));
                            Intent intent = new Intent();
                            intent.putExtra(SKU_BEAN, resultBean);
                            setResult(SEPCIFICATION_SELECT_SUCCESS, intent);
                            finish();
                        }


                    } else {
                        addCartList(beanList.get(0));
                    }
                } else {
                    tip(R.string.tip_select_specification_first);
                }

                break;
            case R.id.root_v_rl:
                finish();
                break;
        }
    }


    private void addCartList(final ProductDetailBean.DataBean.SkusBean skusBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CartItemBean>() {

            @Override
            public void onComplete(CartItemBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        tip(R.string.add_succesfull);
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
                if (skusBean != null) {
                    showLoadingDialog();
                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.SKU_ID, skusBean.getSkuId()));
                    list.add(new BasicKeyValuePair(ParameterConstant.NUM, countTv.getText().toString()));
                    return UserInfoManger.getSignList(list);
                }


                return null;

            }


            @Override
            public String createUrl() {

                return CART_ADD;
            }
        });
    }

}
