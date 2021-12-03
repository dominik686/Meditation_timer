package com.example.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.TimerCoroutine

class TimerViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    //var secondsLeft : MutableLiveData<Int>??
    private lateinit var meditation : Meditation
    private lateinit var timer : TimerCoroutine

    fun startTimer(minutes : Int)
    {
        timer = TimerCoroutine()
        // Timer should tick every second, so minutes * 60
        timer.startTimer(minutes * 60)

        // Create a new meditation object now to store the duartion and minutes, and then after it finishes
        // store the rest of the data in the model?
    }

    fun cancelTimer()
    {
        timer.cancelTimer()
    }



}