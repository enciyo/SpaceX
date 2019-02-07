package com.example.githubproject.data.remote

import com.example.githubproject.data.model.Launches
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{
    @GET("v3/launches")
    fun getLaunches() : Call<List<Launches>>

    @GET("v3/launches/{flight_number}")
    fun getOneLaunch(@Path("flight_number") groupId:Int) : Call<Launches>
}