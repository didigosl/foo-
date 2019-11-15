package com.technology.greenenjoyshoppingstreet.login;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.login.bean.WebBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;

import org.apache.http.util.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/5/31 0031.
 */

public class WebActivity extends BaseActivity {


    @BindView(R.id.web)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initViews();
        getIntentData();


    }


    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            WebBean webBean = intent.getParcelableExtra(Constant.WEB_BEAN);
            if (webBean != null) {
                setBarTitle(webBean.getTitle());
                if (!TextUtils.isEmpty(webBean.getTargetUrl())) {
//                    String url = "http://www.cqjg.gov.cn/netcar/FindThree.aspx";
////post访问需要提交的参数
//                    String postDate = "txtName=zzz&QueryTypeLst=1&CertificateTxt=dsds";
////由于webView.postUrl(url, postData)中 postData类型为byte[] ，
////通过EncodingUtils.getBytes(data, charset)方法进行转换
//                    EncodingUtils.getBytes(webBean.getData(), "UTF-8");
                    webView.postUrl(webBean.getTargetUrl(), EncodingUtils.getBytes(webBean.getData(), "BASE64"))
                    ;
                } else if (!TextUtils.isEmpty(webBean.getData())) {
                    webView.loadData(webBean.getData(), "text/html; charset=UTF-8", null);//这种写法可以正确解码
                }


            }


        }
    }


    private void initViews() {
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);// 水平不显示
        webView.setVerticalScrollBarEnabled(false); // 垂直不显示
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setLoadWithOverviewMode(true);
        // webview漏洞,android3.0~android4.2移除searchBoxJavaBridge
        if (Build.VERSION.SDK_INT > 10 && Build.VERSION.SDK_INT <= 17) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
        }
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setMinimumFontSize(20);



    }


}
