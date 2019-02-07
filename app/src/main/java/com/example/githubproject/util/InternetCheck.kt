package com.example.githubproject.util

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

internal class InternetCheck (val context: Context)  {
     fun isOnline(): Boolean {
        var returner = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        returner = activeNetwork != null && activeNetwork.isConnected
        return returner
    }
}