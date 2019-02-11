package com.example.githubproject.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.SpacexRepository
import com.example.githubproject.data.dao.AppDatabase

class DetailViewModel(context: Context, groupId: Int) : ViewModel() {

    private var fetchFromNetwork: LiveData<Launches>? = null
    private var fetchFromDatabase: LiveData<Launches>? = null

    init {
        fetchFromNetwork = SpacexRepository().getOneLaunchList(groupId)
        fetchFromDatabase = AppDatabase.getDatabaseManager(context).getDao().findByName(groupId)
    }


    fun getOneData(): LiveData<Launches>? = fetchFromNetwork
    fun getDb(): LiveData<Launches>? = fetchFromDatabase

}