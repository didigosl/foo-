package com.technology.greenenjoyshoppingstreet.mine.bean;

/**
 * Created by Administrator on 2018/1/3.
 */

public class UploadImageBean {
    private String uploadId;
    private String imagePath;
    private String compressImagePath;

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCompressImagePath() {
        return compressImagePath;
    }

    public void setCompressImagePath(String compressImagePath) {
        this.compressImagePath = compressImagePath;
    }
}
