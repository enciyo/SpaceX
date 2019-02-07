package com.example.githubproject.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubproject.data.model.Launches
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response

import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


object SpacexRepository {
    lateinit var service: SpacexService

    init {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        service = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
            .create(SpacexService::class.java)


    }

    fun getInstrance(): SpacexRepository {
        return SpacexRepository
    }

    fun getLaunchesList(): LiveData<List<Launches>> {
        val data: MutableLiveData<List<Launches>> = MutableLiveData()
        service.getLaunches().enqueue(object : retrofit2.Callback<List<Launches>> {
            override fun onFailure(call: Call<List<Launches>>, t: Throwable) {
                println("onFailure" + t.message + t.localizedMessage
                +t.cause+t.toString())
                this@SpacexRepository.service.getLaunches().isCanceled
                this@SpacexRepository.getLaunchesList()
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
                this@SpacexRepository.getInstrance().getOneLaunchList(groupId)
            }

            override fun onResponse(call: Call<Launches>, response: Response<Launches>) {
                data.value = response.body()
            }

        })
        return data
    }

}