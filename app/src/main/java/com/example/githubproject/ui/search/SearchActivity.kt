package com.example.githubproject.ui.search

import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.R
import com.example.githubproject.data.model.Launches
import com.example.githubproject.ui.base.BaseActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_search.*
import android.app.Activity
import com.example.githubproject.adapters.SearchAdapter


class SearchActivity : BaseActivity() ,SearchListener{
    override fun show() {
        searchView.isVisible=true

    }

    override fun close() {
        searchView.clearFocus()
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        searchView.isVisible=false
    }

    lateinit var viewModel: SearchViewModel
    lateinit var adapter: SearchAdapter
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
        searchView.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)


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
        val mList: ArrayList<Launches> = arrayListOf()
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

        adapter = SearchAdapter(mutableListOf(), this)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        searchListView.layoutManager = layoutManager
        searchListView.adapter = adapter
    }


    private fun initView(it: List<Launches>) {
        initAdapter()
        adapter.addData(it)
    }


}