package com.example.githubproject.repository

import com.example.githubproject.model.data.Launches
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SpacexService{
    @GET("v3/launches")
    fun getLaunches() : Call<List<Launches>>

    @GET("v3/launches/{flight_number}")
    fun getOneLaunch(@Path("flight_number") groupId:Int) : Call<Launches>
}