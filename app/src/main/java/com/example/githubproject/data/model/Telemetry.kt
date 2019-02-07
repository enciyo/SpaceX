package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class Telemetry(
    @SerializedName("flight_club")
    var flightClub: Any? = Any()
)