package com.osaat.fruity.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.osaat.fruity.screenRobots.HomeActivityRobot
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/*
Instead of writing a lot of tests, my aim was to create a stable and easily extendable base.
The benefit being human readable tests and reusable components that will make adding more tests easier
*/

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

    //Mocking error response lets us test how we handle error response
    @Test
    fun whenAppIsOpenedWithErrorResponse_thenCorrectToastMessageIsDisplayed() = with(homeActivityRobot) {
        launchAppWithErrorResponse()
        checkErrorToastMessageIsDisplayed()
    }

    // Checking list size and validating specific recyclerview items
    @Test
    fun whenAppIsOpenedWithSuccessResponse_thenListOfFruitIsDisplayed() = with(homeActivityRobot) {
        launchAppWithSuccessResponse()
        checkFruitListSize(9)
        checkFirstFruitItem()
        checkLastFruitItem()
    }
}