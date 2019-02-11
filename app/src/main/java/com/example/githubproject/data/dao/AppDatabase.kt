package com.example.githubproject.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubproject.data.model.Launches


@Database(entities = [Launches::class], version = 45, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): LaunchesDao

    companion object {

        var INSTANCE: AppDatabase? = null

        fun getDatabaseManager(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "Launches"
                ) .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }

    }

}