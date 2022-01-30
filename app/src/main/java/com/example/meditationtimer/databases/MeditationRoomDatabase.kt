package com.example.meditationtimer.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.meditationtimer.daos.MeditationDao
import com.example.meditationtimer.models.Meditation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Meditation::class], version = 1, exportSchema = false)
//@TypeConverters(MeditationConverters::class)
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
                .addCallback(MeditationDatabaseCallback(scope))
                .build()
            INSTANCE = instance
            instance
        }
    }

    private class MeditationDatabaseCallback(
    private val scope : CoroutineScope) : RoomDatabase.Callback()
    {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch (Dispatchers.IO) {
                    populateDatabase(database.meditatonDao())
            }
            }
        }
    }

    suspend fun populateDatabase(meditationDao : MeditationDao)
    {
        // Start tyhe app with a clean database every time\
       // // not neeeded if tyou only populate on creation med
        val meditation = Meditation(description = "", duration = 5, date = Date(2020, 12, 11, 14,15).toString())
        meditationDao.insertMeditation(meditation)
    }
}
}