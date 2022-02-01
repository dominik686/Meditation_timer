package com.example.meditationtimer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//https://www.baeldung.com/kotlin/dates

@Entity(tableName = "meditations")

data class Meditation(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                      val description: String,
                      val duration: Int,
                      val date: String )
{
// Display all those meditations in a calendar
//    https://github.com/kizitonwose/CalendarView





    /*
    GMT only works in my timezone. Need to figure out a way to trim it for all timezones
     */
    fun getTrimmedDate() : String
    {
        return date.subSequence(0, date.indexOf("GMT")).toString()
    }
}
