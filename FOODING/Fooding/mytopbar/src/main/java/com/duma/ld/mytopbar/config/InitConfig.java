package com.duma.ld.mytopbar.config;

import android.support.annotation.LayoutRes;

public class InitConfig {
    private PublicConfig publicConfig;
    @LayoutRes
    private int layoutResID;

    public InitConfig(PublicConfig publicConfig) {
        this.publicConfig = publicConfig;
    }

    public PublicConfig setLayoutResID(int layoutResID) {
        this.layoutResID = layoutResID;
        return publicConfig;
    }


    public int getLayoutResID() {
        return layoutResID;
    }
}
