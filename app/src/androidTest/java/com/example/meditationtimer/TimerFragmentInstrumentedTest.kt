package com.example.meditationtimer


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest


@RunWith(AndroidJUnit4::class)
@LargeTest
class TimerFragmentInstrumentedTest
{
    @Test
    fun userDoesntChooseANumber()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
            //onView(withId(R.id.))
    }

}