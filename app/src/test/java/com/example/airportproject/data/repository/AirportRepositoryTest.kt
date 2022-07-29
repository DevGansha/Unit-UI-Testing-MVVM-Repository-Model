package com.example.airportproject.data.repository

import com.example.airportproject.data.AirportAppService
import com.example.airportproject.data.model.ApiResponse
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AirportRepositoryTest{

    @InjectMocks
    var airportRepository =  Mockito.mock(AirportRepository::class.java)

    @Mock
    lateinit var airportAppService: AirportAppService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        airportRepository = AirportRepository(airportAppService)
    }

    @Test
    fun `get all movie test`() {
        runBlocking {
            Mockito.`when`(airportAppService.getAllAirportsData()).thenReturn(Response.success(
                arrayListOf<ApiResponse>()
            ))
            val response = airportRepository.fetchData()
            assertEquals(arrayListOf<ApiResponse>(), response.body())
        }

    }



}