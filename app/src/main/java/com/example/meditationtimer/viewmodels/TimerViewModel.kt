package com.example.meditationtimer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.TimerCoroutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class TimerViewModel : ViewModel() {


//    https://medium.com/android-dev-hacks/exploring-livedata-and-kotlin-flow-7c8d8e706324

    //var secondsLeft : MutableLiveData<Int>??
    private lateinit var meditation : Meditation
    private lateinit var timer : TimerCoroutine


    fun startTimer(minutes : Int) : LiveData<Int>
    {
        timer = TimerCoroutine()
        // Timer should tick every second, so minutes * 60
        var livedata =  timer.startTimer(minutes * 60)


        return livedata



        // Create a new meditation object now to store the duration and minutes, and then after it finishes
        // store the rest of the data in the model?
    }

    fun cancelTimer()
    {
        timer.cancelTimer()
    }



}