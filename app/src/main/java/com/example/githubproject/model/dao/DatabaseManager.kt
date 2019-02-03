package com.example.githubproject.model.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubproject.model.data.Launches

@Database(entities = arrayOf(Launches::class), version = 4,exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun getLaunchesDao() : LaunchesDao

    companion object {
        var INSTANCE: DatabaseManager? = null

        fun getDatabaseManager(context: Context): DatabaseManager {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DatabaseManager::class.java,
                    "Launches"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}