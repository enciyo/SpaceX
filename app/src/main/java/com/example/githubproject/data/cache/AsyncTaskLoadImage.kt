package com.example.githubproject.data.cache

import android.graphics.Bitmap
import android.os.AsyncTask
import com.example.githubproject.data.local.LaunchesDao
import com.example.githubproject.data.model.Launches
import com.example.githubproject.util.Extentions
import java.io.IOException


open class AsyncTaskLoadImage(val repository: LaunchesDao, val launches: List<Launches>) :
    AsyncTask<String, String, Bitmap>() {

    var index: Int = 0

    override fun doInBackground(vararg params: String): Bitmap? {
        try {
            Extentions.myLog(this::class.java, "doInBAckgroung")
            if (repository.getSize() != launches.size) {
                this.index = repository.getSize()
                for (t in -1..launches.size) {
                    if (!repository.findByLaunches(launches[t].flightNumber)) {
                        repository.insert(launches[t])
                        repository.insertImage(
                            UrlConvertToByteArray.getUrl(launches[t].links.missionPatch.toString()),
                            t
                        )
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

    companion object {
        private val TAG = "AsyncTaskLoadImage"
    }
}