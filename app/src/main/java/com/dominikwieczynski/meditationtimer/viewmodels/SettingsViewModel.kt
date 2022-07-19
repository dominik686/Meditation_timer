package com.dominikwieczynski.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dominikwieczynski.meditationtimer.common.Constants
import com.dominikwieczynski.meditationtimer.SharedPrefRepository
import com.dominikwieczynski.meditationtimer.models.Meditation
import com.dominikwieczynski.meditationtimer.models.MoodEmoji
import com.dominikwieczynski.meditationtimer.room.MeditationRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.*

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

     fun addDefaultValues(locale: Locale)
    {
       // val med1 = Meditation(description = , duration = , date = , emoji = )
        // SimpleDateFormat("d MMM yyyy 'at' HH:mm")
        //Format :  16 lip 2022 at 08:31
        if(locale.displayLanguage == "pl_PL")
        {
            val med1 = Meditation(description = "Czuję się świetnie" , duration = 10, date = "16 lip 2022 at 6:01" , emoji = MoodEmoji.GREAT)
            val med2 = Meditation(description = "Czuję się dobrze" , duration = 10, date = "15 lip 2022 at 14:43" , emoji = MoodEmoji.GOOD)
            val med3 = Meditation(description = "Czuję się okej" , duration = 10, date = "13 lip 2022 at 23:12" , emoji = MoodEmoji.OKAY)
            val med4 = Meditation(description = "Czuję się źle" , duration = 10, date = "11 lip 2022 at 12:32" , emoji = MoodEmoji.BAD)
            val med5 = Meditation(description = "Czuję się bardzo źle" , duration = 10, date = "9 lip 2022 at 11:15" , emoji = MoodEmoji.VERY_BAD)

            viewModelScope.launch {
                meditationRepository.insertMeditation(med5)
                meditationRepository.insertMeditation(med4)
                meditationRepository.insertMeditation(med3)
                meditationRepository.insertMeditation(med2)
                meditationRepository.insertMeditation(med1)

                sharedPrefRepository.editStatisticsDebug()
            }
        }
        else{
            val med1 = Meditation(description = "I am feeling great" , duration = 10, date = "16 Jul 2022 at 6:01" , emoji = MoodEmoji.GREAT)
            val med2 = Meditation(description = "I am feeling good" , duration = 10, date = "15 Jul 2022 at 14:43" , emoji = MoodEmoji.GOOD)
            val med3 = Meditation(description = "I am feeling neutral" , duration = 10, date = "13 Jul 2022 at 23:12" , emoji = MoodEmoji.OKAY)
            val med4 = Meditation(description = "I am feeling bad" , duration = 10, date = "11 Jul 2022 at 12:32" , emoji = MoodEmoji.BAD)
            val med5 = Meditation(description = "I am feeling very bad" , duration = 10, date = "9 Jul 2022 at 11:15" , emoji = MoodEmoji.VERY_BAD)
            viewModelScope.launch {
                meditationRepository.insertMeditation(med5)
                meditationRepository.insertMeditation(med4)
                meditationRepository.insertMeditation(med3)
                meditationRepository.insertMeditation(med2)
                meditationRepository.insertMeditation(med1)

                sharedPrefRepository.editStatisticsDebug()

            }
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