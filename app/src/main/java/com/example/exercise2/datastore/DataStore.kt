package com.example.exercise2.datastore

import android.content.Context
import android.content.SharedPreferences
import com.example.exercise2.utils.Constants


object DataStore {

    private var sharedPreferences : SharedPreferences? = null

    fun initialize(context:Context, sharedPreferences: SharedPreferences) {
        DataStore.sharedPreferences = sharedPreferences
    }

    fun saveAuthToken(token: String) {
        val editor = sharedPreferences?.edit()
        editor?.putString(Constants.ACCESS_TOKEN, token)
        editor?.apply()
    }

    fun fetchAuthToken(): String? {
        return sharedPreferences?.getString(Constants.ACCESS_TOKEN, null)
    }
}