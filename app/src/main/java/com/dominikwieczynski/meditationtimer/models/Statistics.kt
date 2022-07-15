package com.dominikwieczynski.meditationtimer.models

data class Statistics(val daysInARow : Int, val longestStreak: Int, val totalMeditations : Int, val moodCount: MoodCount
)

