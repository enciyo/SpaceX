package com.example.githubproject.util

interface NetworkStateReceiver {

    fun onNetworkAvailable()
    fun onNetworkUnavailable()
}