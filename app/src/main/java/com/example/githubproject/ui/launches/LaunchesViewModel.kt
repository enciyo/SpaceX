package com.example.githubproject.ui.launches

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.SpacexRepository
import com.example.githubproject.data.dao.AppDatabase
import com.example.githubproject.data.model.Launches


class LaunchesViewModel(application: Application) : ViewModel() {

    private var fetchFromDatabase: LiveData<List<Launches>>? = null
    private var fetchFromNetwork: LiveData<List<Launches>>? = null

    init {
        fetchFromDatabase = AppDatabase.getDatabaseManager(application).getDao().getAllTask()
        fetchFromNetwork = SpacexRepository().getLaunchesList()

    }

    fun getFetchDb(): LiveData<List<Launches>>? = fetchFromDatabase

    fun getFetchNetwork(): LiveData<List<Launches>>? = fetchFromNetwork


}
