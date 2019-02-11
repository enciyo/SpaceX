package com.example.githubproject.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.AsyncTask
import com.example.githubproject.ui.NetworkStateReceiver
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

internal class InternetCheck(val context: Context) : BroadcastReceiver() {
    private var mManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private var mListeners: MutableList<NetworkStateReceiver> = ArrayList()
    private var mConnected: Boolean = false


    init {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(this, intentFilter)
        isOnline()
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null || intent.extras == null)
            return

        if (isOnline()) notifyStateToAll()
    }

    fun isOnline(): Boolean {
        val prev = mConnected
        val activeNetwork = mManager.activeNetworkInfo
        mConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        return prev != mConnected
    }

    private fun notifyStateToAll() {
        for (listener in mListeners) {
            notifyState(listener)
        }
    }

    private fun notifyState(listener: NetworkStateReceiver?) {
        if (listener != null) {
            if (mConnected)
                listener.onNetworkAvailable()
            else
                listener.onNetworkUnavailable()
        }
    }


    fun addListener(l: NetworkStateReceiver) {
        mListeners.add(l)
        notifyState(l)
    }

    fun removeListener(l: NetworkStateReceiver) {
        mListeners.remove(l)
    }
}