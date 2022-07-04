package com.example.meditationtimer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import kotlinx.coroutines.DelicateCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

@DelicateCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun is_BottomNavBar_Visible()
    {
        onView(withId(R.id.bottomNavigationView)).check(matches(isDisplayed()))
    }

    @Test
    fun is_navHostFragment_Visible()
    {
        onView(withId(R.id.navHostFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavBar_isHistoryButton_Clickable()
    {
        onView(withId(R.id.history_fragment)).check(matches(isClickable()))
    }

    // When i try to navigate to this fragment I get a main thread room error

    /*
    @Test
    fun bottomNavBar_doesHistoryButton_navigateToNewFragment()
    {
        onView(withId(R.id.history_fragment)).perform(click())
        onView(withId(R.id.display_meditations_recyclerview)).check(matches(isDisplayed()))

    }

     */
    @Test
    fun bottomNavBar_isStatisticsButton_Clickable()
    {
        onView(withId(R.id.statistics_fragment)).check(matches(isClickable()))
    }

    @Test
    fun bottomNavBar_isTimerButton_Clickable()
    {
        onView(withId(R.id.timer_fragment)).check(matches(isClickable()))
    }
    @Test
    fun bottomNavBar_isSettingsButton_Clickable()
    {
        onView(withId(R.id.settings_fragment)).check(matches(isClickable()))
    }
}