package com.example.githubproject.ui.launches

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.util.InternetCheck
import com.example.githubproject.ui.launches.adapters.LaunchesAdapter
import com.example.githubproject.ui.base.BaseFragment
import com.example.githubproject.data.model.Launches
import kotlinx.android.synthetic.main.fragment_launches.*


class LaunchesFragment : BaseFragment() {
    var viewModel = LaunchesViewModel()
    val TAG = "LaunchesFragment"

    override fun getLayoutResourceId(): Int {
        return com.example.githubproject.R.layout.fragment_launches
    }

    override fun onResume() {
        super.onResume()
        checkInternet()
    }

    override fun onStart() {
        super.onStart()

            Log.i("MyLogger","Failed")

    }

    fun checkInternet(){
        InternetCheck {
            if (it) {
                viewModel.getData(context!!).observe(this, Observer {
                    initView(it)
                })
            }
            if (!it) {
                viewModel.getDb(context!!).observe(this, Observer {
                    initView(it)
                })
            }
        }
    }
    fun initView(list:List<Launches>){
        val adapter = LaunchesAdapter(context!!,list ,this)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerLaunches.layoutManager = layoutManager
        recyclerLaunches.setHasFixedSize(true)
        recyclerLaunches.adapter = adapter
    }

}