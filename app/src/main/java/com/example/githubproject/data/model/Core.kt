package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class Core(
    @SerializedName("core_serial")
    var coreSerial: Any? = Any(),
    @SerializedName("flight")
    var flight: Any? = Any(),
    @SerializedName("block")
    var block: Any? = Any(),
    @SerializedName("gridfins")
    var gridfins: Any? = Any(),
    @SerializedName("legs")
    var legs: Any? = Any(),
    @SerializedName("reused")
    var reused: Any? = Any(),
    @SerializedName("land_success")
    var landSuccess: Any? = Any(),
    @SerializedName("landing_intent")
    var landingİntent: Any? = Any(),
    @SerializedName("landing_type")
    var landingType: Any? = Any(),
    @SerializedName("landing_vehicle")
    var landingVehicle: Any? = Any()
)