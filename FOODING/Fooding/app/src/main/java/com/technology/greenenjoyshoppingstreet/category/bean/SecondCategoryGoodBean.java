package com.technology.greenenjoyshoppingstreet.category.bean;

import com.technology.greenenjoyshoppingstreet.main.bean.CategoryBean;
import com.technology.greenenjoyshoppingstreet.main.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bern on 2017/12/28 0028.
 */

public class SecondCategoryGoodBean {
    private int index = 1;

    private CategoryBean.DataBean dataBean;

    public CategoryBean.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(CategoryBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }


    public void clearList() {
        index = 1;
        dataBeanList.clear();


    }

    public int getIndex() {
        return index;
    }

    public void incrementIndex() {
        index++;
    }

    private List<ProductBean.DataBean.ListBean> dataBeanList = new ArrayList<>();

    public List<ProductBean.DataBean.ListBean> getDataBeanList() {
        return dataBeanList;
    }

    public void updateData(List<ProductBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            dataBeanList.clear();
        }
        if (data != null && !data.isEmpty()) {
            dataBeanList.addAll(data);
        }

    }
}
