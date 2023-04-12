package com.example.cleanarch.di.retroTime

import com.example.cleanarch.di.TimeNowViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// As a dependency of another class.
@Module
@InstallIn(ViewModelComponent::class)
object TimeModule {
    @Provides
    fun provideTimeService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://worldtimeapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}