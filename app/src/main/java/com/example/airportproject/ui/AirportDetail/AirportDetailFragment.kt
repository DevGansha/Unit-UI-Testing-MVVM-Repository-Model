package com.example.airportproject.ui.AirportDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.airportproject.R
import com.example.airportproject.data.model.ApiResponse
import com.example.airportproject.databinding.FrAirportDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirportDetailFragment : Fragment() {

    var airportData : ApiResponse ?= null

    lateinit var frAirportDetailBinding: FrAirportDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        frAirportDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fr_airport_detail, container, false)
        return frAirportDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        airportData = arguments?.getParcelable<ApiResponse>("AIRPORT")

        airportData?.let{
            frAirportDetailBinding.airport = it
        }
    }

}