package com.api

import retrofit2.Retrofit

object RetrofitInstance {
    private const val baseUrl = "https://api.weatherapi.com"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
            .build()

    }
    val weatherApi: WeatherApi =
        getInstance().create(WeatherApi::class.java)

}