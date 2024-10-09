package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Constant
import com.example.weatherapp.api.NetworkResponce
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.api.WeatherModel
import kotlinx.coroutines.launch


class WeatherViewModel:ViewModel() {

    private  val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponce<WeatherModel>>()
    val WeatherResult : LiveData<NetworkResponce<WeatherModel>> = _weatherResult

    fun getData(city:String){

        _weatherResult.value=NetworkResponce.Loading
        viewModelScope.launch {
           try {
               val responce=  weatherApi.getWeather(Constant.apiKey,city)
               if (responce.isSuccessful) {
                   responce.body()?.let {
                       _weatherResult.value = NetworkResponce.success(it)
                   }
               } else {
                   _weatherResult.value = NetworkResponce.Error("Something went Wrong???")
               }
           }
           catch (e:Exception){
               _weatherResult.value = NetworkResponce.Error("Something went wrong??")
           }
        }



    }
}