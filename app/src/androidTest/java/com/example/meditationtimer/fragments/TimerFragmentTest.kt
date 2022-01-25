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




    @Test
    fun testIsFragmentVisible()
    {

        // Does it throw an error because of properties in the layout??
        // The test time fragment worked after removing margins in the layout. maybe hardociding layout height and width will fix it here?

        val scenario =  launchFragmentInContainer<TimerFragment>(themeResId = R.style.Theme_MaterialComponents)


        onView(withId(R.id.timer_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.floatingActionButton)).perform(click())


        /*
       val scenario =  launchFragmentInContainer<TestTimerFragment>(themeResId = R.style.Theme_MaterialComponents)

        Thread.sleep(1000)

        onView(withId(R.id.test_button)).check(matches(isDisplayed()))
        onView(withId(R.id.test_button)).perform(click())

         */
    }

    @Test
    fun pressStartFAB()
    {
       // onView(allOf(withId(R.id.floatingActionButton), isDescendantOfA(withId(R.id.timer_coordinatorlayout)))).perform(click())

        // Get the ChooseTimeDialog from the fragment manager
       // scenario.childFragmentManager.fragments[2]
    }

    // Three tests

    // Press fab and check if the fragment pops up?

    // after choosing time check if the timer is running?

    // After this check press the cancel button and check if the timer is stopped?

    // check if the timer stops after the chosen interval?

}