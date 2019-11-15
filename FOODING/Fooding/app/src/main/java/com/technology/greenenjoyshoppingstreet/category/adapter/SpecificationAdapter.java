package com.technology.greenenjoyshoppingstreet.category.adapter;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.AddCartActivity;
import com.technology.greenenjoyshoppingstreet.category.bean.ProductDetailBean;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.technology.greenenjoyshoppingstreet.category.bean.SelectMode.SELECT;
import static com.technology.greenenjoyshoppingstreet.category.bean.SelectMode.UNENABLE;
import static com.technology.greenenjoyshoppingstreet.category.bean.SelectMode.UNSELECT;

/**
 * Created by Administrator on 2017/12/30.
 */

public class SpecificationAdapter extends BaseAdapter {

    public static final String TAG = SpecificationAdapter.class.getSimpleName();
    /**
     * The maps.
     */
    private List<ProductDetailBean.DataBean.SpecsBeanX> dataList = new ArrayList<>();



    /**
     * The m inflater.
     */
    private LayoutInflater mInflater;

    /**
     * M context.
     */
    private AddCartActivity mContext;


    /**
     * Instantiates a new Message adapter.
     *
     * @param mContext the m context
     */
    public SpecificationAdapter(AddCartActivity mContext) {
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
    public ProductDetailBean.DataBean.SpecsBeanX getItem(int position) {
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
            convertView = mInflater.inflate(R.layout.item_specification, null);
            holder = new ViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ProductDetailBean.DataBean.SpecsBeanX bean = dataList.get(position);
        if (bean != null) {
            holder.nameTv.setText(bean.getSpecName());
            holder.specificationsTagGroup.removeAll();
            final List<ProductDetailBean.DataBean.SpecsBeanX.SpecsBean> specsBeanList = bean.getSpecs();
            if (specsBeanList != null && !specsBeanList.isEmpty()) {
                for (ProductDetailBean.DataBean.SpecsBeanX.SpecsBean specBean : specsBeanList) {

                    if (specBean.getSelectMode() == UNSELECT) {
                        if (!totalKeys.contains(specBean.getValue())) {
                            specBean.setSelectMode(UNENABLE);
                        }
                    }
                    Tag tag = new Tag(specBean.getValue());
                    tag.layoutColor = mContext.getResources().getColor(R.color.unselect_bg_color);
                    tag.radius = 0;
                    tag.layoutBorderSize = 2;
                    tag.layoutBorderColor = mContext.getResources().getColor(specBean.getSelectMode().getBorderColor());
                    tag.tagTextColor = mContext.getResources().getColor(specBean.getSelectMode().getTextColor());
                    tag.tagTextSize = 14;
                    holder.specificationsTagGroup.addTag(tag);
                }
                holder.specificationsTagGroup.addTags(holder.specificationsTagGroup.getTags());
            }
            holder.specificationsTagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
                @Override
                public void onTagClick(Tag tag, int index) {

                    ProductDetailBean.DataBean.SpecsBeanX.SpecsBean indexBean = specsBeanList.get(index);
                    if (indexBean != null && indexBean.getSelectMode() != UNENABLE) {
                        boolean isRefresh = false;
                        for (int i = 0; i < specsBeanList.size(); i++) {
                            ProductDetailBean.DataBean.SpecsBeanX.SpecsBean specBean = specsBeanList.get(i);
                            if (specBean.getSelectMode() != UNENABLE) {
                                isRefresh = true;
                                specBean.setSelectMode(i == index ? SELECT : UNSELECT);

                            }
                        }
                        if (isRefresh) {
                            selectChange(bean.getSpecName());
                            notifyDataSetChanged();
                        }

                    }


                }
            });

        }


        return convertView;

    }


    public void selectChange(final String specName) {
        Set<String> keys = new HashSet<>();
        for (ProductDetailBean.DataBean.SpecsBeanX specsBeanX : dataList) {
            if (specsBeanX != null) {

                List<ProductDetailBean.DataBean.SpecsBeanX.SpecsBean> specsBeanList = specsBeanX.getSpecs();
                if (specsBeanList != null) {

                    for (ProductDetailBean.DataBean.SpecsBeanX.SpecsBean specsBean : specsBeanList) {

                        if (specsBean.getSelectMode() == SELECT) {
                            keys.add(Tools.concatAll(specsBeanX.getSpecName(), ":", specsBean.getValue()));

                        }


                    }

                }
            }

        }

        List<ProductDetailBean.DataBean.SkusBean> skusBeanList = mContext.getContainsSkuList(keys);
        StringBuilder stringBuilder = new StringBuilder(mContext.getString(R.string.selected));
        for (ProductDetailBean.DataBean.SpecsBeanX specsBeanX : dataList) {
            for (ProductDetailBean.DataBean.SpecsBeanX.SpecsBean specsBean : specsBeanX.getSpecs()) {
                if (specsBean.getSelectMode() == SELECT) {
                    stringBuilder.append(" ").append(specsBeanX.getSpecName()).append(":").append(specsBean.getValue());
                    break;
                }
            }


        }
        selectedSkuList.clear();
        selectedSkuList.addAll(skusBeanList);
        if (skusBeanList != null && !skusBeanList.isEmpty()) {

            if (skusBeanList.size() == 1) {
                stringBuilder.append(" "+mContext.getString(R.string.in_stock)+" ");
            } else {
                stringBuilder.append(" "+mContext.getString(R.string.inprove_specification)+" ");
            }

        } else {
            stringBuilder.append(" "+mContext.getString(R.string.sold_out)+" ");
        }
        if (!TextUtils.isEmpty(stringBuilder.toString())) {
            mContext.updateSelectStr(stringBuilder.toString());
        }

    }

    private List<ProductDetailBean.DataBean.SkusBean> selectedSkuList = new ArrayList<>();

    public List<ProductDetailBean.DataBean.SkusBean> getSelectedSkuList() {
        return selectedSkuList;
    }

    public void setSelectedSkuList(List<ProductDetailBean.DataBean.SkusBean> selectedSkuList) {
        this.selectedSkuList = selectedSkuList;
    }

    private Set<String> totalKeys = new HashSet<>();

    public Set<String> getTotalKeys() {
        return totalKeys;
    }

    public void setTotalKeys(Set<String> totalKeys) {
        this.totalKeys = totalKeys;
    }

    private Set<String> selectKeys = new HashSet<>();

    public Set<String> getSelectKeys() {
        return selectKeys;
    }

    public void setSelectKeys(Set<String> selectKeys) {
        this.selectKeys = selectKeys;
    }


    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */

    public void updateData(List<ProductDetailBean.DataBean.SpecsBeanX> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    static class ViewHolder {
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.specifications_tag_group)
        TagView specificationsTagGroup;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
