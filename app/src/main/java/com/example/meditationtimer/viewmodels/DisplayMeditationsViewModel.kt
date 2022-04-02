package com.example.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.room.MeditationRepository


//TODO: Create a universal viewmodel factory class for viewmodels that depend on the depository?
class DisplayMeditationsViewModel(private val repository: MeditationRepository) : ViewModel() {

    val allMeditations = repository.allMeditations.asLiveData()



    fun groupByDate(meditations : List<Meditation>): Map<String, List<Meditation>> {

        return meditations.groupBy { it.convertToMeditationDate().date }
    }


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


