package com.example.airportproject.ui.AirportListing

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.airportproject.data.AirportAppService
import com.example.airportproject.data.model.*
import com.example.airportproject.data.repository.AirportRepository
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AirportListingViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    lateinit var airportListingViewModel: AirportListingViewModel
//    lateinit var airportRepository: AirportRepository

    @InjectMocks
    var apiService =  Mockito.mock(AirportAppService::class.java)

    @InjectMocks
    var airportRepository =  Mockito.mock(AirportRepository::class.java)

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public  fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        //airportRepository = AirportRepository(apiService)
        airportListingViewModel = AirportListingViewModel(airportRepository,  context)
    }

    @Test
    fun getAirportDataSuccess() {
        var airportList : ArrayList<ApiResponse> = arrayListOf()

        var region = Region("AU", "Australia")
        var country = Country("AU", "Australia")
        var state = State("QLD", "Queensland")
        var city = City("AAB", "Arrabury", "Australia/Brisbane")
        var location = Location(-99999, 26.45, -0.4669, 141.00, 2.4609, "S", "E")

        var apiResponse = ApiResponse("AAB", false, false, false,
            false, false, location, "Arrabury",
            city, state, country, region)

        airportList.add(apiResponse)

        runBlocking {
            Mockito.`when`(airportRepository.fetchData())
                .thenReturn(Response.success(airportList))

            airportListingViewModel.fetchProducts()
            val result = airportListingViewModel.airportList.getOrAwaitValue()
            assertEquals(airportList, result.data) }
    }

    @Test
    fun `empty airport list test`() {

        var message = "NO DATA"
        var airportList : ArrayList<ApiResponse> = arrayListOf()

        var region = Region("AU", "Australia")
        var country = Country("AU", "Australia")
        var state = State("QLD", "Queensland")
        var city = City("AAB", "Arrabury", "Australia/Brisbane")
        var location = Location(-99999, 26.45, -0.4669, 141.00, 2.4609, "S", "E")

        var apiResponse = ApiResponse("AAB", false, false, false,
            false, false, location, "Arrabury", city, state, country, region)

        airportList.add(apiResponse)

        runBlocking {
            Mockito.`when`(airportRepository.fetchData())
                .thenReturn(Response.success(arrayListOf<ApiResponse>()))

            airportListingViewModel.fetchProducts()
            val result = airportListingViewModel.airportList.getOrAwaitValue()
            assertEquals(arrayListOf<ApiResponse>(), result.data) }
    }
}
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}