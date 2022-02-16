package com.example.meditationtimer.data.source

import com.example.meditationtimer.daos.MeditationDao
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
}