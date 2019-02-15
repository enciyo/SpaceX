package com.example.githubproject.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.githubproject.R
import com.example.githubproject.util.Extentions
import org.jetbrains.anko.contentView

abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutResourceId(): Int


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())

    }



}