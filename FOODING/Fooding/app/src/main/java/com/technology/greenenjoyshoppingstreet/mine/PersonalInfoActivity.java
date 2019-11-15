package com.technology.greenenjoyshoppingstreet.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima.wheelpicker.WheelPicker;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.login.bean.UserInfoBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.UploadImageBean;
import com.technology.greenenjoyshoppingstreet.utils.BaseBean;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.LogUtils;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.GsonUtil;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import net.gotev.uploadservice.ServerResponse;
import net.gotev.uploadservice.UploadInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.UPLOAD_AVATAR;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_GETINFO;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_UPDATE;

/**
 * Created by Bern on 2017/12/13 0013.
 */

public class PersonalInfoActivity extends BaseActivity {
    @BindView(R.id.head_iv)
    ImageView headIv;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.sex_tv)
    TextView sexTv;
    @BindView(R.id.sex_rl)
    FrameLayout sexRl;
    @BindView(R.id.age_tv)
    TextView ageTv;
    @BindView(R.id.age_rl)
    FrameLayout ageRl;
    @BindView(R.id.photo_album_bt)
    Button photoAlbumBt;
    @BindView(R.id.select_photo_bt)
    Button selectPhotoBt;
    @BindView(R.id.photo_cancel_bt)
    Button photoCancelBt;
    @BindView(R.id.select_photo_rl)
    RelativeLayout selectPhotoRl;
    @BindView(R.id.sex_cancel_rl)
    RelativeLayout sexCancelRl;
    @BindView(R.id.sex_confirm_rl)
    RelativeLayout sexConfirmRl;
    @BindView(R.id.sex_wp)
    WheelPicker sexWp;
    @BindView(R.id.sex_select_rl)
    RelativeLayout sexSelectRl;
    @BindView(R.id.age_cancel_rl)
    RelativeLayout ageCancelRl;
    @BindView(R.id.age_confirm_rl)
    RelativeLayout ageConfirmRl;
    @BindView(R.id.age_wp)
    WheelPicker ageWp;
    @BindView(R.id.age_select_rl)
    RelativeLayout ageSelectRl;
    @BindView(R.id.top_rl)
    RelativeLayout topRl;
    @BindView(R.id.edit_rl)
    RelativeLayout editRl;
    @BindView(R.id.head_rl)
    FrameLayout headRl;
    @BindView(R.id.name_rl)
    FrameLayout nameRl;
    @BindView(R.id.root_sv)
    ScrollView rootSv;
    private ArrayList<String> sexList = new ArrayList<>();
    private ArrayList<String> ageList = new ArrayList<>();
    @BindView(R.id.refresh_fl)
    PtrClassicFrameLayout refreshFl;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        initViews();

    }

    /**
     * Init refre list.
     */
    public void initRefreList() {
        refreshFl.setPtrHandler(new PtrDefaultHandler2() {

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                getUserInfo();
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return false;

            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, rootSv, header);
            }
        });
        // the following are default settings
        refreshFl.setResistance(1.7f); // you can also set foot and header separately
        refreshFl.setRatioOfHeaderHeightToRefresh(1.2f);
        refreshFl.setDurationToClose(1000);  // you can also set foot and header separately
        // default is false
        refreshFl.setPullToRefresh(false);

        // default is true
        refreshFl.setKeepHeaderWhenRefresh(true);
    }

    private void initViews() {
        setBarTitle(getString(R.string.personal_info));
        sexList.add(getString(R.string.man));
        sexList.add(getString(R.string.woman));
        sexWp.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
                sexId = String.valueOf(i + 1);

            }
        });
        sexWp.setData(sexList);
        sexWp.setSelectedItemPosition(0);
        sexWp.run();
        for (int i = 1; i < 100; i++) {
            ageList.add(String.valueOf(i));
        }
        ageWp.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
                ageId = String.valueOf(i + 1);
            }
        });
        ageWp.setData(ageList);
        ageWp.setSelectedItemPosition(0);
        ageWp.run();
        initRefreList();
        refreshInfo();
    }

    private void refreshInfo() {
        String url = UserInfoManger.getFace();
//        if (!TextUtils.isEmpty(url)) {
//            Glide.with(this).load(url)
//                    .into
//                            (headIv);
//        } else {
//            Glide.with(this).load(R.drawable.shape_defualt_bg)
//                    .into
//                            (headIv);
//        }
        ImageLoader.with_head(url, headIv);
        nameTv.setText(UserInfoManger.getLoginName());
        sexTv.setText(UserInfoManger.getSex());
        ageTv.setText(UserInfoManger.getAge());

    }

    private String sexId = "1";
    private String ageId = "1";


    @Override
    protected void onStart() {
        super.onStart();
        getUserInfo();
    }

    /**
     * Gets user task.
     */
    private void getUserInfo() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<UserInfoBean>() {

            @Override
            public void onComplete(UserInfoBean bean, NetUtils.NetRequestStatus netRequestStatus) {


                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        UserInfoManger.setUserInfo(bean.getData());
                        refreshInfo();

                    }

                }
                cancelLoadingDialog();
                refreshFl.refreshComplete();

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

                return USER_GETINFO;
            }
        });
    }


    @OnClick({R.id.name_rl, R.id.head_rl, R.id.sex_rl, R.id.age_rl, R.id.photo_album_bt, R.id.select_photo_bt, R.id.photo_cancel_bt, R.id.sex_cancel_rl, R.id.sex_confirm_rl, R.id.age_cancel_rl, R.id.age_confirm_rl, R.id.top_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.photo_album_bt:
                TakePhoto takePhoto = getTakePhoto();
                File takePhotoDir = Tools.getCacheDir(Constant.PHOTO_DIR, this);
                File takePhotoFile = new File(takePhotoDir, Constant.PHOTO_NAME);
                LubanOptions option = new LubanOptions.Builder()
                        .setMaxHeight(1000)
                        .setMaxWidth(1000)
                        .setMaxSize(800 * 1000)
                        .create();
                CompressConfig config = CompressConfig.ofLuban(option);
                takePhoto.onEnableCompress(config, false);
                takePhoto.onPickFromCapture(Uri.fromFile(takePhotoFile));
                editRl.setVisibility(View.GONE);
                break;
            case R.id.select_photo_bt:
                TakePhoto selctPhoto = getTakePhoto();
                LubanOptions selectOption = new LubanOptions.Builder()
                        .setMaxHeight(1024)
                        .setMaxWidth(768)
                        .setMaxSize(500 * 1000)
                        .create();
                CompressConfig selectconfig = CompressConfig.ofLuban(selectOption);
                selctPhoto.onEnableCompress(selectconfig, false);
//                    takePhoto.onPickFromGalleryWithCrop();
                selctPhoto.onPickFromGallery();
                editRl.setVisibility(View.GONE);
                break;
            case R.id.photo_cancel_bt:
                editRl.setVisibility(View.GONE);
                break;
            case R.id.sex_cancel_rl:
                editRl.setVisibility(View.GONE);
                break;
            case R.id.sex_confirm_rl:
                updateSex();
                editRl.setVisibility(View.GONE);
                break;
            case R.id.age_cancel_rl:
                editRl.setVisibility(View.GONE);
                break;
            case R.id.age_confirm_rl:
                updateAge();
                editRl.setVisibility(View.GONE);
                break;
            case R.id.top_rl:
                editRl.setVisibility(View.GONE);
                break;
            case R.id.sex_rl:
                showSelect(R.id.sex_select_rl);
                break;
            case R.id.age_rl:
                showSelect(R.id.age_select_rl);
                break;
            case R.id.head_rl:
                showSelect(R.id.select_photo_rl);
                break;
            case R.id.name_rl:
                startActivity(new Intent(this, ChangeNameActivity.class));
                break;
        }
    }

    private void updateAge() {


        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<BaseBean>() {

            @Override
            public void onComplete(BaseBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        getUserInfo();

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

                if (!TextUtils.isEmpty(ageId)) {

                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.AGE, ageId));

                    return UserInfoManger.getSignList(list);

                }

                return null;


            }


            @Override
            public String createUrl() {

                return USER_UPDATE;
            }
        });
    }

    private void updateSex() {


        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<BaseBean>() {

            @Override
            public void onComplete(BaseBean bean, NetUtils.NetRequestStatus netRequestStatus) {

                cancelLoadingDialog();
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        getUserInfo();

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

                if (!TextUtils.isEmpty(sexId)) {

                    showLoadingDialog();

                    List<KeyValuePair> list = new ArrayList<>();
                    list.add(new BasicKeyValuePair(ParameterConstant.GENDER, sexId));

                    return UserInfoManger.getSignList(list);

                }

                return null;


            }


            @Override
            public String createUrl() {

                return USER_UPDATE;
            }
        });
    }


    private void showSelect(int layout) {
        editRl.setVisibility(View.VISIBLE);
        selectPhotoRl.setVisibility(layout == R.id.select_photo_rl ? View.VISIBLE : View.GONE);
        sexSelectRl.setVisibility(layout == R.id.sex_select_rl ? View.VISIBLE : View.GONE);
        ageSelectRl.setVisibility(layout == R.id.age_select_rl ? View.VISIBLE : View.GONE);


    }

    UploadImageBean uploadImageBean;

    /**
     * Take success.
     *
     * @param result the result
     */
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        final TImage image = result.getImage();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (image != null) {
                    uploadImageBean = new UploadImageBean();
                    uploadImageBean.setCompressImagePath(image.getCompressPath());
                    uploadImageBean.setImagePath(image.getOriginalPath());
                    uploadImageBean.setUploadId(Tools.uploadFile(PersonalInfoActivity.this, image.getCompressPath(), UPLOAD_AVATAR, AVATAR, UserInfoManger.getSignList(new ArrayList<KeyValuePair>())));


                }


            }
        });


    }

    @Override
    public void onCompleted(Context context, UploadInfo uploadInfo, ServerResponse serverResponse) {
        super.onCompleted(context, uploadInfo, serverResponse);

        if (uploadInfo != null) {
            String body = serverResponse.getBodyAsString();
            if (!TextUtils.isEmpty(body)) {
                LogUtils.log(NetUtils.TAG, body);
                BaseBean baseBean = GsonUtil.fromJsonStringToObejct(body,
                        BaseBean.class);
                if (baseBean != null && Constant.RESULT_OK.equals(baseBean.getCode())) {
                    getUserInfo();

                }


            }

        }

    }

    public static final String AVATAR = "avatar";
}