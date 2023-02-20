package com.example.domain.model.upcomingsportsevents

data class SportsDomainModel(
    val sportId: String,
    var sportName: String,
    var events: List<EventDomainModel>
)