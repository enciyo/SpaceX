package com.example.githubproject

import android.content.Intent
import android.os.Bundle
import com.example.githubproject.base.BaseActivity
import com.example.githubproject.model.dao.DatabaseManager
import com.example.githubproject.model.data.Launches
import com.example.githubproject.ui.LaunchesActivity
import com.example.githubproject.ui.launches.LaunchesViewModel

class MainActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    var model = LaunchesViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val manager=DatabaseManager.getLaunches(this)



        InternetCheck{
            if(it==false){
                startActivity(Intent(this,LaunchesActivity::class.java))
            }
            else if(it==true){
                model.getData().observeForever(androidx.lifecycle.Observer {
                    manager.insert(it)
                    startActivity(Intent(this,LaunchesActivity::class.java))
                })
            }
        }

    }

}
