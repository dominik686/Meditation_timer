package com.example.meditationtimer.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.flow.Flow

@Dao
interface MeditationDao
{

    @Insert
    suspend fun insertMeditation(meditation: Meditation)

    @Query("SELECT * FROM meditations")
    fun getMeditations() : Flow<List<Meditation>>
}