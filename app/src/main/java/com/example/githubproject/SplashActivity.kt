package com.example.githubproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.example.githubproject.ui.base.BaseActivity
import com.example.githubproject.data.dao.DatabaseManager
import com.example.githubproject.ui.MainActivity
import com.example.githubproject.ui.launches.LaunchesViewModel

class SplashActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    var model = LaunchesViewModel()
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val manager=DatabaseManager.getLaunches(this)


        startActivity(Intent(this,MainActivity::class.java).addFlags(Intent.FILL_IN_ACTION))

    }

}
