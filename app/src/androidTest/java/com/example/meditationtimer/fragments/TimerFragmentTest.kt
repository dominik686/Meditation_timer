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
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.meditationtimer.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@DelicateCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class TimerFragmentTest
{
    // https://developer.android.com/training/testing/espresso/cheat-sheet



    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init()
    {
        launchFragmentInContainer<TimerFragment>(themeResId = R.style.Theme_MaterialComponents)
    }
    @Test
    fun test_Is_FragmentVisible()
    {
        onView(withId(R.id.timer_fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_Is_FabVisible()
    {
        onView(withId(R.id.floatingActionButton)).check(matches(isDisplayed()))
    }

    @Test
    fun test_Is_TimeLeftTextViewVisible()
    {
        onView(withId(R.id.floatingActionButton)).check(matches(isDisplayed()))
    }
    @Test
    fun test_Is_MeditationHistoryButtonVisible()
    {
        onView(withId(R.id.history_button)).check(matches(isDisplayed()))

    }






    // After choosing a time interval
    // Check if the fab icon changed
    // Check if timeLeft textview changed and is changing
    // check if after pressing the fab button again the icon changes back

    @Test
    fun test_PressStartFAB()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
     //   onView(withText("How many minutes would you like to meditate for:")).check(matches(
    //        isDisplayed()))

    }
    @Test
    fun test_IsNumberPickerVisible()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withId(R.id.dialog_number_picker)).check(matches(isDisplayed()))
    }
    @Test
    fun test_Is_DialogTitleVisible()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText("How many minutes would you like to meditate for:")).check(matches(isDisplayed()))
    }

    @Test
    fun test_Is_DialogMessageVisible()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText("Choose a number")).check(matches(isDisplayed()))
    }

    @Test
    fun test_Is_NumberPickerOkButtonVisible()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText((R.string.ok))).check(matches(isDisplayed()))
    }
    @Test
    fun test_Is_NumberPickerCancelButtonVisible()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText((android.R.string.cancel))).check(matches(isDisplayed()))
    }


    @Test
    fun test_StartTimer()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText(R.string.ok)).perform(click())

    }

    @Test
    fun test_StartTimer_IsCancelButtonVisible()
    {

        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.cancelTimerButton)).check(matches(isDisplayed()))
    }

    @Test
    fun test_StartAndStopTimer()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.cancelTimerButton)).perform(click())
    }

    @Test
    fun test_StartStop_StartAndStopTimer()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.cancelTimerButton)).perform(click())
        onView(withId(R.id.floatingActionButton)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.cancelTimerButton)).perform(click())
    }


}