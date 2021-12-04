package com.example.meditationtimer.models

import android.nfc.Tag
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

/*
Code based on: https://stackoverflow.com/a/58448610
 */

// Iimpelenmt Kotlin  stateflow to update the UI>
class TimerCoroutine
{
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)
    var secondsLeftFlow : MutableStateFlow<Int> = MutableStateFlow(0)

    private var secondsLeft = 10
    private fun startCoroutineTimer(delayMillis: Long = 0, repeatMillis: Long = 0,
                                    action: () -> Unit) = scope.launch(Dispatchers.IO)
    {

                                        delay((delayMillis))
            if (repeatMillis > 0) {
                while (secondsLeft > 0) {
                    secondsLeft--
                    secondsLeftFlow.value = secondsLeft
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
        secondsLeftFlow.value = secondsLeft

        scope.launch(Dispatchers.Main)
        {
            Log.d("Timer", "Main thread - tick")

            //doSomethingMainThread()
        }
    }

    // Should this method be async, since it returns secondLeftFlow?
    fun startTimer(seconds : Int) : StateFlow<Int>
    {
        secondsLeft = seconds
        timer.start()
        return secondsLeftFlow
    }

    fun cancelTimer()
    {
        timer.cancel()
    }

}