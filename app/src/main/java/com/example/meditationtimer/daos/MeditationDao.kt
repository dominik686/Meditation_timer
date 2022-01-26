package com.example.meditationtimer.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.meditationtimer.models.Meditation

@Dao
interface MeditationDao
{

    @Insert
    fun insertMeditation(meditation: Meditation)
}