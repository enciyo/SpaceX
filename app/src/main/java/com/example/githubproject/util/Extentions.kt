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




}