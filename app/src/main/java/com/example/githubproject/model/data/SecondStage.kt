package com.example.githubproject.model.data

import com.google.gson.annotations.SerializedName

data class SecondStage(
    @SerializedName("block")
    var block: Any? = Any(),
    @SerializedName("payloads")
    var payloads: List<Payload> = listOf()
)