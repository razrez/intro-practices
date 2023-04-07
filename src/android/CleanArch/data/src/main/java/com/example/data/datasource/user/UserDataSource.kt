package com.example.data.datasource.user

import com.example.core.http_clients.KtorClient
import com.example.domain.common.UserData
import com.example.domain.datasource.IUserDataSource
import io.ktor.client.call.*
import io.ktor.client.request.*

class UserDataSource : IUserDataSource {
    override suspend fun getUser(id: Int): UserData {
        return KtorClient.ktorClient.get("todos/2").body()
    }
}