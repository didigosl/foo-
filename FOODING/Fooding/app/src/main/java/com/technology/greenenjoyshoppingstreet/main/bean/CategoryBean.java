package com.technology.greenenjoyshoppingstreet.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bern on 2017/12/21 0021.
 */

public class CategoryBean extends BaseBean {


    /**
     * data : [{"categoryId":"6","categoryName":"服装","categoryCover":"http://xianggou.kz/uploads/images/e6/88/e6880f56081ba594b9e86656db30fefb3537.png","sons":[{"categoryId":"8","categoryName":"女装","categoryCover":"http://xianggou.kz/uploads/images/e6/88/e6880f56081ba594b9e86656db30fefb3537.png","sons":[{"categoryId":"14","categoryName":"短裙","categoryCover":null,"sons":null}]}]}]
     * code : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * categoryId : 6
         * categoryName : 服装
         * categoryCover : http://xianggou.kz/uploads/images/e6/88/e6880f56081ba594b9e86656db30fefb3537.png
         * sons : [{"categoryId":"8","categoryName":"女装","categoryCover":"http://xianggou.kz/uploads/images/e6/88/e6880f56081ba594b9e86656db30fefb3537.png","sons":[{"categoryId":"14","categoryName":"短裙","categoryCover":null,"sons":null}]}]
         */

        private boolean isChecked;
        private String categoryId;
        private String categoryName;
        private String categoryCover;
        private List<DataBean> sons;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCategoryCover() {
            return categoryCover;
        }

        public void setCategoryCover(String categoryCover) {
            this.categoryCover = categoryCover;
        }

        public List<DataBean> getSons() {
            return sons;
        }

        public void setSons(List<DataBean> sons) {
            this.sons = sons;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
            dest.writeString(this.categoryId);
            dest.writeString(this.categoryName);
            dest.writeString(this.categoryCover);
            dest.writeList(this.sons);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.isChecked = in.readByte() != 0;
            this.categoryId = in.readString();
            this.categoryName = in.readString();
            this.categoryCover = in.readString();
            this.sons = new ArrayList<DataBean>();
            in.readList(this.sons, DataBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
