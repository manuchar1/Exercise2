package com.example.exercise2.network

import com.example.exercise2.model.shops.Shops
import com.example.exercise2.model.token.AuthToken
import com.example.exercise2.utils.Constants.API_ENDPOINT
import com.example.exercise2.utils.Constants.TOKEN_URL_ENDPOINT
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ShopService {
    @GET(API_ENDPOINT)
    suspend fun getShops() : Response<Shops>

    @POST(TOKEN_URL_ENDPOINT)
    @FormUrlEncoded
    suspend fun getToken(
        @Field("grant_type")
        grantType:String,
        @Field("client_id")
        clientId: String,
        @Field("client_secret")
        clientSecret:String,
        @Field("scope")
        scope:String,
    ): Response<AuthToken>
}