package com.example.meditationtimer.models

import junit.framework.TestCase
import org.junit.Test
import org.junit.Assert.*
class TimeLeftTest : TestCase() {



    @Test
    fun test_convertSecondsToMinutes_minutesEqualToSixty_returnsTrue()
    {
        var timeLeft = TimeLeft(3600)

        assertEquals(60, timeLeft.minutes)
    }

    @Test
    fun test_convertSecondsToMinutes_secondsEqualToZero_returnsTrue()
    {
        var timeLeft = TimeLeft(3600)

        assertEquals(0, timeLeft.seconds)
    }

    @Test
    fun test_convertSecondsToMinutes_hoursEqualToZero_returnsTrue()
    {
        //Refactor the method to also convert minutes to hours?
        var timeLeft  = TimeLeft(3600)

        assertEquals(0, timeLeft.hours)
    }

    @Test
    fun test_convertSecondsToMinutes_minutesEqualToFive_returnsTrue()
    {
        var timeLeft = TimeLeft(300)

        assertEquals(5,timeLeft.minutes )
    }

    @Test
    fun test_convertSecondsToMinutes_secondsEqualToFive_returnsTrue()
    {
        var timeLeft = TimeLeft(300)

        assertEquals(0,timeLeft.seconds)
    }




    @Test
    fun test_toString_SixtyMinutesZeroSeconds()
    {
        var timeLeft = TimeLeft(3600)

        assertEquals("60 : 0", timeLeft.toString())
    }

    @Test
    fun test_toString_OneHourZeroMinutesZeroSeconds()
    {

    }
    @Test
    fun test_toString_MinusFiveMinutesZeroSeconds()
    {
        var timeLeft = TimeLeft(-3600)

        assertEquals("-60 : 0", timeLeft.toString())
    }
}