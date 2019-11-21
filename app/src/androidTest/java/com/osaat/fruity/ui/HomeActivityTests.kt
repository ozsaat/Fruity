package com.osaat.fruity.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.osaat.fruity.screenRobots.HomeActivityRobot
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTests {

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
    fun whenAppIsOpened_thenToolbarTitleIsCorrect() = with(homeActivityRobot) {
        launchAppWithSuccessResponse()
        checkToolbarTitle()
    }

    @Test
    fun whenAppIsOpenedWithErrorResponse_thenCorrectToastMessageIsDisplayed() = with(homeActivityRobot) {
        launchAppWithErrorResponse()
        checkErrorToastMessageIsDisplayed()
    }

    @Test
    fun whenAppIsOpenedWithSuccessResponse_thenListOfFruitIsDisplayed() = with(homeActivityRobot) {
        launchAppWithSuccessResponse()
        checkFruitListSize(9)
        checkFirstFruitItem()
        checkLastFruitItem()
    }
}