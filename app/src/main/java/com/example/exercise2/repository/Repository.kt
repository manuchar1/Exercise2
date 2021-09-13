package com.example.exercise2.repository

import com.example.exercise2.app.safeCall
import com.example.exercise2.datastore.DataStore
import com.example.exercise2.model.shops.Shops
import com.example.exercise2.model.token.AuthToken
import com.example.exercise2.network.NetworkClient
import com.example.exercise2.utils.Constants.CLIENT_ID
import com.example.exercise2.utils.Constants.CLIENT_SECRET
import com.example.exercise2.utils.Constants.GRANT_TYPE
import com.example.exercise2.utils.Constants.SCOPE
import com.example.exercise2.utils.Resource
import com.google.gson.Gson

class Repository {

    suspend fun getToken(): Resource<AuthToken> {
        return safeCall {
            val accessToken = NetworkClient.shopsService.getToken(
                GRANT_TYPE,
                CLIENT_ID,
                CLIENT_SECRET,
                SCOPE,
            )
            if (accessToken.isSuccessful){
                val item = accessToken.body()!!
                item.access_token.let { DataStore.saveAuthToken(it) }
                getShops()
                Resource.Success(item)
            }else {
                val error = Gson().fromJson(accessToken.errorBody()!!.string(),Error::class.java)
                Resource.Error(error.toString())
            }

        }
    }

    suspend fun getShops(): Resource<Shops> {
        return safeCall {
            val response = NetworkClient.shopsService.getShops()
            if (response.isSuccessful){
                val body = response.body()!!
                Resource.Success(body)
            }else {
                val error = Gson().fromJson(response.errorBody()!!.string(),Error::class.java)
                Resource.Error(error.toString())
            }

        }
    }
}