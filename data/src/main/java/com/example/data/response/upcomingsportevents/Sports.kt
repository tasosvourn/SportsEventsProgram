package com.example.data.response.upcomingsportevents

import com.google.gson.annotations.SerializedName

data class Sports(
    @SerializedName("i")
    var sportId: String?,
    @SerializedName("d")
    var sportName: String?,
    @SerializedName("e")
    var events: List<Event>?,
)