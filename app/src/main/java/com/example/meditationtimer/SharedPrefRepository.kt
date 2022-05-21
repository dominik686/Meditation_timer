package com.example.meditationtimer

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class SharedPrefRepository(val context : Context) {

    private val  sharedPref : SharedPreferences =  context.getSharedPreferences(Constants.USER_PREFERENCES,
        Context.MODE_PRIVATE)!!
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    public fun putBellPreference(string: String)
    {


        editor.putString(Constants.BELL_PREF, string).apply()

    }

    fun getBellPreference()  : String
    {
        return sharedPref.getString(Constants.BELL_PREF, Constants.ANALOG_WATCH_BELL_PREF)!!
    }


    public fun getDaysInARow() : Int
    {
        return sharedPref.getInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0)
    }

    public fun getLongestDaysInARow() : Int
    {
        return sharedPref.getInt(Constants.LONGEST_STREAK_PREF, 0)
    }
    public fun incrementTotalMeditations()
    {
        val newTotal = getTotalMeditations() + 1
        editor.putInt(Constants.TOTAL_MEDITATIONS_PREF, newTotal).apply()
    }
    public fun getTotalMeditations() : Int
    {
        return sharedPref.getInt(Constants.TOTAL_MEDITATIONS_PREF, 0)
    }
    public fun resetTotalMeditations()
    {
        editor.putInt(Constants.TOTAL_MEDITATIONS_PREF, 0).apply()
    }

    fun resetCurrentStreak() {
        editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 0).apply()
    }

    fun resetLongestStreak() {
        editor.putInt(Constants.LONGEST_STREAK_PREF, 0).apply()
    }

    fun updateStreak() {

        // if current streak is bigger than biggest streak
        // current streak becomes biggest streak
        // TODO
        // also if the streak was on the same day then the streak doesnt increase

        val daysDiff = getDayDifference()
        if(daysDiff >= 2)
        {
            editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 1).apply()
        }
        else if(daysDiff == 1){
            var currentStreak = sharedPref.getInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, 1)

            editor.putInt(Constants.DAYS_IN_A_ROW_STREAK_PREF, currentStreak + 1).apply()
        }
        else if(daysDiff == 0)
        {
            // Dont do anything to the streak
        }

        compareToBestStreak()

        // If the last day meditated was more than one day ago
        //    reset currentStreak
        //    start a new streak
        // else
        //     increment current streak by one

    }

// The streak cant be started because by default lastDayMeditated = currentDay
    private fun getDayDifference() : Int
    {
        val dateFormat = SimpleDateFormat("dd MM yyyy")
        //Format doesnt work for soem reaosn
        val currentDate = dateFormat.parse(dateFormat.format(System.currentTimeMillis()))

        var lastDayMeditated = Date()
        if(sharedPref.getString(Constants.LAST_DAY_MEDITATED_PREF,
                "") == "")
        {
            return 2
        }
        else{
            lastDayMeditated = dateFormat.parse(
                sharedPref.getString(Constants.LAST_DAY_MEDITATED_PREF,
                    "01 12 1999"
                ))
        }


        val timeDiff = currentDate?.time?.minus(lastDayMeditated?.time!!)
        val daysDiff = TimeUnit.MILLISECONDS.toDays(timeDiff!!).toInt()

        return daysDiff
    }

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