package com.example.githubproject.ui.launches.adapters

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
import com.example.githubproject.data.model.Launches
import kotlinx.android.synthetic.main.launches_item.view.*
import android.util.Log
import android.widget.ImageView
import java.io.ByteArrayOutputStream
import android.widget.Toast
import com.example.githubproject.R
import com.example.githubproject.data.dao.LaunchesDao
import com.example.githubproject.util.Extentions
import com.example.githubproject.util.InternetCheck
import org.jetbrains.anko.doAsync


class LaunchesAdapter(val context: Context, var data: List<Launches>, val manager: LaunchesDao) :
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
        val imageView = itemView.findViewById<ImageView>(R.id.launches_image)

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

          loadFromLocal(launches)




        }//

        fun loadFromLocal(launches: Launches){
            if(launches.picture!=null){
                val bitmap = BitmapFactory.decodeByteArray(launches.picture, 0, launches.picture!!.size)

                Glide.with(context).load(bitmap).into(imageView)

            }else {
                saveData(launches)

            }

            Log.i("MyLogger","LoadFromLocal")
        }

        fun saveData(launches: Launches) {
            Glide.with(context).asBitmap().load(launches.links.missionPatchSmall).into(object :
                SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val stream = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val byteArray = stream.toByteArray()
                    Extentions.insertDatabase(manager,launches,byteArray,launches.flightNumber)
                    Glide.with(context).load(resource).into(imageView)

                }
            })
        }


    }
}