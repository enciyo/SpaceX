package com.example.githubproject.model.data

import com.google.gson.annotations.SerializedName

data class Rocket(
    @SerializedName("rocket_id")
    var rocketİd: String = "",
    @SerializedName("rocket_name")
    var rocketName: String = "",
    @SerializedName("rocket_type")
    var rocketType: String = "",
    @SerializedName("first_stage")
    var firstStage: FirstStage = FirstStage(),
    @SerializedName("second_stage")
    var secondStage: SecondStage = SecondStage(),
    @SerializedName("fairings")
    var fairings: Fairings = Fairings()
)