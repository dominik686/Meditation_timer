package com.example.meditationtimer.converters

import androidx.room.TypeConverter
import com.example.meditationtimer.models.MoodEmoji
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration

class MeditationConverters
{
    @TypeConverter
    fun fromMoodEmoji(value : MoodEmoji) : String{
        return value.toString()
    }
    fun toMoodEmoji(string : String) : MoodEmoji{
        return enumValueOf(string)
    }
}