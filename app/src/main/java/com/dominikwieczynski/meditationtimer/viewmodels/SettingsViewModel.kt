package com.dominikwieczynski.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominikwieczynski.meditationtimer.Constants
import com.dominikwieczynski.meditationtimer.SharedPrefRepository
import com.dominikwieczynski.meditationtimer.models.Meditation
import com.dominikwieczynski.meditationtimer.models.MoodEmoji
import com.dominikwieczynski.meditationtimer.room.MeditationRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat

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

     fun addDefaultValues()
    {
       // val med1 = Meditation(description = , duration = , date = , emoji = )
        // SimpleDateFormat("d MMM yyyy 'at' HH:mm")
        val date = SimpleDateFormat("d MMM yyyy 'at' HH:mm").format("15 11 1999 21:33")

        val med1 = Meditation(description = "I am feeling great" , duration = 10, date = date , emoji = MoodEmoji.GREAT)
        viewModelScope.launch {
            meditationRepository.insertMeditation(med1)

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