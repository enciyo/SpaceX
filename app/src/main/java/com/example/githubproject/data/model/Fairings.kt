package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class Fairings(
    @SerializedName("reused")
    var reused: Boolean = false,
    @SerializedName("recovery_attempt")
    var recoveryAttempt: Any? = Any(),
    @SerializedName("recovered")
    var recovered: Boolean = false,
    @SerializedName("ship")
    var ship: Any? = Any()
)