package com.example.githubproject.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo


internal class InternetCheck(val context: Context) : BroadcastReceiver() {
    var mManager: ConnectivityManager = (context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)!!
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

    fun isConnectedToInternet(): Boolean {
        val connectivity = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = connectivity.allNetworkInfo
        if (info != null)
            for (i in info.indices)
                if (info[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
        return false
    }
}