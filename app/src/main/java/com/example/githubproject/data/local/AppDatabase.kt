package com.example.githubproject.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubproject.data.model.Launches


@Database(entities = [Launches::class], version = 53, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): LaunchesDao

    companion object {

        var INSTANCE: AppDatabase? = null

        fun createDb(context: Context): AppDatabase {
            if (AppDatabase.INSTANCE == null) {
                AppDatabase.INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "Launches"
                ) .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return AppDatabase.INSTANCE!!
        }
    }

}