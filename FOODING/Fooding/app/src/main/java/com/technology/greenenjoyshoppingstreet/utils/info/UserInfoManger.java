package com.technology.greenenjoyshoppingstreet.utils.info;


import android.text.TextUtils;

import com.technology.greenenjoyshoppingstreet.login.bean.LoginBean;
import com.technology.greenenjoyshoppingstreet.login.bean.UserInfoBean;
import com.technology.greenenjoyshoppingstreet.mine.bean.AddressListBean;
import com.technology.greenenjoyshoppingstreet.utils.PreferencesUtil;
import com.technology.greenenjoyshoppingstreet.utils.Tools;
import com.technology.greenenjoyshoppingstreet.utils.constant.ParameterConstant;
import com.technology.greenenjoyshoppingstreet.utils.net.BasicKeyValuePair;
import com.technology.greenenjoyshoppingstreet.utils.net.KeyValuePair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.technology.greenenjoyshoppingstreet.utils.PreferencesUtil.getSharedStringData;


/**
 */
public class UserInfoManger {

    public static final String HIDE_GUIDE = "HIDE_GUIDE";
    public static final String AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "SECRET_KEY";
    public static final String LAST_LOGIN_PHONE = "LAST_LOGIN_PHONE";
    public static final String SEARCH_HISTORY = "SEARCH_HISTORY";
    public static final String PRODUCT_HISTORY = "PRODUCT_HISTORY";
    /**
     * User infomation.
     */
    private static UserInfomation userInfomation = new UserInfomation();


    private static final int MAX_HISTORY = 50;

    //    private static LinkedList<ProductListBean.DataBean> productHistoryList = new LinkedList<>();
    private static ArrayList<String> historyList = new ArrayList<>();
    private static LinkedHashMap<String, LinkedHashMap<String, ArrayList<String>>> provinceMap = new LinkedHashMap<>();

    /**
     * Instantiates a new User info manger.
     */
    private UserInfoManger() {
    }

//    public static LinkedList<ProductListBean.DataBean> getProductHistoryList() {
//        return productHistoryList;
//    }

    /**
     * Clear user info.
     */
    public static void clearUserInfo() {
        synchronized (userInfomation) {
            userInfomation = new UserInfomation();
        }
    }

    /**
     * Log off.
     */
    public static void logOff() {
        synchronized (userInfomation) {
            userInfomation = new UserInfomation();
            PreferencesUtil.setSharedStringData(AUTHORIZATION, "");
            PreferencesUtil.setSharedStringData(SECRET_KEY, "");

        }
    }

//    public static String getLastLoginPhone() {
//        synchronized (userInfomation) {
//            return userInfomation.getLastLoginPhone();
//        }
//    }


    public static String getMobile() {
        synchronized (userInfomation) {
            return userInfomation.getPhone();
        }
    }

    public static String getFace() {
        synchronized (userInfomation) {
            return userInfomation.getAvatar();
        }
    }

    public static String getLoginName() {
        synchronized (userInfomation) {
            return userInfomation.getName();
        }
    }

    public static boolean isVip() {
        synchronized (userInfomation) {
            if (!TextUtils.isEmpty(userInfomation.getLevelId()) && TextUtils.isDigitsOnly(userInfomation.getLevelId())) {
                int level = Integer.valueOf(userInfomation.getLevelId());
                return level > 1;

            }
            return false;
        }
    }

    public static String getSex() {
        synchronized (userInfomation) {
            return userInfomation.getGenderText();
        }
    }

    public static String getAge() {
        synchronized (userInfomation) {
            return userInfomation.getAge();
        }
    }

