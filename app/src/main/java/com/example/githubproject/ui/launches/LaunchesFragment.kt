package com.example.githubproject.ui.launches

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.util.InternetCheck
import com.example.githubproject.ui.launches.adapters.LaunchesAdapter
import com.example.githubproject.ui.base.BaseFragment
import com.example.githubproject.data.model.Launches
import com.example.githubproject.util.Extentions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_launches.*


class LaunchesFragment : BaseFragment() {
    lateinit var viewModel: LaunchesViewModel
    var adapter: LaunchesAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LaunchesAdapter(mutableListOf())
        val layoutManager = LinearLayoutManager(context?.applicationContext)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerLaunches.layoutManager = layoutManager
        recyclerLaunches.setHasFixedSize(true)
        recyclerLaunches.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        Log.i("MyLogger", "onStart")

        viewModel = ViewModelProviders.of(this, LaunchesViewModelFactory(activity!!.application))
            .get(LaunchesViewModel::class.java)

    }


    fun initView(list: List<Launches>) {
        Extentions.myLog(this::class.java, "addDataa")
        adapter?.addData(list)
    }


    override fun onNetworkAvailable() {
        viewModel.getFetchNetwork()?.observe(this, Observer {
            initView(it)
        })
    }

    override fun onNetworkUnavailable() {
        Extentions.getSnackbar(view!!)
        viewModel.getFetchDb()?.observe(this, Observer {
            initView(it)
        })
    }


    override fun getLayoutResourceId(): Int = com.example.githubproject.R.layout.fragment_launches


}