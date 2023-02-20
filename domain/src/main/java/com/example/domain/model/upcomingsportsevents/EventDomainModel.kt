package com.example.domain.model.upcomingsportsevents

data class EventDomainModel(
    var eventName: Pair<String, String>,
    var eventId: String,
    var sportId: String,
    var sh: String,
    var eventStartTime: Long,
)
