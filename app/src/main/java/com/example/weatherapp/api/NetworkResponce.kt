package com.example.weatherapp.api

sealed class NetworkResponce <out T>{

    data class success<out T>(val data: T) : NetworkResponce<T>()
    data class Error(val message:String) : NetworkResponce<Nothing>()
    object Loading:NetworkResponce<Nothing>()
}