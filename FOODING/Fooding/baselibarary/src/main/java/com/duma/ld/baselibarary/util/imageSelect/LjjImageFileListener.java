//package com.duma.ld.baselibarary.util.imageSelect;
//
//import android.app.Activity;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.duma.ld.liangjinjin.base.http.MyJsonCallback;
//import com.duma.ld.liangjinjin.model.base.HttpResModel;
//import com.duma.ld.liangjinjin.model.http.UpImgHttpModel;
//import com.duma.ld.liangjinjin.util.StartActivityUtil;
//import com.duma.ld.liangjinjin.util.Ts;
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.model.Response;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.duma.ld.liangjinjin.util.HttpUrl.upload;
//
///**
// * Created by liudong on 2018/5/28.
// */
//
//public class LjjImageFileListener implements ImageListManager.OnImageFileListener {
//    private Activity mActivity;
//
//    public LjjImageFileListener(Activity mActivity) {
//        this.mActivity = mActivity;
//    }
//
//    @Override
//    public void upDataFile(final ImgFileModel imgFileModel, final BaseQuickAdapter adapter, final int position) {
//        OkGo.<HttpResModel<List<UpImgHttpModel>>>post(upload)
//                .tag(imgFileModel.getFile().getPath())
//                .params("upufdmfile", imgFileModel.getFile())
//                .execute(new MyJsonCallback<HttpResModel<List<UpImgHttpModel>>>() {
//                    @Override
//                    protected void onJsonSuccess(Response<HttpResModel<List<UpImgHttpModel>>> respons, HttpResModel<List<UpImgHttpModel>> stringHttpResModel) {
//                        List<UpImgHttpModel> body = stringHttpResModel.getBody();
//                        if (body != null && body.size() != 0) {
//                            //上传成功
//                            String upfileFilePath = body.get(0).getUpfileFilePath();
//                            imgFileModel.setSuccess(upfileFilePath);
//                        } else {
//                            imgFileModel.setError();
//                            Ts.show("上传图片服务器返回为空!");
//                        }
//                        adapter.notifyItemChanged(position);
//                    }
//
//                    @Override
//                    public void onError(Response<HttpResModel<List<UpImgHttpModel>>> response) {
//                        super.onError(response);
//                        imgFileModel.setError();
//                        adapter.notifyItemChanged(position);
//                    }
//                });
//    }
//
//    @Override
//    public void showFile(ImgFileModel imgFileModel) {
//        List<String> mList = new ArrayList<>();
//        if (imgFileModel.getImgURl() != null && imgFileModel.getFile() != null) {
//            mList.add(imgFileModel.getFile().getPath());
//        } else {
//            mList.add(imgFileModel.getImgURl());
//        }
//        StartActivityUtil.getInstance().startPhotoInfo(mActivity, mList, 0);
//    }
//}
