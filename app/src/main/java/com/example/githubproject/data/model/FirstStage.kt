package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class FirstStage(
    @SerializedName("cores")
    var cores: List<Core> = listOf()
)