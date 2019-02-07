package com.example.githubproject.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.dao.DatabaseManager
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.remote.SpacexRepository

class DetailViewModel : ViewModel() {

    fun getOneData(groupId:Int) : LiveData<Launches> {
        return SpacexRepository.getInstrance().getOneLaunchList(groupId)
    }

    fun getDb(context: Context,groupId: Int) : LiveData<Launches>{
        return DatabaseManager.getLaunches(context).findByName(groupId)
    }


}