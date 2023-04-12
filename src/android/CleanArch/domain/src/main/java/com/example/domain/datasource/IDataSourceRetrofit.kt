package com.example.domain.datasource

import com.example.domain.common.TimeData
import com.example.domain.common.UserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IDataSourceRetrofit {

    @GET("posts/{id}")
    fun getUserData(@Path("id") id:String): Call<UserData>

    @GET("users")
    fun getUsersData(@Query("_limit") _limit:String): Call<List<UserData>>

    @GET("api/timezone/{region}")
    fun getTime(@Path("region") region:String): Call<TimeData>
}