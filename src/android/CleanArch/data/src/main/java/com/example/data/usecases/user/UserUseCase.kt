package com.example.data.usecases.user

import com.example.data.datasource.user.DataSources
import com.example.domain.common.UserData
import com.example.domain.usecases.IUserUseCase
import retrofit2.awaitResponse

class UserUseCase : IUserUseCase{

    override suspend fun getUser(id: Int): UserData? {
        if (id == 0) {
            return null
        }

        return DataSources
            .userService
            .getUserData(id.toString())
            .awaitResponse()
            .body()
    }

    override suspend fun getUsers(count: Int): List<UserData>? {

        return DataSources
            .userService
            .getUsersData(count.toString())
            .awaitResponse()
            .body()
    }
}