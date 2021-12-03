package com.example.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.TimerCoroutine

class TimerViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private lateinit var meditation : Meditation
    private lateinit var timer : TimerCoroutine

    fun startTimer(minutes : Int)
    {
        timer = TimerCoroutine()
        // Timer should tick every second, so minutes * 60
        timer.startTimer(minutes * 60)
    }

    fun cancelTimer()
    {
        timer.cancelTimer()
    }



}