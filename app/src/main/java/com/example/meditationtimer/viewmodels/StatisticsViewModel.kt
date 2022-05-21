package com.example.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditationtimer.SharedPrefRepository
import com.example.meditationtimer.models.Statistics

class StatsViewModel(private val sharedPrefRepository: SharedPrefRepository) : ViewModel() {



    fun getStats() : Statistics
    {
        return Statistics(getCurrentDaysInARow(), getLongestDaysInARow(), getTotalMeditations())
    }
        fun getTotalMeditations() : Int
        {
            return sharedPrefRepository.getTotalMeditations()
        }

        fun getLongestDaysInARow(): Int {
            return sharedPrefRepository.getLongestDaysInARow()
        }

        fun getCurrentDaysInARow(): Int {
            return sharedPrefRepository.getDaysInARow()
        }

    }
class StatsViewModelFactory(private val sharedPrefRepository: SharedPrefRepository) : ViewModelProvider.Factory
{
    override  fun <T : ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(StatsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StatsViewModel(sharedPrefRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}