package com.technology.greenenjoyshoppingstreet.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;
import com.technology.greenenjoyshoppingstreet.main.bean.IndexRecommendBean;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.view.ScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/12/26 0026.
 */

public class IndexRecommendAdapter extends BaseAdapter {


    /**
     * The maps.
     */
    private List<IndexRecommendBean.DataBean> dataList = new ArrayList<>();


    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private Activity mContext;


    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public IndexRecommendAdapter(Activity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    /**
     * Gets count.
     *
     * @return the count
     */
    @Override
    public int getCount() {
        return dataList.size();
    }

    /**
     * Gets item.
     *
     * @param position the position
     * @return the item
     */
    @Override
    public IndexRecommendBean.DataBean getItem(int position) {
        return dataList.get(position);
    }


    /**
     * Gets item id.
     *
     * @param position the position
     * @return the item id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Gets view.
     *
     * @param position    the position
     * @param convertView the convert view
     * @param parent      the parent
     * @return the view
     */
/*
         * (non-Javadoc)
         *
         * @see android.widget.SimpleAdapter#getView(int, android.view.View,
         * android.view.ViewGroup)
         */
    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_index_recommend, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final IndexRecommendBean.DataBean bean = dataList.get(position);
        if (bean != null) {
            String url = bean.getRecommendPic();
//            if (!TextUtils.isEmpty(url)) {
//                Glide.with(mContext).load(url)
//                        .into
//                                (holder.iconIv);
//                holder.iconIv.setVisibility(View.VISIBLE);
//            } else {
//                Glide.with(mContext).load(R.mipmap.ic_app_launcher)
//                        .into
//                                (holder.iconIv);
//                holder.iconIv.setVisibility(View.GONE);
//            }
            ImageLoader.with(url, holder.iconIv);
            holder.iconIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<CategoryBean.DataBean> arrayList = new ArrayList<>();
                    CategoryBean.DataBean dataBean = new CategoryBean.DataBean();
                    dataBean.setCategoryId(bean.getCategoryId());
                    dataBean.setCategoryName(bean.getCategoryName());
                    dataBean.setCategoryCover(bean.getRecommendPic());
                    arrayList.add(dataBean);
//                    Intent intent = new Intent(mContext, SecondCategoryActivity.class);
////                    intent.putParcelableArrayListExtra(CATEGORY_LIST, arrayList);
////                    intent.putExtra(CATEGORY_INDEX, 0);
////                    intent.putExtra(CATEGORY_NAME, bean.getCategoryName());
////                    mContext.startActivity(intent);
                    StartActivityUtil.getInstance().goGoodsList(mContext, bean.getCategoryId(), bean.getCategoryName());
                }
            });


            IndexRecommendItemAdapter indexRecommendItemAdapter = new IndexRecommendItemAdapter
                    (mContext);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.dataLv.setLayoutManager(linearLayoutManager);
            holder.dataLv.setAdapter(indexRecommendItemAdapter);
            indexRecommendItemAdapter.updateData(bean.getList(), true);
            //holder.dataLv.setVisibility(indexRecommendItemAdapter.isEmpty() ? View.GONE : View.VISIBLE);
            holder.dataLv.setVisibility(View.GONE);
        }

        return convertView;

    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<IndexRecommendBean.DataBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.icon_iv)
        ImageView iconIv;
        @BindView(R.id.data_lv)
        ScrollRecyclerView dataLv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
