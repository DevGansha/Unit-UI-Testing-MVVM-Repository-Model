package com.example.airportproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiResponse (
    @SerializedName("airportCode"          ) var airportCode          : String?   = null,
    @SerializedName("internationalAirport" ) var internationalAirport : Boolean?  = null,
    @SerializedName("domesticAirport"      ) var domesticAirport      : Boolean?  = null,
    @SerializedName("regionalAirport"      ) var regionalAirport      : Boolean?  = null,
    @SerializedName("onlineIndicator"      ) var onlineIndicator      : Boolean?  = null,
    @SerializedName("eticketableAirport"   ) var eticketableAirport   : Boolean?  = null,
    @SerializedName("location"             ) var location             : Location? = null,
    @SerializedName("airportName"          ) var airportName          : String?   = null,
    @SerializedName("city"                 ) var city                 : City?     = null,
    @SerializedName("state"                ) var state                : State?    = null,
    @SerializedName("country"              ) var country              : Country?  = null,
    @SerializedName("region"               ) var region               : Region?   = null
) : Parcelable