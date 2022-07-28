package com.example.airportproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country (

    @SerializedName("countryCode" ) var countryCode : String? = null,
    @SerializedName("countryName" ) var countryName : String? = null

): Parcelable