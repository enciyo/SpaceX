package com.example.githubproject.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubproject.R
import com.example.githubproject.model.data.Launches
import com.example.githubproject.ui.launches.LaunchesFragment
import kotlinx.android.synthetic.main.launches_item.view.*




class LaunchesAdapter(val context: Context, var data: List<Launches>, val app: LaunchesFragment) :
    RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(data[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.launches_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun initView(launches: Launches) {
            itemView.apply {
                launches_title.text = launches.missionName
                launches_year.text = launches.launchYear
                setOnClickListener {
                    val bundle =Bundle()
                    bundle.putInt("detailReference",launches.flightNumber)

                    Navigation.findNavController(itemView).navigate(com.example.githubproject.R.id.toDetail,bundle)
                }

            }

            Glide.with(context).load(launches.links.missionPatchSmall).into(itemView.launches_image)

        }
    }
}