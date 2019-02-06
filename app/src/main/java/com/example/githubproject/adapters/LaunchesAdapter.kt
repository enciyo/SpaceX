package com.example.githubproject.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.githubproject.model.dao.DatabaseManager
import com.example.githubproject.model.data.Launches
import com.example.githubproject.ui.launches.LaunchesFragment
import kotlinx.android.synthetic.main.launches_item.view.*
import android.util.Log
import java.io.ByteArrayOutputStream
import android.widget.ImageView
import com.example.githubproject.InternetCheck
import com.example.githubproject.R


class LaunchesAdapter(val context: Context, var data: List<Launches>, val app: LaunchesFragment) :
    RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(data[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(com.example.githubproject.R.layout.launches_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val manager = DatabaseManager.getLaunches(context)

        fun initView(launches: Launches) {
            itemView.apply {
                launches_title.text = launches.missionName
                launches_year.text = launches.launchYear
                setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("detailReference", launches.flightNumber)
                    Navigation.findNavController(itemView).navigate(com.example.githubproject.R.id.toDetail, bundle)
                }
            }
            InternetCheck{

                    if(it) loadFromNetImage(launches)
                    if(!it) loadFromLocal(launches)
                }




        }//

        fun loadFromLocal(launches: Launches){
            val bitmap = BitmapFactory.decodeByteArray(launches.picture, 0, launches.picture!!.size)
            val image = itemView.findViewById<ImageView>(R.id.launches_image)
            Glide.with(context).load(bitmap).into(image)
            Log.i("MyLogger","LoadFromLocal")
        }
        fun loadFromNetImage(launches: Launches){
            Log.i("MyLogger","LoadFromLocal")
            Glide.with(context).load(launches.links.missionPatchSmall).into(itemView.launches_image)
            Glide.with(context).asBitmap().load(launches.links.missionPatchSmall).into(object :
                SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val stream = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val byteArray = stream.toByteArray()
                    manager.insertImage(byteArray, launches.flightNumber)
                }
            })
        }

    }
}