package com.example.githubproject.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.model.Launches
import com.example.githubproject.data.SpacexRepository

class DetailViewModel(val context: Context) : ViewModel() {

    fun getOneData(groupId:Int) : LiveData<Launches> {
        return SpacexRepository(context).getOneLaunchList(groupId)
    }

    fun getDb(context: Context,groupId: Int) : LiveData<Launches>{
        return SpacexRepository(context).getDB().findByName(groupId)
    }


}