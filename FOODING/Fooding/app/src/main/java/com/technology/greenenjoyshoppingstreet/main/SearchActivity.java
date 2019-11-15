package com.technology.greenenjoyshoppingstreet.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.technology.greenenjoyshoppingstreet.BaseActivity;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.main.bean.HistoryBean;
import com.technology.greenenjoyshoppingstreet.main.bean.HotKeyBean;
import com.technology.greenenjoyshoppingstreet.utils.constant.Constant;
import com.technology.greenenjoyshoppingstreet.utils.info.UserInfoManger;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.NetExcutor;
import com.technology.greenenjoyshoppingstreet.utils.net.listener.CommonNetUIListener;
import com.technology.greenenjoyshoppingstreet.utils.net.request.NetRequestConfig;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.KEYWORD_LIST;
import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.USER_KEYWORD_LIST;

/**
 * Created by Administrator on 2018/1/7.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.cancel_rl)
    RelativeLayout cancelRl;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.clear_history_tv)
    TextView clearHistoryTv;
    @BindView(R.id.history_tag_group)
    TagView historyTagGroup;
    @BindView(R.id.history_rl)
    RelativeLayout historyRl;
    @BindView(R.id.hot_tag_group)
    TagView hotTagGroup;
    @BindView(R.id.hot_rl)
    RelativeLayout hotRl;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        hotTagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int i) {
                searchEt.setText(tag.text);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra(SearchResultActivity.KEY_NAME, tag.text);
                startActivity(intent);
            }
        });
        historyTagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int i) {
                searchEt.setText(tag.text);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra(SearchResultActivity.KEY_NAME, tag.text);
                startActivity(intent);
            }
        });
        searchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) searchEt.getContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchActivity.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    // 搜索，进行自己要的操作...
                    String keyword = searchEt.getText().toString();
                    if (!TextUtils.isEmpty(keyword)) {
                        Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                        intent.putExtra(SearchResultActivity.KEY_NAME, keyword);
                        startActivity(intent);
                    } else {

                        showDefaultDoubleOperationDialog(getString(R.string.search_not_empty));
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.cancel_rl, R.id.clear_history_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_rl:
                searchEt.setText("");
                finish();
                break;
            case R.id.clear_history_tv:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (UserInfoManger.isLogin()) {
            getUserSerach();
        } else {
            historyRl.setVisibility(View.GONE);
        }
        getHotKey();
    }

    /**
     * Gets user task.
     */
    private void getUserSerach() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<HistoryBean>() {

            @Override
            public void onComplete(HistoryBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                historyRl.setVisibility(View.GONE);

                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        List<HistoryBean.DataBean> data = bean.getData();
                        historyTagGroup.removeAll();
                        if (data != null && !data.isEmpty()) {
                            for (HistoryBean.DataBean key : data) {
                                Tag tag = new Tag(key.getContent());
                                tag.layoutColor = DEFAULT_GRAY_COLOR;
                                tag.tagTextColor = 0XFFA4A4A4;
                                tag.tagTextSize = 13;
                                historyTagGroup.addTag(tag);

                            }
                        }
                        if (historyTagGroup.getTags() == null || historyTagGroup.getTags().isEmpty()) {
                            historyRl.setVisibility(View.GONE);
                        } else {
                            historyRl.setVisibility(View.VISIBLE);
                        }


                    }

                }

            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {
                List<KeyValuePair> list = new ArrayList<>();
                return UserInfoManger.getSignList(list);

            }


            @Override
            public String createUrl() {

                return USER_KEYWORD_LIST;
            }
        });
    }


    private void getHotKey() {
        NetExcutor.executorCommonRequest(this, new CommonNetUIListener<HotKeyBean>() {

            @Override
            public void onComplete(HotKeyBean bean, NetUtils.NetRequestStatus netRequestStatus) {
                if (NetUtils.NetRequestStatus.SUCCESS == netRequestStatus) {
                    if (Constant.RESULT_OK.equals(bean.getCode())) {
                        hotTagGroup.removeAll();
                        List<HotKeyBean.DataBean> data = bean.getData();
                        if (data != null && !data.isEmpty()) {
                            for (HotKeyBean.DataBean key : data) {
                                Tag tag = new Tag(key.getContent());
                                tag.layoutColor = DEFAULT_GRAY_COLOR;
                                tag.tagTextColor = 0XFFA4A4A4;
                                tag.tagTextSize = 13;
                                hotTagGroup.addTag(tag);

                            }
                        }
                        hotTagGroup.addTags(hotTagGroup.getTags());


                    }

                } else {
                    tip(netRequestStatus.getNote());

                }
                if (hotTagGroup.getTags() == null || hotTagGroup.getTags().isEmpty()) {
                    hotRl.setVisibility(View.GONE);
                } else {
                    hotRl.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public NetRequestConfig.Method getMethod() {
                return NetRequestConfig.Method.POST;
            }

            @Override
            public Object submitNetParams() {

                List<KeyValuePair> list = new ArrayList<>();
                return UserInfoManger.getSignList(list);
            }


            @Override
            public String createUrl() {


                return KEYWORD_LIST;
            }
        });
    }
}
