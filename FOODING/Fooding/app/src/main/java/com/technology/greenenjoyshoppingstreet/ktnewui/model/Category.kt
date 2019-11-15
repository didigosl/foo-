package com.technology.greenenjoyshoppingstreet.ktnewui.model

import com.google.gson.annotations.SerializedName

data class Category(
        @SerializedName("code")
        val code: Int,
        @SerializedName("data")
        val `data`: List<Data>,
        @SerializedName("status")
        val status: String
) {
    data class Data(
            @SerializedName("categoryCover")
            val categoryCover: Any,
            @SerializedName("categoryId")
            val categoryId: String,
            @SerializedName("categoryName")
            val categoryName: String,
            @SerializedName("sons")
            val sons: List<Son>?
    ) {
        data class Son(
                @SerializedName("categoryCover")
                val categoryCover: Any,
                @SerializedName("categoryId")
                val categoryId: String,
                @SerializedName("categoryName")
                val categoryName: String,
                @SerializedName("sons")
                val sons: List<Son>
        ) {
            data class Son(
                    @SerializedName("categoryCover")
                    val categoryCover: String,
                    @SerializedName("categoryId")
                    val categoryId: String,
                    @SerializedName("categoryName")
                    val categoryName: String,
                    @SerializedName("sons")
                    val sons: Any
            )
        }
    }
}