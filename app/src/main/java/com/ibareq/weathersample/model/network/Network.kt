package com.ibareq.weathersample.model.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class Network(private val okHttpClient: OkHttpClient){

    private val baseUrl = "https://www.metaweather.com/api/"
    fun getWeatherForCityResponse(cityId: Int): Response{
        val request = Request.Builder().url("${baseUrl}location/$cityId/").build()
        return okHttpClient.newCall(request).execute()
    }

    fun getLocationResponse(cityName: String): Response{
        val request = Request.Builder().url("${baseUrl}location/search/?query=$cityName").build()
        return okHttpClient.newCall(request).execute()
    }

}