package com.example.data.datasource.user

import com.example.core.http_clients.RetrofitClient.Companion.retrofitClient
import com.example.domain.datasource.IDataSourceRetrofit

class DataSources {
    companion object{
        val userService: IDataSourceRetrofit = retrofitClient.create(IDataSourceRetrofit::class.java)
    }
}