package com.ibareq.weathersample.data.network

import com.ibareq.weathersample.data.Status
import com.ibareq.weathersample.data.response.LocationResponse
import com.ibareq.weathersample.data.response.WeatherResponse
import okhttp3.Response

interface IDataStorage {
    fun getWeatherStatus(response: Response): Status<WeatherResponse>
    fun getLocationStatus(response: Response): Status<LocationResponse>
}