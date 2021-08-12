package com.ibareq.weathersample.data.network

import com.ibareq.weathersample.data.Status
import com.ibareq.weathersample.data.response.LocationResponse
import com.ibareq.weathersample.data.response.WeatherResponse
import okhttp3.Response

interface iDataStorage {
    fun getWeatherResponse(response: Response): Status<WeatherResponse>
    fun getLocationResponse(response: Response): Status<LocationResponse>
}