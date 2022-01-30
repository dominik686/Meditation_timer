package com.example.meditationtimer

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.meditationtimer.databases.MeditationRoomDatabase
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MeditationApplication : Application() {
    // No need to cancel this scope as it'll be torn down ith the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather tha nwhen the application starts
    private val database by lazy { MeditationRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { MeditationRepository(database.meditatonDao())}
    override fun onCreate() {
        super.onCreate()
    }
}