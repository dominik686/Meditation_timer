package com.example.meditationtimer

import android.app.Application
import com.example.meditationtimer.databases.MeditationRoomDatabase
import com.example.meditationtimer.room.MeditationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MeditationApplication : Application() {
    // No need to cancel this scope as it'll be torn down ith the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather tha when the application starts
    private val database by lazy { MeditationRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { MeditationRepository(database.meditatonDao()) }
}