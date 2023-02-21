package com.example.data.model.upcomingsportevents.response

import com.google.gson.annotations.SerializedName

data class Sports(
    @SerializedName("i")
    var sportId: String?,
    @SerializedName("d")
    var sportName: String?,
    @SerializedName("e")
    var events: List<Event>?,
)