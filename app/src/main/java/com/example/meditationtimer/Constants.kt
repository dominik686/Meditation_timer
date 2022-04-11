package com.example.meditationtimer

object Constants {

        const val USER_PREFERENCES = "USER_PREFERENCES"
        const val BELL_PREF = "BELL_PREFERENCE"
        const val TIBETAN_BELL_PREF = "TIBETAN_BELL_PREFERENCE"
        const val ANALOG_WATCH_BELL_PREF = "ANALOG_WATCH_BELL_PREFERENCE"
        const val CARTOON_TELEPHONE_BELL_PREF = "CARTOON_TELEPHONE_BELL_PREFERENCE"
        const val FRONT_DESK_BELL_PREF = "FRONT_DESK_BELL_PREFERENCE"

        val BELL_RESOURCES = mapOf(Pair(TIBETAN_BELL_PREF, R.raw.bells_tibetan_daniel_simon),
        Pair(ANALOG_WATCH_BELL_PREF, R.raw.analog_watch_alarm_daniel_simion),
                Pair(CARTOON_TELEPHONE_BELL_PREF, R.raw.cartoon_telephone_daniel_simion),
                Pair(FRONT_DESK_BELL_PREF, R.raw.front_desk_bells_daniel_simon))

}