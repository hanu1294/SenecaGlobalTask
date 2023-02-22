package com.senecaglobal.weatherapp.repositories

import com.senecaglobal.weatherapp.models.WeatherInformationResponse
import com.senecaglobal.weatherapp.network.WeatherApi
import com.senecaglobal.weatherapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi) : BaseRepository() {

    suspend fun getWeatherInformation(
        location: String,
        appId: String
    ): Resource<WeatherInformationResponse> {
        return safeApiCall { weatherApi.getWeatherInformation(location, appId) }
    }
}