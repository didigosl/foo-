package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.baselibarary.util.EventBusUtil;
import com.duma.ld.baselibarary.util.LogUtil;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.google.gson.Gson;
import com.just.agentweb.AgentWeb;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.model.PayHttpModel;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

public class WebPayActivity extends BaseMyActivity {
    //    @BindView(R.id.web_view)
//    WebView webView;
    @BindView(R.id.layout_webview)
    FrameLayout layoutWebview;
    private PayHttpModel payHttpModel;
    private AgentWeb mAgentWeb;
    private PayHttpModel.RedsysBean redsys;
    private WebViewClient mWebViewClient;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_web_pay;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.pay_online));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        payHttpModel = new Gson().fromJson(getIntent().getStringExtra(Constants.intent_Model), PayHttpModel.class);
        redsys = payHttpModel.getRedsys();
        initWeb();
        String postData = "";
        try {
            postData = "Ds_Signature=" + URLEncoder.encode(redsys.getDs_Signature(), "UTF-8")
                    + "&Ds_MerchantParameters=" + URLEncoder.encode(redsys.getDs_MerchantParameters(), "UTF-8")
                    + "&Ds_SignatureVersion=" + URLEncoder.encode(redsys.getDs_SignatureVersion(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mAgentWeb.getUrlLoader().postUrl(redsys.getForm_action(), postData.getBytes());
    }

    private void initWeb() {
        mWebViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 获取页面内容
                view.loadUrl("javascript:window.java_obj.showSource("
                        + "document.getElementsByTagName('body')[0].innerHTML);");
            }
        };
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(layoutWebview, new FrameLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebViewClient(mWebViewClient)
                .addJavascriptInterface("java_obj", new InJavaScriptLocalObj())
                .createAgentWeb()
                .ready()
                .go(null);
    }

    public class InJavaScriptLocalObj {
        @JavascriptInterface
        public void showSource(String html) {
            LogUtil.e("html.body:" + html);
            if (replaceBlank(html).equals(getString(R.string.pay_successfull))) {
                EventBusUtil.sendModel(Constants.event_pay_h5);
                finish();
            }
        }
    }

    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
