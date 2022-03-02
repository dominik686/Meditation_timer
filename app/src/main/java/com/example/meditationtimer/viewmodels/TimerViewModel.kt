package com.example.meditationtimer.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meditationtimer.IMeditationRepository
import com.example.meditationtimer.MeditationRepository
import com.example.meditationtimer.databases.MeditationRoomDatabase
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.MoodEmoji
import com.example.meditationtimer.models.TimerCoroutine
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalArgumentException

class TimerViewModel(private val repository : IMeditationRepository) : ViewModel() {

    private lateinit var timer: TimerCoroutine

    private lateinit var secondsLeft : LiveData<Int>
     private var initialDuration : Int = 0
     private var timerRunning = false
    private var timerStarted = false

    fun insertMeditation(description : String, emoji: MoodEmoji)
    {
        val meditation = Meditation(description = description, duration = initialDuration, emoji = emoji)
        viewModelScope.launch {
            repository.insertMeditation(meditation)
            }
    }

    fun startTimer(seconds: Int): LiveData<Int> {
        timer = TimerCoroutine()

        //secondsLeft = timer.startTimer( seconds)
        secondsLeft = timer.startTimer( 1)
        initialDuration = seconds/60
        timerRunning = true
        timerStarted = true
        return secondsLeft



    }

    fun pauseTimer()
    {
        timer.cancelTimer()
        timerRunning = false
    }


    fun resumeTimer() : LiveData<Int>
    {
        timer = TimerCoroutine()
        secondsLeft = timer.startTimer(secondsLeft.value!!)
        timer.resumeTimer()
        timerRunning = true

        return secondsLeft
    }

    fun cancelTimer() {
        if (this::timer.isInitialized) {
            timer.cancelTimer()
            timerRunning = false
            timerStarted = false
        }


    }

    fun isTimerFinished() : Boolean
    {
       return secondsLeft.value == 0
    }
    fun isTimerStartedAndRunning() : Boolean
    {
        return timerStarted && timerRunning
    }

    fun isTimerStartedAndPaused() : Boolean
    {
        return timerStarted && !timerRunning
    }

    fun isTimerNotStartedAndNotRunning() : Boolean
    {
        return  !timerStarted && !timerRunning
    }
}
class TimerViewModelFactory(private val repository: MeditationRepository) : ViewModelProvider.Factory
{
    override  fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

