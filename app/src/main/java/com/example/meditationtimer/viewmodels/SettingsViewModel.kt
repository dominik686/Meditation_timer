package com.example.meditationtimer.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditationtimer.SharedPrefRepository
import java.lang.IllegalArgumentException

class SettingsViewModel(val sharedPrefRepository: SharedPrefRepository) : ViewModel()
{
    fun putBellPreference(preference : String)
    {
        sharedPrefRepository.putBellPreference(preference)
    }

    fun getBellPreference() : String
    {
        return sharedPrefRepository.getBellPreference()
    }
}

public class SettingsViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingsViewModel::class.java))
            {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(SharedPrefRepository(context)) as T
            }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}