package com.example.meditationtimer.fragments

import android.widget.NumberPicker
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*

import org.junit.Before
import org.junit.runner.RunWith

@DelicateCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class TimerTest
{

    private lateinit var timerFragment : Timer
    var minutes = 0;
    @Before
    fun setUp()
    {
        with(launchFragment<Timer>())
        {
            onFragment{

                    fragment ->
                timerFragment = fragment
                GlobalScope.launch(Dispatchers.Main) {
                    timerFragment.parentFragmentManager.executePendingTransactions()

                }
            }
        }

        minutes = 0
    }


}