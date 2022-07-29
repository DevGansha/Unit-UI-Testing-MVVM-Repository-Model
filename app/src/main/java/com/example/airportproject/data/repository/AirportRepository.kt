package com.example.airportproject.data.repository

import com.example.airportproject.data.AirportAppService
import com.example.airportproject.data.model.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AirportRepository@Inject constructor(
    private val airportAppService: AirportAppService) {

    open suspend fun fetchData(): Response<ArrayList<ApiResponse>> = withContext(
        Dispatchers.IO) {
         airportAppService.getAllAirportsData()
    }
}
