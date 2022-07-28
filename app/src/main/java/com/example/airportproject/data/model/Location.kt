package com.example.airportproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location (
    @SerializedName("aboveSeaLevel"      ) var aboveSeaLevel      : Int?    = null,
    @SerializedName("latitude"           ) var latitude           : Double? = null,
    @SerializedName("latitudeRadius"     ) var latitudeRadius     : Double? = null,
    @SerializedName("longitude"          ) var longitude          : Double? = null,
    @SerializedName("longitudeRadius"    ) var longitudeRadius    : Double? = null,
    @SerializedName("latitudeDirection"  ) var latitudeDirection  : String? = null,
    @SerializedName("longitudeDirection" ) var longitudeDirection : String? = null
) : Parcelable
