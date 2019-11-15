package com.technology.greenenjoyshoppingstreet.newui.api.service;


import com.technology.greenenjoyshoppingstreet.newui.model.cartShop.CartShop;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ICartClient {

    @POST("/api/cart/add")
    @FormUrlEncoded
    Call<CartShop> addCart(
            @Field("num") String num,
            @Field("sign") String secretKey,
            @Field("skuId") String skuId,
            @Field("time") String time,
            @Field("token") String token
    );


}
