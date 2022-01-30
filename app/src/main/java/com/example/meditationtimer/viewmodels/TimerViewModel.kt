package com.example.meditationtimer.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meditationtimer.MeditationRepository
import com.example.meditationtimer.databases.MeditationRoomDatabase
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.TimerCoroutine
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import java.lang.IllegalArgumentException

class TimerViewModel(private val repository : MeditationRepository) : ViewModel() {

    //Working on the room db

    //    https://medium.com/android-dev-hacks/exploring-livedata-and-kotlin-flow-7c8d8e706324
    // https://github.com/googlecodelabs/android-room-with-a-view/blob/kotlin/app/src/main/java/com/example/android/roomwordssample/NewWordActivity.kt
    // https://developer.android.com/training/data-storage/room#kotlin
    //var secondsLeft : MutableLiveData<Int>??
    private lateinit var meditation: Meditation
    private lateinit var timer: TimerCoroutine

     var timerRunning = false
    var timerStarted = false

    fun insertMeditation(meditation : Meditation)
    {
        viewModelScope.launch {
            repository.insertMeditation(meditation)
            }
    }

    fun startTimer(minutes: Int): LiveData<Int> {
        timer = TimerCoroutine()
        // Timer should tick every second, so minutes * 60
        var livedata = timer.startTimer(minutes * 60)
        timerRunning = true
        timerStarted = true
        return livedata


        // Create a new meditation object now to store the duration and minutes, and then after it finishes
        // store the rest of the data in the model?
    }

    // Pause the timer
    fun pauseTimer()
    {
        timer.cancelTimer()
        timerRunning = false
    }

    // Resume the timer
    fun resumeTimer()
    {
        timer.resumeTimer()
        timerRunning = true
    }

    //Cancel the timer if its already running
    fun cancelTimer() {
        if (this::timer.isInitialized) {
            timer.cancelTimer()
            timerRunning = false
            // Maybe change the value of timer to null?
            timerStarted = false
            timerRunning = false
        }


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

