package com.example.githubproject.ui.launches

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.SpacexRepository
import com.example.githubproject.data.model.Launches
import com.example.githubproject.util.Extentions


class LaunchesViewModel(val application: Application) : ViewModel() {

    private var getData: MutableLiveData<List<Launches>>? = MutableLiveData()
    private var fetchData = SpacexRepository(application.applicationContext).getLaunches()


    init {
        Extentions.myLog(this::class.java, "İnit")
        fetchData!!.subscribe({
                getData!!.postValue(it) })

    }

    fun getData(): LiveData<List<Launches>>? = getData

}
