package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class OrbitParams(
    @SerializedName("reference_system")
    var referenceSystem: String = "",
    @SerializedName("regime")
    var regime: String = "",
    @SerializedName("longitude")
    var longitude: Any? = Any(),
    @SerializedName("semi_major_axis_km")
    var semiMajorAxisKm: Any? = Any(),
    @SerializedName("eccentricity")
    var eccentricity: Any? = Any(),
    @SerializedName("periapsis_km")
    var periapsisKm: Any? = Any(),
    @SerializedName("apoapsis_km")
    var apoapsisKm: Any? = Any(),
    @SerializedName("inclination_deg")
    var inclinationDeg: Any? = Any(),
    @SerializedName("period_min")
    var periodMin: Any? = Any(),
    @SerializedName("lifespan_years")
    var lifespanYears: Any? = Any(),
    @SerializedName("epoch")
    var epoch: Any? = Any(),
    @SerializedName("mean_motion")
    var meanMotion: Any? = Any(),
    @SerializedName("raan")
    var raan: Any? = Any(),
    @SerializedName("arg_of_pericenter")
    var argOfPericenter: Any? = Any(),
    @SerializedName("mean_anomaly")
    var meanAnomaly: Any? = Any()
)