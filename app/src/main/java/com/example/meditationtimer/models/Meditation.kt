package com.example.meditationtimer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import kotlin.time.Duration

//https://www.baeldung.com/kotlin/dates

@Entity(tableName = "meditations")
data class Meditation(@PrimaryKey val id: Int, val description : String, val duration : Duration, val date : LocalDateTime )
{
// Create a Meditation data class that stores data using Room/Realm
// Display all those meditations in a calendar
//    https://github.com/kizitonwose/CalendarView
}
