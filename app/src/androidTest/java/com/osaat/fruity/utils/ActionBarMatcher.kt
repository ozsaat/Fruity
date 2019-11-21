package com.osaat.fruity.utils

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

object ActionBarMatcher {

    fun withResourceName(resourceName: String): Matcher<View> {
        return withResourceName(Matchers.`is`(resourceName))
    }

    fun withResourceName(resourceNameMatcher: Matcher<String>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with resource name: ")
                resourceNameMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val id = view.id
                return id != View.NO_ID && id != 0 && view.resources != null && resourceNameMatcher.matches(
                    view.resources.getResourceName(id)
                )
            }
        }
    }
}