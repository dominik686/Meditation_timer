package com.example.meditationtimer.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.meditationtimer.data.source.FakeMeditationDao
import com.example.meditationtimer.data.source.FakeMeditationRepository
import com.example.meditationtimer.models.Meditation
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimerViewModelTest
{


    private lateinit var timerViewModel : TimerViewModel
    private lateinit var meditationRepository: FakeMeditationRepository

    @Before
    fun setupViewModel()
    {
         meditationRepository = FakeMeditationRepository(FakeMeditationDao())
        timerViewModel = TimerViewModel(meditationRepository)
    }

    @Test
     fun test_insertMeditation_equalsTrue()
    {
         val meditation = Meditation(description = "description", duration = 5)
        timerViewModel.insertMeditation(meditation.description)
    }

    @Test
    fun test_startTimer()
    {
        val secondsLeft = timerViewModel.startTimer(2)

        assertNotNull(secondsLeft.value)
    }
    @Test
    fun test_pauseTimer_NoTicks()
    {
        val secondsLeft = timerViewModel.startTimer(2)
       // secondsLeft.observe()
        timerViewModel.pauseTimer()

        assertEquals(2, secondsLeft.value)
    }

    @Test
    fun test_pauseTimer_OneTick()
    {
        val secondsLeft = timerViewModel.startTimer(2)
        Thread.sleep(2000)
        timerViewModel.pauseTimer()

        assertEquals(1, secondsLeft.value)

    }
}