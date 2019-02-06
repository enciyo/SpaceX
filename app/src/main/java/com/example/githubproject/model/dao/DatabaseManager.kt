package com.example.githubproject.model.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubproject.model.data.Launches
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration



@Database(entities = [Launches::class], version = 15,exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun getDao() : LaunchesDao

    companion object {


        var INSTANCE: DatabaseManager? = null

        fun getDatabaseManager(context: Context): DatabaseManager {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    DatabaseManager::class.java,
                    "Launches"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
        fun getLaunches(context: Context): LaunchesDao {
            return getDatabaseManager(context).getDao()
        }

    }


}