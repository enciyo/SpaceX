package com.example.githubproject.model.data

import com.google.gson.annotations.SerializedName

data class FirstStage(
    @SerializedName("cores")
    var cores: List<Core> = listOf()
)