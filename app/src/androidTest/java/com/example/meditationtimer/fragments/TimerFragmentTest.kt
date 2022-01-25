package com.example.meditationtimer.fragments

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.meditationtimer.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@DelicateCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class TimerFragmentTest
{
    // https://developer.android.com/training/testing/espresso/cheat-sheet



    /*
    Should I add some sort of check at the end of each test?
     */
    @Before
    fun init()
    {
        launchFragmentInContainer<TimerFragment>(themeResId = R.style.Theme_MaterialComponents)
    }
    @Test
    fun testIfFragmentVisible()
    {
        //launchFragmentInContainer<TimerFragment>(themeResId = R.style.Theme_MaterialComponents)


        onView(withId(R.id.timer_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.floatingActionButton)).perform(click())
    }

    @Test
    fun testPressStartFAB()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
    }

    @Test
    fun testStartTimer()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText(R.string.ok)).perform(click())

    }


    @Test
    fun testStartAndStopTimer()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withText(R.string.cancel)).perform(click())
    }
    // Three tests

    // Press fab and check if the fragment pops up?

    // after choosing time check if the timer is running?

    // After this check press the cancel button and check if the timer is stopped?

    // check if the timer stops after the chosen interval?

}