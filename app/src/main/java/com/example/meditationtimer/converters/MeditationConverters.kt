package com.example.meditationtimer.converters

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.*
import kotlin.time.Duration

class MeditationConverters
{
    @TypeConverter
    fun dateToString(date : Date) : String
    {
       return date.toString()
    }
}