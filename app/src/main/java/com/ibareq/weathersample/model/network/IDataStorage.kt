package com.ibareq.weathersample.model.network

import com.ibareq.weathersample.model.Status
import com.ibareq.weathersample.model.response.LocationResponse
import com.ibareq.weathersample.model.response.WeatherResponse
import okhttp3.Response

interface IDataStorage {
    fun getWeatherStatus(response: Response): Status<WeatherResponse>
    fun getLocationStatus(response: Response): Status<LocationResponse>
}