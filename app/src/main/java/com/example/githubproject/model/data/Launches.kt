package com.example.githubproject.model.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Launches")
data class Launches(
    @PrimaryKey(autoGenerate = true)
    var mId: Int = 0,
    @SerializedName("flight_number")
    var flightNumber: Int = 0,
    @SerializedName("mission_name")
    var missionName: String = "",
    @Ignore
    @SerializedName("mission_id")
    var missionİd: List<Any> = listOf(),
    @SerializedName("upcoming")
    var upcoming: Boolean = false,
    @SerializedName("launch_year")
    var launchYear: String = "",
    @SerializedName("launch_date_unix")
    var launchDateUnix: Int = 0,
    @SerializedName("launch_date_utc")
    var launchDateUtc: String = "",
    @SerializedName("launch_date_local")
    var launchDateLocal: String = "",
    @SerializedName("is_tentative")
    var isTentative: Boolean = false,
    @SerializedName("tentative_max_precision")
    var tentativeMaxPrecision: String = "",
    @SerializedName("tbd")
    var tbd: Boolean = false,
    @Ignore
    @SerializedName("launch_window")
    var launchWindow: Any? = Any(),
    @Ignore
    @SerializedName("rocket")
    var rocket: Rocket = Rocket(),
    @Ignore
    @SerializedName("ships")
    var ships: List<Any> = listOf(),
    @Ignore
    @SerializedName("telemetry")
    var telemetry: Telemetry = Telemetry(),
    @Ignore
    @SerializedName("launch_site")
    var launchSite: LaunchSite = LaunchSite(),
    @Ignore
    @SerializedName("launch_success")
    var launchSuccess: Any? = Any(),
    @Ignore
    @SerializedName("launch_failure_details")
    var launchFailureDetails: LaunchFailureDetails = LaunchFailureDetails(),
    @Ignore
    @SerializedName("links")
    var links: Links = Links(),
    @Ignore
    @SerializedName("details")
    var details: Any? = Any(),
    @Ignore
    @SerializedName("static_fire_date_utc")
    var staticFireDateUtc: Any? = Any(),
    @Ignore
    @SerializedName("static_fire_date_unix")
    var staticFireDateUnix: Any? = Any(),
    @Ignore
    @SerializedName("timeline")
    var timeline: Any? = Any()
) : Serializable