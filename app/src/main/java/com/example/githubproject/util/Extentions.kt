package com.example.githubproject.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar


object Extentions {

    fun <T> myLog(tag: Class<T>, string: String): Int {
        return Log.i("MyLogger", tag.name + ": $string")
    }

    fun getProgressDialog(context: Context): ProgressDialog {

        val progressBar = ProgressDialog(context)
        progressBar.setCancelable(false)
        progressBar.setMessage("File downloading ...")
        progressBar.setTitle("Loading...")
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressBar.progress = 0
        progressBar.max = 100
        return progressBar
    }

    fun getSnackbar(view:View) {
        return Snackbar.make(view,"Network Error", Snackbar.LENGTH_SHORT)
            .setActionTextColor(Color.BLUE)
            .show()
    }



}