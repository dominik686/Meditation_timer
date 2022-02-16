package com.example.meditationtimer.viewmodels

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.meditationtimer.MeditationRepository
import com.example.meditationtimer.data.source.FakeMeditationDao
import com.example.meditationtimer.data.source.FakeMeditationRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class TimerViewModelTest
{
    private lateinit var timerViewModel : TimerViewModel

    @Before
    fun setupViewModel()
    {
        val meditationRepository = FakeMeditationRepository(FakeMeditationDao())
        timerViewModel = TimerViewModel(meditationRepository)
    }

    @Test
    fun test_insertMeditation_()
    {
        timerViewModel.insertMeditation("description")
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
        secondsLeft.observe()
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