package com.example.meditationtimer.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.meditationtimer.R
import kotlinx.coroutines.DelicateCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



/*
 Dunno how to test this because this fragment looks different depending on whether or not it fetched room data
 */
@DelicateCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class DisplayMeditationsFragmentTest {



    @Before
    fun init()
    {
        launchFragmentInContainer<DisplayMeditationsFragment>(themeResId = R.style.Theme_MaterialComponents)
    }

    @Test
    fun are_MeditationsDisplayed()
    {
        //onView(withId(R.id.no_meditations_recorded)).check(matches(isDisplayed()))
        onView(withId(R.id.display_meditations_recyclerview)).check(matches(isDisplayed()))

    }
}