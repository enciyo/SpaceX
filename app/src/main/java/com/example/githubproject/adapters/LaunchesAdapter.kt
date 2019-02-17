package com.example.githubproject.adapters

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.githubproject.data.model.Launches
import kotlinx.android.synthetic.main.adapter_item.view.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.githubproject.R
import com.example.githubproject.data.local.AppDatabase
import com.example.githubproject.data.local.LaunchesDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LaunchesAdapter(var data: MutableList<Launches>) :
    RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {
    var repository: LaunchesDao? = null

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(data[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(com.example.githubproject.R.layout.adapter_item, parent, false)
        repository = AppDatabase.createDb(view.context).getDao()
        return ViewHolder(view)
    }

    fun addData(list: List<Launches>) {
        data.addAll(list)
        notifyItemRangeInserted(data.size - list.size - 1, list.size)
    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        var imageView = itemView.findViewById<ImageView>(R.id.launches_image)

        fun initView(launches: Launches) {
            itemView.apply {
                launches_title.text = launches.missionName
                launches_year.text = launches.launchYear
                setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("detailReference", launches.flightNumber)
                    bundle.putParcelable("detail", launches)
                    Navigation.findNavController(itemView).navigate(com.example.githubproject.R.id.toDetail, bundle)
                }
            }
            loadFromNet(launches)


        }

        fun loadFromNet(launches: Launches) {
            if (launches.bitmap != null) {
                Observable.fromCallable {
                    BitmapFactory.decodeByteArray(launches.bitmap, 0, launches.bitmap!!.size)

                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        imageView.setImageBitmap(it)
                    }, {
                    })

            } else {
                Glide.with(itemView).load(launches.links.missionPatch.toString()).into(imageView)
            }

        }


    }


}



