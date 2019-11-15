package com.technology.greenenjoyshoppingstreet.category.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.technology.greenenjoyshoppingstreet.utils.BaseBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Bern on 2017/12/29 0029.
 */

public class ProductDetailBean extends BaseBean {


    /**
     * data : {"spuId":"5","spuName":"中高腰黑色显瘦修身小脚女牛仔裤","sn":"Z2015789456","cover":"http://xgj.didigo.es/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg","video":"","pics":["http://xgj.didigo.eshttp://xianggou.kz/uploads//9e/59/9e595003db0a4e1c4579331d95990e42143958_m.jpg"],"labels":[{"labelId":"1","labelName":"新品"}],"coupon":[{"levelId":"1","levelName":"年卡VIP","rebate":"0.00"}],"originPrice":"5.00","price":"3.00","stock":"888","content":"<p>商品介绍详细图文内容<\/p>","status":"1","statusText":"上架","skus":[{"skuId":"1","specInfo":"颜色:红色,尺码:XL","stock":"100","price":"1.80","specMode":"000011"}],"specs":[{"specId":"1","specName":"颜色","specs":[{"mode":"1","value":"红色"}]}],"comments":{"total":1,"totalPages":1,"pageLimit":20,"page":1,"list":{"orderCommentId":"1","content":"样式好看，质量不错","star":"5","userId":"2","userName":"Jolin Wong","orderCreateTime":"2017-12-01 15:03:40"}}}
     * code : 0
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * spuId : 5
         * spuName : 中高腰黑色显瘦修身小脚女牛仔裤
         * sn : Z2015789456
         * cover : http://xgj.didigo.es/uploads/images/e1/cf/e1cf2a59a2f8493bac4ae26663859adb23061_m.jpg
         * video :
         * pics : ["http://xgj.didigo.eshttp://xianggou.kz/uploads//9e/59/9e595003db0a4e1c4579331d95990e42143958_m.jpg"]
         * labels : [{"labelId":"1","labelName":"新品"}]
         * coupon : [{"levelId":"1","levelName":"年卡VIP","rebate":"0.00"}]
         * originPrice : 5.00
         * price : 3.00
         * stock : 888
         * content : <p>商品介绍详细图文内容</p>
         * status : 1
         * statusText : 上架
         * skus : [{"skuId":"1","specInfo":"颜色:红色,尺码:XL","stock":"100","price":"1.80","specMode":"000011"}]
         * specs : [{"specId":"1","specName":"颜色","specs":[{"mode":"1","value":"红色"}]}]
         * comments : {"total":1,"totalPages":1,"pageLimit":20,"page":1,"list":{"orderCommentId":"1","content":"样式好看，质量不错","star":"5","userId":"2","userName":"Jolin Wong","orderCreateTime":"2017-12-01 15:03:40"}}
         */

        private String minInCart;
        private String minToBuy;


        private String spuId;
        private String spuName;
        private String sn;
        private String cover;
        private String video;
        private String originPrice;
        private String price;
        private String stock;
        private String content;
        private String status;
        private String statusText;
        private CommentsBean comments;
        private List<String> pics;
        private List<LabelsBean> labels;
        private List<CouponBean> coupon;
        private List<SkusBean> skus;
        private List<SpecsBeanX> specs;

        public String getMinToBuy() {
            return minToBuy;
        }

        public void setMinToBuy(String minToBuy) {
            this.minToBuy = minToBuy;
        }

        public String getMinInCart() {
            return minInCart;
        }

        public void setMinInCart(String minInCart) {
            this.minInCart = minInCart;
        }

        public String getSpuId() {
            return spuId;
        }

        public void setSpuId(String spuId) {
            this.spuId = spuId;
        }

        public String getSpuName() {
            return spuName;
        }

