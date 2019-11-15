package com.duma.ld.baselibarary.util.imageSelect;

import java.io.File;

/**
 * Created by liudong on 2018/5/28.
 */

public class ImgFileModel {
    private String imgURl;
    private File file;
    /**
     * 上传状态
     * 0 没上传/上传成功 默认
     * 1 上传中
     * 2 上传失败
     */
    private int upType;


    //上传成功
    public void setSuccess(String url) {
        this.imgURl = url;
        this.upType = 0;
    }

    //上传失败
    public void setError() {
        this.upType = 2;
    }

    public int getUpType() {
        return upType;
    }

    public void setUpType(int upType) {
        this.upType = upType;
    }

    public String getImgURl() {
        return imgURl;
    }

    public void setImgURl(String imgURl) {
        this.imgURl = imgURl;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
