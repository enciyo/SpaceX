package com.example.githubproject.data.cache

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import com.example.githubproject.data.dao.DaoRepository
import com.example.githubproject.data.model.Launches
import com.example.githubproject.util.Extentions
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL


open class AsyncTaskLoadImage(val repository: DaoRepository, val launches: Launches,val bitmap: Bitmap) :
    AsyncTask<String, String, Bitmap>() {

    override fun doInBackground(vararg params: String): Bitmap? {
        try {
            Extentions.myLog(this::class.java,"doInBAckgroung")
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 1, stream)
            val byteArray = stream.toByteArray()
            repository.insert(launches,byteArray)

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

    companion object {
        private val TAG = "AsyncTaskLoadImage"
    }
}