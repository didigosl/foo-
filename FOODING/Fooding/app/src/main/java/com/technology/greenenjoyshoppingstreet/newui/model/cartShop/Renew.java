
package com.technology.greenenjoyshoppingstreet.newui.model.cartShop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Renew {

    @SerializedName("data")
    @Expose
    private RenewData data;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private long code;

    /**
     * No args constructor for use in serialization
     *
     */
    public Renew() {
    }

    /**
     *
     * @param status
     * @param data
     * @param code
     */
    public Renew(RenewData data, String status, long code) {
        super();
        this.data = data;
        this.status = status;
        this.code = code;
    }

    public RenewData getData() {
        return data;
    }

    public void setData(RenewData data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    class RenewData {

        @SerializedName("total")
        @Expose
        private String total;

        /**
         * No args constructor for use in serialization
         *
         */
        public RenewData() {
        }

        /**
         *
         * @param total
         */
        public RenewData(String total) {
            super();
            this.total = total;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

    }

}





