package com.example.data.response.upcomingsportevents

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("d")
    var eventName: String?,
    @SerializedName("i")
    var eventId: String?,
    @SerializedName("si")
    var sportId: String?,
    @SerializedName("sh")
    var sh: String?,
    @SerializedName("tt")
    var eventStartTime: Long?,
)
