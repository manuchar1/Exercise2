package com.example.exercise2.app

import android.app.Application
import android.content.Context
import com.example.exercise2.datastore.DataStore

class MoitaneApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DataStore.initialize(this,getSharedPreferences("_sp_",Context.MODE_PRIVATE))
    }
}