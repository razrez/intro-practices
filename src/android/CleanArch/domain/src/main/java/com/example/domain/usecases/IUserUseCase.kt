package com.example.domain.usecases

import com.example.domain.common.UserData

interface IUserUseCase {
    suspend fun getUser(id: Int) : UserData?
    suspend fun getUsers(count: Int) : List<UserData?>?
}