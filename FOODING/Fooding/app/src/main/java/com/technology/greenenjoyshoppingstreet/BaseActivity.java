package com.technology.greenenjoyshoppingstreet;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.billions.utils.ToastUtil;
import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragmentActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.utils.DialogConfigBean;
import com.technology.greenenjoyshoppingstreet.utils.RefreshListener;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;

import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;
import net.gotev.uploadservice.UploadStatusDelegate;

import java.io.File;
import java.util.Calendar;
import java.util.List;


/**
 * 界面基类.
 *
 * @version V1.0
 * @date 2017.05.16
 */
public class BaseActivity extends TakePhotoFragmentActivity implements View.OnClickListener, RefreshListener, UploadStatusDelegate {
    /**
     * 标题.
     */
    private TextView titleTV;
    private RelativeLayout rootRL;
    private View backV;
    private View homeV;
    private View personalV;
    public Dialog chooseDialog;
    private static boolean finishAll = false;
    protected BaseActivity mActivity;

    @Override
    protected void onStart() {
        super.onStart();
        if (finishAll) {
            finish();
        }
    }

    public static boolean isFinishAll() {
        return finishAll;
    }

    public void setFinishAll() {
        finishAll = true;
    }

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
    }

    public void initBack() {
        backV = findViewById(R.id.back_titlebar_iv);
        if (backV != null) {
            backV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void initHomeBack() {
//        homeV = findViewById(R.id.back_);
//        if (homeV != null) {
//            homeV.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Tools.clearTopBack(BaseActivity.this, MainActivity.class);
//                    finish();
//                }
//            });
//        }
    }

    public void initPersonal() {
//        personalV = findViewById(R.id.personal_titlebar_rl);
//        if (personalV != null) {
//            personalV.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
    }

    /**
     * 设置标题.
     *
     * @param title the title
     */
    public void setTitle(@StringRes int title) {
        titleTV = (TextView) findViewById(R.id.title_titlebar_tv);
        if (titleTV != null && title != View.NO_ID) {
            titleTV.setText(title);
        }

    }

    public void setBackVisible(int visible) {
        backV = findViewById(R.id.back_titlebar_iv);
        if (backV != null) {
            backV.setVisibility(visible);
        }

    }

    public void setTitleString(String title) {
        titleTV = (TextView) findViewById(R.id.title_titlebar_tv);
        if (titleTV != null) {
            titleTV.setText(title);
        }

    }

    public String getDisplayTitle() {
        titleTV = (TextView) findViewById(R.id.title_titlebar_tv);
        if (titleTV != null) {
            return titleTV.getText().toString();
        }
        return null;
    }

    public void setTitleBackground(@ColorRes int color) {
        rootRL = (RelativeLayout) findViewById(R.id.root_titlebar_rl);
        if (rootRL != null) {
            rootRL.setBackgroundColor(getResources().getColor(color));
        }


    }


    public void setBarTitle(String title) {
        titleTV = (TextView) findViewById(R.id.title_titlebar_tv);
        if (titleTV != null) {
            titleTV.setText(title);
        }

    }

    public void showDatePickDailog(DatePickerDialog.OnDateSetListener onDateSetListener) {
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, R.style.DatePickerDialog,
                // 绑定监听器
                onDateSetListener
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();
    }

//    public void showShareDialog() {
//        if (!isOver()) {
//            if (chooseDialog != null && chooseDialog.isShowing()) {
//                chooseDialog.cancel();
//            }
//            chooseDialog = new Dialog(this, R.style.HintDialog);
//            View dialogChooseView = LayoutInflater.from(this).inflate(
//                    R.layout.dialog_share_wx, null);
//            chooseDialog.setContentView(dialogChooseView);
//            chooseDialog.setCancelable(true);
//
//            RelativeLayout wxSessionRl = (RelativeLayout) dialogChooseView.findViewById(R.id
//                    .wx_session_rl);
//            RelativeLayout wxFriendRl = (RelativeLayout) dialogChooseView.findViewById(R.id
//                    .wx_friend_rl);
//            Button cancelBT = (Button) dialogChooseView.findViewById(R.id.cancel_bt);
//            cancelBT.setOnClickListener(onDefaultClickListener);
//            wxSessionRl.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    WXUtils.shareWebPageToSession(BaseActivity.this, SHARE_URL, "点点购-百元店老板都在用的叫货app",
//                            "点点购-西班牙叫货app");
//                    dismissChooseDialog();
//                }
//            });
//            wxFriendRl.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    WXUtils.shareWebPageToTimeLine(BaseActivity.this, SHARE_URL, "点点购-百元店老板都在用的叫货app",
//                            "点点购-西班牙叫货app");
//                    dismissChooseDialog();
//                }
//            });
//
//
//            chooseDialog.setCanceledOnTouchOutside(false);
//            Window window = chooseDialog.getWindow();
//            if (window != null) {
//                window.setBackgroundDrawableResource(android.R.color.transparent);
//                // 设置大小
//                WindowManager.LayoutParams layoutParams = window
//                        .getAttributes();
//                DisplayMetrics dm = new DisplayMetrics();
//                // 取得窗口属性
//                getWindowManager().getDefaultDisplay().getMetrics(dm);
//                // 设置动画
//                window.setWindowAnimations(R.style.dialogWindowAnim);
//                // 窗口的宽度
//                int screenWidth = dm.widthPixels;
//                layoutParams.width = screenWidth;
//                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                layoutParams.gravity = Gravity.BOTTOM;
//                window.setAttributes(layoutParams);
//            }
//            chooseDialog.show();
//        }
//
//    }

    /**
     * 照片选择
     */
    public void showPhotoPick() {
        if (!isOver()) {
            if (chooseDialog != null && chooseDialog.isShowing()) {
                chooseDialog.cancel();
            }
            chooseDialog = new Dialog(this, R.style.HintDialog);
            View dialogChooseView = LayoutInflater.from(this).inflate(
                    R.layout.dialog_select_photo, null);
            chooseDialog.setContentView(dialogChooseView);
            chooseDialog.setCancelable(true);

            Button albumBT = (Button) dialogChooseView.findViewById(R.id.album_bt);
            Button takePhotoBT = (Button) dialogChooseView.findViewById(R.id.take_photo_bt);
            Button cancelBT = (Button) dialogChooseView.findViewById(R.id.cancel_bt);
            cancelBT.setOnClickListener(onDefaultClickListener);
            albumBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TakePhoto takePhoto = getTakePhoto();
                    LubanOptions option = new LubanOptions.Builder()
                            .setMaxHeight(1024)
                            .setMaxWidth(768)
                            .setMaxSize(500 * 1000)
                            .create();
                    CompressConfig config = CompressConfig.ofLuban(option);
                    takePhoto.onEnableCompress(config, false);
//                    takePhoto.onPickFromGalleryWithCrop();
                    takePhoto.onPickFromGallery();
                    dismissChooseDialog();
                }
            });

            takePhotoBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TakePhoto takePhoto = getTakePhoto();
                    File takePhotoDir = Tools.getCacheDir(Constant.PHOTO_DIR, BaseActivity.this);
                    File takePhotoFile = new File(takePhotoDir, Constant.PHOTO_NAME);
                    LubanOptions option = new LubanOptions.Builder()
                            .setMaxHeight(1000)
                            .setMaxWidth(1000)
                            .setMaxSize(800 * 1000)
                            .create();
                    CompressConfig config = CompressConfig.ofLuban(option);
                    takePhoto.onEnableCompress(config, false);
                    takePhoto.onPickFromCapture(Uri.fromFile(takePhotoFile));
                    dismissChooseDialog();
                }
            });


            chooseDialog.setCanceledOnTouchOutside(false);
            Window window = chooseDialog.getWindow();
            if (window != null) {
                window.setBackgroundDrawableResource(android.R.color.transparent);
                // 设置大小
                WindowManager.LayoutParams layoutParams = window
                        .getAttributes();
                DisplayMetrics dm = new DisplayMetrics();
                // 取得窗口属性
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                // 设置动画
                window.setWindowAnimations(R.style.dialogWindowAnim);
                // 窗口的宽度
                int screenWidth = dm.widthPixels;
                layoutParams.width = screenWidth * 3 / 4;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(layoutParams);
            }
            chooseDialog.show();
        }
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        initBack();
        initHomeBack();
        initPersonal();
        initRefreshList();

    }


    public void setTitleBackgroundDraw(@DrawableRes int draw) {
        rootRL = (RelativeLayout) findViewById(R.id.root_titlebar_rl);
        if (rootRL != null) {
            rootRL.setBackgroundResource(draw);
        }


    }

    public void showLoadingDialog() {
        if (loadingDalog == null) {
            loadingDalog = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        }
        loadingDalog.show();
    }

    private KProgressHUD loadingDalog = null;

    public void cancelLoadingDialog() {
        if (loadingDalog != null && loadingDalog.isShowing()) {
            loadingDalog.dismiss();
        }
    }


    public void showDefaultDoubleOperationDialog(String context) {
        DialogConfigBean dialogConfigBean = DialogConfigBean
                .getDefaultDoubleConfig(this);
        dialogConfigBean.setContentText(context);
        showOperationDialog(dialogConfigBean);
    }


    public void showDefaultSingleOperationDialog(String context) {
        DialogConfigBean dialogConfigBean = DialogConfigBean
                .getDefaultSingleNoTitleConfig();
        dialogConfigBean.setContentText(context);
        showOperationDialog(dialogConfigBean);
    }


    public void hideKeybroad() {
        // 先隐藏键盘
        ((InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public void showOperationDialog(DialogConfigBean dialogConfigBean) {
        if (dialogConfigBean != null) {
            if (!isOver()) {

                if (chooseDialog != null && chooseDialog.isShowing()) {
                    chooseDialog.dismiss();
                }

                chooseDialog = new Dialog(this, R.style.HintDialog);
                View dialogChooseView = LayoutInflater.from(this).inflate(
                        R.layout.dialog_common, null);
                chooseDialog.setContentView(dialogChooseView);
                chooseDialog.setCancelable(dialogConfigBean.isCanBack());
                TextView titleTv = (TextView) dialogChooseView.findViewById(R.id.title_tv);
                if (titleTv != null) {
                    if (dialogConfigBean.isHasTitle()) {
                        titleTv.setVisibility(View.VISIBLE);
                        titleTv.setText(dialogConfigBean.getTitle());
                    } else {
                        titleTv.setVisibility(View.GONE);
                        titleTv.setText(dialogConfigBean.getTitle());
                    }
                }

                TextView contentTv = (TextView) dialogChooseView.findViewById(R.id.content_tv);
                if (contentTv != null) {

                    contentTv.setText(dialogConfigBean.getContentText());

                }


                TextView singleTv = (TextView) dialogChooseView.findViewById(R.id.single_tv);
                RelativeLayout doubleRl = (RelativeLayout) dialogChooseView.findViewById(R.id
                        .double_rl);
                if (singleTv != null) {
                    singleTv.setVisibility(dialogConfigBean.isSingle() ? View.VISIBLE : View.GONE);
                    singleTv.setText(dialogConfigBean.getSingleButtonText());
                    if (dialogConfigBean.getSingleClick() != null) {
                        singleTv.setOnClickListener(dialogConfigBean.getSingleClick());
                    } else {
                        singleTv.setOnClickListener(onDefaultClickListener);
                    }
                }

                if (doubleRl != null) {
                    doubleRl.setVisibility(dialogConfigBean.isSingle() ? View.GONE : View.VISIBLE);
                }
                TextView leftTv = (TextView) dialogChooseView.findViewById(R.id.left_tv);
                if (leftTv != null) {
                    leftTv.setText(dialogConfigBean.getLeftText());
                    if (dialogConfigBean.getLeftClick() != null) {
                        leftTv.setOnClickListener(dialogConfigBean.getLeftClick());
                    } else {
                        leftTv.setOnClickListener(onDefaultClickListener);
                    }
                }
                TextView rightTv = (TextView) dialogChooseView.findViewById(R.id.right_tv);
                if (rightTv != null) {
                    rightTv.setText(dialogConfigBean.getRightText());
                    if (dialogConfigBean.getRightClick() != null) {
                        rightTv.setOnClickListener(dialogConfigBean.getRightClick());
                    } else {
                        rightTv.setOnClickListener(onDefaultClickListener);
                    }
                }

                chooseDialog.setCanceledOnTouchOutside(dialogConfigBean.isCanceledOnTouchOutside());
                Window window = chooseDialog.getWindow();
                if (window != null) {
                    // 设置大小
                    WindowManager.LayoutParams layoutParams = window
                            .getAttributes();
                    DisplayMetrics dm = new DisplayMetrics();
                    // 取得窗口属性
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    // 设置动画
                    window.setWindowAnimations(R.style.dialogWindowAnim);
                    // 窗口的宽度
                    int screenWidth = dm.widthPixels;
                    layoutParams.width = screenWidth * 3 / 4;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    window.setAttributes(layoutParams);
                }
                chooseDialog.show();


            }


        }


    }

    public static final int DEFAULT_RED_COLOR = Color.parseColor("#FFE70012");
    public static final int DEFAULT_BLACK_COLOR = Color.parseColor("#FF343434");
    public static final int DEFAULT_GRAY_COLOR = Color.parseColor("#FFF5F5F5");

    public void showAddCartDialog(final ProductDetailBean.DataBean dataBean) {
        if (!isOver()) {
            if (chooseDialog != null && chooseDialog.isShowing()) {
                chooseDialog.dismiss();
            }

            chooseDialog = new Dialog(this, R.style.TranDialog);
            View dialogChooseView = LayoutInflater.from(this).inflate(
                    R.layout.dialog_add_cart, null);
            chooseDialog.setContentView(dialogChooseView);
            chooseDialog.setCancelable(true);

//            ImageView productIv = (ImageView) dialogChooseView.findViewById(R.id.add_cart_float_product_iv);
//            List<ProductDetailBean.DataBean.NavigationImagesBean> imagesBeanList = dataBean
//                    .getNavigationImages();
//
//
//            if (imagesBeanList != null && !imagesBeanList.isEmpty()) {
//                ProductDetailBean.DataBean.NavigationImagesBean navigationImagesBean =
//                        imagesBeanList.get(0);
//                if (navigationImagesBean != null) {
//                    String url = navigationImagesBean.getUrl();
//                    if (!TextUtils.isEmpty(url)) {
//                        Glide.with(this).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).error(R
//                                .drawable.shape_defualt_bg)
//                                .into
//                                        (productIv);
//                    }
//                }
//
//
//            }
//            final TextView addGoodsCountTv = (TextView) dialogChooseView.findViewById(R.id
//                    .add_goods_count_tv);
//            Button addCartBt = (Button) dialogChooseView.findViewById(R.id.add_cart_float_bt);
//
//            final ImageView minusCountIv = (ImageView) dialogChooseView.findViewById(R.id
//                    .minus_count_iv);
//
//            minusCountIv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (dataBean.getBuyCount() > 1) {
//                        dataBean.decrement();
//                        addGoodsCountTv.setText(String.valueOf(dataBean.getBuyCount()));
//                    } else {
//                        minusCountIv.setEnabled(false);
//                    }
//
//                }
//            });
//            ImageView addCountIv = (ImageView) dialogChooseView.findViewById(R.id.add_count_iv);
//            addCountIv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dataBean.increment();
//                    minusCountIv.setEnabled(true);
//                    addGoodsCountTv.setText(String.valueOf(dataBean.getBuyCount()));
//                }
//            });
//
//
//            TextView productName = (TextView) dialogChooseView.findViewById(R.id.add_cart_float_product_name_tv);
//            productName.setText(dataBean.getName());
//            View addCartFloatCloseV = dialogChooseView.findViewById(R.id.add_cart_float_close_v);
//            addCartFloatCloseV.setOnClickListener(onDefaultClickListener);
//            final TextView addCartProductPriceTv = (TextView) dialogChooseView.findViewById(R.id
//                    .add_cart_float_product_price_tv);
////            addCartProductPriceTv.setText("¥ " + dataBean.getSalePrice());
//            final TextView addCartFloatProductStockTv = (TextView) dialogChooseView.findViewById(R.id.add_cart_float_product_stock_tv);
////            addCartFloatProductStockTv.setText("库存：" + dataBean.getStock());
//
//
////            final float tagTextSize = DensityUtil.dip2px(this, 13);
//            final TagView specificationsTag = (TagView) dialogChooseView.findViewById(R.id
//                    .specifications_tag_group);
//
//
//            final TagView attributeTag = (TagView) dialogChooseView.findViewById(R.id.attribute_tag_group);
//            List<ProductDetailBean.DataBean.ProductSpecificationBean> productSpecificationList =
//                    dataBean.getProductSpecification();
//
//            final LinkedHashMap<String, ArrayList<String>> resultList = new LinkedHashMap<>();
//            if (productSpecificationList != null && !productSpecificationList.isEmpty()) {
//
//                TextView specificationsNameTv = (TextView) dialogChooseView.findViewById(R.id
//                        .specifications_name_tv);
//                if (specificationsNameTv != null) {
//                    ProductDetailBean.DataBean.ProductSpecificationBean productSpecificationBean = productSpecificationList.get(0);
//                    if (productSpecificationBean != null) {
//                        specificationsNameTv.setText(productSpecificationBean.getGroupName());
//                    }
//                }
//
//                for (ProductDetailBean.DataBean.ProductSpecificationBean specBean : productSpecificationList) {
//                    Tag tag = new Tag(specBean.getGroupName());
//                    tag.layoutColor = DEFAULT_GRAY_COLOR;
//                    tag.tagTextColor = 0XFF343434;
//                    tag.tagTextSize = 13;
//                    specificationsTag.addTag(tag);
//
//
//                    List<ProductDetailBean.DataBean.ProductSpecificationBean.ProductSpecificationsBean> attributesBeanList =
//                            specBean
//                                    .getProductSpecifications();
//                    if (attributesBeanList != null && !attributesBeanList.isEmpty()) {
//                        ArrayList<String> arrayList = new ArrayList<>();
//                        for (ProductDetailBean.DataBean.ProductSpecificationBean.ProductSpecificationsBean attBean : attributesBeanList) {
//                            arrayList.add(attBean.getSpecification());
//
//                        }
//                        resultList.put(specBean.getGroupName(), arrayList);
//                    }
//
//
//                }
//
//            }
//
//            TagView.OnTagClickListener speciTagClick = new TagView.OnTagClickListener() {
//                @Override
//                public void onTagClick(Tag tag, int position) {
//
//                    if (tag.layoutColor == DEFAULT_GRAY_COLOR) {
//                        setTagGroupCheck(specificationsTag, position);
//                        ArrayList<String> arrayList = resultList.get(tag.text);
//                        if (arrayList != null && !arrayList.isEmpty()) {
//                            attributeTag.removeAll();
//                            for (String str : arrayList) {
//                                Tag attTag = new Tag(str);
//                                attTag.layoutColor = DEFAULT_GRAY_COLOR;
//                                attTag.tagTextColor = 0XFF343434;
//                                attTag.tagTextSize = 13;
//                                attributeTag.addTag(attTag);
//
//
//                            }
//                            setTagGroupCheck(attributeTag, 0);
//                        }
//
//
//                    }
//
//                }
//            };
//            //set click listener
//            specificationsTag.setOnTagClickListener(speciTagClick);
//            TagView.OnTagClickListener attributeClick = new TagView.OnTagClickListener() {
//                @Override
//                public void onTagClick(Tag tag, int position) {
//
//                    if (tag.layoutColor == DEFAULT_GRAY_COLOR) {
//                        setTagGroupCheck(attributeTag, position);
//
//                        int spePostion = getSelectPosition(specificationsTag);
//                        if (spePostion != NO_POSTION) {
//
//                            ProductDetailBean.DataBean.ProductSpecificationBean
//                                    .ProductSpecificationsBean attributesBean = dataBean.getProductSpecification().get(spePostion)
//                                    .getProductSpecifications().get(position);
//                            if (attributesBean != null) {
//                                dataBean.setBuyCount(1);
//                                addGoodsCountTv.setText(String.valueOf(dataBean.getBuyCount()));
//                                minusCountIv.setEnabled(false);
//                                addCartProductPriceTv.setText(attributesBean.getPrice());
//                                addCartFloatProductStockTv.setText("库存：" + attributesBean.getStock());
//                            }
//                        }
//                    }
//
//                }
//            };
//            attributeTag.setOnTagClickListener(attributeClick);
//
//            addCartBt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    int spePostion = getSelectPosition(specificationsTag);
//                    if (spePostion != NO_POSTION) {
//                        int attPosition = getSelectPosition(attributeTag);
//                        if (attPosition != NO_POSTION) {
//
//                            ProductDetailBean.DataBean.ProductSpecificationBean
//                                    .ProductSpecificationsBean attributesBean = dataBean.getProductSpecification().get(spePostion)
//                                    .getProductSpecifications().get(attPosition);
//                            if (attributesBean != null) {
//                                String attId = attributesBean.getId();
//                                if (!TextUtils.isEmpty(attId)) {
//                                    ShoppingCartItemBean shoppingCartItemBean = new ShoppingCartItemBean();
//                                    shoppingCartItemBean.setId(attId);
//                                    shoppingCartItemBean.setCount(addGoodsCountTv.getText().toString());
//                                    shoppingCartItemBean.setName(dataBean.getName());
//                                    shoppingCartItemBean.setProductSpecificationText(attributesBean.getSpecification());
//                                    List<ProductDetailBean.DataBean.NavigationImagesBean> imagesBeanList = dataBean.getNavigationImages();
//                                    if (imagesBeanList != null && !imagesBeanList.isEmpty()) {
//                                        ProductDetailBean.DataBean.NavigationImagesBean bean = imagesBeanList.get
//                                                (0);
//                                        if (bean != null) {
//                                            if (!TextUtils.isEmpty(bean.getUrl())) {
//                                                shoppingCartItemBean.setProductUrl(bean.getUrl());
//                                            }
//
//                                        }
//                                    }
//                                    shoppingCartItemBean.setUnitPrice(attributesBean.getPrice());
//                                    dismissChooseDialog();
//                                    if (flag == ProductDetailActivity.PURCHASE_FLAG) {
//
//                                        ShoppingCartManager.deleteGood(shoppingCartItemBean.getId
//                                                ());
//                                        ShoppingCartManager.addShoppingCart(shoppingCartItemBean);
//                                        Intent confirmOrderIntent = new Intent(BaseActivity.this,
//                                                ConfirmOrderActivity.class);
//                                        ArrayList<ShoppingCartItemBean> dataList = new
//                                                ArrayList<>();
//                                        dataList.add(shoppingCartItemBean);
//                                        confirmOrderIntent.putParcelableArrayListExtra
//                                                (ORDER_ITEMS, dataList);
//                                        startActivity(confirmOrderIntent);
//
//
////                                        ArrayList<OrderItem> list = new ArrayList<OrderItem>();
////                                        OrderItem orderItem = new OrderItem();
////                                        orderItem.setPrice(dataBean.getPrice());
////                                        orderItem.setQuality(dataBean.getQuality());
////                                        list.add(orderItem);
////                                        Intent intent = new Intent(this, ConfirmOrderActivity.class);
////                                        intent.putExtra(ORDER_ITEMS, list);
////                                        startActivity(intent);
//
//                                    } else {
//                                        ShoppingCartManager.addShoppingCart(shoppingCartItemBean);
//                                        dismissChooseDialog();
//                                        tip("添加成功");
//                                    }
//
//                                    BaseActivity.this.refresh();
//                                }
//
//                            }
//
//
//                        }
//
//                    }
//
//                }
//            });
//            if (specificationsTag.getTags() != null && !specificationsTag.getTags().isEmpty()) {
//                speciTagClick.onTagClick(specificationsTag.getTags().get(0), 0);
//            }
////            setTagGroupCheck(specificationsTag, 0);
//
//            int spePostion = getSelectPosition(specificationsTag);
//            if (spePostion != NO_POSTION) {
//                int attPosition = getSelectPosition(attributeTag);
//                if (attPosition != NO_POSTION) {
//
//                    ProductDetailBean.DataBean.ProductSpecificationBean
//                            .ProductSpecificationsBean attributesBean = dataBean.getProductSpecification().get(spePostion)
//                            .getProductSpecifications().get(attPosition);
//                    if (attributesBean != null) {
//                        addCartProductPriceTv.setText(attributesBean.getPrice());
//                        addCartFloatProductStockTv.setText("库存：" + attributesBean.getStock());
//
//                    }
//                }
//            }
            chooseDialog.setCanceledOnTouchOutside(true);
            Window window = chooseDialog.getWindow();
            if (window != null) {
                // 设置大小
                WindowManager.LayoutParams layoutParams = window
                        .getAttributes();
                DisplayMetrics dm = new DisplayMetrics();
                // 取得窗口属性
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                // 设置动画
                window.setWindowAnimations(R.style.dialogWindowAnim);
                // 窗口的宽度
                int screenWidth = dm.widthPixels;
                layoutParams.width = screenWidth;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                layoutParams.gravity = Gravity.BOTTOM;
                window.setAttributes(layoutParams);
            }
            chooseDialog.show();


        }

    }


    public static final int NO_POSTION = -1;

    public int getSelectPosition(TagView tagView) {
        int position = NO_POSTION;
        if (tagView != null) {
            List<Tag> tagList = tagView.getTags();
            if (tagList != null && !tagList.isEmpty()) {
                int length = tagList.size();
                for (int i = 0; i < length; i++) {
                    Tag tagV = tagList.get(i);
                    if (tagV != null && tagV.layoutColor == DEFAULT_BLACK_COLOR) {
                        position = i;
                        break;

                    }

                }
            }

        }
        return position;


    }


    public void setTagGroupCheck(TagView tagView, int position) {
        if (tagView != null) {
            List<Tag> tagList = tagView.getTags();
            if (tagList != null && !tagList.isEmpty()) {
                int length = tagList.size();
                for (int i = 0; i < length; i++) {
                    Tag tagV = tagList.get(i);

                    if (i != position) {
                        tagV.tagTextSize = 13;
                        tagV.layoutColor = DEFAULT_GRAY_COLOR;
                        tagV.tagTextColor = 0XFF343434;
                    } else {
                        tagV.tagTextSize = 13;
                        tagV.layoutColor = DEFAULT_BLACK_COLOR;
                        tagV.tagTextColor = 0XFFFFFFFF;
                    }


                }
                tagView.addTags(tagView.getTags());
            }

        }

    }

    public boolean isOver() {
        boolean isDes = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            isDes = isDestroyed();
        }

        return (isFinishing() || isDes);
    }

    /**
     * 单个按钮默认的选择框按钮的点击事件.
     */
    public View.OnClickListener onDefaultClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            dismissChooseDialog();
        }
    };

    public void tip(@StringRes int str) {
        ToastUtil.showMessage(this, str, Toast.LENGTH_SHORT);

    }

    public void tip(String str) {
        if (!TextUtils.isEmpty(str)) {
            ToastUtil.showMessage(this, str, Toast.LENGTH_SHORT);
        }

    }

    public void dismissChooseDialog() {
        if (null != chooseDialog && chooseDialog.isShowing()) {
            chooseDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {

    }

    private void initRefreshList() {
        View view = findViewById(R.id.no_list_data_refresh_rl);
        if (view != null) {


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refresh();
                }
            });

        }


    }

    public void setNoDataVisible(int visibility) {

        View view = findViewById(R.id.no_list_data_refresh_rl);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    @Override
    public void refresh() {
    }

    public void showProgressLoading(int progress, String hint) {
        if (loadingDalog == null || !loadingDalog.isShowing()) {
            loadingDalog = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.ANNULAR_DETERMINATE)
                    .setLabel(hint)
                    .setMaxProgress(100).setCancellable(false)
                    .show();


        }
        loadingDalog.setProgress(progress);
        loadingDalog.setLabel(hint);

    }

    @Override
    public void onError(Context context, UploadInfo uploadInfo, ServerResponse serverResponse, Exception e) {
        cancelLoadingDialog();
        tip("上传图片失败");
    }


    @Override
    public void onProgress(Context context, UploadInfo uploadInfo) {

        if (uploadInfo != null) {
            showProgressLoading(uploadInfo.getProgressPercent(), "上传图片中...");
        }


    }


    @Override
    public void onCompleted(Context context, UploadInfo uploadInfo, ServerResponse serverResponse) {
        showProgressLoading(100, "上传图片中...");
        cancelLoadingDialog();
        tip("上传成功");
    }

    @Override
    public void onCancelled(Context context, UploadInfo uploadInfo) {
        cancelLoadingDialog();
        tip("上传图片取消");

    }
}
