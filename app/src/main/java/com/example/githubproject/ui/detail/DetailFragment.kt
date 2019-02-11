package com.example.githubproject.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.example.githubproject.util.InternetCheck
import com.example.githubproject.R
import com.example.githubproject.data.model.Launches
import com.example.githubproject.ui.base.BaseFragment
import com.example.githubproject.ui.detail.DetailViewModel
import com.example.githubproject.ui.detail.DetailViewModelFactory
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment() {
    override fun onNetworkAvailable() {
        viewModel.getOneData()?.observe(this, Observer {
            initView(it)
            Glide.with(this@DetailFragment).load(it.links.missionPatch.toString()).into(detail_image)
        })

    }

    override fun onNetworkUnavailable() {
        viewModel.getDb()?.observe(this, Observer { t ->
            initView(t)
            val bitmap: Bitmap? = t.bitmap?.size?.let { BitmapFactory.decodeByteArray(t.bitmap, 0, it) }
            Glide.with(context!!).load(bitmap).into(detail_image)
        })

    }

    lateinit var viewModel: DetailViewModel
    var position: Int = 0

    override fun getLayoutResourceId(): Int = R.layout.fragment_detail


    override fun onStart() {
        super.onStart()
        position = arguments?.getInt("detailReference")!!

        viewModel =
            ViewModelProviders.of(this, DetailViewModelFactory(context!!, position)).get(DetailViewModel::class.java)


    }

    fun initView(it: Launches) {
        detail_title.text = it.missionName
        detail_detail.text = it.details.toString() + "\nReason: " + it.launchFailureDetails.reason.toString()
        detail_year.text = it.launchYear.toString()
        detail_state.text = "Succes: " + it.launchSuccess.toString()
    }


}
