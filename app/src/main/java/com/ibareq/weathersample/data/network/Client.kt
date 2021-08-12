package com.ibareq.weathersample.data.network

import com.ibareq.weathersample.data.Status
import com.ibareq.weathersample.data.response.LocationResponse
import com.ibareq.weathersample.data.response.WeatherResponse

object Client{
    private lateinit var dataStorage: DataStorage
    private lateinit var network: Network

    fun getWeatherForCity(cityId: Int): Status<WeatherResponse>{
       val response =  network.getWeatherForCity(cityId)
        return dataStorage.getWeatherResponse(response)
    }

    fun getLocationResponse(cityName: String): Status<LocationResponse>{
        val response = network.getLocationResponse(cityName)
        return dataStorage.getLocationResponse(response)
    }
}