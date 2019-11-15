package com.technology.greenenjoyshoppingstreet.newui.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.duma.ld.baselibarary.model.HttpResModel;
import com.duma.ld.baselibarary.util.Constants;
import com.duma.ld.mytopbar.config.PublicConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.newui.adapter.CommentAdapter;
import com.technology.greenenjoyshoppingstreet.newui.base.BaseMyActivity;
import com.technology.greenenjoyshoppingstreet.newui.base.MyJsonCallback;
import com.technology.greenenjoyshoppingstreet.newui.model.GoodsCommentModel;

import butterknife.BindView;

import static com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant.ORDER_COMMENT_LIST;

public class GoodsCommentActivity extends BaseMyActivity {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private String id;
    private CommentAdapter mAdapter;

    @Override
    protected int setLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_goods_comment;
    }

    @Override
    protected void initConfig(Bundle savedInstanceState, PublicConfig mPublicConfig) {
        mPublicConfig.setTopBar(getString(R.string.list_comments));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        setRefresh(refreshLayout, true);
        mAdapter = new CommentAdapter(rvList, mActivity);
        id = getIntent().getStringExtra(Constants.intent_id);
        starRefresh();

    }

    @Override
    public void onHttpRefresh(int page) {
        super.onHttpRefresh(page);
        OkGo.<HttpResModel<GoodsCommentModel>>post(ORDER_COMMENT_LIST)
                .tag(this)
                .params("spu_id", id)
                .params("page", page)
                .params("pageLimit", "20")
                .execute(new MyJsonCallback<HttpResModel<GoodsCommentModel>>(mPublicConfig) {
                    @Override
                    protected void onJsonSuccess(Response<HttpResModel<GoodsCommentModel>> respons, HttpResModel<GoodsCommentModel> goodsModelHttpResModel) {
                        setRefreshData(mAdapter.getmAdapter(), goodsModelHttpResModel.getData().getList());
                    }
                });
    }
}
