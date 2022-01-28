package com.example.meditationtimer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.meditationtimer.converters.MeditationConverters
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration

//https://www.baeldung.com/kotlin/dates

@Entity(tableName = "meditations")

data class Meditation(@PrimaryKey(autoGenerate = true) val id: Int,
                      val description : String,
                      val duration : Int,
                      val date : String)
{
// Create a Meditation data class that stores data using Room/Realm
// Display all those meditations in a calendar
//    https://github.com/kizitonwose/CalendarView
}
