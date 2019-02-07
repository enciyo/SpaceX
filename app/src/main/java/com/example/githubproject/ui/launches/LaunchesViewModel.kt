package com.example.githubproject.ui.launches

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.dao.DatabaseManager
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.remote.SpacexRepository


class LaunchesViewModel : ViewModel() {

    fun getData(context: Context) : LiveData<List<Launches>> {
       return SpacexRepository.getInstrance().getLaunchesList()
    }

    fun getDb(context: Context) : LiveData<List<Launches>>{
        return DatabaseManager.getLaunches(context).getAllTask()
    }

}