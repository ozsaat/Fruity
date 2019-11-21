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

/*
Using Robot design pattern to make the tests easier to read and maintain.
Also has the benefit of making it easy to extend by providing reusable functions
*/

class HomeActivityRobot {

    private val mockWebServer = MockWebServer()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    var homeActivityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    private lateinit var mockResponseSuccess: MockResponse
    private lateinit var mockResponseEmpty: MockResponse
    private lateinit var mockResponseError: MockResponse

    fun setup() {
        mockWebServer.start(8080)
        Constants.BASE_URL = mockWebServer.url("/").toString()

        //Successful response with data
        mockResponseSuccess = context.createResponse(localResponsePath = "response_success.json", responseCode = 200)
        //Response returns empty json
        mockResponseEmpty = context.createResponse(localResponsePath = "response_success_empty.json", responseCode = 200)
        //Error response
        mockResponseError = context.createResponse(responseCode = 505)
    }

    fun cleanup() {
        mockWebServer.shutdown()
    }

    fun tapFirstItem() {
        onView(withRecyclerView(R.id.recycler_view)
            !!.atPositionOnView(0, R.id.home_item_title_view))
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

    private fun checkRecyclerViewItem(position: Int, itemName: String) {
        onView(withRecyclerView(R.id.recycler_view)
        !!.atPositionOnView(position, R.id.home_item_title_view))
            .check(matches(allOf(withText(itemName), isCompletelyDisplayed())))
    }

    fun checkFirstFruitItem() {
        checkRecyclerViewItem(0, APPLE_TITLE)
    }

    fun checkLastFruitItem() {
        onView(withId(R.id.recycler_view))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(8))

        checkRecyclerViewItem(8, KIWI_TITLE)
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