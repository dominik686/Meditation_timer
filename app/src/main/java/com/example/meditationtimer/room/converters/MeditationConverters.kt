package com.example.meditationtimer.room.converters

import androidx.room.TypeConverter
import com.example.meditationtimer.models.MoodEmoji

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