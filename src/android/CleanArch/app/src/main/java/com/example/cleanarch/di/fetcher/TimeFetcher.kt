package com.example.cleanarch.di.fetcher

import com.example.domain.datasource.IDataSourceRetrofit
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import kotlin.random.Random

class TimeFetcher @Inject constructor() : ITimeFetcher {
    var regions = arrayListOf("Europe/Andorra","Europe/Astrakhan","Europe/Athens","Europe/Belgrade",
        "Europe/Berlin","Europe/Brussels","Europe/Bucharest","Europe/Budapest","Europe/Chisinau","Europe/Dublin","Europe/Gibraltar","Europe/Helsinki","Europe/Istanbul","Europe/Kaliningrad","Europe/Kirov","Europe/Kyiv","Europe/Lisbon","Europe/London","Europe/Madrid","Europe/Malta","Europe/Minsk","Europe/Moscow","Europe/Paris","Europe/Prague","Europe/Riga","Europe/Rome","Europe/Samara","Europe/Saratov","Europe/Simferopol","Europe/Sofia","Europe/Tallinn","Europe/Tirane","Europe/Ulyanovsk","Europe/Vienna","Europe/Vilnius","Europe/Volgograd","Europe/Warsaw","Europe/Zurich")

    private val client: IDataSourceRetrofit = Retrofit.Builder()
        .baseUrl("https://worldtimeapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IDataSourceRetrofit::class.java)

    override suspend fun getTime(): String {
        val randomRegion = regions.get(Random.nextInt(regions.size))

        val response = client.getTime(randomRegion).awaitResponse().body()

        return "${response?.abbreviation} - ${response?.datetime}"
    }

}