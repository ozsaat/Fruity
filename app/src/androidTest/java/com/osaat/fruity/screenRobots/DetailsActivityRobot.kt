package com.osaat.fruity.screenRobots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.osaat.fruity.R
import com.osaat.fruity.activities.DetailsActivity
import com.osaat.fruity.utils.TestConstants.ACTION_BAR_NAME
import com.osaat.fruity.utils.TestConstants.APPLE_DETAILS_TITLE
import com.osaat.fruity.utils.TestConstants.APPLE_PRICE
import com.osaat.fruity.utils.TestConstants.APPLE_WEIGHT
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule

class DetailsActivityRobot {

    @get:Rule
    var detailsActivityRule = ActivityTestRule(DetailsActivity::class.java, false, false)

    fun checkPriceIsDisplayedCorrectly() {
        onView(withId(R.id.price_view))
            .check(matches(allOf(withText(APPLE_PRICE))))
    }

    fun checkWeightIsDisplayedCorrectly() {
        onView(withId(R.id.weight_view))
            .check(matches(allOf(withText(APPLE_WEIGHT))))
    }

    fun checkToolbarTitle() {
      onView(allOf(isDescendantOfA(withResourceName(ACTION_BAR_NAME)), withText(APPLE_DETAILS_TITLE)))
                .check(matches(isCompletelyDisplayed()))
    }
}