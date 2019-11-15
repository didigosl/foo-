package com.duma.ld.baselibarary.model;

import java.io.File;

public class ZiZhiModel {
    private String name;
    private File file;
    private String url;

    public ZiZhiModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
