package com.example.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditationtimer.MeditationRepository
import java.lang.IllegalArgumentException

class DisplayMeditationsViewModel(private val repository: MeditationRepository) : ViewModel() {
    // TODO: Implement the ViewModel
}


    class DisplayMeditationsViewModelFactory(private val repository: MeditationRepository) : ViewModelProvider.Factory
    {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(TimerViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return TimerViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
