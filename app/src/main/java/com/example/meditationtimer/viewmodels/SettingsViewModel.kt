package com.example.meditationtimer.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditationtimer.SharedPrefRepository
import com.example.meditationtimer.room.MeditationRepository
import com.example.meditationtimer.room.databases.MeditationRoomDatabase
import java.lang.IllegalArgumentException

class SettingsViewModel(
    private val sharedPrefRepository: SharedPrefRepository,
    private val meditationRepository: MeditationRepository) : ViewModel()
{
    fun putBellPreference(preference : String)
    {
        sharedPrefRepository.putBellPreference(preference)
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

public class SettingsViewModelFactory(
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