package com.example.githubproject.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.R
import com.example.githubproject.data.model.Launches
import com.example.githubproject.ui.base.BaseActivity
import com.example.githubproject.ui.launches.adapters.LaunchesAdapter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    lateinit var viewModel: SearchViewModel
    lateinit var adapter: LaunchesAdapter
    lateinit var searchList: List<Launches>
    lateinit var myText: String

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        backButton.setOnClickListener {
            this.finish()
            overridePendingTransition(0, 0)
        }

        viewModel = SearchViewModel(application)

        searchView.doAfterTextChanged {
            myText = it.toString()
            if (!it.isNullOrEmpty()) {
                loadData()
            } else {
                initAdapter()
            }
        }


    }

    fun loadData() {
        viewModel.getData().observeForever {
            filterData(it)
        }
    }

    fun filterData(list: List<Launches>) {
        var mList: ArrayList<Launches> = arrayListOf()
        Observable.fromCallable {
            for (i in 0..list.size - 1) {
                if (myText.length <= list[i].missionName.length) {
                    if (list[i].missionName.toLowerCase().trim().contains(myText.toLowerCase().trim())) {
                        mList.add(list[i])
                    }
                }
            }
        }
            .doOnNext {
                initView(mList)
            }
            .subscribe()

    }

    fun initAdapter() {
        adapter = LaunchesAdapter(mutableListOf())
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        searchListView.layoutManager = layoutManager
        searchListView.adapter = adapter
    }


    private fun initView(it: List<Launches>) {
        initAdapter()
        adapter.addData(it)
    }


}