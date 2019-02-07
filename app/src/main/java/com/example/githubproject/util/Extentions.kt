package com.example.githubproject.util

import android.graphics.Bitmap
import android.util.Log
import android.os.AsyncTask
import com.example.githubproject.data.dao.LaunchesDao
import com.example.githubproject.data.model.Launches


object Extentions {

    fun <T> myLog(tag: Class<T>, string: String): Int {
        return Log.i("MyLogger", tag.simpleName + ": $string")
    }

    fun insertDatabase(launchesDao: LaunchesDao, launches: Launches, bitmap: ByteArray, value: Int) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                if (launchesDao.findByLaunches(launches.flightNumber).isEmpty()) {
                    launchesDao.insert(launches)
                    launchesDao.insertImage(bitmap, value)
                }
                return null
            }
        }.execute()
    }


}