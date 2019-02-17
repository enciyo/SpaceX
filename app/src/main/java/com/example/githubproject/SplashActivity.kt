package com.example.githubproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.githubproject.ui.base.BaseActivity
import com.example.githubproject.ui.MainActivity

class SplashActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed(object : Runnable{
            override fun run() {
                startActivity(Intent(this@SplashActivity,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
            }
        },200)

    }

}
