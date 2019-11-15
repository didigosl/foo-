//package com.duma.ld.baselibarary.util.imageSelect;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.bumptech.glide.Glide;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.duma.ld.baselibarary.R;
//import com.duma.ld.baselibarary.util.PermissionUtil;
//import com.duma.ld.baselibarary.util.Ts;
//import com.duma.ld.baselibarary.util.ZhuanHuanUtil;
//import com.luck.picture.lib.PictureSelector;
//import com.luck.picture.lib.config.PictureConfig;
//import com.luck.picture.lib.config.PictureMimeType;
//import com.luck.picture.lib.entity.LocalMedia;
//import com.lzy.okgo.OkGo;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import static android.app.Activity.RESULT_OK;
//import static com.duma.ld.baselibarary.util.PermissionUtil.QuanXian_paizhao;
//
///**
// * Created by liudong on 2017/8/28.
// */
//
//public class ImageListManager implements PaiZhaoDialog.ClickListenerInterface {
//    private Activity mActivity;
//    private RecyclerView mRvList;
//    private Fragment fragment;
//    private PictureSelector pictureSelector;
//    private PaiZhaoDialog mDialog;
//    private int maxNum = 8;
//    private BaseQuickAdapter<ImgFileModel, BaseViewHolder> mAdapter;
//    private List<ImgFileModel> mList;
//    private OnImageFileListener onImageFileListener;
//    private int chooseRequest;
//
//    public interface OnImageFileListener {
//        void upDataFile(ImgFileModel imgFileModel, BaseQuickAdapter adapter, int position);
//
//        void showFile(ImgFileModel imgFileModel);
//    }
//
//    public ImageListManager(Activity mActivity, RecyclerView mRvList) {
//        this.mActivity = mActivity;
//        this.mRvList = mRvList;
//        this.chooseRequest = PictureConfig.CHOOSE_REQUEST;
//    }
//
//    public ImageListManager setChooseRequest(int chooseRequest) {
//        this.chooseRequest = chooseRequest;
//        return this;
//    }
//
//    public ImageListManager setFragment(Fragment fragment) {
//        this.fragment = fragment;
//        return this;
//    }
//
//    public ImageListManager setMaxNum(int maxNum) {
//        this.maxNum = maxNum;
//        return this;
//    }
//
//    public ImageListManager init(final OnImageFileListener onImageFileListener) {
//        this.onImageFileListener = onImageFileListener;
//        if (fragment != null) {
//            pictureSelector = PictureSelector.create(fragment);
//        } else {
//            pictureSelector = PictureSelector.create(mActivity);
//        }
//        mList = new ArrayList<>();
//        mDialog = new PaiZhaoDialog(mActivity);
//        mDialog.setClicklistener(this);
//        mAdapter = new BaseQuickAdapter<ImgFileModel, BaseViewHolder>(R.layout.rv_photo_img, mList) {
//            @Override
//            protected void convert(final BaseViewHolder helper, final ImgFileModel item) {
//                ImageView img_photo = helper.getView(R.id.img_photo);
//                FrameLayout layout_finsh = helper.getView(R.id.layout_finsh);
//                FrameLayout layout_loading = helper.getView(R.id.layout_loading);
//                LinearLayout layout_error = helper.getView(R.id.layout_error);
//                if (item.getUpType() == 1) {
//                    layout_loading.setVisibility(View.VISIBLE);
//                } else {
//                    layout_loading.setVisibility(View.GONE);
//                }
//                if (item.getUpType() == 2) {
//                    layout_error.setVisibility(View.VISIBLE);
//                } else {
//                    layout_error.setVisibility(View.GONE);
//                }
//                layout_finsh.setVisibility(View.VISIBLE);
//                if (item.getFile() != null) {
//                    Glide.with(mActivity)
//                            .load(item.getFile())
//                            .into(img_photo);
//                } else {
//                    if (item.getImgURl() != null) {
//                        Glide.with(mActivity)
//                                .load(item.getImgURl())
//                                .into(img_photo);
//                    } else {
//                        layout_finsh.setVisibility(View.GONE);
//                        Glide.with(mActivity)
//                                .load(ZhuanHuanUtil.getDrawable(R.drawable.t1))
//                                .into(img_photo);
//                    }
//                }
//                layout_finsh.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mList.remove(helper.getLayoutPosition());
//                        if (item.getUpType() == 1) {
//                            OkGo.getInstance().cancelTag(item.getFile().getPath());
//                        }
//                        refreshAdapter();
//                    }
//                });
//            }
//        };
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ImgFileModel model = mAdapter.getData().get(position);
//                if (model.getFile() == null && model.getImgURl() == null) {
//                    PermissionUtil permissionUtil = new PermissionUtil(mActivity, new PermissionUtil.onPermissionListener() {
//                        @Override
//                        public void onResult(int requestCode, boolean result) {
//                            if (requestCode == QuanXian_paizhao && result) {
//                                mDialog.show();
//                            }
//                        }
//                    });
//                    permissionUtil.openCamera();
//                } else {
//                    if (model.getUpType() == 2) {
//                        //重新上传
//                        //上传中
//                        model.setUpType(1);
//                        onImageFileListener.upDataFile(model, mAdapter, position);
//                        mAdapter.notifyDataSetChanged();
//                    } else {
//                        onImageFileListener.showFile(model);
//                    }
//                }
//
//            }
//        });
//        mRvList.setFocusable(false);
//        mRvList.setNestedScrollingEnabled(false);
//        mRvList.setLayoutManager(new GridLayoutManager(mActivity, 4));
//        mRvList.setAdapter(mAdapter);
//        refreshAdapter();
//        return this;
//    }
//
//    @Override
//    public void paiZhao() {
//        pictureSelector.openCamera(PictureMimeType.ofImage())
//                .maxSelectNum(maxNum - (mList.size() - 1))
//                .compress(true)
//                .minimumCompressSize(100)
//                .forResult(chooseRequest);
//    }
//
//    @Override
//    public void xiangce() {
//        pictureSelector.openGallery(PictureMimeType.ofImage())
//                .maxSelectNum(maxNum - (mList.size() - 1))
//                .compress(true)
//                .minimumCompressSize(100)
//                .forResult(chooseRequest);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            if (requestCode == chooseRequest) {
//                // 图片选择结果回调
//                List<LocalMedia> mediaList = PictureSelector.obtainMultipleResult(data);
//                // 例如 LocalMedia 里面返回三种path
//                // 1.media.getPath(); 为原图path
//                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
//                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
//                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                for (int i = 0; i < mediaList.size(); i++) {
//                    File file;
//                    LocalMedia localMedia = mediaList.get(i);
//                    if (localMedia.isCompressed() || (localMedia.isCut() && localMedia.isCompressed())) {
//                        file = new File(localMedia.getCompressPath());
//                    } else {
//                        file = new File(localMedia.getPath());
//                    }
//                    ImgFileModel model = new ImgFileModel();
//                    model.setFile(file);
//                    mList.add(model);
//                }
//                refreshAdapter();
//                startUpData();
//            }
//        }
//    }
//
//    private void startUpData() {
//        for (int i = 0; i < mList.size(); i++) {
//            ImgFileModel imgFileModel = mList.get(i);
//            if (imgFileModel.getImgURl() == null && imgFileModel.getFile() != null && imgFileModel.getUpType() == 0) {
//                //上传中
//                imgFileModel.setUpType(1);
//                onImageFileListener.upDataFile(imgFileModel, mAdapter, i);
//            }
//        }
//        //因为修改上传状态了 所以刷新下
//        mAdapter.notifyDataSetChanged();
//    }
//
//    private void refreshAdapter() {
//        //删除空的
//        Iterator<ImgFileModel> iterator = mList.iterator();
//        while (iterator.hasNext()) {
//            ImgFileModel next = iterator.next();
//            if (next.getFile() == null && next.getImgURl() == null) {
//                iterator.remove();
//            }
//        }
//        //查看最大数达到没
//        if (mList.size() < maxNum) {
//            mList.add(new ImgFileModel());
//        }
//        //刷新
//        mAdapter.notifyDataSetChanged();
//    }
//
//    public void setList(List<String> strings) {
//        for (int i = 0; i < strings.size(); i++) {
//            ImgFileModel model = new ImgFileModel();
//            model.setImgURl(strings.get(i));
//            mList.add(model);
//        }
//        refreshAdapter();
//    }
//
//    public List<ImgFileModel> getmList() {
//        return mList;
//    }
//
//    //判读上传图片完毕没有
//    public boolean isFinsh() {
//        for (int i = 0; i < mList.size(); i++) {
//            ImgFileModel model = mList.get(i);
//            switch (model.getUpType()) {
//                case 1:
//                    Ts.show("有图片上传中,请稍后再试!");
//                    return false;
//                case 2:
//                    Ts.show("有图片上传失败,请处理!");
//                    return false;
//            }
//        }
//        return true;
//    }
//
//    //判读是否选择图片为空 true 为空
//    public boolean isEmpty() {
//        if (mList.size() == 1) {
//            ImgFileModel model = mList.get(0);
//            if (model.getFile() == null && model.getImgURl() == null) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
