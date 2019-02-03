package com.example.githubproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.githubproject.base.BaseActivity
import com.example.githubproject.ui.LaunchesActivity

class MainActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed(
            {
                startActivity(Intent(this, LaunchesActivity::class.java))
            }, 2000L
        )
    }

}
