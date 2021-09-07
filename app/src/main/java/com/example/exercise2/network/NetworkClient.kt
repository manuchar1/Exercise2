package com.example.exercise2.network

import com.example.exercise2.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object NetworkClient {

    val shopsService by lazy { createShopsService() }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createShopsService(): ShopService {
        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(BASE_URL)
        retrofitBuilder.client(
            OkHttpClient().newBuilder()
                .addInterceptor(AuthInterceptor())
                .addInterceptor(loggingInterceptor).build()
        )
        retrofitBuilder.addConverterFactory(mochiConvertor())
        return retrofitBuilder.build().create(ShopService::class.java)
    }

    private fun mochiConvertor() =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
        )
}