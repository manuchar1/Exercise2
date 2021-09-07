package com.example.exercise2.network

import com.example.exercise2.datastore.DataStore
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        DataStore.fetchAuthToken()?.let {
            builder.addHeader("Authorization","Bearer ${DataStore.fetchAuthToken()}")
        }
        return chain.proceed(builder.build())

    }
}