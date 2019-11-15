package com.technology.greenenjoyshoppingstreet.category.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.main.adapter.SubCategoryAdapter;
import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.view.GridViewInScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Bern on 2017/12/28 0028.
 */

public class CategorySecondAdapter extends BaseAdapter {
    /**
     * The maps.
     */
    private ArrayList<CategoryBean.DataBean> dataList = new ArrayList<>();


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
    public CategorySecondAdapter(Activity mContext) {
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
            convertView = mInflater.inflate(R.layout.item_category_second, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CategoryBean.DataBean bean = dataList.get(position);
        if (bean != null) {
            holder.nameRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, SecondCategoryActivity.class);
//                    intent.putParcelableArrayListExtra(CATEGORY_LIST, dataList);
//                    intent.putExtra(CATEGORY_INDEX, 0);
//                    intent.putExtra(CATEGORY_NAME, bean.getCategoryName());
//                    mContext.startActivity(intent);
                    StartActivityUtil.getInstance().goGoodsList(mContext, bean.getCategoryId(), bean.getCategoryName());
                }
            });
            holder.nameTv.setText(bean.getCategoryName());
            holder.nameTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            SubCategoryAdapter categoryAdapter = new SubCategoryAdapter(mContext);
            holder.dataGv.setAdapter(categoryAdapter);
            categoryAdapter.setCategoryName(bean.getCategoryName());
            categoryAdapter.updateData(bean.getSons(), true);
//            holder.dataGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    Intent intent = new Intent(mContext, SecondCategoryActivity.class);
////                    intent.putParcelableArrayListExtra(CATEGORY_LIST, dataList);
////                    intent.putExtra(CATEGORY_INDEX, position);
////                    intent.putExtra(CATEGORY_NAME, bean.getCategoryName());
////                    mContext.startActivity(intent);
////
////                    CategoryBean.DataBean dataBean = bean.getSons().get(position);
////                    StartActivityUtil.getInstance().goGoodsList(mContext, dataBean.getCategoryId(), dataBean.getCategoryName());
//                }
//            });

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


        @BindView(R.id.name_rl)
        RelativeLayout nameRl;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.data_gv)
        GridViewInScroll dataGv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
