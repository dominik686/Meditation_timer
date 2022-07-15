package com.dominikwieczynski.meditationtimer.models

import  android.util.Log
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


    }
    private val timer: Job = startCoroutineTimer (delayMillis = 0, repeatMillis = 1000)
    {

        //doSomethingBackGround()
        scope.launch(Dispatchers.Main)
        {
            secondsLeftLiveData.value = secondsLeft
            //doSomethingMainThread()
        }
    }

    fun pauseTimer()
    {
        timer.cancel()
    }


    //
    fun startTimer(seconds : Int) : LiveData<Int>
    {
        secondsLeft = seconds
        secondsLeftLiveData.value = seconds

        timer.start()
        return secondsLeftLiveData
    }

    fun cancelTimer()
    {
        timer.cancel()
    }

}