package com.example.meditationtimer.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meditationtimer.SharedPrefRepository
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.MoodEmoji
import com.example.meditationtimer.room.IMeditationRepository
import com.example.meditationtimer.room.MeditationRepository
import com.example.meditationtimer.services.TimerService
import kotlinx.coroutines.launch

class TimerViewModel(private val repository : IMeditationRepository,
                     private val sharedPref : SharedPrefRepository) : ViewModel() {


    private lateinit var service: TimerService
    private lateinit var secondsLeft: LiveData<Int>
    private var initialDuration: Int = 0
    private var timerRunning = false
    private var timerStarted = false

    fun insertMeditation(description: String, emoji: MoodEmoji) {
        val meditation =
            Meditation(description = description, duration = initialDuration, emoji = emoji)
        viewModelScope.launch {
            repository.insertMeditation(meditation)
        }
    }

    fun startTimer(seconds: Int, service: TimerService): LiveData<Int> {

        this.service = service
        secondsLeft = service.startTimerService(seconds)
        initialDuration = seconds / 60
        timerRunning = true
        timerStarted = true
        return secondsLeft

    }

    fun pauseTimer() {
        service.pauseTimerService()
        timerRunning = false
    }


    fun resumeTimer() : LiveData<Int> {

        secondsLeft = service.resumeTimerService()
        timerRunning = true

        return secondsLeft
    }


    fun cancelTimer() {
        if (this::service.isInitialized) {
            //     timer.cancelTimer()
            service.cancelTimerService()

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

    fun getBellPreference() : String
    {
        return sharedPref.getBellPreference()
    }
}
class TimerViewModelFactory(private val repository: MeditationRepository, private val context: Context) : ViewModelProvider.Factory
{
    override  fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimerViewModel(repository, SharedPrefRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

