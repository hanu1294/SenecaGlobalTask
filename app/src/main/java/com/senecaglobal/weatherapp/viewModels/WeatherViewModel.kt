package com.senecaglobal.weatherapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senecaglobal.weatherapp.models.WeatherInformationResponse
import com.senecaglobal.weatherapp.repositories.WeatherRepository
import com.senecaglobal.weatherapp.util.Resource
import com.senecaglobal.weatherapp.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel
@Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _weatherInformation = MutableLiveData<Resource<WeatherInformationResponse>>()
    val weatherInformation: LiveData<Resource<WeatherInformationResponse>> = _weatherInformation

    fun getWeatherInformation(location: String, appId: String) =
        viewModelScope.launch {
            _weatherInformation.value = Resource(Status.LOADING, null, "")
            _weatherInformation.value = repository.getWeatherInformation(location, appId)
        }
}