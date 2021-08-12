package com.ibareq.weathersample.data.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class Network(private val okHttpClient: OkHttpClient) {

    private val baseUrl = "https://www.metaweather.com/api/"

    fun getWeatherForCity(cityId: Int): Response{
        val request = Request.Builder().url("${baseUrl}location/$cityId/").build()
        return response(request)
    }

    fun getLocationResponse(cityName: String): Response{
        val request = Request.Builder().url("${baseUrl}location/search/?query=$cityName").build()
        return response(request)
    }


    fun response(request: Request): Response{
        return okHttpClient.newCall(request).execute()
    }

}