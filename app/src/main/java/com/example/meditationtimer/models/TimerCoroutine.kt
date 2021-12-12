package com.example.meditationtimer.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

/*
Code based on: https://stackoverflow.com/a/58448610
 */

class TimerCoroutine
{
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    var secondsLeftLiveData : MutableLiveData<Int> = MutableLiveData(0)

    private var secondsLeft = 10
    private fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 0,
                                    action: () -> Unit) = scope.launch(Dispatchers.IO)
    {

                                        delay((delayMillis))
            if (repeatMillis > 0) {
                while (secondsLeft > 0) {
                    secondsLeft--
                    action()
                    delay(repeatMillis)
                }
            } else {
                secondsLeft--
                action()
            }
        timer.cancel()
        Log.d("Timer", "Coroutine finished")


    }
    private val timer: Job = startCoroutineTimer (delayMillis = 0, repeatMillis = 1000)
    {
        Log.d("Timer", "Background - tick")

        //doSomethingBackGround()
        scope.launch(Dispatchers.Main)
        {
            Log.d("Timer", "Main thread - tick")
            secondsLeftLiveData.value = secondsLeft
            //doSomethingMainThread()
        }
    }

    // Should this method be async, since it returns secondLeftFlow?
    fun startTimer(seconds : Int) : LiveData<Int>
    {
        //If the timer is already running, cancel it before starting again


       // secondsLeft = seconds
        secondsLeft = 10;
        secondsLeftLiveData.value = seconds



        timer.start()
        return secondsLeftLiveData
    }

    fun cancelTimer()
    {
        timer.cancel()
    }

}