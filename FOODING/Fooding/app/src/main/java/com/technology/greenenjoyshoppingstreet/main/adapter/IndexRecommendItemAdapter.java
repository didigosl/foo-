package com.technology.greenenjoyshoppingstreet.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.greenenjoyshoppingstreet.R;
import com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity;
import com.technology.greenenjoyshoppingstreet.main.bean.IndexRecommendBean;
import com.technology.greenenjoyshoppingstreet.newui.util.StartActivityUtil;
import com.technology.greenenjoyshoppingstreet.utils.ImageLoader;
import com.technology.greenenjoyshoppingstreet.utils.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.technology.greenenjoyshoppingstreet.category.ProductDetailActivity.GOOD_ID;

/**
 * Created by Bern on 2017/12/26 0026.
 */

public class IndexRecommendItemAdapter extends  RecyclerView.Adapter  {


    /**
     * The maps.
     */
    private List<IndexRecommendBean.DataBean.ListBean> dataList = new ArrayList<>();


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
    public IndexRecommendItemAdapter(Activity mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    public boolean isEmpty(){
        return dataList.isEmpty();
    }


    @Override
    public int getItemCount() {
        return dataList.size();
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


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IndexRecommendItemAdapter.ViewHolder holder =new IndexRecommendItemAdapter.ViewHolder(mInflater.inflate(R.layout.item_flash_sale,null));
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final IndexRecommendBean.DataBean.ListBean bean = dataList.get(position);
        if (bean != null) {
            ( (IndexRecommendItemAdapter.ViewHolder)holder).nameTv.setText(bean.getSpuName());
            String url = bean.getCover();
//            if (!TextUtils.isEmpty(url)) {
//                Glide.with(mContext).load(url)
//                        .into
//                                ( ( (IndexRecommendItemAdapter.ViewHolder)holder).iconIv);
//            } else {
//                Glide.with(mContext).load(R.mipmap.ic_app_launcher)
//                        .into
//                                ( ( (IndexRecommendItemAdapter.ViewHolder)holder).iconIv);
//            }
            ImageLoader.with(url, ( (IndexRecommendItemAdapter.ViewHolder)holder).iconIv);
            ( (IndexRecommendItemAdapter.ViewHolder)holder).priceTv.setText(Tools.formatPriceText(bean.getPrice()));
            ( (IndexRecommendItemAdapter.ViewHolder)holder).rootRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(mContext, ProductDetailActivity.class);
                    intent.putExtra(GOOD_ID, bean.getSpuId());
                    mContext.startActivity(intent);*/
                    StartActivityUtil.getInstance().goGoodsDetail(mContext, bean.getSpuId());
                }
            });
        }

    }



    /**
     * 更新数据.
     *
     * @param data       the data
     * @param isClearOld the is clear old
     */
    public void updateData(List<IndexRecommendBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataList.addAll(data);
        }
        notifyDataSetChanged();

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.root_rl)
        RelativeLayout rootRl;
        @BindView(R.id.icon_iv)
        ImageView iconIv;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.price_tv)
        TextView priceTv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }
}
