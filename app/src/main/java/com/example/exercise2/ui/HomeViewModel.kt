package com.example.exercise2.ui

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise2.datastore.DataStore
import com.example.exercise2.model.shops.Shops
import com.example.exercise2.model.token.AuthToken
import com.example.exercise2.network.NetworkClient
import com.example.exercise2.utils.Constants.CLIENT_ID
import com.example.exercise2.utils.Constants.CLIENT_SECRET
import com.example.exercise2.utils.Constants.GRANT_TYPE
import com.example.exercise2.utils.Constants.SCOPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private lateinit var sharedPreferences: SharedPreferences

    private val shops = MutableLiveData<Shops>().apply {
        mutableListOf<Shops>()
    }
    private val token = MutableLiveData<AuthToken>().apply {
        mutableListOf<AuthToken>()
    }

    val _shopsLiveData: LiveData<Shops> = shops

    private val loadingLiveData = MutableLiveData<Boolean>()
    val _loadingLiveData: LiveData<Boolean> = loadingLiveData

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            getToken()
        }
    }

    private suspend fun getToken(){
        loadingLiveData.postValue(true)
        val accessToken = NetworkClient.shopsService.getToken(GRANT_TYPE,CLIENT_ID,CLIENT_SECRET,SCOPE)
        if (accessToken.isSuccessful) {
            val items = accessToken.body()
            token.postValue(items)
            items?.access_token?.let { DataStore.saveAuthToken(it) }

           getShops()

        }
        loadingLiveData.postValue(false)

    }


    private suspend fun getShops() {
        loadingLiveData.postValue(true)
        val result = NetworkClient.shopsService.getShops()
        if (result.isSuccessful) {
            val items = result.body()
            shops.postValue(items)
        }
        loadingLiveData.postValue(false)

    }
}