package com.example.domain.model.upcomingsportsevents

/** Domain model to hold the data for the viewModel */
data class EventDomainModel(
    var eventName: Pair<String, String>,
    var eventId: String,
    var sportId: String,
    var sh: String,
    var eventStartTime: Long,
)
