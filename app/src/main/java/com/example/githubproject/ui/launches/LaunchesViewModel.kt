package com.example.githubproject.ui.launches

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.SpacexRepository


class LaunchesViewModel (val context: Context): ViewModel() {

    fun getData(context: Context) : LiveData<List<Launches>> {
       return SpacexRepository(context).getLaunchesList()
    }

    fun getDb(context: Context) : LiveData<List<Launches>>{
        return SpacexRepository(this.context).getDB().getAllTask()
    }

}