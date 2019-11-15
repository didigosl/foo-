package com.technology.greenenjoyshoppingstreet.category;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.cart.bean.CartItemBean;
import com.technology.greenenjoyshoppingstreet.category.adapter.EvaluateAdapter;
import com.technology.greenenjoyshoppingstreet.category.bean.BuyNowBean;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.login.LoginActivity;
import com.technology.greenenjoyshoppingstreet.mine.BuyVIPActivity;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.PhoneInfoManager;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;
import com.technology.greenenjoyshoppingstreet.utils.view.ListViewInScroll;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.category.ConfirmOrderActivity.ORDER_ITEMS;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.CART_ADD;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.GOODS_SPU_GET;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_BUYNOW;

/**
 * Created by Bern on 2017/12/29 0029.
 */

public class ProductDetailActivity extends BaseActivity {

    public static final String GOOD_ID = "GOOD_ID";
    @BindView(R.id.buy_bt)
    Button buyBt;
    @BindView(R.id.specification_ll)
    LinearLayout specificationLl;
    @BindView(R.id.add_cart_ll)
    LinearLayout addCartLl;
    @BindView(R.id.banner_cb)
    ConvenientBanner bannerCb;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.price_tv)
    TextView priceTv;
    @BindView(R.id.vip_info_tv)
    TextView vipInfoTv;
    @BindView(R.id.vip_rl)
    RelativeLayout vipRl;
    @BindView(R.id.product_info_tv)
    HtmlTextView productInfoTv;
    @BindView(R.id.evaluate_count_tv)
    TextView evaluateCountTv;
    @BindView(R.id.all_evaluate_rl)
    RelativeLayout allEvaluateRl;
    @BindView(R.id.evaluate_data_lv)
    ListViewInScroll evaluateDataLv;
    @BindView(R.id.root_sv)
    ScrollView rootSv;
    @BindView(R.id.hot_tag_group)
    TagView hotTagGroup;

    @BindView(R.id.old_price_tv)
    TextView oldPriceTv;
    @BindView(R.id.banner_fl)
    FrameLayout bannerFl;
    @BindView(R.id.product_info_rl)
    RelativeLayout productInfoRl;

    private String goodId;
    EvaluateAdapter evaluateAdapter;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        initViews();
        getIntentData();

    }

    private void initViews() {
        setBarTitle(getString(R.string.product_detail));
        vipInfoTv.setText("升级VIP，可获取返利");


        evaluateAdapter = new EvaluateAdapter(this);
        evaluateDataLv.setAdapter(evaluateAdapter);
        oldPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        ViewGroup.LayoutParams layoutParams = bannerFl.getLayoutParams();
        layoutParams.height = PhoneInfoManager.getWidthPixels();
        bannerFl.setLayoutParams(layoutParams);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            goodId = intent.getStringExtra(GOOD_ID);
            if (!TextUtils.isEmpty(goodId)) {
                getProductDetail();

            }
        }

    }

    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url = null;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "img");
            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight());
            return drawable;
        }
    };

    List<String> bannerList = new ArrayList<>();

    private void refreshBanner(final List<String> data) {
        bannerList.clear();
        if (data != null && !data.isEmpty()) {
            bannerList.addAll(data);

        }
        bannerCb.setPages(
                new CBViewHolderCreator<Holder>() {
                    @Override
                    public Holder createHolder() {
                        return new Holder<String>() {
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
                            public void UpdateUI(Context context, int position, String data) {
//                                if (data != null && !TextUtils.isEmpty(data)) {
//
//                                    Glide.with(context).load(data).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shape_defualt_bg)
//                                            .into
//                                                    (imageView);
//                                } else {
//                                    Glide.with(context).load(R.drawable.shape_defualt_bg).diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.shape_defualt_bg)
//                                            .into
//                                                    (imageView);
//                                }

                                ImageLoader.with(data, imageView);
                            }
                        };
                    }
                }, data);
        bannerCb.startTurning(5000);
        bannerCb.setPageIndicator(new int[]{R.drawable.banner_white, R
                .drawable.banner_purple});
        //设置指示器位置（左、中、右）
        bannerCb.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        bannerCb.setPointViewVisible(true);
        bannerCb.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                    BannerBean.DataBean dataBean = data.get(position);
//                    if (dataBean != null) {
//
//                        Intent intent = new Intent(Main.this, WebActivity.class);
//                        intent.putExtra(WebActivity.WEB_BEAN, dataBean);
//                        startActivity(intent);
//
//
//                    }
            }
        });

    }

    ProductDetailBean.DataBean dataBean;

    Handler handler = new Handler();

    private void getProductDetail() {

        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<ProductDetailBean>() {

            @Override
            public void onComplete(ProductDetailBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        vipRl.setVisibility(View.GONE);
                        dataBean = bean.getData();
                        if (dataBean != null) {
                            if (!TextUtils.isEmpty(dataBean.getMinInCart()) && TextUtils.isDigitsOnly(dataBean.getMinInCart())) {
                                minIncart = Integer.valueOf(dataBean.getMinInCart());
                            }
                            if (!TextUtils.isEmpty(dataBean.getMinToBuy()) && TextUtils.isDigitsOnly(dataBean.getMinToBuy())) {
                                minToBuy = Integer.valueOf(dataBean.getMinToBuy());
                            }
                            List<String> pics = new ArrayList<String>();
                            if (!TextUtils.isEmpty(dataBean.getCover())) {
                                pics.add(dataBean.getCover());
                            }
                            if (dataBean.getPics() != null && !dataBean.getPics().isEmpty()) {
                                pics.addAll(dataBean.getPics());
                            }
                            refreshBanner(pics);
                            nameTv.setText(dataBean.getSpuName());
                            priceTv.setText(Tools.formatPriceText(dataBean.getPrice()));
                            oldPriceTv.setVisibility(View.GONE);
                            if (Tools.isDouble(dataBean.getOriginPrice()) && Tools.isDouble(dataBean.getPrice())) {
                                if (Double.valueOf(dataBean.getOriginPrice()) > Double.valueOf(dataBean.getPrice())) {
                                    oldPriceTv.setVisibility(View.VISIBLE);
                                    oldPriceTv.setText(Tools.formatPriceText(dataBean.getOriginPrice()));

                                }
                            }
                            if (!TextUtils.isEmpty(dataBean.getContent())) {
                                productInfoRl.setVisibility(View.VISIBLE);
                                HtmlHttpImageGetter getter = new HtmlHttpImageGetter
                                        (productInfoTv, null, true);

//                                getter.enableCompressImage(false);
                                productInfoTv.setHtml(dataBean.getContent(),
                                        getter);
//                                productInfoTv.setText(Html.fromHtml(dataBean.getContent()));
//                                Spanned sp = Html.fromHtml(dataBean.getContent(), new Html.ImageGetter() {
//                                    @Override
//                                    public Drawable getDrawable(String source) {
//                                        InputStream is = null;
//                                        try {
//                                            is = (InputStream) new URL(source).getContent();
//                                            Drawable d = Drawable.createFromStream(is, "src");
//                                            d.setBounds(0, 0, d.getIntrinsicWidth(),
//                                                    d.getIntrinsicHeight());
//                                            is.close();
//                                            return d;
//                                        } catch (Exception e) {
//                                            return null;
//                                        }
//                                    }
//                                }, null);
//                                productInfoTv.setText(sp);
                            } else {
                                productInfoRl.setVisibility(View.GONE);
                            }
                            List<String> list = new ArrayList<>();
                            List<ProductDetailBean.DataBean.CouponBean> couponBeenList = dataBean.getCoupon();
                            if (couponBeenList != null && !couponBeenList.isEmpty()) {
                                for (ProductDetailBean.DataBean.CouponBean vipsBean : couponBeenList) {
                                    list.add(Tools.concatAll(getString(R.string.update), vipsBean.getLevelName(), getString(R.string.member_might_descount)+"：€ ", vipsBean.getRebate()));
                                }


                            }
//                            List<ProductDetailBean.DataBean.SkusBean> skusBeanList = dataBean.getSkus();
//                            if (skusBeanList != null && skusBeanList.size() == 1) {
//                                selectskusBean = skusBeanList.get(0);
//                                selectskusBean.setSelectCount("1");
//
//                            }

                            if (list != null && !list.isEmpty()) {
                                vipRl.setVisibility(View.VISIBLE);
                                vipInfoTv.setText(TextUtils.join("\n", list));
                            }
                            ProductDetailBean.DataBean.CommentsBean commentsBean = dataBean.getComments();
                            if (commentsBean != null) {
                                evaluateAdapter.updateData(commentsBean.getList(), true);
                                evaluateCountTv.setText("用户评价（" + evaluateAdapter.getCount() + "）");
                            }

                            hotTagGroup.removeAll();
                            if (list != null && !list.isEmpty()) {
                                Tag tag = new Tag(getString(R.string.rebaja));
                                tag.layoutColor = getResources().getColor(R.color.main_price);
                                tag.radius = 0;
                                tag.layoutBorderSize = 2;
                                tag.layoutBorderColor = getResources().getColor(R.color.main_price);
                                tag.tagTextColor = getResources().getColor(R.color.white_pure);
                                tag.tagTextSize = 14;
                                hotTagGroup.addTag(tag);
                            }
                            List<ProductDetailBean.DataBean.LabelsBean> labelsBeans = dataBean.getLabels();
                            if (labelsBeans != null && !labelsBeans.isEmpty()) {
                                for (ProductDetailBean.DataBean.LabelsBean label : labelsBeans) {
                                    if (!TextUtils.isEmpty(label.getLabelName())) {
                                        Tag tag = new Tag(label.getLabelName());
                                        tag.layoutColor = getResources().getColor(R.color.main_price);
                                        tag.radius = 0;
                                        tag.layoutBorderSize = 2;
                                        tag.layoutBorderColor = getResources().getColor(R.color.main_price);
                                        tag.tagTextColor = getResources().getColor(R.color.white_pure);
                                        tag.tagTextSize = 14;
                                        hotTagGroup.addTag(tag);
                                    }


                                }

                            }

                        }
                        rootSv.smoothScrollTo(0, 0);

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

                list.add(new BasicKeyValuePair(ParameterConstant.SPU_ID, goodId));
                return list;


            }


            @Override
            public String createUrl() {

                return GOODS_SPU_GET;
            }
        });
    }

    public static final int SEPCIFICATION_SELECT = 3434;
    public static final int SEPCIFICATION_SELECT_SUCCESS = 5634;
    public static final String SKU_BEAN = "SKU_BEAN";
    public static final String TARGET_TYPE = "TARGET_TYPE";
    public static final String SELECTSEPC_TYPE = "SELECTSEPC_TYPE";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SEPCIFICATION_SELECT_SUCCESS) {
            if (data != null) {
                selectskusBean = data.getParcelableExtra(SKU_BEAN);

            }
        }

    }

    @OnClick({R.id.buy_bt, R.id.specification_ll, R.id.add_cart_ll, R.id.vip_rl, R.id.all_evaluate_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buy_bt:
                if (UserInfoManger.isLogin()) {
                    if (selectskusBean != null) {
                        buyNow(selectskusBean);
                    } else {
                        tip(getString(R.string.tip_select_specification_first));

                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }


                break;
            case R.id.specification_ll:
                Intent specificationIntent = new Intent(this, AddCartActivity.class);
                specificationIntent.putExtra(AddCartActivity.PRODUCT_DETAIL, dataBean);
                specificationIntent.putExtra(TARGET_TYPE, SELECTSEPC_TYPE);
                startActivityForResult(specificationIntent, SEPCIFICATION_SELECT);
                break;
            case R.id.add_cart_ll:
                if (UserInfoManger.isLogin()) {
                    if (selectskusBean != null) {
                        addCartList(selectskusBean, false);
                    } else {
                        Intent addCartIntent = new Intent(this, AddCartActivity.class);
                        addCartIntent.putExtra(AddCartActivity.PRODUCT_DETAIL, dataBean);
                        startActivity(addCartIntent);
                    }
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.vip_rl:
                if (UserInfoManger.isLogin()) {
                    startActivity(new Intent(this, BuyVIPActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.all_evaluate_rl:
                if (dataBean != null) {
                    Intent evaluateListIntent = new Intent(this, EvaluateListActivity.class);
                    evaluateListIntent.putExtra(EvaluateListActivity.SPU_ID, dataBean.getSpuId());
                    startActivity(evaluateListIntent);
                }
                break;
        }
    }


    private int minToBuy = 1;
    private int minIncart = 1;
    private ProductDetailBean.DataBean.SkusBean selectskusBean = null;


    private void buyNow(final ProductDetailBean.DataBean.SkusBean skusBean) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<BuyNowBean>() {

            @Override
            public void onComplete(BuyNowBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        if (!TextUtils.isEmpty(bean.getData())) {
                            ArrayList<CartItemBean.DataBean.ListBean> cartList = new ArrayList<>();
                            CartItemBean.DataBean.ListBean listBean = new CartItemBean.DataBean
                                    .ListBean();
                            listBean.setCartId(bean.getData());
                            listBean.setNum(skusBean.getSelectCount());
                            listBean.setCover(dataBean.getCover());
                            listBean.setPrice(dataBean.getPrice());
                            listBean.setSpuId(dataBean.getSpuId());
                            listBean.setSpuName(dataBean.getSpuName());
                            cartList.add(listBean);
                            Intent buyIntent = new Intent(ProductDetailActivity.this,
                                    ConfirmOrderActivity.class);
                            buyIntent.putExtra(ORDER_ITEMS, cartList);
                            startActivity(buyIntent);
                        }


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
                    list.add(new BasicKeyValuePair(ParameterConstant.NUM, skusBean.getSelectCount()));
                    return UserInfoManger.getSignList(list);
                }


                return null;

            }


            @Override
            public String createUrl() {

                return ORDER_BUYNOW;
            }
        });
    }

    private void addCartList(final ProductDetailBean.DataBean.SkusBean skusBean, final boolean isPuschase) {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<CartItemBean>() {

            @Override
            public void onComplete(CartItemBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        selectskusBean = null;
                        if (isPuschase) {
                            CartItemBean.DataBean dataBean = bean.getData();
                            if (dataBean != null) {
                                List<CartItemBean.DataBean.ListBean> list = dataBean.getList();
                                if (list != null && !list.isEmpty()) {
                                    Intent intent = new Intent(ProductDetailActivity.this, ConfirmOrderActivity.class);
                                    intent.putParcelableArrayListExtra(ORDER_ITEMS, new ArrayList<>(list));
                                    startActivity(intent);
                                    finish();

                                }
                            }

                        } else {
                            tip(getString(R.string.add_succesfull));
                        }


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
                    list.add(new BasicKeyValuePair(ParameterConstant.NUM, skusBean.getSelectCount()));
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
