package com.osaat.fruity.screenRobots

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.osaat.fruity.Constants
import com.osaat.fruity.R
import com.osaat.fruity.activities.HomeActivity
import com.osaat.fruity.utils.*
import com.osaat.fruity.utils.TestConstants.ACTION_BAR_NAME
import com.osaat.fruity.utils.TestConstants.APPLE_TITLE
import com.osaat.fruity.utils.TestConstants.HOME_SCREEN_TITLE
import com.osaat.fruity.utils.TestConstants.KIWI_TITLE
import com.osaat.fruity.utils.TestConstants.TOAST_ERROR_MESSAGE
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
import org.junit.Rule

class HomeActivityRobot {

    private val mockWebServer = MockWebServer()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    var homeActivityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    //Successful response with data
    private lateinit var mockResponseSuccess: MockResponse

    //Response returns empty json
    private lateinit var mockResponseEmpty: MockResponse

    //Error response
    private lateinit var mockResponseError: MockResponse

    fun setup() {
        mockWebServer.start(8080)
        Constants.BASE_URL = mockWebServer.url("/").toString()

        mockResponseSuccess = context.createResponse(localResponsePath = "response_success.json", responseCode = 200)
        mockResponseEmpty = context.createResponse(localResponsePath = "response_success_empty.json", responseCode = 200)
        mockResponseError = context.createResponse(responseCode = 505)
    }

    fun cleanup() {
        mockWebServer.shutdown()
    }

    fun tapFirstItem() {
        onView(withRecyclerView(R.id.recycler_view)
            ?.atPositionOnView(0, R.id.home_item_title_view))
            .perform(click())
    }

    fun launchAppWithSuccessResponse() {
        mockWebServer.enqueue(mockResponseSuccess)
        homeActivityRule.launchActivity(null)
    }

    fun launchAppWithErrorResponse() {
        mockWebServer.enqueue(mockResponseError)
        homeActivityRule.launchActivity(null)
    }

    fun checkErrorToastMessageIsDisplayed() {
        onView(withText(TOAST_ERROR_MESSAGE)).inRoot(ToastMatcher()).check(
            matches(isDisplayed())
        )
    }

    fun checkFirstFruitItem() {
        onView(withRecyclerView(R.id.recycler_view)
            ?.atPositionOnView(0, R.id.home_item_title_view))
            .check(matches(allOf(withText(APPLE_TITLE), isCompletelyDisplayed())))
    }

    fun checkLastFruitItem() {
        onView(withId(R.id.recycler_view))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(8))

        onView(withRecyclerView(R.id.recycler_view)
            ?.atPositionOnView(8, R.id.home_item_title_view))
            .check(matches(allOf(withText(KIWI_TITLE), isCompletelyDisplayed())))
    }

    fun checkFruitListSize(expectedNumberOfItems: Int) {
        onView(withId(R.id.recycler_view))
            .check(RecyclerViewItemCountAssertion(expectedNumberOfItems))
    }

    fun checkToolbarTitle() {
        onView(allOf(isDescendantOfA(withResourceName(ACTION_BAR_NAME)), withText(HOME_SCREEN_TITLE)))
            .check(matches(isCompletelyDisplayed()))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher? {
        return RecyclerViewMatcher(recyclerViewId)
    }
}