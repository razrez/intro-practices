package com.example.cleanarch.di

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarch.di.fetcher.TimeFetcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeNowViewModel @Inject constructor(): ViewModel() {
    val timeNow = MutableLiveData<String>()

    @Inject
    lateinit var timeFetcher: TimeFetcher

    fun getTime() {
        viewModelScope.launch {
            val res = timeFetcher.getTime()
            timeNow.postValue(res)
        }
    }
}