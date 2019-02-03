package com.example.githubproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubproject.model.data.Launches
import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

object SpacexRepository {
    lateinit var service: SpacexService



    init {

        service = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpacexService::class.java)

    }

    fun getInstrance(): SpacexRepository {
        return this
    }

    fun getLaunchesList(): LiveData<List<Launches>> {
        var data: MutableLiveData<List<Launches>> = MutableLiveData()
        service.getLaunches().enqueue(object : retrofit2.Callback<List<Launches>> {
            override fun onFailure(call: Call<List<Launches>>, t: Throwable) {
                println("sada")
            }

            override fun onResponse(call: Call<List<Launches>>, response: Response<List<Launches>>) {
                data.value = response.body()
            }
        })
        return data
    }

    fun getOneLaunchList(groupId: Int): LiveData<Launches> {
        var data: MutableLiveData<Launches> = MutableLiveData()

        service.getOneLaunch(groupId).enqueue(object : retrofit2.Callback<Launches> {
            override fun onFailure(call: Call<Launches>, t: Throwable) {
                println("onFailure")
            }

            override fun onResponse(call: Call<Launches>, response: Response<Launches>) {
                data.value = response.body()
            }

        })
        return data
    }

}