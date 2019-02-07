package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class LaunchSite(
    @SerializedName("site_id")
    var siteİd: String = "",
    @SerializedName("site_name")
    var siteName: String = "",
    @SerializedName("site_name_long")
    var siteNameLong: String = ""
)