package com.dominikwieczynski.meditationtimer.data.source

import com.dominikwieczynski.meditationtimer.models.Meditation
import com.dominikwieczynski.meditationtimer.room.daos.MeditationDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMeditationDao : MeditationDao{

    private var meditationsList = mutableListOf<Meditation>(Meditation(description = "1", duration = 5),
        Meditation(description = "2", duration = 10),
        Meditation(description = "3", duration = 15)
    )
    private var meditationsFlow = flowOf(meditationsList)
    private fun updateMeditationsFlow() {
        meditationsFlow = flowOf(meditationsList)
    }
    override suspend fun insertMeditation(meditation: Meditation) {
        meditationsList.add(meditation)
        updateMeditationsFlow()
    }

    override fun getMeditations(): Flow<List<Meditation>> {
        return meditationsFlow
    }

    override fun clearMeditations() {
        TODO("Not yet implemented")
    }
}