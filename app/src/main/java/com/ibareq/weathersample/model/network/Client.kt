package com.ibareq.weathersample.model.network

import com.ibareq.weathersample.model.Status
import com.ibareq.weathersample.model.response.LocationResponse
import com.ibareq.weathersample.model.response.WeatherResponse

class Client(
    private var network: Network,
    private var dataStorage: IDataStorage
) {

    fun getWeatherForCity(cityId: Int): Status<WeatherResponse>{
       val response =  network.getWeatherForCityResponse(cityId)
        return dataStorage.getWeatherStatus(response)
    }

    fun getLocationResponse(cityName: String): Status<LocationResponse>{
        val response = network.getLocationResponse(cityName)
        return dataStorage.getLocationStatus(response)
    }

}