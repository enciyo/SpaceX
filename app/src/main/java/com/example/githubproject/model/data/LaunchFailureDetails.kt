package com.example.githubproject.model.data

import com.google.gson.annotations.SerializedName

data class LaunchFailureDetails(
    @SerializedName("time")
    var time: Int = 0,
    @SerializedName("altitude")
    var altitude: Int = 0,
    @SerializedName("reason")
    var reason: String = ""
)