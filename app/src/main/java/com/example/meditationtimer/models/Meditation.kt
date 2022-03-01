package com.example.meditationtimer.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.meditationtimer.converters.MeditationConverters
import java.text.SimpleDateFormat
import java.util.*

//https://www.baeldung.com/kotlin/dates

@Entity(tableName = "meditations")
@TypeConverters(MeditationConverters::class)
data class Meditation(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                      val description: String,
                      val duration: Int,
                      val date: String = SimpleDateFormat("d MMM yyyy 'at' HH:mm").format(Date(System.currentTimeMillis())),
                      val emoji: MoodEmoji = MoodEmoji.NEUTRAL
)
{
// Display all those meditations in a calendar
//    https://github.com/kizitonwose/CalendarView

}


enum class MoodEmoji{
    VERY_BAD, BAD, NEUTRAL, GOOD, GREAT;


}