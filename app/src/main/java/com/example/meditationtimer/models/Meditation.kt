package com.example.meditationtimer.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

//https://www.baeldung.com/kotlin/dates

@Entity(tableName = "meditations")

data class Meditation(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                      val description: String,
                      val duration: Int,
                      val date: String = SimpleDateFormat("yyyy-MM-dd 'at' HH:mm").format(Date(System.currentTimeMillis()))
)
{
// Display all those meditations in a calendar
//    https://github.com/kizitonwose/CalendarView

}
