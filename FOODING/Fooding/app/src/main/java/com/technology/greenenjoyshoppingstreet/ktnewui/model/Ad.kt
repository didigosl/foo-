package com.technology.greenenjoyshoppingstreet.ktnewui.model

import com.google.gson.annotations.SerializedName

data class Ad(
        @SerializedName("code")
        val code: Int,
        @SerializedName("data")
        val data: MutableList<Data>,
        @SerializedName("status")
        val status: String
) {
    data class Data(
            @SerializedName("adId")
            val adId: String,
            @SerializedName("adName")
            val adName: String,
            @SerializedName("img")
            val img: String,
            @SerializedName("linkId")
            val linkId: String,
            @SerializedName("linkType")
            val linkType: String,
            @SerializedName("linkUrl")
            val linkUrl: Any
    )
}