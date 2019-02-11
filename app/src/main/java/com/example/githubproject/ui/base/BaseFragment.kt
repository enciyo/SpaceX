package com.example.githubproject.ui.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.githubproject.R
import com.example.githubproject.ui.NetworkStateReceiver
import com.example.githubproject.util.InternetCheck
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment(), NetworkStateReceiver {
    private var networkStateReceiver: InternetCheck? = null


    abstract fun getLayoutResourceId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResourceId(), container, false)
    }


    override fun onResume() {
        super.onResume()
        setNetworkState()

    }
    fun setNetworkState() {
        println("MyLogger")
        networkStateReceiver = InternetCheck(context!!)
        networkStateReceiver!!.addListener(this)
        activity!!.applicationContext.registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }



}