        public void setSpuName(String spuName) {
            this.spuName = spuName;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(String originPrice) {
            this.originPrice = originPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusText() {
            return statusText;
        }

        public void setStatusText(String statusText) {
            this.statusText = statusText;
        }

        public CommentsBean getComments() {
            return comments;
        }

        public void setComments(CommentsBean comments) {
            this.comments = comments;
        }

        public List<String> getPics() {
            return pics;
        }

        public void setPics(List<String> pics) {
            this.pics = pics;
        }

        public List<LabelsBean> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBean> labels) {
            this.labels = labels;
        }

        public List<CouponBean> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<CouponBean> coupon) {
            this.coupon = coupon;
        }

        public List<SkusBean> getSkus() {
            return skus;
        }

        public void setSkus(List<SkusBean> skus) {
            this.skus = skus;
        }

        public List<SpecsBeanX> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBeanX> specs) {
            this.specs = specs;
        }

        public static class CommentsBean implements Parcelable {
            /**
             * total : 1
             * totalPages : 1
             * pageLimit : 20
             * page : 1
             * list : {"orderCommentId":"1","content":"样式好看，质量不错","star":"5","userId":"2","userName":"Jolin Wong","orderCreateTime":"2017-12-01 15:03:40"}
             */

            private String total;
            private String totalPages;
            private String pageLimit;
            private String page;
            private List<ListBean> list;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getTotalPages() {
                return totalPages;
            }

            public void setTotalPages(String totalPages) {
                this.totalPages = totalPages;
            }

            public String getPageLimit() {
                return pageLimit;
            }

            public void setPageLimit(String pageLimit) {
                this.pageLimit = pageLimit;
            }

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean implements Parcelable {
                /**
                 * orderCommentId : 1
                 * content : 样式好看，质量不错
                 * star : 5
                 * userId : 2
                 * userName : Jolin Wong
                 * orderCreateTime : 2017-12-01 15:03:40
                 */

                private String avatar;
                private String orderCommentId;
                private String content;
                private String star;
                private String userId;
                private String userName;
                private String orderCreateTime;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getOrderCommentId() {
                    return orderCommentId;
                }

                public void setOrderCommentId(String orderCommentId) {
                    this.orderCommentId = orderCommentId;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getStar() {
                    return star;
                }

                public void setStar(String star) {
                    this.star = star;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }

                public String getOrderCreateTime() {
                    return orderCreateTime;
                }

                public void setOrderCreateTime(String orderCreateTime) {
                    this.orderCreateTime = orderCreateTime;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.avatar);
                    dest.writeString(this.orderCommentId);
                    dest.writeString(this.content);
                    dest.writeString(this.star);
                    dest.writeString(this.userId);
                    dest.writeString(this.userName);
                    dest.writeString(this.orderCreateTime);
                }

                public ListBean() {
                }

                protected ListBean(Parcel in) {
                    this.avatar = in.readString();
                    this.orderCommentId = in.readString();
                    this.content = in.readString();
                    this.star = in.readString();
                    this.userId = in.readString();
                    this.userName = in.readString();
                    this.orderCreateTime = in.readString();
                }

                public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
                    @Override
                    public ListBean createFromParcel(Parcel source) {
                        return new ListBean(source);
                    }

                    @Override
                    public ListBean[] newArray(int size) {
                        return new ListBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.total);
                dest.writeString(this.totalPages);
                dest.writeString(this.pageLimit);
                dest.writeString(this.page);
                dest.writeTypedList(this.list);
            }

            public CommentsBean() {
            }

            protected CommentsBean(Parcel in) {
                this.total = in.readString();
                this.totalPages = in.readString();
                this.pageLimit = in.readString();
                this.page = in.readString();
                this.list = in.createTypedArrayList(ListBean.CREATOR);
            }

            public static final Parcelable.Creator<CommentsBean> CREATOR = new Parcelable.Creator<CommentsBean>() {
                @Override
                public CommentsBean createFromParcel(Parcel source) {
                    return new CommentsBean(source);
                }

                @Override
                public CommentsBean[] newArray(int size) {
                    return new CommentsBean[size];
                }
            };
        }

        public static class LabelsBean implements Parcelable {
            /**
             * labelId : 1
             * labelName : 新品
             */

            private String labelId;
            private String labelName;

            public String getLabelId() {
                return labelId;
            }

            public void setLabelId(String labelId) {
                this.labelId = labelId;
            }

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.labelId);
                dest.writeString(this.labelName);
            }

            public LabelsBean() {
            }

            protected LabelsBean(Parcel in) {
                this.labelId = in.readString();
                this.labelName = in.readString();
            }

            public static final Parcelable.Creator<LabelsBean> CREATOR = new Parcelable.Creator<LabelsBean>() {
                @Override
                public LabelsBean createFromParcel(Parcel source) {
                    return new LabelsBean(source);
                }

                @Override
                public LabelsBean[] newArray(int size) {
                    return new LabelsBean[size];
                }
            };
        }

        public static class CouponBean implements Parcelable {
            /**
             * levelId : 1
             * levelName : 年卡VIP
             * rebate : 0.00
             */

            private String levelId;
            private String levelName;
            private String rebate;

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

            public String getRebate() {
                return rebate;
            }

            public void setRebate(String rebate) {
                this.rebate = rebate;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.levelId);
                dest.writeString(this.levelName);
                dest.writeString(this.rebate);
            }

            public CouponBean() {
            }

            protected CouponBean(Parcel in) {
                this.levelId = in.readString();
                this.levelName = in.readString();
                this.rebate = in.readString();
            }

            public static final Parcelable.Creator<CouponBean> CREATOR = new Parcelable.Creator<CouponBean>() {
                @Override
                public CouponBean createFromParcel(Parcel source) {
                    return new CouponBean(source);
                }

                @Override
                public CouponBean[] newArray(int size) {
                    return new CouponBean[size];
                }
            };
        }

        public static class SkusBean implements Parcelable {
            /**
             * skuId : 1
             * specInfo : 颜色:红色,尺码:XL
             * stock : 100
             * price : 1.80
             * specMode : 000011
             */

            private String selectCount;
            private String skuId;
            private String specInfo;
            private String stock;
            private String price;
            private String specMode;

            private long specModeNumber;

            public String getSelectCount() {
                return selectCount;
            }

            public void setSelectCount(String selectCount) {
                this.selectCount = selectCount;
            }

            public long getSpecModeNumber() {
                return specModeNumber;
            }

            public void setSpecModeNumber(long specModeNumber) {
                this.specModeNumber = specModeNumber;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getSpecInfo() {
                return specInfo;
            }

            public Set<String> getSepcLst() {
                Set<String> strings = new HashSet<>();
                if (!TextUtils.isEmpty(specInfo)) {
                    String[] array = specInfo.split(",");
                    if (array != null && array.length > 0) {
                        for (String str : array) {
                            if (!TextUtils.isEmpty(str) && str.contains(":")) {
                                strings.add(str.substring(str.indexOf(":") + 1));
                            }

                        }

                    }


                }
                return strings;
            }

            public void setSpecInfo(String specInfo) {
                this.specInfo = specInfo;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSpecMode() {
                return specMode;
            }

            public void setSpecMode(String specMode) {
                this.specMode = specMode;
            }

            public SkusBean() {
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.selectCount);
                dest.writeString(this.skuId);
                dest.writeString(this.specInfo);
                dest.writeString(this.stock);
                dest.writeString(this.price);
                dest.writeString(this.specMode);
                dest.writeLong(this.specModeNumber);
            }

            protected SkusBean(Parcel in) {
                this.selectCount = in.readString();
                this.skuId = in.readString();
                this.specInfo = in.readString();
                this.stock = in.readString();
                this.price = in.readString();
                this.specMode = in.readString();
                this.specModeNumber = in.readLong();
            }

            public static final Creator<SkusBean> CREATOR = new Creator<SkusBean>() {
                @Override
                public SkusBean createFromParcel(Parcel source) {
                    return new SkusBean(source);
                }

                @Override
                public SkusBean[] newArray(int size) {
                    return new SkusBean[size];
                }
            };
        }

        public static class SpecsBeanX implements Parcelable {
            /**
             * specId : 1
             * specName : 颜色
             * specs : [{"mode":"1","value":"红色"}]
             */

            private String specId;
            private String specName;
            private List<SpecsBean> specs;

            public String getSpecId() {
                return specId;
            }

            public void setSpecId(String specId) {
                this.specId = specId;
            }

            public String getSpecName() {
                return specName;
            }

            public void setSpecName(String specName) {
                this.specName = specName;
            }

            public List<SpecsBean> getSpecs() {
                return specs;
            }

            public void setSpecs(List<SpecsBean> specs) {
                this.specs = specs;
            }

            public static class SpecsBean implements Parcelable {
                /**
                 * mode : 1
                 * value : 红色
                 */

                private String mode;
                private String value;
                private SelectMode selectMode = SelectMode.UNSELECT;

                private long modeNumber = 0;

                public long getModeNumber() {
                    return modeNumber;
                }

                public void setModeNumber(long modeNumber) {
                    this.modeNumber = modeNumber;
                }

                public SelectMode getSelectMode() {
                    return selectMode;
                }

                public void setSelectMode(SelectMode selectMode) {
                    this.selectMode = selectMode;
                }

                public String getMode() {
                    return mode;
                }

                public void setMode(String mode) {
                    this.mode = mode;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.mode);
                    dest.writeString(this.value);
                }

                public SpecsBean() {
                }

                protected SpecsBean(Parcel in) {
                    this.mode = in.readString();
                    this.value = in.readString();
                }

                public static final Parcelable.Creator<SpecsBean> CREATOR = new Parcelable.Creator<SpecsBean>() {
                    @Override
                    public SpecsBean createFromParcel(Parcel source) {
                        return new SpecsBean(source);
                    }

                    @Override
                    public SpecsBean[] newArray(int size) {
                        return new SpecsBean[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.specId);
                dest.writeString(this.specName);
                dest.writeTypedList(this.specs);
            }

            public SpecsBeanX() {
            }

            protected SpecsBeanX(Parcel in) {
                this.specId = in.readString();
                this.specName = in.readString();
                this.specs = in.createTypedArrayList(SpecsBean.CREATOR);
            }

            public static final Parcelable.Creator<SpecsBeanX> CREATOR = new Parcelable.Creator<SpecsBeanX>() {
                @Override
                public SpecsBeanX createFromParcel(Parcel source) {
                    return new SpecsBeanX(source);
                }

                @Override
                public SpecsBeanX[] newArray(int size) {
                    return new SpecsBeanX[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.spuId);
            dest.writeString(this.spuName);
            dest.writeString(this.sn);
            dest.writeString(this.cover);
            dest.writeString(this.video);
            dest.writeString(this.originPrice);
            dest.writeString(this.price);
            dest.writeString(this.stock);
            dest.writeString(this.content);
            dest.writeString(this.status);
            dest.writeString(this.statusText);
            dest.writeString(this.minInCart);
            dest.writeString(this.minToBuy);
            dest.writeParcelable(this.comments, flags);
            dest.writeStringList(this.pics);
            dest.writeTypedList(this.labels);
            dest.writeTypedList(this.coupon);
            dest.writeTypedList(this.skus);
            dest.writeTypedList(this.specs);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.spuId = in.readString();
            this.spuName = in.readString();
            this.sn = in.readString();
            this.cover = in.readString();
            this.video = in.readString();
            this.originPrice = in.readString();
            this.price = in.readString();
            this.stock = in.readString();
            this.content = in.readString();
            this.status = in.readString();
            this.statusText = in.readString();
            this.minInCart = in.readString();
            this.minToBuy = in.readString();
            this.comments = in.readParcelable(CommentsBean.class.getClassLoader());
            this.pics = in.createStringArrayList();
            this.labels = in.createTypedArrayList(LabelsBean.CREATOR);
            this.coupon = in.createTypedArrayList(CouponBean.CREATOR);
            this.skus = in.createTypedArrayList(SkusBean.CREATOR);
            this.specs = in.createTypedArrayList(SpecsBeanX.CREATOR);
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
