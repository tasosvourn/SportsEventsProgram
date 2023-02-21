package com.example.domain.model.upcomingsportsevents

/** Domain model to hold the data for the viewModel */
data class SportsDomainModel(
    val sportId: String,
    var sportName: String,
    var events: List<EventDomainModel>,
)