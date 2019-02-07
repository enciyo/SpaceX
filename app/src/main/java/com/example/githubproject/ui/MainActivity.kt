package com.example.githubproject.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.githubproject.R
import com.example.githubproject.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_launches.*
import androidx.navigation.ui.NavigationUI
import androidx.navigation.Navigation


class MainActivity : BaseActivity() {




    override fun getLayoutResourceId(): Int {
        return R.layout.activity_launches
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(findViewById(com.example.githubproject.R.id.my_toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)
        NavigationUI.setupWithNavController(my_toolbar, Navigation.findNavController(this, R.id.my_nav_host_fragment))
        supportActionBar!!.title = ""




    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.appbarSearch -> {
                Toast.makeText(applicationContext, "Active", Toast.LENGTH_LONG).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }

}