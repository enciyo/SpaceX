package com.example.githubproject.ui.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.model.data.Launches
import com.example.githubproject.repository.SpacexRepository


class LaunchesViewModel : ViewModel() {

    fun getData() : LiveData<List<Launches>> {
       return SpacexRepository.getInstrance().getLaunchesList()
    }
}