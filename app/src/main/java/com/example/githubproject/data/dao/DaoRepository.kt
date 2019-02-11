package com.example.githubproject.data.dao


import android.os.AsyncTask
import com.example.githubproject.data.model.Launches
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class DaoRepository(val appDatabase: LaunchesDao) {

    fun insert(launches: Launches, byteArray: ByteArray) {
        appDatabase.insertImage(byteArray, launches.flightNumber)
    }
}



