package com.example.meditationtimer

import android.content.Context
import android.content.SharedPreferences

class SharedPrefRepository(val context : Context) {

    val  sharedPref : SharedPreferences =  context?.getSharedPreferences(Constants.USER_PREFERENCES,
        Context.MODE_PRIVATE)!!
    val editor = sharedPref.edit()

    public fun putBellPreference(string: String)
    {


        editor.putString(Constants.BELL_PREF, string).apply()

    }

    fun getBellPreference()  : String
    {
        return sharedPref.getString(Constants.BELL_PREF, Constants.ANALOG_WATCH_BELL_PREF)!!
    }
}