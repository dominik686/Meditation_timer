package com.example.meditationtimer.models

import junit.framework.TestCase
import org.junit.Test
import org.junit.Assert.*
class TimeLeftTest : TestCase() {

    @Test
    fun test_convertSecondsToMinutes_3600seconds_returnsTrue()
    {
        var timeLeft = TimeLeft(3600)

        assertEquals(60, timeLeft.minutes)
    }

}