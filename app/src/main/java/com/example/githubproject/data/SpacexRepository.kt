package com.example.githubproject.data

import android.content.Context
import com.example.githubproject.data.cache.AsyncTaskLoadImage
import com.example.githubproject.data.local.AppDatabase
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.remote.ApiClient
import com.example.githubproject.util.Extentions
import com.example.githubproject.util.InternetCheck
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SpacexRepository(var context: Context) {

    private var service = ApiClient().getService()
    private var appDatabase = AppDatabase.createDb(context).getDao()


    fun getLaunches(): Observable<List<Launches>>? {
        val hasConnection = InternetCheck(context).isConnectedToInternet()
        Extentions.myLog(this::class.java, "InternetCheck")
        return if(hasConnection) getFromNetworkSaveDatabase() else getLaunchesFromDatabase()
    }

    private fun getFromNetworkSaveDatabase(): Observable<List<Launches>> {
        return service.getLaunches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                AsyncTaskLoadImage(appDatabase,it).execute()
            }
            .doOnError {
                Extentions.myLog(this::class.java,it.message.toString())
            }

    }

    private fun getLaunchesFromDatabase(): Observable<List<Launches>> {
        return appDatabase!!.getAllLaunches()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }



    fun getDetailLaunch(groupId: Int) : Observable<Launches>{
        val hasConnection = InternetCheck(context).isConnectedToInternet()
        return if(hasConnection) getDetailLaunchFromNet(groupId) else getDetailLaunchFromDb(groupId)
    }

   private fun getDetailLaunchFromNet(groupId:Int) : Observable<Launches>{
        return service.getOneLaunch(groupId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    private fun getDetailLaunchFromDb(groupId: Int) : Observable<Launches> {
        return appDatabase.findByName(groupId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}

