package com.osaat.fruity

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.osaat.fruity.activities.HomeActivity
import androidx.test.rule.ActivityTestRule
import com.osaat.fruity.screenRobots.HomeActivityRobot
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTests {

    @get:Rule
    var homeActivityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    private lateinit var homeActivityRobot: HomeActivityRobot

    @Before
    fun setup() {
        homeActivityRobot = HomeActivityRobot()
        homeActivityRobot.setup()
    }

    @After
    fun cleanup() {
        homeActivityRobot.cleanup()
    }

    @Test
    fun whenAppIsOpenedWithErrorResponse_thenCorrectToastMessageIsDisplayed() = with(homeActivityRobot) {
        launchAppWithErrorResponse()
        checkErrorToastMessageIDisplayed()
    }

    @Test
    fun whenAppIsOpenedWithSuccessResponse_thenListOfFruitIsDisplayed() = with(homeActivityRobot) {
        launchAppWithSuccessResponse()
        checkFruitListSize(9)
        checkFirstFruitItem()
        checkLastFruitItem()
    }
}