package com.technology.greenenjoyshoppingstreet.cart;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.technology.greenenjoyshoppingstreet.cart.bean.ShoppingCartItemBean;
import com.technology.greenenjoyshoppingstreet.utils.PreferencesUtil;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.net.tools.GsonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/10/29.
 */

public class ShoppingCartManager {


    public static final String GOODS = "GOODS";
    private static HashMap<String, ShoppingCartItemBean> saveItemBeens = new HashMap<>();

    private static void saveChange() {
        PreferencesUtil.setSharedStringData(GOODS, GsonUtil.fromObjectToJsonString(saveItemBeens));
    }


    public static void initCart() {
        saveItemBeens.clear();
        String goods = PreferencesUtil.getSharedStringData(GOODS);
        if (!TextUtils.isEmpty(goods)) {

            HashMap<String, ShoppingCartItemBean> detailList = GsonUtil
                    .fromJsonStringToCollection(goods, new
                            TypeToken<HashMap<String, ShoppingCartItemBean>>() {
                            }.getType());

            if (detailList != null && !detailList.isEmpty()) {
                saveItemBeens.clear();
                saveItemBeens.putAll(detailList);

            }

        }

    }

    public static void deleteGood(String productId) {
        if (!TextUtils.isEmpty(productId)) {
            saveItemBeens.remove(productId);
            saveChange();

        }


    }

    public static void addGoodsCount(String productId) {
        if (!TextUtils.isEmpty(productId)) {
            ShoppingCartItemBean shoppingCartItemBean = saveItemBeens.get(productId);
            if (shoppingCartItemBean != null) {
                shoppingCartItemBean.increment();
                saveChange();
            }
        }


    }


    public static void minusGoodsCount(String productId) {
        if (!TextUtils.isEmpty(productId)) {
            ShoppingCartItemBean shoppingCartItemBean = saveItemBeens.get(productId);
            if (shoppingCartItemBean != null) {
                shoppingCartItemBean.decrement();
                if (!shoppingCartItemBean.isHasGoods()) {
                    saveItemBeens.remove(productId);
                }
                saveChange();
            }
        }


    }


    public static int getALLGoodsCount() {
        int count = 0;
        Collection<ShoppingCartItemBean> itemList = saveItemBeens.values();
        for (ShoppingCartItemBean itemBean : itemList) {

            if (Tools.isDouble(itemBean.getCount())) {
                count += Integer.valueOf(itemBean.getCount());
            }

        }
        return count;

    }

    public static void addShoppingCart(ShoppingCartItemBean shoppingCartItemBean) {
        if (shoppingCartItemBean != null && !TextUtils.isEmpty(shoppingCartItemBean.getId())) {
            ShoppingCartItemBean saveBean = saveItemBeens.get(shoppingCartItemBean.getId());
            if (saveBean != null) {
                saveBean.increment(shoppingCartItemBean.getCount());
            } else {
                saveItemBeens.put(shoppingCartItemBean.getId(), shoppingCartItemBean);
            }
            saveChange();
        }


    }


    public static ShoppingCartItemBean getShoppingCartItemById(String id) {
        if (!TextUtils.isEmpty(id)) {
            return saveItemBeens.get(id);

        }
        return null;


    }


    public static List<ShoppingCartItemBean> getShoppingCartItemByIds(List<String> ids) {
        List<ShoppingCartItemBean> resultList = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            for (String id : ids) {
                ShoppingCartItemBean bean = saveItemBeens.get(id);
                if (bean != null) {
                    resultList.add(bean);

                }
            }

        }

        return resultList;


    }

    public static void setAllGoodsSelectStatus(boolean isCheck) {
        List<ShoppingCartItemBean> dataList = getAllGoods();
        if (dataList != null && !dataList.isEmpty()) {

            for (ShoppingCartItemBean bean : dataList) {
                bean.setChecked(isCheck);
            }
        }


    }

    public static List<ShoppingCartItemBean> getAllGoods() {
        List<ShoppingCartItemBean> dataList = new ArrayList<>();

        if (saveItemBeens != null && !saveItemBeens.isEmpty()) {
            Collection<ShoppingCartItemBean> data = saveItemBeens.values();
            if (data != null && !data.isEmpty()) {
                dataList.addAll(data);
            }
        }

        return dataList;
    }

}
