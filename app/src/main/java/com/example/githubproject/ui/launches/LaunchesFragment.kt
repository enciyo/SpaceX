package com.example.githubproject.ui.launches

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.githubproject.adapters.LaunchesAdapter
import com.example.githubproject.base.BaseFragment
import com.example.githubproject.model.dao.DatabaseManager
import kotlinx.android.synthetic.main.fragment_launches.*


class LaunchesFragment : BaseFragment() {
    var viewModel = LaunchesViewModel()
    val TAG = "LaunchesFragment"
    private var count: Int = 0
    private val SAVE_COUNT = "save_count"

    override fun getLayoutResourceId(): Int {
        return com.example.githubproject.R.layout.fragment_launches
    }


    override fun onStart() {
        super.onStart()
        val manager = DatabaseManager.getDatabaseManager(context!!)
        viewModel.getData().observeForever(androidx.lifecycle.Observer {

            Log.i("MyLogger","Failed")
            manager.getLaunchesDao().insert(it)

            val adapter = LaunchesAdapter(context!!, it, this)
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = RecyclerView.VERTICAL
            recyclerLaunches.layoutManager = layoutManager
            recyclerLaunches.setHasFixedSize(true)
            recyclerLaunches.adapter = adapter
        })

    }

}