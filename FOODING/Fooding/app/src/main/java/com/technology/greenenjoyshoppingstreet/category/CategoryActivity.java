package com.technology.greenenjoyshoppingstreet.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.adapter.CategorySecondAdapter;
import com.technology.greenenjoyshoppingstreet.category.adapter.MainCategoryAdapter;
import com.technology.greenenjoyshoppingstreet.main.bean.AdBean;
import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.AD_GET;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CATEGORY_LIST;

/**
 * Created by Administrator on 2017/12/10.
 */

public class CategoryActivity extends BaseActivity {

    @BindView(R.id.category_lv)
    ListView categoryLv;
    @BindView(R.id.refresh_fl)
    PtrClassicFrameLayout refreshFl;

    @BindView(R.id.goods_lv)
    ListView goodsLv;

    MainCategoryAdapter mainCategoryAdapter;

    CategorySecondAdapter categorySubTreeAdapter;
    @BindView(R.id.banner_cb)
    ConvenientBanner bannerCb;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        setBarTitle(getString(R.string.category));
        setBackVisible(View.GONE);
        mainCategoryAdapter = new MainCategoryAdapter(this);
        categoryLv.setAdapter(mainCategoryAdapter);

        categorySubTreeAdapter = new CategorySecondAdapter(this);
        goodsLv.setAdapter(categorySubTreeAdapter);
        getCategoryList();
    }

    public void updateSub(CategoryBean.DataBean dataBean) {

        categorySubTreeAdapter.updateData(dataBean.getSons(), true);
        getAD(dataBean);

    }

    List<AdBean.DataBean> bannerList = new ArrayList<>();

    private void refreshBanner(final List<AdBean.DataBean> data) {
        bannerList.clear();
        if (data != null && !data.isEmpty()) {
            bannerList.addAll(data);

        }
        if (bannerList.isEmpty()) {
            bannerCb.setVisibility(View.GONE);

        } else {
            bannerCb.setVisibility(View.VISIBLE);
            bannerCb.setPages(
                    new CBViewHolderCreator<Holder>() {
                        @Override
                        public Holder createHolder() {
                            return new Holder<AdBean.DataBean>() {
                                private ImageView imageView;

                                @Override
                                public View createView(Context context) {
                                    View root = LayoutInflater.from(context)
                                            .inflate(R
                                                    .layout
                                                    .item_banner, null);
                                    imageView = (ImageView) root.findViewById(R.id
                                            .banner_ic);

                                    return root;
                                }

                                @Override
                                public void UpdateUI(Context context, int position, AdBean.DataBean data) {
//                                    if (data != null && !TextUtils.isEmpty(data.getImg())) {
//
//                                        Glide.with(context).load(data.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shape_defualt_bg)
//                                                .into
//                                                        (imageView);
//                                    } else {
//                                        Glide.with(context).load(R.drawable.shape_defualt_bg).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shape_defualt_bg)
//                                                .into
//                                                        (imageView);
//                                    }
                                    ImageLoader.with(data.getImg(), imageView);

                                }
                            };
                        }
                    }, data);
            bannerCb.startTurning(5000);
            bannerCb.setPageIndicator(new int[]{R.drawable.ui39, R
                    .drawable.ui35});
            //设置指示器位置（左、中、右）
            bannerCb.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            bannerCb.setPointViewVisible(true);
            bannerCb.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    AdBean.DataBean listBean = data.get(position);
//                    if (listBean != null) {
//                        if (TextUtils.equals(AdBean.GOODS, listBean.getLinkType())) {
//                            Intent intent = new Intent(CategoryActivity.this, ProductDetailActivity.class);
//                            intent.putExtra(GOOD_ID, listBean.getLinkId());
//                            startActivity(intent);
//                        } else if (TextUtils.equals(AdBean.CATEGORY, listBean.getLinkType())) {
//                            CategoryBean.DataBean dataBean = new CategoryBean.DataBean();
//                            dataBean.setCategoryId(listBean.getLinkId());
//                            dataBean.setCategoryName(listBean.getAdName());
//                            ArrayList<CategoryBean.DataBean> arrayList = new ArrayList<>();
//                            arrayList.add(dataBean);
//                            Intent intent = new Intent(CategoryActivity.this, SecondCategoryActivity.class);
//                            intent.putParcelableArrayListExtra(SecondCategoryActivity.CATEGORY_LIST, arrayList);
//                            intent.putExtra(CATEGORY_INDEX, 0);
//                            intent.putExtra(CATEGORY_NAME, listBean.getAdName());
//                            startActivity(intent);
//                        }
//
//                    }
                }
            });
        }


    }

    private void getAD(final CategoryBean.DataBean dataBean) {

        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<AdBean>() {

            @Override
            public void onComplete(AdBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {

                        refreshBanner(bean.getData());
                    }


                }
                cancelLoadingDialog();

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.TYPE, AdBean.CATEGORY_TYPE));
                list.add(new BasicKeyValuePair(ParameterConstant.CATEGORYID, dataBean.getCategoryId()));
                return UserInfoManger.getSignList(list);

            }


            @Override
            public String createUrl() {

                return AD_GET;
            }
        });
    }


    private void getCategoryList() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CategoryBean>() {

            @Override
            public void onComplete(CategoryBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        List<CategoryBean.DataBean> beanList = bean.getData();
                        if (beanList != null && !beanList.isEmpty()) {
                            CategoryBean.DataBean dataBean = beanList.get(0);
                            if (dataBean != null) {
                                dataBean.setChecked(true);
                                updateSub(dataBean);
                            }
                        }
                        mainCategoryAdapter.updateData(beanList, true);
                        mainCategoryAdapter.checkItem(0);


                    }

                }
                cancelLoadingDialog();

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                showLoadingDialog();
                List<KeyValuePair> list = new ArrayList<>();
                list.add(new BasicKeyValuePair(ParameterConstant.TYPE, "children"));
                return list;


            }


            @Override
            public String createUrl() {

                return CATEGORY_LIST;
            }
        });

    }
}
