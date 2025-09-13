package com.example

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.api.Constant
import com.api.NetworkResponse
import com.api.RetrofitInstance
import com.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: MutableLiveData<NetworkResponse<WeatherModel>> = _weatherResult
    fun getData(city: String) {
        _weatherResult.postValue(NetworkResponse.Loading)

        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(Constant.API_KEY,city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.postValue(NetworkResponse.Success(it))
                    }
                } else {
                    _weatherResult.postValue(NetworkResponse.Error(response.message()))
                    Log.i("TAG", "failed to getData: ${response.message()}")
                }
            }
            catch (e: Exception) {
                _weatherResult.postValue(NetworkResponse.Error(e.message ?: "Unknown Error"))
                Log.i("TAG", "failed to getData: ${e.message}")
            }

        }

    }
}