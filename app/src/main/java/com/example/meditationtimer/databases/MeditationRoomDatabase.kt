package com.example.meditationtimer.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.meditationtimer.converters.MeditationConverters
import com.example.meditationtimer.daos.MeditationDao
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Meditation::class], version = 1)
@TypeConverters(MeditationConverters::class)
abstract class MeditationRoomDatabase : RoomDatabase() {
abstract fun meditatonDao() : MeditationDao

companion object{
    @Volatile
    private var INSTANCE : MeditationRoomDatabase? = null

    fun getDatabase(
        context: Context,
        scope: CoroutineScope
    ) : MeditationRoomDatabase{
        // if the INSTANCe is not null, then return it,
        // if it is, then create the database
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                MeditationRoomDatabase::class.java,
                "meditation_database"
            )
            // Wipes and rebuilds instead of migrating if no Migration object
            // Migration is not part of this codelab
                .fallbackToDestructiveMigration()
               // .addCallback(MeditationDatabaseCallback(scope))
                .build()
            INSTANCE = instance
            // return instance
            instance
        }
    }
}
}