    public static boolean isLogin() {
        synchronized (userInfomation) {
            return !TextUtils.isEmpty(userInfomation.getToken());
        }

    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public static String getToken() {
        synchronized (userInfomation) {
            return userInfomation.getToken();
        }
    }

    /**
     * Gets token.
     *
     * @return the token
     */
    public static String getSecretKey() {
        synchronized (userInfomation) {
            return userInfomation.getSecretKey();
        }
    }

    /**
     * Is login boolean.
     *
     * @return the boolean
     */
    public static boolean isHasToken() {

        synchronized (userInfomation) {
            return !TextUtils.isEmpty(userInfomation.getToken());
        }


    }


    public static List<String> getProvinces() {
        ArrayList<String> pronvinces = new ArrayList<>();
        pronvinces.addAll(provinceMap.keySet());
        return pronvinces;
    }

    public static List<String> getCitys(String province) {
        ArrayList<String> citys = new ArrayList<>();
        if (!TextUtils.isEmpty(province)) {
            LinkedHashMap<String, ArrayList<String>> citysMap = provinceMap.get(province);
            if (citysMap != null && !citysMap.isEmpty()) {
                citys.addAll(citysMap.keySet());

            }

        }

        return citys;

    }

    public static List<String> getAreas(String province, String city) {
        ArrayList<String> areas = new ArrayList<>();
        if (!TextUtils.isEmpty(province) && !TextUtils.isEmpty(city)) {

            LinkedHashMap<String, ArrayList<String>> citysMap = provinceMap.get(province);
            if (citysMap != null && !citysMap.isEmpty()) {
                ArrayList<String> areaList = citysMap.get(city);
                if (areaList != null) {
                    areas.addAll(areaList);
                }

            }

        }

        return areas;

    }

//
//    public static void cleaRecordHistory() {
//        if (!historyList.isEmpty()) {
//            historyList.clear();
//            PreferencesUtil.setSharedStringData(SEARCH_HISTORY, GsonUtil.fromObjectToJsonString(historyList));
//        }
//
//
//    }
//
//    public static boolean recordHistory(String history) {
//        boolean result = false;
//        if (!TextUtils.isEmpty(history) && !historyList.contains(history)) {
//            result = true;
//            historyList.add(history);
//            PreferencesUtil.setSharedStringData(SEARCH_HISTORY, GsonUtil.fromObjectToJsonString(historyList));
//        }
//        return result;
//
//
//    }
//
//
//    public static boolean recordHistoryProduct(ProductListBean.DataBean history) {
//        boolean result = false;
//        if (history != null) {
//            String productId = history.getId();
//            if (!TextUtils.isEmpty(productId)) {
//                result = true;
//                Iterator iterator = productHistoryList.iterator();
//                while (iterator.hasNext()) {
//                    ProductListBean.DataBean bean = (ProductListBean.DataBean) iterator.next();
//                    if(productId.equals(bean.getId())){
//                        iterator.remove();
//                    }
//
//                }
//                if(productHistoryList.size()>=MAX_HISTORY){
//                    productHistoryList.removeLast();
//                }
//                productHistoryList.addFirst(history);
//                PreferencesUtil.setSharedStringData(PRODUCT_HISTORY, GsonUtil.fromObjectToJsonString
//                        (productHistoryList));
//
//
//            }
//
//
//        }
//        return result;
//
//
//    }

    public static ArrayList<String> getHistoryList() {
        return historyList;
    }

    public static void initLoginState() {


//
        String token = getSharedStringData(AUTHORIZATION);
        if (!TextUtils.isEmpty(token)) {
            userInfomation.setToken(token);
        }
        String secretKey = getSharedStringData(SECRET_KEY);
        if (!TextUtils.isEmpty(secretKey)) {
            userInfomation.setSecretKey(secretKey);
        }

//        String productHistory = getSharedStringData(PRODUCT_HISTORY);
//        if (!TextUtils.isEmpty(productHistory)) {
//            LinkedList<ProductListBean.DataBean> detailList = GsonUtil
//                    .fromJsonStringToCollection(productHistory, new
//                            TypeToken<LinkedList<ProductListBean.DataBean>>() {
//                            }.getType());
//            if (detailList != null && !detailList.isEmpty()) {
//                productHistoryList.clear();
//                productHistoryList.addAll(detailList);
//            }
//
//        }

//        String hisList = getSharedStringData(SEARCH_HISTORY);
//        if (!TextUtils.isEmpty(hisList)) {
//            ArrayList<String> detailList = GsonUtil
//                    .fromJsonStringToCollection(hisList, new
//                            TypeToken<ArrayList<String>>() {
//                            }.getType());
//            if (detailList != null && !detailList.isEmpty()) {
//                historyList.clear();
//                historyList.addAll(detailList);
//            }
//
//        }
//        String lastLoginPhone = getSharedStringData(LAST_LOGIN_PHONE);
//        if (!TextUtils.isEmpty(lastLoginPhone)) {
//            userInfomation.setLastLoginPhone(lastLoginPhone);
//        }
    }

    //    public static void changeMobile(String phone) {
//        synchronized (userInfomation) {
//            userInfomation.setMobile(phone);
//        }
//
//
//    }
//
    public static void login(LoginBean.DataBean dataBean) {
        if (dataBean != null) {
            synchronized (userInfomation) {
                userInfomation.setPhone(dataBean.getPhone());
                userInfomation.setSecretKey(dataBean.getSecretKey());
                userInfomation.setToken(dataBean.getToken());
                PreferencesUtil.setSharedStringData(
                        AUTHORIZATION, dataBean.getToken());
                PreferencesUtil.setSharedStringData(
                        SECRET_KEY, dataBean.getSecretKey());
                PreferencesUtil.setSharedStringData(
                        LAST_LOGIN_PHONE, dataBean.getPhone());


            }
        }
    }


    public static AddressListBean.DataBean getDefaultAddress() {
        synchronized (userInfomation) {
            List<AddressListBean.DataBean> dataList = userInfomation.getAddressList();
            if (dataList != null && !dataList.isEmpty()) {
                for (AddressListBean.DataBean bean : dataList) {
                    if (bean.isDefaultAddress()) {
                        return bean;

                    }
                }

            }
            return null;

        }
    }

    public static void setAddressList(List<AddressListBean.DataBean> dataList) {
        if (dataList != null) {
            synchronized (userInfomation) {
                userInfomation.setAddressList(dataList);

            }
        }
    }

    public static void setUserInfo(UserInfoBean.DataBean userData) {
        if (userData != null) {
            synchronized (userInfomation) {
                userInfomation.setAge(userData.getAge());
                userInfomation.setAvatar(userData.getAvatar());
                userInfomation.setGender(userData.getGender());
                userInfomation.setGenderText(userData.getGenderText());
                userInfomation.setName(userData.getName());
                userInfomation.setLevelId(userData.getLevelId());
                userInfomation.setLevelName(userData.getLevelName());


            }
        }
    }

    public static String[] getSignRetrofit(Map<String,String> map){
        String[] signTimeToken = new String[3];
        long second = System.currentTimeMillis() / 1000;

        if (map != null){
            StringBuilder sBuilder = new StringBuilder();
            boolean isFirst = true;
            for (Map.Entry<String, String> entry: map.entrySet()) {
                if (isFirst){
                    sBuilder.append(entry.getKey()).append("=").append(entry.getValue());
                    isFirst = false;
                }else {
                    sBuilder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                }
            }

            String secret = getSecretKey();
            String complex =
                    sBuilder
                            + secret
                            + second;
            String md5 = Tools.convertMD5(complex);

            signTimeToken[0] = md5;
            signTimeToken[1] = String.valueOf(second);
            signTimeToken[2] = getToken();
            return signTimeToken;
        }else {
            String simple =
                    getSecretKey()
                            + second;
            String md5 = Tools.convertMD5(simple);

            signTimeToken[0] = md5;
            signTimeToken[1] = String.valueOf(second);
            signTimeToken[2] = getToken();
            return signTimeToken;
        }
    }


    public static List<KeyValuePair> getSignList(List<KeyValuePair> keys) {

        if (isLogin()) {
            Collections.sort(keys, new Comparator<KeyValuePair>() {
                public int compare(KeyValuePair arg0, KeyValuePair arg1) {
                    return arg0.getKey().compareTo(arg1.getKey());
                }
            });
            long second = System.currentTimeMillis() / 1000;

            String string = formatList(keys)
                    + getSecretKey()
                    + second;
            String md5 = Tools.convertMD5(string);
            keys.add(new BasicKeyValuePair(ParameterConstant.SIGN, md5));
            keys.add(new BasicKeyValuePair(ParameterConstant.TOKEN, getToken()));
            keys.add(new BasicKeyValuePair(ParameterConstant.TIME, String.valueOf(second)));

        }
        return keys;

    }

    public static String formatList(List<KeyValuePair> keys) {
        StringBuilder builder = new StringBuilder("");
        if (keys != null && !keys.isEmpty()) {

            for (int i = 0; i < keys.size(); i++) {
                KeyValuePair keyValuePair = keys.get(i);
                if (i == 0) {
                    builder.append(keyValuePair.getKey() + "=" + keyValuePair.getValue());
                } else {
                    builder.append("&" + keyValuePair.getKey() + "=" + keyValuePair.getValue());

                }
            }

        }
        return builder.toString();

    }

    /**
     * Created by Administrator on 2017/3/31 0031.
     *
     * @version V1.0
     * @date 2017.03.31
     */
    private static class UserInfomation {

        private List<AddressListBean.DataBean> addressList;

        public List<AddressListBean.DataBean> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<AddressListBean.DataBean> addressList) {
            this.addressList = addressList;
        }

        private String phone;
        private String token;
        private String secretKey;
        private String avatar;
        private String name;
        private String gender;
        private String genderText;
        private String age;

        private String levelId;
        private String levelName;


        public String getLevelId() {
            return levelId;
        }

        public void setLevelId(String levelId) {
            this.levelId = levelId;
        }

        public String getLevelName() {
            return levelName;
        }

        public void setLevelName(String levelName) {
            this.levelName = levelName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getGenderText() {
            return genderText;
        }

        public void setGenderText(String genderText) {
            this.genderText = genderText;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        /**
         * Instantiates a new User infomation.
         */
        public UserInfomation() {
        }


    }
}
