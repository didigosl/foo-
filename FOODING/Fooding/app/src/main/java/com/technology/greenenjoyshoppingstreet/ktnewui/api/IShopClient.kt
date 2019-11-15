package com.technology.greenenjoyshoppingstreet.ktnewui.api

import com.technology.greenenjoyshoppingstreet.ktnewui.model.Ad
import com.technology.greenenjoyshoppingstreet.ktnewui.model.Category
import com.technology.greenenjoyshoppingstreet.ktnewui.model.GoodSpu
import com.technology.greenenjoyshoppingstreet.newui.model.cartShop.CartShop
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface IShopClient {

    @POST("/api/ad/get")
    @FormUrlEncoded
    fun getAdList(
            @Field("type") type: String
    ): Call<Ad>

    @GET("/api/category/list")
    fun getCategoryList(): Call<Category>

    @POST("/api/goods_spu/list")
    @FormUrlEncoded
    fun getGoodList(
            @Field("categoryId") categoryId: String,
            @Field("pageLimit") pageLimit: String,
            @Field("order") order: String
    ): Call<GoodSpu>

    @POST("/api/goods_spu/list")
    @FormUrlEncoded
    fun getGoodListLabel(
            @Field("labelId") labelId: String,
            @Field("page") page: String,
            @Field("pageLimit") pageLimit: String
    ): Call<GoodSpu>
}