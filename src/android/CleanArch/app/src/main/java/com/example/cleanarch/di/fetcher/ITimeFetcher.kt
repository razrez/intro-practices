package com.example.cleanarch.di.fetcher

interface ITimeFetcher {
    suspend fun getTime(): String
}