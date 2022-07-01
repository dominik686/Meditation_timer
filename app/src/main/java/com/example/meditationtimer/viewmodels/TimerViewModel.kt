package com.example.meditationtimer.viewmodels

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

class TimerViewModel(private val meditationRepository : IMeditationRepository,
                     private val sharedPref : SharedPrefRepository) : ViewModel() {


    private lateinit var secondsLeft: LiveData<Int>
    private var initialDuration: Int = 0
    private var timerRunning = false
    private var timerStarted = false
    private lateinit var latestMeditation: Meditation
    var serviceBound: Boolean = false

    fun insertMeditation(description: String, emoji: MoodEmoji) {
         latestMeditation =
            Meditation(description = description, duration = initialDuration, emoji = emoji)

        viewModelScope.launch {
            meditationRepository.insertMeditation(latestMeditation)
        }
    }

    fun startTimer(seconds: Int, service: TimerService): LiveData<Int> {

        secondsLeft = service.startTimerService(seconds)
        initialDuration = seconds / 60
        timerRunning = true
        timerStarted = true
        return secondsLeft

    }

    fun pauseTimer(service : TimerService) {
        service.pauseTimerService()
        timerRunning = false
    }


    fun resumeTimer(service: TimerService) : LiveData<Int> {

        secondsLeft = service.resumeTimerService()
        timerRunning = true

        return secondsLeft
    }


    fun cancelTimer(service: TimerService) {

        if(timerRunning || timerStarted)
        {
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

    fun incrementTotalMeditations()
    {
        sharedPref.incrementTotalMeditations()
    }

    fun updateMeditationStreak()
    {
        sharedPref.updateStreak()
    }

    fun updateMoodCount()
    {
         if(this::latestMeditation.isInitialized)
         {
            sharedPref.incrementMoodCount(latestMeditation.emoji)
         }
    }
}
class TimerViewModelFactory(private val meditationRepository: MeditationRepository,
                            private val sharedPrefRepository: SharedPrefRepository) : ViewModelProvider.Factory
{
    override  fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TimerViewModel(meditationRepository, sharedPrefRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

