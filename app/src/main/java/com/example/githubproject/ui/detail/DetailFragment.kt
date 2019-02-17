package com.example.githubproject.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.example.githubproject.R
import com.example.githubproject.data.model.Launches
import com.example.githubproject.ui.base.BaseFragment
import com.example.githubproject.ui.detail.DetailViewModel
import com.example.githubproject.ui.detail.DetailViewModelFactory
import com.example.githubproject.util.Extentions
import com.example.githubproject.util.InternetCheck
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseFragment() {

    var state: Boolean? = null
    val data: MutableLiveData<Launches> = MutableLiveData()


    lateinit var viewModel: DetailViewModel
    var position: Int = 0
    lateinit var item: Launches

    override fun getLayoutResourceId(): Int = R.layout.fragment_detail


    override fun onStart() {
        super.onStart()
        position = arguments?.getInt("detailReference")!!
        item = arguments!!.getParcelable("detail")!!

        viewModel =
            ViewModelProviders.of(this, DetailViewModelFactory(context!!, position))
                .get(DetailViewModel::class.java)


        initView(item)

    }

    fun initView(it: Launches) {
        Observable.fromCallable {
            detail_title.text = it.missionName
            detail_detail.text = it?.details.toString() + "\nReason: " + it?.launchFailureDetails?.reason.toString()
            detail_year.text = it?.launchYear.toString()
            detail_state.text = "Succes: " + it?.launchSuccess.toString()
            if (InternetCheck(context!!).isConnectedToInternet()) {
                Extentions.myLog(this::class.java, "Internet: True")
                Glide.with(this@DetailFragment).load(item.links.missionPatch.toString()).into(detail_image)


            } else {
                Extentions.myLog(this::class.java, "Internet: false")
                val bitmap: Bitmap? =
                    item.bitmap?.size?.let { BitmapFactory.decodeByteArray(item.bitmap, 0, item.bitmap!!.size) }
                Glide.with(context!!).load(bitmap).into(detail_image)
            }


        }.subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    override fun onNetworkAvailable() {
        this.state = true
    }

    override fun onNetworkUnavailable() {
        this.state = false
    }

}
