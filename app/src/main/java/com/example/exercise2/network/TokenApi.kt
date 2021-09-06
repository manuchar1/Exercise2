package com.example.exercise2.network

import com.example.exercise2.data.AuthToken
import com.example.exercise2.utils.Constants.API_ENDPOINT
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.POST

interface TokenApi {

    @POST(API_ENDPOINT)
    suspend fun getToken(
        @Field("client_id")
        clientId:String,

      //  @Field(CLIENT_SECRET)
    ):Response<AuthToken>


}