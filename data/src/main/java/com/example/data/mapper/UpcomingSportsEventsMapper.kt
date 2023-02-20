package com.example.data.mapper

import com.example.data.response.upcomingsportevents.Event
import com.example.data.response.upcomingsportevents.Sports
import com.example.domain.model.upcomingsportsevents.EventDomainModel
import com.example.domain.model.upcomingsportsevents.SportsDomainModel

fun Sports.mapToDomain(): SportsDomainModel {
    return SportsDomainModel(
        sportId = sportId ?: "",
        sportName = sportName ?: "",
        events = events?.map { it.mapToDomain() } ?: listOf()
    )
}

fun Event.mapToDomain(): EventDomainModel {
    return EventDomainModel(
        eventName = getEventNamePair(eventName) ?: Pair("", ""),
        eventId = eventId ?: "",
        sportId = sportId ?: "",
        sh = sh ?: "",
        eventStartTime = eventStartTime?.let { it * 1000L } ?: System.currentTimeMillis()
    )
}

fun getEventNamePair(eventName: String?): Pair<String, String>? {
    eventName?.let {
        val list = it.split(" - ")
        if (list.size == 2) {
            val first = list[0]
            val second = list[1]
            return Pair(first, second)
        }
    }
    return null
}
