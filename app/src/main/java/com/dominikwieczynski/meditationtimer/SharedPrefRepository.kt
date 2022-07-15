package com.dominikwieczynski.meditationtimer

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.dominikwieczynski.meditationtimer.models.MoodCount
import com.dominikwieczynski.meditationtimer.models.MoodEmoji
import com.dominikwieczynski.meditationtimer.models.Statistics
import java.time.Duration
import java.time.Instant

class SharedPrefRepository(val context : Context) {

    private val  sharedPref : SharedPreferences =  context.getSharedPreferences(Constants.USER_PREFERENCES,
        Context.MODE_PRIVATE)!!
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun putBellPreference(string: String)
    {

        editor.putString(Constants.BELL_PREF, string).apply()

    }

    fun getBellPreference()  : String
    {
        return sharedPref.getString(Constants.BELL_PREF, Constants.ANALOG_WATCH_BELL_PREF)!!
    }


    fun incrementMoodCount(mood : MoodEmoji)
    {
        when(mood){
            MoodEmoji.VERY_BAD -> incrementVeryBadCount()
            MoodEmoji.BAD ->incrementBadCount()
            MoodEmoji.NEUTRAL -> incrementNeutralCount()
            MoodEmoji.GOOD -> incrementGoodCount()
            MoodEmoji.GREAT -> incrementGreatCount()
        }
    }

    private fun incrementVeryBadCount()
    {
        val count = sharedPref.getInt(Constants.VERY_BAD_MOOD_COUNT_PREF, 0) + 1
        editor.putInt(Constants.VERY_BAD_MOOD_COUNT_PREF, count).apply()

    }

    private fun incrementBadCount()
    {
        val count = sharedPref.getInt(Constants.BAD_MOOD_COUNT_PREF, 0) + 1
        editor.putInt(Constants.BAD_MOOD_COUNT_PREF, count).apply()
    }

    private fun incrementNeutralCount()
    {
        val count = sharedPref.getInt(Constants.NEUTRAL_MOOD_COUNT_PREF, 0) + 1
        editor.putInt(Constants.NEUTRAL_MOOD_COUNT_PREF, count).apply()
    }

    private fun incrementGoodCount()
    {
        val count = sharedPref.getInt(Constants.GOOD_MOOD_COUNT_PREF, 0) + 1
        editor.putInt(Constants.GOOD_MOOD_COUNT_PREF, count).apply()
    }

    private fun incrementGreatCount()
    {
        val count = sharedPref.getInt(Constants.GREAT_MOOD_COUNT_PREF, 0) + 1
        editor.putInt(Constants.GREAT_MOOD_COUNT_PREF, count).apply()
    }

    private fun getMoodCount() : MoodCount
    {
        val veryBadCount = sharedPref.getInt(Constants.VERY_BAD_MOOD_COUNT_PREF, 0)
        val badCount = sharedPref.getInt(Constants.BAD_MOOD_COUNT_PREF, 0)
        val neutralCount = sharedPref.getInt(Constants.NEUTRAL_MOOD_COUNT_PREF, 0)
        val goodCount = sharedPref.getInt(Constants.GOOD_MOOD_COUNT_PREF, 0)
        val greatCount = sharedPref.getInt(Constants.GREAT_MOOD_COUNT_PREF, 0)

        return MoodCount(greatCount, goodCount, neutralCount, badCount, veryBadCount)
    }

    fun resetMoodCount()
    {

        editor.putInt(Constants.VERY_BAD_MOOD_COUNT_PREF, 0).apply()
        editor.putInt(Constants.BAD_MOOD_COUNT_PREF, 0).apply()
        editor.putInt(Constants.NEUTRAL_MOOD_COUNT_PREF, 0).apply()
        editor.putInt(Constants.GOOD_MOOD_COUNT_PREF, 0).apply()
        editor.putInt(Constants.GREAT_MOOD_COUNT_PREF, 0).apply()

    }
    fun getStatistics() : Statistics
    {

        return Statistics(getDaysInARow(), getLongestDaysInARow(), getTotalMeditations(), getMoodCount())
    }
    fun getDaysInARow() : Int
    {
        return sharedPref.getInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0)
    }

    fun getLongestDaysInARow() : Int
    {
        return sharedPref.getInt(Constants.LONGEST_STREAK_PREF, 0)
    }
    fun incrementTotalMeditations()
    {
        val newTotal = getTotalMeditations() + 1
        editor.putInt(Constants.TOTAL_MEDITATIONS_PREF, newTotal).apply()
    }
    fun getTotalMeditations() : Int
    {
        return sharedPref.getInt(Constants.TOTAL_MEDITATIONS_PREF, 0)
    }
    fun resetTotalMeditations()
    {
        editor.putInt(Constants.TOTAL_MEDITATIONS_PREF, 0).apply()
    }

    fun resetCurrentStreak() {
        editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0).apply()
    }

    fun resetLongestStreak() {
        editor.putInt(Constants.LONGEST_STREAK_PREF, 0).apply()
    }

    fun updateStreakIfNotZero()
    {
        var currentStreak = sharedPref.getInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0)


        if(currentStreak > 0)
        {
            val hoursDiff = getHoursPassedSinceMeditation()
            if(hoursDiff < 24L)
            {

            }

            else if(hoursDiff in 24L..48L){

                editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, currentStreak + 1).apply()
                updateLastDayStreakUpdated()

            }
            else if(hoursDiff > 48L)
            {
                editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0).apply()
                updateLastDayStreakUpdated()

            }
        }

        compareToBestStreak()
    }
    fun updateStreak() {

        var currentStreak = sharedPref.getInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0)

        if(currentStreak == 0)
        {
            incrementCurrentStreak()
            updateLastDayStreakUpdated()
        }
        else if(currentStreak > 0)
        {
            val hoursDiff = getHoursPassedSinceMeditation()
            if(hoursDiff < 24L)
            {

            }

            else if(hoursDiff in 24L..48L){

                editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, currentStreak + 1).apply()
                updateLastDayStreakUpdated()

            }
            else if(hoursDiff > 48L)
            {
                editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0).apply()
                updateLastDayStreakUpdated()

            }
        }

        compareToBestStreak()
    }

    private fun incrementCurrentStreak()

    {
        val currentStreak = sharedPref.getInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0)
        editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, currentStreak + 1).apply()
    }

    private fun updateLastDayStreakUpdated()
    {
        val todayTimeStamp = Instant.now()
        editor.putString(Constants.LAST_DAY_STREAK_UPDATED_PREF, todayTimeStamp.toString())
    }
    private fun getHoursPassedSinceMeditation() : Long
    {


        val currentTimestamp  = sharedPref.getString(Constants.LAST_DAY_STREAK_UPDATED_PREF, "No timestamp")
        if(currentTimestamp == "No timestamp")
        {
            return 0
        }
        else
        {
            val lastMeditationDate = Instant.parse(currentTimestamp)
            val nowDate = Instant.now()
            val hoursSinceLastMeditation = Duration.between(lastMeditationDate, nowDate).toHours()

            return hoursSinceLastMeditation
        }
    }
    //private fun getHours

    private fun compareToBestStreak()
    {
        val currentStreak = sharedPref.getInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 1)
        val currentBestStreak = sharedPref.getInt(Constants.LONGEST_STREAK_PREF, 1)
        if(currentStreak >= currentBestStreak)
        {
            editor.putInt(Constants.LONGEST_STREAK_PREF, currentStreak).apply()
        }
    }
}