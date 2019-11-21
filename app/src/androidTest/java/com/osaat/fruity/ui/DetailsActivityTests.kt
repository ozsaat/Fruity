package com.osaat.fruity.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.osaat.fruity.screenRobots.DetailsActivityRobot
import com.osaat.fruity.screenRobots.HomeActivityRobot
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsActivityTests {

    private lateinit var homeActivityRobot: HomeActivityRobot
    private lateinit var detailsActivityRobot: DetailsActivityRobot

    @Before
    fun setup() {
        detailsActivityRobot = DetailsActivityRobot()
        homeActivityRobot = HomeActivityRobot()
        homeActivityRobot.setup()
    }

    @After
    fun cleanup() {
        homeActivityRobot.cleanup()
    }

    @Test
    fun whenUserNavigatesToDetailsScreen_thenDetailsScreenTitleIsCorrect() {
        with(homeActivityRobot) {
            launchAppWithSuccessResponse()
            tapFirstItem()
        }

        with(detailsActivityRobot) {
            checkToolbarTitle()
        }
    }

    @Test
    fun whenUserNavigatesToDetailsScreen_thenPriceAndWeightAreDisplayedCorrectly() {
        with(homeActivityRobot) {
            launchAppWithSuccessResponse()
            tapFirstItem()
        }

        with(detailsActivityRobot) {
            checkPriceIsDisplayedCorrectly()
            checkWeightIsDisplayedCorrectly()
        }
    }
}