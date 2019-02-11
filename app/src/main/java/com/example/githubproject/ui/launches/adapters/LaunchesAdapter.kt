package com.example.githubproject.ui.launches.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubproject.data.model.Launches
import kotlinx.android.synthetic.main.launches_item.view.*
import android.widget.ImageView
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.githubproject.R
import com.example.githubproject.data.SpacexRepository
import com.example.githubproject.data.cache.AsyncTaskLoadImage
import com.example.githubproject.data.dao.AppDatabase
import com.example.githubproject.data.dao.DaoRepository
import com.example.githubproject.data.dao.LaunchesDao
import com.example.githubproject.util.Extentions
import com.example.githubproject.util.InternetCheck
import com.squareup.picasso.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception


class LaunchesAdapter(var data: MutableList<Launches>) :
    RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {



    lateinit var repository : DaoRepository

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.initView(data[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.example.githubproject.R.layout.launches_item, parent, false)
        repository= DaoRepository(AppDatabase.getDatabaseManager(view.context).getDao())
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
                    Navigation.findNavController(itemView).navigate(com.example.githubproject.R.id.toDetail, bundle)
                }
            }
            if (launches.bitmap==null) {
                loadFromNet(launches)
            }
            if (launches.bitmap!=null) {
                loadFromLocal(launches)
            }


        }

        fun loadFromNet(launches: Launches) {
            Glide.with(this.itemView.context).asBitmap().load(launches.links.missionPatch)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                       imageView.setImageBitmap(resource)
                        if (!repository.appDatabase.findByLaunches(launches.flightNumber) && launches.bitmap == null) {
                            Extentions.myLog(this::class.java, "LoadFromNet")
                            repository.appDatabase.insert(launches)
                            if (launches.flightNumber in 1..9) {
                                AsyncTaskLoadImage(repository, launches, resource).execute()
                            }

                        }
                    }

                    override fun onLoadStarted(placeholder: Drawable?) {
                        super.onLoadStarted(placeholder)
                        imageView.setImageDrawable(placeholder)
                    }

                })
        }

        fun loadFromLocal(launches: Launches) {
            if (launches.bitmap != null) {
                Extentions.myLog(this::class.java, "loadFromLocal")
                object : AsyncTask<Void, Void, Bitmap>() {
                    override fun doInBackground(vararg params: Void?): Bitmap {
                        val bitmap = BitmapFactory.decodeByteArray(launches.bitmap, 0, launches.bitmap!!.size)
                        return bitmap!!
                    }

                    override fun onPostExecute(result: Bitmap?) {
                        super.onPostExecute(result)
                        Glide.with(itemView.context).load(result).into(itemView.launches_image)
                    }
                }.execute()
            }


        }

    }
}



