package com.example.githubproject

import android.content.Intent
import android.os.Bundle
import com.example.githubproject.ui.base.BaseActivity
import com.example.githubproject.ui.MainActivity

class SplashActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this,MainActivity::class.java))

    }

}
