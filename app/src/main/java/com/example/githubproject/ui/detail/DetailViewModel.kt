package com.example.githubproject.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.model.data.Launches
import com.example.githubproject.repository.SpacexRepository

class DetailViewModel : ViewModel() {

    fun getOneData(groupId:Int) : LiveData<Launches> {
        return SpacexRepository.getInstrance().getOneLaunchList(groupId)
    }

}