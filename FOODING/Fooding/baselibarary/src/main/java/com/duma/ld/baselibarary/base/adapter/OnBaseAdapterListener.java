package com.duma.ld.baselibarary.base.adapter;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by liudong on 2017/12/13.
 */

public interface OnBaseAdapterListener<T> {
    void convert(BaseViewHolder helper, T item);
}
