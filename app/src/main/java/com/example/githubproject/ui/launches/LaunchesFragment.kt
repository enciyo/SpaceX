package com.example.githubproject.ui.launches

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.util.InternetCheck
import com.example.githubproject.ui.launches.adapters.LaunchesAdapter
import com.example.githubproject.ui.base.BaseFragment
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.SpacexRepository
import kotlinx.android.synthetic.main.fragment_launches.*


class LaunchesFragment : BaseFragment() {
    val TAG = "LaunchesFragment"
    lateinit var viewModel: LaunchesViewModel
    var adapter: LaunchesAdapter? = null
    override fun getLayoutResourceId(): Int {
        return com.example.githubproject.R.layout.fragment_launches
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()
        viewModel = LaunchesViewModel(activity!!.application)
        Log.i("MyLogger", "Failed")
        checkInternet()
    }

    fun checkInternet() {

        adapter = LaunchesAdapter(context!!, mutableListOf(), SpacexRepository(context!!).getDB())
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerLaunches.layoutManager = layoutManager
        recyclerLaunches.setHasFixedSize(true)
        recyclerLaunches.adapter = adapter

        if (InternetCheck(context!!).isOnline()) {
            viewModel.getData(context!!).observe(this, Observer {
                initView(it)
            })
        }
        if (!InternetCheck(context!!).isOnline()) {
            viewModel.getDb(context!!).observe(this, Observer {
                initView(it)
            })
        }

    }

    fun initView(list: List<Launches>) {
        adapter?.addData(list)
    }

}