package com.example.meditationtimer.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.meditationtimer.fragments.TimerFragment
import com.example.meditationtimer.models.TimerCoroutine

class TimerService : Service() {

  //  https://developer.android.com/guide/components/bound-services
    private val binder = LocalBinder()

    private lateinit var timerCoroutine: TimerCoroutine
    lateinit var secondsLeft: LiveData<Int>
    lateinit var builder: TimerRunningNotificationBuilder
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
    }

    fun resumeTimerService(): LiveData<Int> {

        Log.d("TimerService", "Resume timer")

        timerRunning = true

        timerCoroutine = TimerCoroutine()
        secondsLeft = timerCoroutine.startTimer(secondsLeft.value!!)

        return secondsLeft
    }

    fun startTimerService(seconds: Int): LiveData<Int> {
        timerCoroutine = TimerCoroutine()
        secondsLeft = timerCoroutine.startTimer(3)
        //     generateForegroundNotification(secondsLeft.value!!)
        timerRunning = true

        generateNotification()
        Log.d("TimerService", "Starting timer")

        secondsLeft.observeForever {

            builder.updateText(it)
            if (it == 0) {
                stopForeground(true)
                stopSelf()
            }

        }

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


    /*
    private fun generateForegroundNotification(secondsLeft: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intentMainLanding = Intent(this, TimerFragment::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intentMainLanding, 0)

            iconNotification =
                BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_add_alarm_24)
            if (mNotificationManager == null) {
                mNotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                assert(mNotificationManager != null)
                mNotificationManager?.createNotificationChannelGroup(
                    NotificationChannelGroup(
                        "chats_group",
                        "Chats"
                    )
                )
                val notificationChannel = NotificationChannel(
                    "service_channel",
                    "Service Notifications",
                    NotificationManager.IMPORTANCE_MIN
                )
                notificationChannel.enableLights(false)
                notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                mNotificationManager?.createNotificationChannel(notificationChannel)
            }

            Log.d("TimerService", "Build notification")

            val timeLeft = TimeLeft(secondsLeft)
            builder.setContentTitle(
                StringBuilder("Timer").append(" is running").toString()
            )
                .setTicker(StringBuilder("Timer ").append("is running").toString())
                .setContentText(timeLeft.toString())
                .setSmallIcon(R.drawable.ic_baseline_add_alarm_24)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setWhen(0)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
                .setOngoing(true)

            if (iconNotification != null) {
                builder.setLargeIcon(Bitmap.createScaledBitmap(iconNotification!!, 128, 128, false))
            }

            builder.color = resources.getColor(R.color.purple_200)
            notification = builder.build()
            startForeground(mNotificationId, notification)
        }

        startForeground(mNotificationId, notification)
    }


     */

    private fun generateNotification() {
        builder = TimerRunningNotificationBuilder(context = this, seconds = secondsLeft.value!!)
        val notification = builder.generateTimerRunningNotification()
        startForeground(builder.mNotificationId, notification)
    }

    override fun onDestroy() {
        if (this::timerCoroutine.isInitialized) {
            timerCoroutine.cancelTimer()
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