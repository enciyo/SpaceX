package com.example.githubproject.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.example.githubproject.util.InternetCheck
import com.example.githubproject.R
import com.example.githubproject.ui.base.BaseFragment
import com.example.githubproject.ui.detail.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment() {
    lateinit var viewModel: DetailViewModel
    var position: Int = 0
    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_detail
    }

    override fun onStart() {
        super.onStart()
        position = arguments?.getInt("detailReference")!!
        viewModel = DetailViewModel(context!!)


        if (InternetCheck(context!!).isOnline()) {
            viewModel.getOneData(position).observe(this, Observer {
                detail_title.text = it.missionName
                detail_detail.text = it.flightNumber.toString()
                detail_year.text = it.launchYear.toString()
                Glide.with(this@DetailFragment).load(it.links.missionPatch.toString()).into(detail_image)
            })
        }
        if (!InternetCheck(context!!).isOnline()) {
            viewModel.getDb(context!!, position).observe(this, Observer {
                detail_title.text = it.missionName
                detail_detail.text = it.flightNumber.toString()
                detail_year.text = it.launchYear.toString()
                val bitmap = BitmapFactory.decodeByteArray(it.bitmap, 0, it.bitmap!!.size)
                Glide.with(context!!).load(bitmap).into(detail_image)

            })

        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}
