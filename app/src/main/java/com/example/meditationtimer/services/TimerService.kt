package com.example.meditationtimer.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.meditationtimer.fragments.TimerFragment
import com.example.meditationtimer.models.TimerCoroutine

class TimerService : Service() {

  //  https://developer.android.com/guide/components/bound-services
    private val binder = LocalBinder()

    private lateinit var timerCoroutine: TimerCoroutine
    private lateinit var secondsLeft: LiveData<Int>
    private lateinit var builder: TimerRunningNotificationBuilder
    var timerRunning = false

    inner class LocalBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun pauseTimerService() {
        Log.d("TimerService", "Pause timer")

        timerRunning = false
        timerCoroutine.pauseTimer()
    }

    fun cancelTimerService() {
        Log.d("TimerService", "cancel timer")
        timerRunning = false

        timerCoroutine.cancelTimer()
        timerCoroutine = TimerCoroutine()
        stopForeground(true)
        stopSelf()
    }

    fun resumeTimerService(): LiveData<Int> {

        Log.d("TimerService", "Resume timer")
        stopForeground(true)
        stopSelf()

        startTimerService(secondsLeft.value!!)


        return secondsLeft
    }

    private val observer = Observer<Int> {
        builder.updateText(it)
        if (it == 0) {
            stopForeground(true)
            stopSelf()
        }

    }

    fun startTimerService(seconds: Int): LiveData<Int> {
        timerCoroutine = TimerCoroutine()
        secondsLeft = timerCoroutine.startTimer(2)

        timerRunning = true
        generateNotification()
        Log.d("TimerService", "Starting timer")

        secondsLeft.observeForever(observer)




        return secondsLeft
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action != null && intent.action.equals(
                TimerFragment.TIMER_STOP,
                ignoreCase = true
            )
        ) {

            stopForeground(true)
            stopSelf()
        } else if (intent?.action != null && intent.action.equals(
                TimerFragment.TIMER_START,
                ignoreCase = true
            )
        ) {

            startTimerService(intent.getIntExtra("seconds", 0))


        }
        return START_NOT_STICKY
    }


    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        secondsLeft.removeObserver(observer)

    }
    private fun generateNotification() {
        builder = TimerRunningNotificationBuilder(context = this, seconds = secondsLeft.value!!)
        val notification = builder.generateTimerRunningNotification()
        startForeground(builder.mNotificationId, notification)
    }

    override fun onDestroy() {
        if (this::timerCoroutine.isInitialized) {
            timerCoroutine.cancelTimer()
        }

        if (!timerRunning) {
            stopForeground(true)
            stopSelf()
        }

        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        timerCoroutine.cancelTimer()
        stopForeground(true)
        stopSelf()
    }
}