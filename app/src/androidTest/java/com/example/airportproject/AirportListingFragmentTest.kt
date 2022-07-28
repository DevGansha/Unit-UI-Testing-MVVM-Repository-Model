package com.example.airportproject

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.airportproject.R
import com.example.airportproject.data.model.*
import com.example.airportproject.ui.AirportListing.AirportListingFragment
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class AirportListingFragmentTest {

    @Test
    fun test_isAirportsListVisible() {

        var airportList : MutableList<ApiResponse> = mutableListOf()

        var region = Region("AU", "Australia")
        var country = Country("AU", "Australia")
        var state = State("QLD", "Queensland")
        var city = City("AAB", "Arrabury", "Australia/Brisbane")
        var location = Location(-99999, 26.45, -0.4669, 141.00, 2.4609, "S", "E")

        var apiResponse = ApiResponse("AAB", false, false, false,
        false, false, location, "Arrabury",
        city, state, country, region)

        val bundle = Bundle()
        bundle.putParcelable("AIRPORT", apiResponse)

        repeat(5){
            airportList.add(apiResponse)
        }

        val scenario = launchFragmentInContainer<AirportListingFragment>(
            bundle
        )

        // VERIFY
        // VERIFY
        onView(withId(R.id.recycler_view))
            .check(matches(withText(airportList.toString())))
    }
}