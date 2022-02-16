package com.example.meditationtimer.data.source

import com.example.meditationtimer.IMeditationRepository
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.flow.Flow

class FakeMeditationRepository(val dao : FakeMeditationDao) : IMeditationRepository{

    override val allMeditations: Flow<List<Meditation>>
        get() = dao.getMeditations()
    override suspend fun insertMeditation(meditation: Meditation) {
        dao.insertMeditation(meditation)
    }

    fun checkIfAdded(meditation: Meditation)
    {

    }
}