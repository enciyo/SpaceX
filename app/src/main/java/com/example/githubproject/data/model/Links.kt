package com.example.githubproject.data.model

import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("mission_patch")
    var missionPatch: Any? = Any(),
    @SerializedName("mission_patch_small")
    var missionPatchSmall: Any? = Any(),
    @SerializedName("reddit_campaign")
    var redditCampaign: Any? = Any(),
    @SerializedName("reddit_launch")
    var redditLaunch: Any? = Any(),
    @SerializedName("reddit_recovery")
    var redditRecovery: Any? = Any(),
    @SerializedName("reddit_media")
    var redditMedia: Any? = Any(),
    @SerializedName("presskit")
    var presskit: Any? = Any(),
    @SerializedName("article_link")
    var articleLink: Any? = Any(),
    @SerializedName("wikipedia")
    var wikipedia: Any? = Any(),
    @SerializedName("video_link")
    var videoLink: Any? = Any(),
    @SerializedName("youtube_id")
    var youtubeİd: Any? = Any(),
    @SerializedName("flickr_images")
    var flickrİmages: List<Any> = listOf()
)