package com.technology.greenenjoyshoppingstreet.ktnewui.model

import com.google.gson.annotations.SerializedName

data class GoodSpu(
        @SerializedName("code")
        val code: Int,
        @SerializedName("data")
        val data: Data,
        @SerializedName("status")
        val status: String
) {
    data class Data(
            @SerializedName("list")
            val list: List<Good>,
            @SerializedName("page")
            val page: Int,
            @SerializedName("pageLimit")
            val pageLimit: Int,
            @SerializedName("totalPages")
            val totalPages: Int
    ) {
        data class Good(
                @SerializedName("cover")
                val cover: String,
                @SerializedName("hasDefaultSku")
                val hasDefaultSku: String,
                @SerializedName("labels")
                val labels: List<Label>,
                @SerializedName("minInCart")
                val minInCart: String,
                @SerializedName("minToBuy")
                val minToBuy: String,
                @SerializedName("originPrice")
                val originPrice: String,
                @SerializedName("perLimit")
                val perLimit: Int,
                @SerializedName("price")
                val price: String,
                @SerializedName("rebates")
                val rebates: Any,
                @SerializedName("skus")
                val skus: List<Sku>,
                @SerializedName("specs")
                val specs: List<Any>,
                @SerializedName("spuId")
                val spuId: String,
                @SerializedName("spuName")
                val spuName: String,
                @SerializedName("stock")
                val stock: String,
                @SerializedName("unit")
                val unit: String
        ) {
            data class Sku(
                    @SerializedName("price")
                    val price: String,
                    @SerializedName("skuId")
                    val skuId: String,
                    @SerializedName("specInfo")
                    val specInfo: String,
                    @SerializedName("specMode")
                    val specMode: String,
                    @SerializedName("stock")
                    val stock: String
            )

            data class Label (
                    @SerializedName("labelId")
                    val labelId: String,
                    @SerializedName("labelName")
                    val labelName: String
            )
        }
    }
}