package com.example.meditationtimer

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.time.DurationUnit

class SharedPrefRepository(val context : Context) {

    val  sharedPref : SharedPreferences =  context.getSharedPreferences(Constants.USER_PREFERENCES,
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


    public fun incrementDaysInARow()
    {

    }

    public fun getDaysInARow() : Int
    {
        return 1
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
        editor.putInt(Constants.DAYS_IN_A_ROW_PREF, 0).apply()
    }

    fun resetLongestStreak() {
        editor.putInt(Constants.LONGEST_STREAK_PREF, 0).apply()
    }

    fun updateStreak() {
        TODO("Not yet implemented")


        // If the last day meditated was more than one day ago
        //    reset currentStreak
        //    start a new streak
        // else
        //     increment current streak by one


      //  var currentDate = SimpleDateFormat("d MMM yyyy").format(Date(System.currentTimeMillis()))



             // d2. getTime() – d1. getTime()
    }



}