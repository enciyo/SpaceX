package com.example.githubproject.ui

interface NetworkStateReceiver {

    fun onNetworkAvailable()
    fun onNetworkUnavailable()
}