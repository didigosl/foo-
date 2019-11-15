package com.technology.greenenjoyshoppingstreet.main.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bern on 2017/12/25 0025.
 */

public class MainPageBean {
    private boolean isGetData = false;
    private CategoryBean.DataBean categoryBean;

    private int currentSeasonNextPage = 1;
    private int currentCategoryGoodPage = 1;

    public boolean isGetData() {
        return isGetData;
    }

    public void setGetData(boolean getData) {
        isGetData = getData;
    }

    private List<IndexRecommendBean.DataBean> indexRecommendList = new ArrayList<>();

    private List<CategoryBean.DataBean> subCategoryList = new ArrayList<>();
    private List<AdBean.DataBean> adList = new ArrayList<>();
    private List<ProductBean.DataBean.ListBean> hotSingleList = new ArrayList<>();
    private List<ProductBean.DataBean.ListBean> currentSeasonList = new ArrayList<>();
    private List<ProductBean.DataBean.ListBean> categoryGoodList = new ArrayList<>();
    public List<IndexRecommendBean.DataBean> getIndexRecommendList() {
        return indexRecommendList;
    }


    public void updateIndexRecommendList(List<IndexRecommendBean.DataBean> data, boolean isClearOld) {
        if (isClearOld) {
            indexRecommendList.clear();
        }
        if (data != null && !data.isEmpty()) {
            indexRecommendList.addAll(data);
        }

    }

    public void incrementCategoryGoodsPage() {
        currentCategoryGoodPage++;
    }

    public int getCurrentCategoryGoodPage() {
        return currentCategoryGoodPage;
    }

    public void setCurrentCategoryGoodPage(int currentCategoryGoodPage) {
        this.currentCategoryGoodPage = currentCategoryGoodPage;
    }

    public void incrementPage() {
        currentSeasonNextPage++;
    }

    public int getCurrentSeasonNextPage() {
        return currentSeasonNextPage;
    }

    public void setCurrentSeasonNextPage(int currentSeasonNextPage) {
        this.currentSeasonNextPage = currentSeasonNextPage;
    }

    public List<ProductBean.DataBean.ListBean> getHotSingleList() {
        return hotSingleList;
    }

    public List<ProductBean.DataBean.ListBean> getCategoryGoodList() {
        return categoryGoodList;
    }

    public void setCategoryGoodList(List<ProductBean.DataBean.ListBean> categoryGoodList) {
        this.categoryGoodList = categoryGoodList;
    }
    public void updateCategoryGoodsList(List<ProductBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            categoryGoodList.clear();
        }
        if (data != null && !data.isEmpty()) {
            categoryGoodList.addAll(data);
        }

    }
    public void updateHotSingleList(List<ProductBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            hotSingleList.clear();
        }
        if (data != null && !data.isEmpty()) {
            hotSingleList.addAll(data);
        }

    }

    public List<CategoryBean.DataBean> getSubCategoryList() {
        return subCategoryList;
    }


    public void updateSubCategoryList(List<CategoryBean.DataBean> data, boolean isClearOld) {
        if (isClearOld) {
            subCategoryList.clear();
        }
        if (data != null && !data.isEmpty()) {
            subCategoryList.addAll(data);
        }

    }

    public List<AdBean.DataBean> getAdList() {
        return adList;
    }


    public void updateAdList(List<AdBean.DataBean> data, boolean isClearOld) {
        if (isClearOld) {
            adList.clear();
        }
        if (data != null && !data.isEmpty()) {
            adList.addAll(data);
        }

    }

    public List<ProductBean.DataBean.ListBean> getCurrentSeasonList() {
        return currentSeasonList;
    }


    public void updateCurrentSeasonList(List<ProductBean.DataBean.ListBean> data, boolean isClearOld) {
        if (isClearOld) {
            currentSeasonList.clear();
        }
        if (data != null && !data.isEmpty()) {
            currentSeasonList.addAll(data);
        }

    }

    public CategoryBean.DataBean getCategoryBean() {
        return categoryBean;
    }


    public void setCategoryBean(CategoryBean.DataBean categoryBean) {
        this.categoryBean = categoryBean;
    }
}
