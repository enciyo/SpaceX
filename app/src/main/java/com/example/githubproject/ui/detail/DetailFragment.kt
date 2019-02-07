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
    lateinit var viewModel : DetailViewModel
    var position : Int = 0
    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_detail
    }

    override fun onStart() {
        super.onStart()
        position = arguments?.getInt("detailReference")!!

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        InternetCheck {
            if (it) {
                viewModel.getOneData(position).observe(this, Observer {
                    detail_title.text = it.missionName
                    detail_detail.text = it.details.toString()
                    detail_year.text = it.launchYear.toString()
                    Glide.with(this@DetailFragment).load(it.links.missionPatch.toString()).into(detail_image)
                })
            }
            if (!it) {
                viewModel.getDb(context!!, position).observe(this, Observer {
                    detail_title.text = it.missionName
                    detail_detail.text = it.details
                    detail_year.text = it.launchYear.toString()
                    val bitmap = BitmapFactory.decodeByteArray(it.picture, 0, it.picture!!.size)
                    Glide.with(context!!).load(bitmap).into(detail_image)

                })

            }
        }




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
