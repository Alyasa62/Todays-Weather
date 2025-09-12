package com.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.annotation.processing.Generated

interface WeatherApi {

    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apikey: String,
        @Query("q") city: String
    ) : Response<WeatherModel>


}