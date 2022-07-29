package com.example.airportproject

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.airportproject.data.AirportAppService
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RetrofitTest {
    lateinit var mockWebServer: MockWebServer

    lateinit var apiService: AirportAppService
    lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(AirportAppService::class.java)
    }

    @Test
    fun `get all airport api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getAllAirportsData()
            val request = mockWebServer.takeRequest()
            assertEquals("/airport",request.path)
            assertEquals(true, response.body()?.isEmpty() == true)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}