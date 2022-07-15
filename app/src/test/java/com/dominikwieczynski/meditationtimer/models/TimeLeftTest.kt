package com.dominikwieczynski.meditationtimer.models

import junit.framework.TestCase
import org.junit.Test

class TimeLeftTest : TestCase() {



    @Test
    fun test_convertSecondsToMinutes_minutesEqualToSixty()
    {
        var timeLeft = TimeLeft(3600)
        var minutesLeft = timeLeft.minutes

        assertEquals(60, minutesLeft)
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