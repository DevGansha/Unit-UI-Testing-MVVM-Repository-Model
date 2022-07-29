package com.example.airportproject.ui.AirportDetail

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.airportproject.R
import com.example.airportproject.data.model.*
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AirportDetailFragmentTest {

    @Test
    fun test_isAirportDetailVisible() {

        // GIVEN
        var region = Region("AU", "Australia")
        var country = Country("AU", "Australia")
        var state = State("QLD", "Queensland")
        var city = City("AAB", "Arrabury", "Australia/Brisbane")
        var location = Location(-99999, 26.45, -0.4669, 141.00, 2.4609, "S", "E")

        var apiResponse = ApiResponse("AAB", false, false, false,
            false, false, location, "Arrabury", city, state, country, region)

        val fragmentFactory = FragmentFactory()
        val bundle = Bundle()
        bundle.putParcelable("AIRPORT", apiResponse)
        val scenario = launchFragmentInContainer<AirportDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // VERIFY
        Espresso.onView(withId(R.id.region))
            .check(ViewAssertions.matches(withText(apiResponse.region?.regionName)))
    }
}