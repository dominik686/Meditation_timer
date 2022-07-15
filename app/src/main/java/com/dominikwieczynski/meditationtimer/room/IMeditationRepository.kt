package com.dominikwieczynski.meditationtimer.room

import androidx.annotation.WorkerThread
import com.dominikwieczynski.meditationtimer.models.Meditation
import kotlinx.coroutines.flow.Flow

interface IMeditationRepository {
    // Room executes all queries on a separate thread
    // Observed Flow will notify the observer when the data has changed.
    val allMeditations: Flow<List<Meditation>>

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMeditation(meditation: Meditation)
}