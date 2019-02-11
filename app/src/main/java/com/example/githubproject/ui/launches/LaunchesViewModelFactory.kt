package com.example.githubproject.ui.launches

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LaunchesViewModelFactory(val application: Application) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchesViewModel(application) as T
    }

}