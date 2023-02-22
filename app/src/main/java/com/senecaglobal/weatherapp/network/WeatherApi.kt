package com.senecaglobal.weatherapp.network

import com.senecaglobal.weatherapp.models.WeatherInformationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET(GET_WEATHER_INFORMATION)
    suspend fun getWeatherInformation(
        @Query(LOCATION_KEY) locationKey: String,
        @Query(APP_ID) appId: String,
        @Query(UNITS) units: String = "metric"
    ): Response<WeatherInformationResponse>

}