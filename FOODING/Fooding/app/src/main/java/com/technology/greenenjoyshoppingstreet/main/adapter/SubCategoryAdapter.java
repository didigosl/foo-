package com.technology.greenenjoyshoppingstreet.main.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bern on 2017/12/26 0026.
 */

public class SubCategoryAdapter extends BaseAdapter {


    /**
     * The maps.
     */
    private ArrayList<CategoryBean.DataBean> dataList = new ArrayList<>();


    private String categoryName;


    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private Activity mContext;

    public ArrayList<CategoryBean.DataBean> getDataList() {
        return dataList;
    }

    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public SubCategoryAdapter(Activity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    public CategoryBean.DataBean getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_sub_category, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CategoryBean.DataBean bean = dataList.get(position);
        if (bean != null) {
            holder.nameTv.setText(bean.getCategoryName());
            String url = bean.getCategoryCover();
//            if (!TextUtils.isEmpty(url)) {
//                Glide.with(mContext).load(url)
//                        .into
//                                (holder.iconIv);
//            } else {
//                Glide.with(mContext).load(R.mipmap.ic_app_launcher)
//                        .into
//                                (holder.iconIv);
//            }
            ImageLoader.with(url, holder.iconIv);
            holder.rootRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, SecondCategoryActivity.class);
//                    intent.putExtra(CATEGORY_NAME, categoryName);
//                    intent.putParcelableArrayListExtra(CATEGORY_LIST, dataList);
//                    intent.putExtra(CATEGORY_INDEX, position);
//                    mContext.startActivity(intent);
                    StartActivityUtil.getInstance().goGoodsList(mContext, bean.getCategoryId(), bean.getCategoryName());

                }
            });

        }

        return convertView;

    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<CategoryBean.DataBean> data, boolean isClearOld) {
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
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.root_rl)
        RelativeLayout rootRl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
