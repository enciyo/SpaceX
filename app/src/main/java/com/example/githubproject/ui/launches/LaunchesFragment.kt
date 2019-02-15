package com.example.githubproject.ui.launches

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.githubproject.ui.launches.adapters.LaunchesAdapter
import com.example.githubproject.ui.base.BaseFragment
import com.example.githubproject.util.Extentions
import com.example.githubproject.util.InternetCheck
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_launches.*


class LaunchesFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {


    lateinit var viewModel: LaunchesViewModel
    var adapter: LaunchesAdapter? = null

    override fun getLayoutResourceId(): Int = com.example.githubproject.R.layout.fragment_launches

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    override fun onStart() {
        super.onStart()
        getData()
        refleshLayout.setOnRefreshListener(this)

    }

    fun setAdapter() {
        adapter = LaunchesAdapter(mutableListOf())
        val layoutManager = LinearLayoutManager(context?.applicationContext)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerLaunches.layoutManager = layoutManager
        recyclerLaunches.setHasFixedSize(true)
        recyclerLaunches.adapter = adapter
        refleshLayout.isRefreshing = true
    }


    fun getData() {

        viewModel = ViewModelProviders.of(this, LaunchesViewModelFactory(activity!!.application))
            .get(LaunchesViewModel::class.java)

        viewModel.getData()!!.observe(this, Observer {
            adapter?.addData(it)
            refleshLayout.isRefreshing = false

        })


    }

    override fun onRefresh() {
        if (InternetCheck(view!!.context).isConnectedToInternet()) {
            Observable.just(viewModelStore.clear())
                .doOnNext {
                    setAdapter()
                    getData()
                }.subscribe()
        } else {
            refleshLayout.isRefreshing=false
            Extentions.getSnackbar(view!!)
        }
    }


}