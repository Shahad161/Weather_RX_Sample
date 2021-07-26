package com.ibareq.weathersample.data.repository

import com.ibareq.weathersample.data.Status
import com.ibareq.weathersample.data.network.Client
import com.ibareq.weathersample.data.response.WeatherResponse
import com.ibareq.weathersample.data.response.LocationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow


object WeatherRepository {
    fun getWeatherForCity(cityName: String): Flow<Status<WeatherResponse>> {
        return getLocationInfo(cityName).flatMapConcat {
            when(it){
                is Status.Error -> {
                    flow {
                        emit(it)
                    }
                }
                is Status.Loading -> {
                    flow {
                        emit(it)
                    }
                }
                is Status.Success -> {
                    flow {
                        if (it.data.isEmpty()){
                            emit(Status.Error("city not found"))
                        } else {
                            emit(Client.getWeatherForCity(it.data[0].cityId)) //to make it easier we pick the first city and skip others
                        }
                    }
                }
            }
        }
    }

    private fun getLocationInfo(cityName: String): Flow<Status<LocationResponse>> {
        return flow {
            emit(Status.Loading)
            emit(Client.getLocationResponse(cityName))
        }
    }

}