package com.example.airportproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Region (
    @SerializedName("regionCode" ) var regionCode : String? = null,
    @SerializedName("regionName" ) var regionName : String? = null
): Parcelable