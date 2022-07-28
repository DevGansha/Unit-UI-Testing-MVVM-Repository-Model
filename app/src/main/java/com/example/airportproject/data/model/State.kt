package com.example.airportproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class State (
    @SerializedName("stateCode" ) var stateCode : String? = null,
    @SerializedName("stateName" ) var stateName : String? = null
): Parcelable