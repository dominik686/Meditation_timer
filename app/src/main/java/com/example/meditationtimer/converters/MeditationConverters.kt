package com.example.meditationtimer.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime
import kotlin.time.Duration

class MeditationConverters
{
    @TypeConverter
    fun fromDurationToLong(duration : Duration) : Long
    {
        return duration.inWholeMinutes
    }

    @TypeConverter
    fun fromLocalDateTimeToString(date : LocalDateTime) : String
    {
       return date.toString()
    }
}