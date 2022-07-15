package com.dominikwieczynski.meditationtimer.room

import androidx.annotation.WorkerThread
import com.dominikwieczynski.meditationtimer.models.Meditation
import com.dominikwieczynski.meditationtimer.room.daos.MeditationDao
import kotlinx.coroutines.flow.Flow

class MeditationRepository(private val meditationDao: MeditationDao) : IMeditationRepository {
    // Room executes all queries on a separate thread
    // Observed Flow will notify the observer when the data has changed.
    override val allMeditations : Flow<List<Meditation>> = meditationDao.getMeditations()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertMeditation(meditation: Meditation)
{
    meditationDao.insertMeditation(meditation)
}
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun clearMeditations()
    {
        meditationDao.clearMeditations()
    }


}