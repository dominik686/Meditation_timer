package com.example.meditationtimer

import android.content.Context
import android.content.SharedPreferences

class SharedPrefRepository(val context : Context) {

    val  sharedPref : SharedPreferences =  context?.getSharedPreferences(Constants.USER_PREFERENCES,
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

}