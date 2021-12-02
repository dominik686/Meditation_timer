package com.example.meditationtimer.models

import java.time.LocalDateTime
import kotlin.time.Duration

//https://www.baeldung.com/kotlin/dates
data class Meditation(private var description : String, private var duration : Duration,private var dateTime : LocalDateTime )
{

}
