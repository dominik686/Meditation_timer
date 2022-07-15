package com.dominikwieczynski.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dominikwieczynski.meditationtimer.Constants
import com.dominikwieczynski.meditationtimer.SharedPrefRepository
import com.dominikwieczynski.meditationtimer.room.MeditationRepository
import java.lang.IllegalArgumentException

class SettingsViewModel(
    private val sharedPrefRepository: SharedPrefRepository,
    private val meditationRepository: MeditationRepository) : ViewModel()
{
    fun putBellPreference(preference : String)
    {
        sharedPrefRepository.putBellPreference(preference)
        when(preference)
        {
            "Analog Watch" -> sharedPrefRepository.putBellPreference(Constants.ANALOG_WATCH_BELL_PREF)
            "Tibetan Bell" -> sharedPrefRepository.putBellPreference(Constants.TIBETAN_BELL_PREF)
            "Cartoon Telephone" -> sharedPrefRepository.putBellPreference(Constants.CARTOON_TELEPHONE_BELL_PREF)
            "Front Desk Bell" -> sharedPrefRepository.putBellPreference(Constants.FRONT_DESK_BELL_PREF)

        }
    }

    fun getBellPreference() : String
    {
        return sharedPrefRepository.getBellPreference()
    }

    fun resetStatistics()
    {
        resetTotalMeditations()
        resetCurrentStreak()
        resetLongestStreak()
        resetMoodCount()
    }
    private fun resetTotalMeditations()
    {
        sharedPrefRepository.resetTotalMeditations()
    }

    private fun resetCurrentStreak()
    {
        sharedPrefRepository.resetCurrentStreak()
    }
    private fun resetLongestStreak()
    {
        sharedPrefRepository.resetLongestStreak()
    }
    private fun resetMoodCount()
    {
        sharedPrefRepository.resetMoodCount()
    }
    suspend fun resetMeditationHistory()
    {
        meditationRepository.clearMeditations()
    }


    }

class SettingsViewModelFactory(
    private val sharedPrefRepository: SharedPrefRepository,
    private val meditationRepository: MeditationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingsViewModel::class.java))
            {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(sharedPrefRepository, meditationRepository) as T
            }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}