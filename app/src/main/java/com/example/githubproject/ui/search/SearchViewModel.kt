package com.example.githubproject.ui.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.SpacexRepository
import com.example.githubproject.data.model.Launches


class SearchViewModel(val application: Application): ViewModel() {

    private val data: MutableLiveData<List<Launches>> by lazy {
        MutableLiveData<List<Launches>>().also {
            loadData()
        }
    }

    private fun loadData(){
       SpacexRepository(application).getLaunches()!!.subscribe({
           data.postValue(it)
       })
   }

    fun getData(): LiveData<List<Launches>> {
        return data
    }


}