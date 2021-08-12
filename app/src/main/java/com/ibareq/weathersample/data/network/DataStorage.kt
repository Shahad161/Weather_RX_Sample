package com.ibareq.weathersample.data.network

import com.google.gson.Gson
import com.ibareq.weathersample.data.Status
import com.ibareq.weathersample.data.response.LocationResponse
import com.ibareq.weathersample.data.response.WeatherResponse
import okhttp3.Response

class DataStorage(private val gson: Gson): iDataStorage{

    fun getStatusOfWeatherForCity(response: Response): Status<WeatherResponse>{
        return if (response.isSuccessful){
            val weatherResponse = gson.fromJson(response.body!!.string(), WeatherResponse::class.java)
            Status.Success(weatherResponse)
        } else {
            errorMessage(response)
        }
    }

    fun getStatusOfLocationResponse(response: Response): Status<LocationResponse>{
        return if (response.isSuccessful){
            val locationResponse = gson.fromJson(response.body!!.string(), LocationResponse::class.java)
            Status.Success(locationResponse)
        } else {
            errorMessage(response)
        }
    }


    private fun errorMessage(response: Response): Status.Error {
        return Status.Error(response.message)
    }

    override fun getWeatherResponse(response: Response): Status<WeatherResponse> {
        return getStatusOfWeatherForCity(response)
    }

    override fun getLocationResponse(response: Response): Status<LocationResponse> {
        return getStatusOfLocationResponse(response)
    }

}