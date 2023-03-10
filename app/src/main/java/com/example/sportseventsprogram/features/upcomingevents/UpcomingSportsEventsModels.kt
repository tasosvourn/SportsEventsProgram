package com.example.sportseventsprogram.features.upcomingevents

import com.example.sportseventsprogram.common.adapterdelegates.RowUiItem

data class SportRowUiItem(
    val sportName: String,
    var isExpanded: Boolean = false,
    var events: List<EventRowUiItem>
): RowUiItem

data class EventRowUiItem(
    val sportName: String,
    val eventName: String,
    var startTime: Long,
    var remainingTime: Long = 0L,
    var isFavorite: Boolean,
    val firstCompetitor: String,
    val secondCompetitor: String
): RowUiItem