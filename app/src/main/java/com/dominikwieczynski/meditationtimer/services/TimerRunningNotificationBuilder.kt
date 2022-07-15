package com.dominikwieczynski.meditationtimer.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.dominikwieczynski.meditationtimer.R
import com.dominikwieczynski.meditationtimer.fragments.TimerFragment
import com.dominikwieczynski.meditationtimer.models.TimeLeft

class TimerRunningNotificationBuilder(val context: Context, val seconds: Int) {
    private var iconNotification: Bitmap? = null
    private var mNotificationManager: NotificationManager? = null
    val mNotificationId = 122
    private val builder = NotificationCompat.Builder(context, "service_channel")

    fun generateTimerRunningNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intentMainLanding = Intent(context, TimerFragment::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intentMainLanding, 0)

            iconNotification =
                BitmapFactory.decodeResource(context.resources, R.drawable.meditation)
            if (mNotificationManager == null) {
                mNotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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


            val timeLeft = TimeLeft(seconds)
            builder.setContentTitle(
                StringBuilder("Timer").append(" is running").toString()
            )
                .setTicker(StringBuilder("Timer ").append("is running").toString())
                .setContentText(timeLeft.toString())
                .setSmallIcon(R.drawable.meditation)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setWhen(0)
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
                .setOngoing(true)

            if (iconNotification != null) {
                builder.setLargeIcon(Bitmap.createScaledBitmap(iconNotification!!, 128, 128, false))
            }

            builder.color = context.resources.getColor(R.color.purple_200)
            return builder.build()
        }
        return Notification()
    }

    fun updateText(seconds: Int) {

        val timeLeft = TimeLeft(seconds)
        builder.setContentText(timeLeft.toString())
        mNotificationManager?.notify(mNotificationId, builder.build())
    }
}