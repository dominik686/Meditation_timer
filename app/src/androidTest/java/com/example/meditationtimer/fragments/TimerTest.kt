package com.example.meditationtimer.fragments

import android.widget.NumberPicker
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.meditationtimer.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@DelicateCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class TimerTest
{

    private lateinit var scenario : Timer
    var minutes = 0;
    @Before
    fun setUp()
    {
        with(launchFragment<Timer>())
        {
            onFragment{

                    fragment ->
                scenario = fragment
                GlobalScope.launch(Dispatchers.Main) {
                    scenario.parentFragmentManager.executePendingTransactions()

                }
            }
        }

        minutes = 0
    }


    @Test
    fun pressStartFAB()
    {
        onView(withId(R.id.floatingActionButton)).perform(click())

    }

    // Three tests

    // Press fab and check if the fragment pops up?

    // after choosing time check if the timer is running?

    // After this check press the cancel button and check if the timer is stopped?

    // check if the timer stops after the chosen interval?

}