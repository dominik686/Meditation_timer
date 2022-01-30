package com.example.meditationtimer.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//https://www.baeldung.com/kotlin/dates

@Entity(tableName = "meditations")

data class Meditation(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                      val description: String,
                      val duration: Int,
                      val date: String)
{
// Create a Meditation data class that stores data using Room/Realm
// Display all those meditations in a calendar
//    https://github.com/kizitonwose/CalendarView
}
