package com.example.domain.repository

import com.example.domain.model.DomainResult
import com.example.domain.model.upcomingsportsevents.SportsDomainModel
import kotlinx.coroutines.flow.Flow

interface UpcomingSportsEventsRepository {
    fun getUpcomingSportsEvents(): Flow<DomainResult<List<SportsDomainModel>>>
}