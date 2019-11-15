package com.technology.greenenjoyshoppingstreet.ktnewui.api

import com.technology.greenenjoyshoppingstreet.utils.constant.URLConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {
        val BASE_URL = URLConstant.HOST

        fun create(): IShopClient {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create(IShopClient::class.java)
        }
    }
}