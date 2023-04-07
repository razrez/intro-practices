package com.example.cleanarch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.usecases.user.UserUseCase
import com.example.domain.common.UserData
import kotlinx.coroutines.launch

class CleanArchViewModel: ViewModel() {

    private val userUseCase = UserUseCase()
    val userDataMutable = MutableLiveData<UserData?>()
    val usersMutableList = MutableLiveData<List<UserData>?>()

    fun getUserData(id: Int) {
        viewModelScope.launch {
            val userDataResponse = userUseCase.getUser(id = id)
            userDataMutable.postValue(userDataResponse)
        }
    }

    fun getUsers(count: Int) {
        viewModelScope.launch {
            val postsDataResponse = userUseCase.getUsers(count)
            print(postsDataResponse.toString())
            usersMutableList.postValue(postsDataResponse)
        }
    }


}