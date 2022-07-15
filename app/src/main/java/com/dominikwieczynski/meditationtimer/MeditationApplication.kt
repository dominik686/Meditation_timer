package com.dominikwieczynski.meditationtimer

import android.app.Application
import com.dominikwieczynski.meditationtimer.room.MeditationRepository
import com.dominikwieczynski.meditationtimer.room.databases.MeditationRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MeditationApplication : Application() {
    // No need to cancel this scope as it'll be torn down ith the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather tha when the application starts
    private val database by lazy { MeditationRoomDatabase.getDatabase(this, applicationScope) }
    val meditationRepository by lazy { MeditationRepository(database.meditatonDao()) }
    val sharedPrefRepository by lazy {SharedPrefRepository(this )}
}