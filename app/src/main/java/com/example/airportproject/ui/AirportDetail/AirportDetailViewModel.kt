package com.example.airportproject.ui.AirportDetail

import android.content.Context
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
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AirportDetailViewModel @Inject constructor(
    private val productRepository: AirportRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val product: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

//    fun fetchProduct(product_id: Int){
//        product.postValue(Resource.Loading())
//
//        viewModelScope.launch {
//            try {
//                if (hasInternetConnection(context)) {
//                    val response = productRepository.fetchProductDetail(product_id)
//                    if (response.isSuccessful) {
//                        product.postValue(Resource.Success(response.body()!!))
//                    } else{
//                        product.postValue(Resource.Error(response.message()))
//                    }
//                }
//                else{
//                    product.postValue(Resource.Error("No Internet Connection"))
//                }
//            }catch (ex: Exception) {
//                when (ex) {
//                    is IOException -> product.postValue(Resource.Error("Network Failure " + ex.localizedMessage))
//                    else -> product.postValue(Resource.Error(ex.message.toString()))
//                }
//            }
//        }
//    }
}