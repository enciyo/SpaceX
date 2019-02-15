package com.example.githubproject.data.cache

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

object UrlConvertToByteArray {


        fun getUrl(src: String): ByteArray {
            var url = URL(src)
            var connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            var stream: InputStream = connection.inputStream
            var bitmap: Bitmap = BitmapFactory.decodeStream(stream)
            var stream2 = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream2)
            var byteArray = stream2.toByteArray()
            bitmap.recycle()
            return byteArray
        }



}