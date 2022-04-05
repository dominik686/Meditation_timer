package com.example.meditationtimer.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LiveData
import com.example.meditationtimer.R
import com.example.meditationtimer.fragments.TimerFragment
import com.example.meditationtimer.models.TimerCoroutine

class TimerService : Service() {

  //  https://developer.android.com/guide/components/bound-services
    private val binder = LocalBinder()

    private val timerCoroutine = TimerCoroutine()
    lateinit var secondsLeft: LiveData<Int>

    private var iconNotification: Bitmap? = null
    private var notification: Notification? = null
    private var mNotificationManager: NotificationManager? = null
    private val mNotificationId = 122
    private val builder = NotificationCompat.Builder(this, "service_channel")

    inner class LocalBinder : Binder() {
        fun getService(): TimerService = this@TimerService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun startTimerService(seconds: Int): LiveData<Int> {
        secondsLeft = timerCoroutine.startTimer(seconds)
        generateForegroundNotification(secondsLeft.value!!)

        secondsLeft.observeForever {
            builder.setContentText(it.toString())
            mNotificationManager?.notify(mNotificationId, builder.build())

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


            builder.setContentTitle(
                StringBuilder("Timer").append(" is running").toString()
            )
                .setTicker(StringBuilder("Timer ").append("is running").toString())
                .setContentText(secondsLeft.toString())
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

    }


    override fun onDestroy() {
        timerCoroutine.cancelTimer()

        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        timerCoroutine.cancelTimer()
        stopForeground(true)
        stopSelf()
    }
}