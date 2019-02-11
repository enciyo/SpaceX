package com.example.githubproject.data.remote

import com.example.githubproject.util.Extentions
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

import java.util.concurrent.TimeUnit

class ApiClient {

     private val service: ApiService


    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        service = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)




    }
    fun getService(): ApiService {
        Extentions.myLog(this::class.java,"getService()")
        return service
    }


}