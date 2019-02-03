package com.example.githubproject.model.data

import com.google.gson.annotations.SerializedName

data class Telemetry(
    @SerializedName("flight_club")
    var flightClub: Any? = Any()
)