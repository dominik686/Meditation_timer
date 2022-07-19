package com.dominikwieczynski.meditationtimer

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



        const val DAYS_IN_A_ROW_STREAK_PREF = "DAYS_IN_A_ROW_PREFERENCE"
        const val LONGEST_STREAK_PREF = "LONGEST_STREAK_PREFERENCE"
        const val TOTAL_MEDITATIONS_PREF = "TOTAL_MEDITATIONS_PREFERENCE"
        const val LAST_DAY_STREAK_UPDATED_PREF = "LAST_DAY_STREAK_UPDATED_PREFERENCE"


        const val VERY_BAD_MOOD_COUNT_PREF = "VERY_BAD_MOOD_COUNT_PREF"
        const val BAD_MOOD_COUNT_PREF = "BAD_MOOD_COUNT_PREF"
        const val NEUTRAL_MOOD_COUNT_PREF = "NEUTRAL_MOOD_COUNT_PREF"
        const val GOOD_MOOD_COUNT_PREF = "GOOD_MOOD_COUNT_PREF"
        const val GREAT_MOOD_COUNT_PREF = "GREAT_MOOD_COUNT_PREF"
}