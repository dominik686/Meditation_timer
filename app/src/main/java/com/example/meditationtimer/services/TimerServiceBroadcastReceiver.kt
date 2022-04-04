package com.example.meditationtimer.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.meditationtimer.fragments.TimerFragment

class TimerServiceBroadcastReceiver : BroadcastReceiver() {
    var seconds = 0
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if (intent.action == TimerFragment.TIMER_SERVICE_SEND_SECONDS) {
                seconds = intent.getIntExtra("seconds", 0)
            }
        }
    }


}