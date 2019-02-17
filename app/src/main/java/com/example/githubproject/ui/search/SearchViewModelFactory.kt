package com.example.githubproject.ui.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubproject.ui.launches.LaunchesViewModel

class SearchViewModelFactory(val application: Application): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = LaunchesViewModel(application) as T

}