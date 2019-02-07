package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class SecondStage(
    @SerializedName("block")
    var block: Any? = Any(),
    @SerializedName("payloads")
    var payloads: List<Payload> = listOf()
)