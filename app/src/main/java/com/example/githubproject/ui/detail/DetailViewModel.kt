package com.example.githubproject.ui.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubproject.data.SpacexRepository
import com.example.githubproject.data.model.Launches

class DetailViewModel(context: Context, groupId: Int) : ViewModel() {

    private var data: MutableLiveData<Launches>? = MutableLiveData()
    private var fetchData = SpacexRepository(context).getDetailLaunch(groupId = groupId)

    init {

        fetchData!!.subscribe({
            data!!.postValue(it)
        })

    }

    fun getData() : MutableLiveData<Launches> = data!!


}
