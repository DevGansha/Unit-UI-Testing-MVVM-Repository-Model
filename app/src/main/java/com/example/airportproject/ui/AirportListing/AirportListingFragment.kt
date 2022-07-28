package com.example.airportproject.ui.AirportListing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.airportproject.R
import com.example.airportproject.data.model.ApiResponse
import com.example.airportproject.databinding.FrAirportListingBinding
import com.example.airportproject.util.Resource
import com.example.airportproject.util.Util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AirportListingFragment : Fragment(), RecyclerViewHomeClickListener{

    lateinit var frAirportListingBinding: FrAirportListingBinding
    val airportListingViewModel: AirportListingViewModel by viewModels()
    private val airportAdapter: AirportAdapter by lazy { AirportAdapter(requireContext(), this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        frAirportListingBinding = DataBindingUtil.inflate(inflater, R.layout.fr_airport_listing, container, false)
        return frAirportListingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()

        frAirportListingBinding.recyclerView.apply {
            adapter = airportAdapter
        }
    }
    fun observeUI(){
        airportListingViewModel.airportList.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    Log.d("AirportData", it.data.toString())
                    val data = it.data

                    data?.let {
                        airportAdapter.submitList(it)
                    }
                    frAirportListingBinding.progress.visibility = View.GONE
                }
                is Resource.Error -> {
                    frAirportListingBinding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        context?.toast(message)
                    }
                }

                is Resource.Loading -> {
                    frAirportListingBinding.progress.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun clickOnItem(data: ApiResponse, card: View) {
        val bundle = Bundle()
        bundle.putParcelable("AIRPORT", data)
        Navigation.findNavController(card).navigate(R.id.action_airportListingFragment_to_airportDetailFragment, bundle)
    }

}