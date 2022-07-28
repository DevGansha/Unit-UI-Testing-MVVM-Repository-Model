package com.example.airportproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City (
    @SerializedName("cityCode"     ) var cityCode     : String? = null,
    @SerializedName("cityName"     ) var cityName     : String? = null,
    @SerializedName("timeZoneName" ) var timeZoneName : String? = null
) : Parcelable