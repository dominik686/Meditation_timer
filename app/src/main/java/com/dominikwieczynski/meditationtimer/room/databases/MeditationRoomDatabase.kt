package com.dominikwieczynski.meditationtimer.room.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dominikwieczynski.meditationtimer.models.Meditation
import com.dominikwieczynski.meditationtimer.room.daos.MeditationDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Meditation::class], version = 1, exportSchema = false)
abstract class MeditationRoomDatabase : RoomDatabase() {
    abstract fun meditatonDao(): MeditationDao

    companion object {
        @Volatile
        private var INSTANCE: MeditationRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MeditationRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MeditationRoomDatabase::class.java,
                    "meditation_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(MeditationDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }


        private class MeditationDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO)
                    {
                    }
                }
            }
        }



    }
}




