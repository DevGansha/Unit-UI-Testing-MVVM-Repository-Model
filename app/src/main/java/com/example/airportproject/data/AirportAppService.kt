package com.example.airportproject.data

import com.example.airportproject.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirportAppService {
    @GET("airport")
    suspend fun getAllAirportsData(): Response<ArrayList<ApiResponse>>

}