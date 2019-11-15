package com.technology.greenenjoyshoppingstreet.login.bean;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

/**
 * Created by Bern on 2017/12/27 0027.
 */

public class UserInfoBean extends BaseBean {


    /**
     * data : {"avatar":null,"name":"杰克王","gender":"1","genderText":"男","age":null}
     * code : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * avatar : null
         * name : 杰克王
         * gender : 1
         * genderText : 男
         * age : null
         */

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
    }
}
