package com.example.githubproject

import com.example.githubproject.ui.base.BaseFragment

class SearchFragment : BaseFragment(){

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_search
    }

    override fun onNetworkAvailable() {
    }

    override fun onNetworkUnavailable() {
    }

}