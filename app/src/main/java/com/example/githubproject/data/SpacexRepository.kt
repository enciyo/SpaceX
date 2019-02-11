package com.example.githubproject.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.githubproject.data.dao.AppDatabase
import com.example.githubproject.data.dao.LaunchesDao
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.remote.ApiClient
import com.example.githubproject.data.remote.ApiService
import com.example.githubproject.util.Extentions
import com.example.githubproject.util.Extentions.myLog
import retrofit2.Call
import retrofit2.Response
import android.os.AsyncTask
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SpacexRepository {

    private var TAG: String = SpacexRepository::class.java.name
    private var service = ApiClient().getService()


    fun getLaunchesList(): LiveData<List<Launches>> {
        val data: MutableLiveData<List<Launches>> = MutableLiveData()
        service.getLaunches().enqueue(object : retrofit2.Callback<List<Launches>> {
            override fun onFailure(call: Call<List<Launches>>, t: Throwable) {
                Extentions.myLog(this@SpacexRepository::class.java, t.message!!)

            }

            override fun onResponse(call: Call<List<Launches>>, response: Response<List<Launches>>) {
                data.value = response.body()
                Extentions.myLog(this@SpacexRepository::class.java, "getLaunchesList().OnResponse")
            }
        })
        return data
    }

    fun getOneLaunchList(groupId: Int): LiveData<Launches> {
        val data: MutableLiveData<Launches> = MutableLiveData()

        service.getOneLaunch(groupId).enqueue(object : retrofit2.Callback<Launches> {
            override fun onFailure(call: Call<Launches>, t: Throwable) {
                Extentions.myLog(this@SpacexRepository::class.java, "getOneLaunchList: " + t.message!!)

            }

            override fun onResponse(call: Call<Launches>, response: Response<Launches>) {
                data.value = response.body()
                Extentions.myLog(this@SpacexRepository::class.java, "getOneLaunchList: OnResponse")
            }

        })
        return data
    }


}