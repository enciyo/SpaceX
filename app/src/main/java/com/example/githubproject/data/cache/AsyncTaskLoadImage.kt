package com.example.githubproject.data.cache

import android.graphics.Bitmap
import android.os.AsyncTask
import com.example.githubproject.data.local.LaunchesDao
import com.example.githubproject.data.model.Launches
import com.example.githubproject.util.Extentions
import java.io.IOException


open class AsyncTaskLoadImage(val repository: LaunchesDao, val launches: List<Launches>) :
    AsyncTask<String, String, Bitmap>() {


    override fun doInBackground(vararg params: String): Bitmap? {
        try {
            Extentions.myLog(this::class.java, "doInBAckgroung")
            if (repository.getSize() != launches.size) {
                for (t in 1..launches.size) {
                    if (!repository.findByLaunches(launches[t].flightNumber)) {
                        repository.insert(launches[t])
                        if (launches[t].flightNumber > 0 && launches[t].flightNumber < 10) {
                            repository.insertImage(
                                UrlConvertToByteArray.getUrl(launches[t].links.missionPatch.toString()), t)
                        }
                    }
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


}