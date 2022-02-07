package com.example.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.meditationtimer.MeditationRepository
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

class DisplayMeditationsViewModel(private val repository: MeditationRepository) : ViewModel() {

    val allMeditations = repository.allMeditations.asLiveData()





}
    class DisplayMeditationsViewModelFactory(private val repository: MeditationRepository) : ViewModelProvider.Factory
    {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DisplayMeditationsViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return DisplayMeditationsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


