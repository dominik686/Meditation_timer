package com.dominikwieczynski.meditationtimer.data.source

import com.dominikwieczynski.meditationtimer.models.Meditation
import com.dominikwieczynski.meditationtimer.room.IMeditationRepository
import kotlinx.coroutines.flow.Flow

class FakeMeditationRepository(val dao: FakeMeditationDao) : IMeditationRepository {

    override val allMeditations: Flow<List<Meditation>>
        get() = dao.getMeditations()

    override suspend fun insertMeditation(meditation: Meditation) {
        dao.insertMeditation(meditation)
    }

    fun checkIfAdded(meditation: Meditation) {

    }
}