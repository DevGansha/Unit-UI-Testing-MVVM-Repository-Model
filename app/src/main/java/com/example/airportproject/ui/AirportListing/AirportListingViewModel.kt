package com.example.airportproject.ui.AirportListing

import android.content.Context
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airportproject.data.model.ApiResponse
import com.example.airportproject.data.repository.AirportRepository
import com.example.airportproject.util.Resource
import com.example.airportproject.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AirportListingViewModel @Inject constructor(
    private val airportRepository: AirportRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var airportDataResponse: List<ApiResponse>?= null
    val airportList: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    init {
        fetchProducts()
    }

    private fun fetchProducts(){
        airportList.postValue(Resource.Loading())

        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = airportRepository.fetchData()
                    if (response.isSuccessful) {
                        airportList.postValue(handleResponse(response))
                    } else{
                        airportList.postValue(Resource.Error(response.message()))
                    }
                }
                else{
                    airportList.postValue(Resource.Error("No Internet Connection"))
                }
            }catch (ex: Exception) {
                when (ex) {
                    is IOException -> airportList.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
                    else -> airportList.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }

    private fun handleResponse(response: Response<ArrayList<ApiResponse>>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { it ->
                if (airportDataResponse == null)
                    airportDataResponse = it

                return Resource.Success(airportDataResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }
}