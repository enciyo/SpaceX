package com.example.githubproject.model.data

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("payload_id")
    var payloadİd: String = "",
    @SerializedName("norad_id")
    var noradİd: List<Any> = listOf(),
    @SerializedName("reused")
    var reused: Boolean = false,
    @SerializedName("customers")
    var customers: List<String> = listOf(),
    @SerializedName("nationality")
    var nationality: String = "",
    @SerializedName("manufacturer")
    var manufacturer: String = "",
    @SerializedName("payload_type")
    var payloadType: String = "",
    @SerializedName("payload_mass_kg")
    var payloadMassKg: Any? = Any(),
    @SerializedName("payload_mass_lbs")
    var payloadMassLbs: Any? = Any(),
    @SerializedName("orbit")
    var orbit: String = "",
    @SerializedName("orbit_params")
    var orbitParams: OrbitParams = OrbitParams()
)