package com.example.exercise2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise2.model.shops.Shops
import com.example.exercise2.model.token.AuthToken
import com.example.exercise2.repository.Repository
import com.example.exercise2.utils.Event
import com.example.exercise2.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel() : ViewModel() {

    private lateinit var repository: Repository


    private val _postTokenLiveData = MutableLiveData<Event<Resource<AuthToken>>>()
    val postTokenLiveData: LiveData<Event<Resource<AuthToken>>> = _postTokenLiveData

    private val _postLiveData = MutableLiveData<Event<Resource<Shops>>>()
    val postLiveData: LiveData<Event<Resource<Shops>>> = _postLiveData

    fun initShops() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository = Repository()
                _postTokenLiveData.postValue(Event(repository.getToken()))
                _postLiveData.postValue(Event(repository.getShops()))
            }

        }

    }
}