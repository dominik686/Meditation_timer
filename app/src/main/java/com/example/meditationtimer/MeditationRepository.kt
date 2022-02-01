package com.example.meditationtimer

import androidx.annotation.WorkerThread
import com.example.meditationtimer.daos.MeditationDao
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.flow.Flow

class MeditationRepository(private val meditationDao: MeditationDao)
{
    // Room executes all queries on a separate thread
    // Observed Flow will notify the observer when teh data has changed.
    val allMeditations : Flow<List<Meditation>> =meditationDao.getMeditations()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
suspend fun insertMeditation(meditation: Meditation)
{
    meditationDao.insertMeditation(meditation)
}


}